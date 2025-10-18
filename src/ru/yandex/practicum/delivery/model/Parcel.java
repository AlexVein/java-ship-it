package ru.yandex.practicum.delivery.model;

import ru.yandex.practicum.delivery.navigation.Address;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Parcel {
    protected static int id = 0;

    protected DeliveryType deliveryType;
    protected String description;
    protected double weight;
    protected Address deliveryAddress;
    protected LocalDate sendDay;
    protected String parcelID;

    public Parcel(String description, double weight, Address deliveryAddress, LocalDate sendDay) {
        id++;
        this.deliveryType = DeliveryType.STANDARD;
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
        parcelID = getDateNow() + "-" + id;
    }

    public void packageItem() {
        System.out.printf("Посылка %s упакована.\n", parcelID);
    }

    public void deliver() {
        System.out.printf("Посылка %s \"%s\" доставлена по адресу %s\n",
                parcelID, description, deliveryAddress.getHumanReadableAddress());
    }

    public int calculateDeliveryCost() {
        return (int) (weight * deliveryType.getRate());
    }

    private String getDateNow() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate currentDate = LocalDate.now();
        return currentDate.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return Double.compare(weight, parcel.weight) == 0 &&
                deliveryType == parcel.deliveryType &&
                Objects.equals(description, parcel.description) &&
                Objects.equals(deliveryAddress, parcel.deliveryAddress) &&
                Objects.equals(sendDay, parcel.sendDay) &&
                Objects.equals(parcelID, parcel.parcelID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryType, description, weight, deliveryAddress, sendDay, parcelID);
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "deliveryType=" + deliveryType +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", deliveryAddress=" + deliveryAddress +
                ", sendDay=" + sendDay +
                ", parcelID='" + parcelID + '\'' +
                '}';
    }
}
