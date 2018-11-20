package com.kb.server.controller;

import com.kb.server.dto.DifferenceDto;
import com.kb.server.dto.UserDto;
import com.kb.server.model.DefaultRes;
import com.kb.server.model.Point;
import com.kb.server.security.token.PostAuthorizationToken;
import com.kb.server.service.PointHistoryService;
import com.kb.server.service.PointProductService;
import com.kb.server.service.PointService;
import com.kb.server.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ehay@naver.com on 2018-11-16
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@RestController
@RequestMapping("/key/point")
public class PointController {

    private final UserService userService;
    private final PointService pointService;
    private final PointHistoryService pointHistoryService;
    private final PointProductService pointProductService;

    public PointController(UserService userService, PointService pointService, PointHistoryService pointHistoryService, PointProductService pointProductService) {
        this.userService = userService;
        this.pointService = pointService;
        this.pointHistoryService = pointHistoryService;
        this.pointProductService = pointProductService;
    }

    /**
     *
     * @param authentication
     * @return
     */
    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public DefaultRes<Map<String, Object>> getPointData(Authentication authentication) {
        PostAuthorizationToken token = (PostAuthorizationToken) authentication;
        String id = token.getAccountContext().getUsername();

        Map<String, Object> response = new HashMap<>();

        UserDto userDto = userService.findById(id);

        if (userDto == null) return DefaultRes.res("FAILED", "NOT_FOUND_USER");

        Point point = pointService.findByUserIdx(userDto.getUserIdx());

        response.put("point", point);

        return DefaultRes.res("SUCCESS", response);
    }

    /**
     *
     * @param authentication
     * @param difference
     * @return
     */
    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_USER')")
    public DefaultRes<Map<String, Object>> updatePoint(Authentication authentication,
                                           @RequestBody final DifferenceDto difference) {
        PostAuthorizationToken token = (PostAuthorizationToken) authentication;
        String id = token.getAccountContext().getUsername();

        Map<String, Object> response = new HashMap<>();

        UserDto userDto = userService.findById(id);

        if (userDto == null) return DefaultRes.res("FAILED", "NOT_FOUND_USER");

        Point point = pointService.findByUserIdx(userDto.getUserIdx());

        long balace = point.getBalance();
        balace += difference.getDifference();

        point.setBalance(balace);
        pointService.update(point.getPointIdx(), point);

        response.put("Point", pointService.findByPointIdx(point.getPointIdx()));

        return DefaultRes.res("SUCCESS", response);
    }

    /**
     *
     * @param authentication
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/histories")
    @PreAuthorize("hasRole('ROLE_USER')")
    public DefaultRes<Map<String, Object>> getPointHistories(Authentication authentication,
                                                 @RequestParam(value = "page", defaultValue = "0") final int page,
                                                 @RequestParam(value = "size", defaultValue = "3") final int size) {
        PostAuthorizationToken token = (PostAuthorizationToken) authentication;
        String id = token.getAccountContext().getUsername();

        Map<String, Object> response = new HashMap<>();

        UserDto userDto = userService.findById(id);

        if (userDto == null) return DefaultRes.res("FAILED", "NOT_FOUND_USER");

        Point point = pointService.findByUserIdx(userDto.getUserIdx());

        int max = pointHistoryService.countPointHistoriesByPointIdx(point.getPointIdx());
        int current = page * size;

        if (max == 0) return DefaultRes.res("FAILED", "NOT_FOUND_HISTORY");
        if (size <= 0) return DefaultRes.res("FAILED", "BAD_REQUEST");
        if (current >= max) return DefaultRes.res("FAILED", "NOT_FOUND_PAGE");

        response.put("endPage", max % size == 0 ? max / size - 1 : max / size);
        response.put("pointHistories", pointHistoryService.findPointHistoriesByPointIdx(point.getPointIdx(), current, size));

        return DefaultRes.res("SUCCESS", response);
    }

    /**
     *
     * @param category
     * @param name
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/products")
    @PreAuthorize("hasRole('ROLE_USER')")
    public DefaultRes<Map<String, Object>> getPointProduct(@RequestParam(value = "category", defaultValue = "0") final int category,
                                               @RequestParam(value = "name", defaultValue = "전체") final String name,
                                               @RequestParam(value = "page", defaultValue = "0") final int page,
                                               @RequestParam(value = "size", defaultValue = "3") final int size) {
        Map<String, Object> response = new HashMap<>();

        int max;

        if (category == 0) {
            if ("전체".equals(name)) {
                max = pointProductService.countAll();
            } else {
                max = pointProductService.countProductsByName(name);
            }
        } else {
            if ("전체".equals(name)) {
                max = pointProductService.countProductsByCategory(category);
            } else {
                max = pointProductService.countProductsByCategoryAndName(category, name);
            }
        }

        int current = page * size;

        if (max == 0) return DefaultRes.res("FAILED", "NOT_FOUND_PRODUCT");
        if (size <= 0) return DefaultRes.res("FAILED", "BAD_REQUEST");
        if (current >= max) return DefaultRes.res("FAILED", "NOT_FOUND_PAGE");

        response.put("endPage", max % size == 0 ? max / size - 1 : max / size);

        if (category == 0) {
            if ("전체".equals(name)) {
                response.put("pointProduct", pointProductService.findAll(current, size));
            } else {
                response.put("pointProduct", pointProductService.findProductsByName(name, current, size));
            }
        } else {
            if ("전체".equals(name)) {
                response.put("pointProduct", pointProductService.findProductsByCategory(category, current, size));
            } else {
                response.put("pointProduct", pointProductService.findProductsByCategoryAndName(category, name, current, size));
            }
        }

        return DefaultRes.res("SUCCESS", response);
    }
}
