package com.ncy.store.service.ex;

public class DeleteAddressException extends ServiceException{
    public DeleteAddressException() {
        super();
    }

    public DeleteAddressException(String message) {
        super(message);
    }

    public DeleteAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteAddressException(Throwable cause) {
        super(cause);
    }

    protected DeleteAddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
