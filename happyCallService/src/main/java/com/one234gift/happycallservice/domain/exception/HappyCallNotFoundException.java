package com.one234gift.happycallservice.domain.exception;

public class HappyCallNotFoundException extends RuntimeException {
    public HappyCallNotFoundException(){
        super("해당 해피콜 정보가 존재하지 않습니다.");
    }
}
