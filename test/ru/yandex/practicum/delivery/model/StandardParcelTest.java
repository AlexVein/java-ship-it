package ru.yandex.practicum.delivery.model;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.navigation.Address;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StandardParcelTest {

    public static final String DESCRIPTION = "Стандартная посылка";
    public static final Address DELIVERY_ADDRESS = new Address(
            "100100", "Москва", "Московская", 1, 1);
    public static final LocalDate DELIVERY_DATE = LocalDate.now();

    @Test
    void calculateDeliveryCost_shouldBe2_whenStandardParcelAnd1Weight() {
        Parcel parcel = new StandardParcel(DESCRIPTION, 1, DELIVERY_ADDRESS, DELIVERY_DATE);
        int cost = parcel.calculateDeliveryCost();
        int expectedCost = 2;

        assertEquals(expectedCost, cost);
    }

    @Test
    void calculateDeliveryCost_shouldBe20_whenStandardParcelAnd10Weight() {
        Parcel parcel = new StandardParcel(DESCRIPTION, 10, DELIVERY_ADDRESS, DELIVERY_DATE);
        int cost = parcel.calculateDeliveryCost();
        int expectedCost = 20;

        assertEquals(expectedCost, cost);
    }
}