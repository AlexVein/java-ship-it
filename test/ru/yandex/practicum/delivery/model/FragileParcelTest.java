package ru.yandex.practicum.delivery.model;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.navigation.Address;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FragileParcelTest {

    public static final String DESCRIPTION = "Хрупкая посылка";
    public static final Address DELIVERY_ADDRESS = new Address(
            "100100", "Москва", "Московская", 1, 1);
    public static final LocalDate DELIVERY_DATE = LocalDate.now();

    @Test
    void calculateDeliveryCost_shouldBe4_whenFragileParcelAnd1Weight() {
        Parcel parcel = new FragileParcel(DESCRIPTION, 1, DELIVERY_ADDRESS, DELIVERY_DATE);
        int cost = parcel.calculateDeliveryCost();
        int expectedCost = 4;

        assertEquals(expectedCost, cost);
    }

    @Test
    void calculateDeliveryCost_shouldBe40_whenFragileParcelAnd10Weight() {
        Parcel parcel = new FragileParcel(DESCRIPTION, 10, DELIVERY_ADDRESS, DELIVERY_DATE);
        int cost = parcel.calculateDeliveryCost();
        int expectedCost = 40;

        assertEquals(expectedCost, cost);
    }
}