package com.one234gift.saleshistoryservice.salesHistory.command.presentation;

import com.one234gift.saleshistoryservice.salesHistory.command.application.RemoveSalesHistoryService;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.SalesHistoryId;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.Writer;
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
    public ResponseEntity<String> remove(@PathVariable SalesHistoryId salesHistoryId,
                                         Principal principal){
        removeSalesHistoryService.remove(salesHistoryId, getWriter(principal));
        return ResponseEntity.ok("영업 기록 삭제 완료");
    }

    private Writer getWriter(Principal principal){
        return Writer.builder().phone(principal.getName()).build();
    }
}
