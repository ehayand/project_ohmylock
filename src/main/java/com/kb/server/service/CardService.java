package com.kb.server.service;

import com.kb.server.mapper.CardMapper;
import com.kb.server.model.Card;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Service
public class CardService {

    private final CardMapper cardMapper;

    public CardService(CardMapper cardMapper) {
        this.cardMapper = cardMapper;
    }

    public List<Card> findAll() {
        return cardMapper.findAll();
    }

    public List<Card> findCardsByUserIdx(final int userIdx) {
        return cardMapper.findCardsByUserIdx(userIdx);
    }

    public List<Card> findCardsByUserIdxPaging(final int userIdx, final int page, final int size) {
        return cardMapper.findCardsByUserIdxPaging(userIdx, page, size);
    }

    public int countCardsByUserIdx(final int userIdx) {
        return cardMapper.countCardsByUserIdx(userIdx);
    }

    public List<Card> findCardsByAccountIdx(final int accountIdx) {
        return cardMapper.findCardsByAccountIdx(accountIdx);
    }

    public List<Card> findCardsByCardTypeIdx(final int cardTypeIdx) {
        return cardMapper.findCardsByCardTypeIdx(cardTypeIdx);
    }

    public Card findByCardIdx(final int cardIdx) {
        return cardMapper.findByCardIdx(cardIdx);
    }

    public Card findByNumber(final Long number) {
        return cardMapper.findByNumber(number);
    }

    public int save(final Card card) {
        return cardMapper.save(card);
    }

    public void update(final int cardIdx, final Card card) {
        cardMapper.update(cardIdx, card);
    }

    public void delete(final int cardIdx) {
        cardMapper.delete(cardIdx);
    }
}
