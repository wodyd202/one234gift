package com.one234gift.customerservice.customer.command.presentation;

import com.one234gift.customerservice.customer.command.application.ResponsibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/customer/{customerId}/responsible")
public class ResponsibleAPI {
    @Autowired
    private ResponsibleService responsibleService;

    @PostMapping
    public ResponseEntity<Void> responsible(@PathVariable long customerId, Principal principal){
        responsibleService.flag(customerId, principal.getName());
        return ResponseEntity.ok(null);
    }
}
