package ru.yandex.practicum.delivery.model;

import ru.yandex.practicum.delivery.navigation.Address;

import java.time.LocalDate;

public class FragileParcel extends Parcel {
    public FragileParcel(String description, double weight, Address deliveryAddress, LocalDate sendDay) {
        super(description, weight, deliveryAddress, sendDay);
        this.deliveryType = DeliveryType.FRAGILE;
    }

    @Override
    public void packageItem() {
        System.out.printf("Посылка %s обёрнута в защитную плёнку", parcelID);
        super.packageItem();
    }
}
