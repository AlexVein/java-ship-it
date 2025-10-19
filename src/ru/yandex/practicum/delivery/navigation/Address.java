package ru.yandex.practicum.delivery.navigation;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Address {
    private final String postalCode;
    private final String city;
    private final String street;
    private final int house;
    private final int flat;

    public Address(String postalCode, String city, String street, int house, int flat) {
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public List<String> getAddress() {
        return Arrays.asList(postalCode, city, street, String.valueOf(house), String.valueOf(flat));
    }

    public String getHumanReadableAddress() {
        return String.format("%s, ул. %s, д. %d, кв. %d", city, street, house, flat);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return house == address.house &&
                flat == address.flat &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postalCode, city, street, house, flat);
    }

    @Override
    public String toString() {
        return "Address{" +
                "postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", flat=" + flat +
                '}';
    }
}
