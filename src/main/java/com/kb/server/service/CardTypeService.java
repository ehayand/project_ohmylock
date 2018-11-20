package com.kb.server.service;

import com.kb.server.mapper.CardTypeMapper;
import com.kb.server.model.CardType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Service
public class CardTypeService {

    private final CardTypeMapper cardTypeMapper;

    public CardTypeService(CardTypeMapper cardTypeMapper) {
        this.cardTypeMapper = cardTypeMapper;
    }

    public List<CardType> findAll() {
        return cardTypeMapper.findAll();
    }

    public List<CardType> findCardTypesByIsCredit(final boolean isCredit) {
        return cardTypeMapper.findCardTypesByIsCredit(isCredit);
    }

    public CardType findByCardTypeIdx(final int cardTypeIdx) {
        return cardTypeMapper.findByCardTypeIdx(cardTypeIdx);
    }

    public CardType findByName(final String name) {
        return cardTypeMapper.findByName(name);
    }

    public int save(final CardType cardType) {
        return cardTypeMapper.save(cardType);
    }

    public void update(final int cardTypeIdx, final CardType cardType) {
        cardTypeMapper.update(cardTypeIdx, cardType);
    }

    public void delete(final int cardTypeIdx) {
        cardTypeMapper.delete(cardTypeIdx);
    }
}
