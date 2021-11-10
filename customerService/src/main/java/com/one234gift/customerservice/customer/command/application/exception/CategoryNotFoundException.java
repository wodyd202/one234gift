package com.one234gift.customerservice.customer.command.application.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(){
        super("해당 카테고리 정보가 존재하지 않습니다. 카테고리를 다시 확인해주세요.");
    }
}
