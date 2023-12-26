package com.sh.mvc.board.model;
// constructor 전부 선택해서 만들기
public class BoardException extends RuntimeException {
    public BoardException() {
    }

    public BoardException(String message) {
        super(message);
    }

    public BoardException(String message, Throwable cause) {
        super(message, cause);
    }

    public BoardException(Throwable cause) {
        super(cause);
    }

    public BoardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
