package ru.yandex.practicum.delivery.model;

public enum DeliveryType {
    STANDARD(2),
    PERISHABLE(3),
    FRAGILE(4);

    private final int rate;

    DeliveryType(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}