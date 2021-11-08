package com.one234gift.saleshistoryservice.command.presentation;

import com.one234gift.saleshistoryservice.command.application.RemoveSalesHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/sales-history/{salesHistoryId}")
public class RemoveSalesHistoryAPI {
    @Autowired private RemoveSalesHistoryService removeSalesHistoryService;

    @DeleteMapping
    public ResponseEntity<String> remove(@PathVariable long salesHistoryId,
                                 Principal principal){
        removeSalesHistoryService.remove(salesHistoryId, principal.getName());
        return ResponseEntity.ok("영업 기록 삭제 완료");
    }
}
