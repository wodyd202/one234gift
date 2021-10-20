package com.one234gift.customerservice.command.application.exception;

public class DataBaseNotAccessAbleException extends RuntimeException {
    public DataBaseNotAccessAbleException(){
        super("잠시 서버에서 에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
    }
}
