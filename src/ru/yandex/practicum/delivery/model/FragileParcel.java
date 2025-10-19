package ru.yandex.practicum.delivery.model;

import ru.yandex.practicum.delivery.navigation.Address;

import java.time.LocalDate;

public class FragileParcel extends Parcel implements Trackable {
    public FragileParcel(String description, int weight, Address deliveryAddress, LocalDate sendDay) {
        super(description, weight, deliveryAddress, sendDay);
        this.deliveryType = DeliveryType.FRAGILE;
    }

    @Override
    public void packageItem() {
        System.out.printf("Посылка %s обёрнута в защитную плёнку\n", parcelID);
        super.packageItem();
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.printf("Хрупкая посылка %s изменила местоположение на %s\n", parcelID, newLocation);
    }
}