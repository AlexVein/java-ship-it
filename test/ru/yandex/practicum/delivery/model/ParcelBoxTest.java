package ru.yandex.practicum.delivery.model;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.navigation.Address;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ParcelBoxTest {
    public static final String DESCRIPTION = "Стандартная посылка";
    public static final Address DELIVERY_ADDRESS = new Address(
            "100100", "Москва", "Московская", 1, 1);
    public static final LocalDate DELIVERY_DATE = LocalDate.now();
    public static final int BOX_CAPACITY = 10;

    @Test
    void addParcel_whenParcelWeight5AndBoxCapacity10_shouldAddParcelAndReduceWeight() {
        StandardParcel parcel = new StandardParcel(DESCRIPTION, 5, DELIVERY_ADDRESS, DELIVERY_DATE);
        ParcelBox<StandardParcel> box = new ParcelBox<>(BOX_CAPACITY);
        box.addParcel(parcel);

        int numParcelsInBox = box.getAllParcels().size();
        int expectedNumParcelsInBox = 1;
        assertEquals(expectedNumParcelsInBox, numParcelsInBox,
                "Количество посылок в коробке должно быть равно 1");

        int boxFreeWeight = box.getFreeWeight();
        int expectedBoxFreeWeight = 5;
        assertEquals(expectedBoxFreeWeight, boxFreeWeight, "Вместимость коробки должна быть равна 5");
    }

    @Test
    void addParcel_whenParcelWeight9AndBoxCapacity10_shouldAddParcelAndReduceWeight() {
        StandardParcel parcel = new StandardParcel(DESCRIPTION, 9, DELIVERY_ADDRESS, DELIVERY_DATE);
        ParcelBox<StandardParcel> box = new ParcelBox<>(BOX_CAPACITY);
        box.addParcel(parcel);

        int numParcelsInBox = box.getAllParcels().size();
        int expectedNumParcelsInBox = 1;
        assertEquals(expectedNumParcelsInBox, numParcelsInBox,
                "Количество посылок в коробке должно быть равно 1");

        int boxFreeWeight = box.getFreeWeight();
        int expectedBoxFreeWeight = 1;
        assertEquals(expectedBoxFreeWeight, boxFreeWeight, "Вместимость коробки должна быть равна 1");
    }

    @Test
    void addParcel_whenParcelWeight10AndBoxCapacity10_shouldAddParcelAndReduceWeight() {
        StandardParcel parcel = new StandardParcel(DESCRIPTION, 10, DELIVERY_ADDRESS, DELIVERY_DATE);
        ParcelBox<StandardParcel> box = new ParcelBox<>(BOX_CAPACITY);
        box.addParcel(parcel);

        int numParcelsInBox = box.getAllParcels().size();
        int expectedNumParcelsInBox = 1;
        assertEquals(expectedNumParcelsInBox, numParcelsInBox,
                "Количество посылок в коробке должно быть равно 1");

        int boxFreeWeight = box.getFreeWeight();
        int expectedBoxFreeWeight = 0;
        assertEquals(expectedBoxFreeWeight, boxFreeWeight, "Вместимость коробки должна быть равна 0");
    }

    @Test
    void addParcel_whenParcelWeight11AndBoxCapacity10_shouldNotAddParcelAndNotReduceWeight() {
        StandardParcel parcel = new StandardParcel(DESCRIPTION, 11, DELIVERY_ADDRESS, DELIVERY_DATE);
        ParcelBox<StandardParcel> box = new ParcelBox<>(BOX_CAPACITY);
        box.addParcel(parcel);

        int numParcelsInBox = box.getAllParcels().size();
        int expectedNumParcelsInBox = 0;
        assertEquals(expectedNumParcelsInBox, numParcelsInBox,
                "Количество посылок в коробке должно быть равно 0");

        int boxFreeWeight = box.getFreeWeight();
        int expectedBoxFreeWeight = 10;
        assertEquals(expectedBoxFreeWeight, boxFreeWeight, "Вместимость коробки должна быть равна 10");
    }
}