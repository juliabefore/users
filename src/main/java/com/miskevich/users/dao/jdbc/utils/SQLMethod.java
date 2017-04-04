package com.miskevich.users.dao.jdbc.utils;

public enum SQLMethod {
    INSERT("INSERT"),
    UPDATE("UPDATE");

    private String value;

    SQLMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
