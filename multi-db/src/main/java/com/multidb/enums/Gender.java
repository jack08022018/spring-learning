package com.multidb.enums;

import java.util.Arrays;

public enum Gender {
    MALE("M", ""),
    FE_MALE("f", "");

    private final String code;
    private final String detail;

    Gender(String code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    public static Gender getEnum(String key) {
        return Arrays.stream(values())
                .filter(s -> s.getCode().equals(key))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No matching Gender for [" + key + "]"));
    }

    public String getCode() {
        return code;
    }

    public String getDetail() {
        return detail;
    }
}
