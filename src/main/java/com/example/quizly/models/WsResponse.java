package com.example.quizly.models;

import java.util.List;

public class WsResponse {
    private String message;
    private List<String> messageList;
    private WsMethod method;

    public WsResponse(String message, WsMethod method) {
        this.message = message;
        this.method = method;
    }

    public WsResponse(List<String> message, WsMethod method) {
        this.messageList = message;
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
