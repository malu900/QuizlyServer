package com.example.quizly.models;

public class WsResponse {
    private String message;
    private WsMethod method;

    public WsResponse(String message, WsMethod method) {
        this.message = message;
        this.method = method;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WsMethod getMethod() {
        return method;
    }

    public void setMethod(WsMethod method) {
        this.method = method;
    }
}
