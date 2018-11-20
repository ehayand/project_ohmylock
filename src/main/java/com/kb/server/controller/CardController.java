package com.kb.server.controller;

import com.kb.server.dto.UserDto;
import com.kb.server.model.*;
import com.kb.server.security.token.PostAuthorizationToken;
import com.kb.server.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ehay@naver.com on 2018-11-16
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@RestController
@RequestMapping("/key/card")
public class CardController {

    private final UserService userService;
    private final CardService cardService;
    private final CardTypeService cardTypeService;
    private final BankService bankService;
    private final AccountService accountService;
    private final AccountHistoryService accountHistoryService;
    private final BalanceHistoryService balanceHistoryService;

    public CardController(UserService userService, CardService cardService, CardTypeService cardTypeService, BankService bankService, AccountService accountService, AccountHistoryService accountHistoryService, BalanceHistoryService balanceHistoryService) {
        this.userService = userService;
        this.cardService = cardService;
        this.cardTypeService = cardTypeService;
        this.bankService = bankService;
        this.accountService = accountService;
        this.accountHistoryService = accountHistoryService;
        this.balanceHistoryService = balanceHistoryService;
    }

    /**
     *
     * @param authentication
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/inapp")
    @PreAuthorize("hasRole('ROLE_USER')")
    public DefaultRes<Map<String, Object>> getCard(Authentication authentication,
                                                   @RequestParam(value = "page", defaultValue = "0") final int page,
                                                   @RequestParam(value = "size", defaultValue = "1") final int size) {
        PostAuthorizationToken token = (PostAuthorizationToken) authentication;
        String id = token.getAccountContext().getUsername();

        Map<String, Object> response = new HashMap<>();
        UserDto userDto = userService.findById(id);

        if (userDto == null) return DefaultRes.res("FAILED", "NOT_FOUND_USER");

        int max = cardService.countCardsByUserIdx(userDto.getUserIdx());
        int current = page * size;

        if (max == 0) return DefaultRes.res("FAILED", "NOT_FOUND_CARD");
        if (size <= 0) return DefaultRes.res("FAILED", "BAD_REQUEST");
        if (current >= max) return DefaultRes.res("FAILED", "NOT_FOUND_PAGE");

        List<Map<String, Object>> cards = new ArrayList<>();

        for (final Card card : cardService.findCardsByUserIdxPaging(userDto.getUserIdx(), current, size)) {
            Map<String, Object> item = new HashMap<>();

            CardType cardType = cardTypeService.findByCardTypeIdx(card.getCardTypeIdx());
            Account account = accountService.findByAccountIdx(card.getAccountIdx());
            Bank bank = bankService.findByBankIdx(cardType.getBankIdx());

            item.put("cardIdx", card.getCardIdx());
            item.put("limit", card.getCardLimit());
            item.put("credit", cardType.isCredit());
            item.put("cardname", cardType.getName());
            item.put("cardimage", cardType.getImage());
            item.put("bank", bank.getName());

            long sum = 0;
            LocalDateTime currentTime = LocalDateTime.now();

            for (AccountHistory accountHistory : accountHistoryService.findAccountHistoriesBySendAccountIdx(account.getAccountIdx())) {
                if (accountHistory.getTime().getMonthValue() == currentTime.getMonth().getValue()) {
                    sum += accountHistory.getDifference();
                }
            }

            item.put("expenditure", sum);

            cards.add(item);
        }

        if (cards.isEmpty()) return DefaultRes.res("FAILED", "NOT_FOUND_CARD");

        response.put("cards", cards);
        response.put("endPage", max % size == 0 ? max / size - 1 : max / size);

        return DefaultRes.res("SUCCESS", response);
    }

    /**
     *
     * @param authentication
     * @return
     */
    @GetMapping("/lock")
    @PreAuthorize("hasRole('ROLE_USER')")
    public DefaultRes<Map<String, Object>> getCardList(Authentication authentication) {
        PostAuthorizationToken token = (PostAuthorizationToken) authentication;
        String id = token.getAccountContext().getUsername();

        Map<String, Object> response = new HashMap<>();
        UserDto userDto = userService.findById(id);

        if (userDto == null) return DefaultRes.res("FAILED", "NOT_FOUND_USER");

        List<Card> userCards = cardService.findCardsByUserIdx(userDto.getUserIdx());

        if (userCards == null) return DefaultRes.res("FAILED", "NOT_FOUND_CARD");

        List<Map<String, Object>> cards = new ArrayList<>();

        for (Card card : userCards) {
            Map<String, Object> item = new HashMap<>();
            CardType cardType = cardTypeService.findByCardTypeIdx(card.getCardTypeIdx());
            Account account = accountService.findByAccountIdx(card.getAccountIdx());

            item.put("card", card);
            item.put("cardType", cardType);

            long sum = 0;
            LocalDateTime current = LocalDateTime.now();

            for (AccountHistory accountHistory : accountHistoryService.findAccountHistoriesBySendAccountIdx(account.getAccountIdx())) {
                if (accountHistory.getTime().getMonthValue() == current.getMonth().getValue()) {
                    sum += accountHistory.getDifference();
                }
            }

            item.put("expenditure", sum);
            cards.add(item);
        }

        response.put("cards", cards);

        return DefaultRes.res("SUCCESS", response);
    }

