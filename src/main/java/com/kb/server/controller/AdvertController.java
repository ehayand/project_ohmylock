package com.kb.server.controller;

import com.kb.server.model.DefaultRes;
import com.kb.server.service.AdvertisementService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by ehay@naver.com on 2018-11-16
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@RestController
@RequestMapping("/key/advert")
public class AdvertController {

    private final AdvertisementService advertisementService;

    public AdvertController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    /**
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/inapp/list")
    @PreAuthorize("hasRole('ROLE_USER')")
    public DefaultRes<Map<String, Object>> getInappAdverts(@RequestParam(value = "page", defaultValue = "0") final int page,
                                               @RequestParam(value = "size", defaultValue = "3") final int size) {
        Map<String, Object> response = new HashMap<>();

        int max = advertisementService.countInappAdverts();
        int current = page * size;

        if (max == 0) return DefaultRes.res("FAILED", "NOT_FOUND_ADVERT");
        if (size <= 0) return DefaultRes.res("FAILED", "BAD_REQUEST");
        if (current >= max) return DefaultRes.res("FAILED", "NOT_FOUND_PAGE");

        response.put("endPage", max % size == 0 ? max / size - 1 : max / size);
        response.put("adverts", advertisementService.findInappAdverts(current, size));

        return DefaultRes.res("SUCCESS", response);
    }

    /**
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/lock/list")
    @PreAuthorize("hasRole('ROLE_USER')")
    public DefaultRes<Map<String, Object>> getLockAdverts(@RequestParam(value = "page", defaultValue = "0") final int page,
                                              @RequestParam(value = "size", defaultValue = "3") final int size) {
        Map<String, Object> response = new HashMap<>();

        int max = advertisementService.countLockAdverts();
        int current = page * size;

        if (max == 0) return DefaultRes.res("FAILED", "NOT_FOUND_ADVERT");
        if (size <= 0) return DefaultRes.res("FAILED", "BAD_REQUEST");
        if (current >= max) return DefaultRes.res("FAILED", "NOT_FOUND_PAGE");

        response.put("endPage", max % size == 0 ? max / size - 1 : max / size);
        response.put("adverts", advertisementService.findLockAdverts(current, size));

        return DefaultRes.res("SUCCESS", response);
    }

    /**
     *
     * @return
     */
    @GetMapping("/lock")
    @PreAuthorize("hasRole('ROLE_USER')")
    public DefaultRes<Map<String, Object>> getLockAdvert() {
        Map<String, Object> response = new HashMap<>();

        int max = advertisementService.countLockAdverts();

        if (max == 0) return DefaultRes.res("FAILED", "NOT_FOUND_ADVERT");

        Random random = new Random();
        int select = random.nextInt(max);

        response.put("advert", advertisementService.findByLockAdvertIdx(select+1));

        return DefaultRes.res("SUCCESS", response);
    }
}
