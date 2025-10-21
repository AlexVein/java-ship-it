package ru.yandex.practicum.delivery.model;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    private final ArrayList<T> packedParcels = new ArrayList<>();

    private final int weight;
    private int freeWeight;

    public ParcelBox(int weight) {
        this.weight = weight;
        this.freeWeight = weight;
    }

    public int getFreeWeight() {
        return freeWeight;
    }

    public void addParcel(T parcel) {
        if (freeWeight - parcel.getWeight() < 0) {
            System.err.printf("""
                            Невозможно добавить посылку %s в коробку.
                            Вес посылки составляет %d кг. Коробка может вместить еще %d кг.
                            """,
                    parcel.getParcelID(), parcel.getWeight(), weight);
            return;
        }

        packedParcels.add(parcel);
        freeWeight -= parcel.getWeight();
    }

    public ArrayList<T> getAllParcels() {
        return packedParcels;
    }
}