    /**
     *
     * @param cardIdx
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/histories/{cardIdx}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public DefaultRes<Map<String, Object>> getCardHistories(@PathVariable(value = "cardIdx") final int cardIdx,
                                                            @RequestParam(value = "page", defaultValue = "0") final int page,
                                                            @RequestParam(value = "size", defaultValue = "3") final int size) {
        Map<String, Object> response = new HashMap<>();

        Card card = cardService.findByCardIdx(cardIdx);

        int max = accountHistoryService.countAccountHistoriesBySendAccountIdxOrReceiveAccountIdx(card.getAccountIdx());
        int current = page * size;

        if (max == 0) return DefaultRes.res("FAILED", "NOT_FOUND_HISTORY");
        if (size <= 0) return DefaultRes.res("FAILED", "BAD_REQUEST");
        if (current >= max) return DefaultRes.res("FAILED", "NOT_FOUND_PAGE");

        response.put("endPage", max % size == 0 ? max / size - 1 : max / size);

        List<Map<String, Object>> result = new ArrayList<>();

        for (AccountHistory accountHistory : accountHistoryService.findAccountHistoriesBySendAccountIdxOrReceiveAccountIdx(card.getAccountIdx(), current, size)) {
            Map<String, Object> item = new HashMap<>();

            long difference = accountHistory.getDifference();
            String traderName;
            long balance;

            if (accountHistory.getSendAccountIdx() == card.getAccountIdx()) {
                difference *= -1;
                traderName = userService.findByUserIdx(accountService.findByAccountIdx(accountHistory.getReceiveAccountIdx()).getUserIdx()).getName();
                balance = balanceHistoryService.findByAccountHistoryIdxAndAccountIdx(accountHistory.getAccountHistoryIdx(), accountHistory.getSendAccountIdx()).getBalance();
            } else {
                traderName = userService.findByUserIdx(accountService.findByAccountIdx(accountHistory.getSendAccountIdx()).getUserIdx()).getName();
                balance = balanceHistoryService.findByAccountHistoryIdxAndAccountIdx(accountHistory.getAccountHistoryIdx(), accountHistory.getReceiveAccountIdx()).getBalance();
            }

            item.put("year", accountHistory.getTime().getYear());
            item.put("month", accountHistory.getTime().getMonthValue());
            item.put("day", accountHistory.getTime().getDayOfMonth());
            item.put("difference", difference);
            item.put("traderName", traderName);
            item.put("balance", balance);

            result.add(item);
        }

        response.put("history", result);

        return DefaultRes.res("SUCCESS", response);
    }
}
