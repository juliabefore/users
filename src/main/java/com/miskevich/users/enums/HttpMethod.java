package com.miskevich.users.enums;

public enum HttpMethod {
    GET("GET"),
    POST("POST");

    private String value;

    HttpMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
