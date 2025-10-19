package ru.yandex.practicum.delivery.model;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.navigation.Address;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PerishableParcelTest {

    public static final String DESCRIPTION = "Скоропортящаяся посылка";
    public static final int WEIGHT = 10;
    public static final Address DELIVERY_ADDRESS = new Address(
            "100100", "Москва", "Московская", 1, 1);
    public static final LocalDate DELIVERY_DATE = LocalDate.now();
    public static final int TIME_TO_LIVE = 7;

    @Test
    void calculateDeliveryCost_shouldBe3_whenPerishableParcelAnd1Weight() {
        Parcel parcel = new PerishableParcel(DESCRIPTION, 1, DELIVERY_ADDRESS, DELIVERY_DATE, TIME_TO_LIVE);
        int cost = parcel.calculateDeliveryCost();
        int expectedCost = 3;

        assertEquals(expectedCost, cost);
    }

    @Test
    void calculateDeliveryCost_shouldBe30_whenPerishableParcelAnd10Weight() {
        Parcel parcel = new PerishableParcel(DESCRIPTION, 10, DELIVERY_ADDRESS, DELIVERY_DATE, TIME_TO_LIVE);
        int cost = parcel.calculateDeliveryCost();
        int expectedCost = 30;

        assertEquals(expectedCost, cost);
    }

    @Test
    void isExpired_shouldReturnTrue_whenDateIsAfterExpiry() {
        PerishableParcel parcel = new PerishableParcel(
                DESCRIPTION, WEIGHT, DELIVERY_ADDRESS, DELIVERY_DATE, TIME_TO_LIVE);
        LocalDate testDate = DELIVERY_DATE.plusDays(TIME_TO_LIVE + 1);

        assertTrue(parcel.isExpired(testDate));
    }

    @Test
    void isExpired_shouldReturnFalse_whenDateIsBeforeExpiry() {
        PerishableParcel parcel = new PerishableParcel(
                DESCRIPTION, WEIGHT, DELIVERY_ADDRESS, DELIVERY_DATE, TIME_TO_LIVE);
        LocalDate testDate = DELIVERY_DATE.plusDays(TIME_TO_LIVE - 2);

        assertFalse(parcel.isExpired(testDate));
    }

    @Test
    void isExpired_shouldBeReturnFalse_whenDateIsExactlyOnExpiry() {
        PerishableParcel parcel = new PerishableParcel(
                DESCRIPTION, WEIGHT, DELIVERY_ADDRESS, DELIVERY_DATE, TIME_TO_LIVE
        );
        LocalDate testDate = DELIVERY_DATE.plusDays(TIME_TO_LIVE);

        assertFalse(parcel.isExpired(testDate));
    }
}