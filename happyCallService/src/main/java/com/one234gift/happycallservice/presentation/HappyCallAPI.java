package com.one234gift.happycallservice.presentation;

import com.one234gift.happycallservice.application.HappyCallService;
import com.one234gift.happycallservice.domain.read.HappyCallModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/user/happy-call")
public class HappyCallAPI {
    @Autowired
    private HappyCallService happyCallService;

    @GetMapping
    public ResponseEntity<List<HappyCallModel>> findTodayHappyCalls(Principal principal){
        List<HappyCallModel> happyCalls = happyCallService.findTodayHappyCallsByUserId(principal.getName());
        return ResponseEntity.ok(happyCalls);
    }

    @GetMapping("{happycallId}")
    public ResponseEntity<Void> read(@PathVariable Long happycallId, Principal principal){
        happyCallService.read(happycallId, principal.getName());
        return ResponseEntity.ok(null);
    }

}