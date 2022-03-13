package com.getir.project.bookretail.util.enums;

public enum StatusEnum implements BaseEnum {

    FAILED("0"),
    SUCCESS("1");

    private String value;

    private StatusEnum(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}