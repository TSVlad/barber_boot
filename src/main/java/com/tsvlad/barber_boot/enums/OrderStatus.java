package com.tsvlad.barber_boot.enums;

public enum OrderStatus {
    CANCEL("Отменен"),

    ACCEPT("Принят");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
