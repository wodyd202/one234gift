package com.one234gift.saleshistoryservice.command.application.exception;

public class NotAbleRepositoryException extends RuntimeException {
    public NotAbleRepositoryException(){
        super("죄송합니다. 잠시 에러가 발생했습니다. 잠시후 다시 시도해주세요.");
    }
}
