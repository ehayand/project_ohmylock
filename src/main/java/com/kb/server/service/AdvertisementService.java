package com.kb.server.service;

import com.kb.server.mapper.InappAdvertMapper;
import com.kb.server.mapper.LockAdvertMapper;
import com.kb.server.model.InappAdvert;
import com.kb.server.model.LockAdvert;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Service
public class AdvertisementService {

    private final InappAdvertMapper inappAdvertMapper;
    private final LockAdvertMapper lockAdvertMapper;

    public AdvertisementService(InappAdvertMapper inappAdvertMapper, LockAdvertMapper lockAdvertMapper) {
        this.inappAdvertMapper = inappAdvertMapper;
        this.lockAdvertMapper = lockAdvertMapper;
    }

    public List<InappAdvert> findInappAdverts(final int page, final int size) {
        return inappAdvertMapper.findAll(page, size);
    }

    public int countInappAdverts() {
        return inappAdvertMapper.countAll();
    }

    public List<InappAdvert> findInappAdvertsByAdvertiser(final String title) {
        return inappAdvertMapper.findInappAdvertsByAdvertiser(title);
    }

    public InappAdvert findByInappAdvertIdx(final int advertisementIdx) {
        return inappAdvertMapper.findByInappAdvertIdx(advertisementIdx);
    }

    public int inappAdvertSave(final InappAdvert inappAdvert) {
        return inappAdvertMapper.save(inappAdvert);
    }

    public void inappAdvertUpdate(final int advertisementIdx, final InappAdvert inappAdvert) {
        inappAdvertMapper.update(advertisementIdx, inappAdvert);
    }

    public void inappAdvertDelete(final int advertisementIdx) {
        inappAdvertMapper.delete(advertisementIdx);
    }

    public List<LockAdvert> findLockAdverts(final int page, final int size) {
        return lockAdvertMapper.findAll(page, size);
    }

    public int countLockAdverts() {
        return lockAdvertMapper.countAll();
    }

    public List<LockAdvert> findLockAdvertsByAdvertiser(final String title) {
        return lockAdvertMapper.findLockAdvertsByAdvertiser(title);
    }

    public LockAdvert findByLockAdvertIdx(final int advertisementIdx) {
        return lockAdvertMapper.findByLockAdvertIdx(advertisementIdx);
    }

    public int lockAdvertSave(final LockAdvert lockAdvert) {
        return lockAdvertMapper.save(lockAdvert);
    }

    public void lockAdvertUpdate(final int advertisementIdx, final LockAdvert lockAdvert) {
        lockAdvertMapper.update(advertisementIdx, lockAdvert);
    }

    public void lockAdvertDelete(final int advertisementIdx) {
        lockAdvertMapper.delete(advertisementIdx);
    }
}
