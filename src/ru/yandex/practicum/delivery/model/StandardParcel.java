package ru.yandex.practicum.delivery.model;

import ru.yandex.practicum.delivery.navigation.Address;

import java.time.LocalDate;

public class StandardParcel extends Parcel {
    public StandardParcel(String description, double weight, Address deliveryAddress, LocalDate sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }
}