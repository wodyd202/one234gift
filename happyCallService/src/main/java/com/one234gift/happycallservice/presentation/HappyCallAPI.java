package com.one234gift.happycallservice.presentation;

import com.one234gift.happycallservice.application.HappyCallService;
import com.one234gift.happycallservice.common.Pageable;
import com.one234gift.happycallservice.domain.value.Reserver;
import com.one234gift.happycallservice.application.model.HappyCallModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 해피콜 조회 API
 */
@RestController
@RequestMapping("api/happy-call")
public class HappyCallAPI {
    @Autowired
    private HappyCallService happyCallService;

    /**
     * @param pageable
     * @param principal
     * # 해피콜 목록 조회
     */
    @GetMapping
    public ResponseEntity<HappyCallModels> findTodayHappyCall(Pageable pageable, Principal principal){
        HappyCallModels happyCallModels = happyCallService.findTodayHappyCall(pageable, new Reserver(principal.getName()));
        return ResponseEntity.ok(happyCallModels);
    }

    /**
     * @param happycallId
     * @param principal
     * # 해피콜 읽음
     */
    @GetMapping("{happycallId}")
    public ResponseEntity<Void> read(@PathVariable Long happycallId, Principal principal){
        happyCallService.read(happycallId, new Reserver(principal.getName()));
        return ResponseEntity.ok(null);
    }

}
