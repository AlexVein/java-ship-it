package ru.yandex.practicum.delivery.model;

import ru.yandex.practicum.delivery.navigation.Address;

import java.time.LocalDate;
import java.util.Objects;

public class PerishableParcel extends Parcel {
    private final int timeToLive;

    public PerishableParcel(String description, int weight, Address deliveryAddress, LocalDate sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.deliveryType = DeliveryType.PERISHABLE;
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(LocalDate date) {
        return date.isAfter(sendDay.plusDays(timeToLive));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PerishableParcel that = (PerishableParcel) o;
        return timeToLive == that.timeToLive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), timeToLive);
    }

    @Override
    public String toString() {
        return super.toString() + ", timeToLive=" + timeToLive + '}';
    }
}