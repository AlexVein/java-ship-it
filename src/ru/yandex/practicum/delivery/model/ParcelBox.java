package ru.yandex.practicum.delivery.model;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    private final ArrayList<T> packedParcels = new ArrayList<>();

    private double weight;

    public ParcelBox(double weight) {
        this.weight = weight;
    }

    public void addParcel(T parcel) {
        if (weight < weight + parcel.getWeight()) {
            System.err.printf("""
                            Невозможно добавить посылку %s в коробку.
                            Вес посылки составляет %.2f кг. Коробка может вместить еще %.2f кг.
                            """,
                    parcel.getParcelID(), parcel.getWeight(), weight);
            return;
        }

        packedParcels.add(parcel);
        weight -= parcel.getWeight();
    }

    public ArrayList<T> getAllParcels() {
        return packedParcels;
    }
}