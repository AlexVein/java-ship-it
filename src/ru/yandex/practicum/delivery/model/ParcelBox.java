package ru.yandex.practicum.delivery.model;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    private final ArrayList<T> packedParcels = new ArrayList<>();

    private int weight;

    public ParcelBox(int weight) {
        this.weight = weight;
    }

    public void addParcel(T parcel) {
        if (weight < weight + parcel.getWeight()) {
            System.err.printf("""
                            Невозможно добавить посылку %s в коробку.
                            Вес посылки составляет %d кг. Коробка может вместить еще %d кг.
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