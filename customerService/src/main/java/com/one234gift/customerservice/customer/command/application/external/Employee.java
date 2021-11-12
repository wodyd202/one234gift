package com.one234gift.customerservice.customer.command.application.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {
    private String username;
    private String phone;
}
