package ru.yandex.practicum.delivery;

import ru.yandex.practicum.delivery.model.DeliveryType;
import ru.yandex.practicum.delivery.model.FragileParcel;
import ru.yandex.practicum.delivery.model.Parcel;
import ru.yandex.practicum.delivery.model.ParcelBox;
import ru.yandex.practicum.delivery.model.PerishableParcel;
import ru.yandex.practicum.delivery.model.StandardParcel;
import ru.yandex.practicum.delivery.navigation.Address;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {
    public static final double SIZE_STANDARD_BOX = 100;
    public static final double SIZE_FRAGILE_BOX = 20;
    public static final double SIZE_PERISHABLE_BOX = 50;

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<FragileParcel> allFragileParcels = new ArrayList<>();
    private static final ParcelBox<StandardParcel> packedStandardParcels = new ParcelBox<>(SIZE_STANDARD_BOX);
    private static final ParcelBox<FragileParcel> packedFragileParcels = new ParcelBox<>(SIZE_FRAGILE_BOX);
    private static final ParcelBox<PerishableParcel> packedPerishableParcels = new ParcelBox<>(SIZE_PERISHABLE_BOX);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addParcel();
                case 2 -> sendParcels();
                case 3 -> calculateCosts();
                case 4 -> changeStatus();
                case 5 -> showParcelsFromBox();
                case 0 -> running = false;
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    private static void addParcel() {
        DeliveryType type = getDeliveryType();
        if (type == null) {
            System.err.println("Указан неподдерживаемый тип посылки!");
            return;
        }

        System.out.println("Введите описание посылки:");
        String description = scanner.nextLine();
        System.out.println("Введите вес посылки:");
        double weight = Double.parseDouble(scanner.nextLine());
        Address deliveryAddress = getDeliveryAddress();
        LocalDate sendDay = getSendDay();

        int timeToLive = 0;
        if (type == DeliveryType.PERISHABLE) {
            System.out.println("Укажите срок годности отправления в днях:");
            timeToLive = Integer.parseInt(scanner.nextLine());
            if (timeToLive <= 0) {
                System.err.println("Срок годности не может быть меньше 1 дня!");
                return;
            }
        }

        switch (type) {
            case STANDARD -> {
                StandardParcel parcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(parcel);
                packedStandardParcels.addParcel(parcel);
            }
            case FRAGILE -> {
                FragileParcel parcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(parcel);
                packedFragileParcels.addParcel(parcel);
                allFragileParcels.add(parcel);
            }
            case PERISHABLE -> {
                PerishableParcel parcel = new PerishableParcel(
                        description, weight, deliveryAddress, sendDay, timeToLive);
                allParcels.add(parcel);
                packedPerishableParcels.addParcel(parcel);
            }
        }
    }

    private static void calculateCosts() {
        int sumParcels = 0;

        for (Parcel parcel : allParcels) {
            sumParcels += parcel.calculateDeliveryCost();
        }

        System.out.printf("Общая стоимость всех отправлений: %d руб.", sumParcels);
    }

    private static void changeStatus() {
        System.out.println("Введите местоположение посылки:");
        String newLocation = scanner.nextLine();
        for (FragileParcel parcel : allFragileParcels) {
            parcel.reportStatus(newLocation);
        }
    }

    private static Address getDeliveryAddress() {
        System.out.println("Введите через запятую адрес доставки в формате \"индекс, город, улица, дом, квартира\":");
        String[] address = scanner.nextLine().split(",");
        String postalCode = address[0].trim();
        String city = address[1].trim();
        String street = address[2].trim();
        int house = Integer.parseInt(address[3].trim());
        int flat = Integer.parseInt(address[4].trim());
        return new Address(postalCode, city, street, house, flat);
    }

    private static DeliveryType getDeliveryType() {
        System.out.println("Введите тип посылки: 1 — стандартная, 2 — хрупкая, 3 — скоропортящаяся");
        int userChoice = Integer.parseInt(scanner.nextLine());
        return switch (userChoice) {
            case 1 -> DeliveryType.STANDARD;
            case 2 -> DeliveryType.FRAGILE;
            case 3 -> DeliveryType.PERISHABLE;
            default -> null;
        };
    }

    private static LocalDate getSendDay() {
        System.out.println("Введите дату отправления посылки в формате дд.мм.гггг (например, 01.01.2001):");
        return LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Изменить статус посылки");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void showParcelsFromBox() {
        System.out.println("Укажите тип содержимого коробки: 1 — стандартная, 2 — хрупкая, 3 — скоропортящаяся");
        int userChoice = Integer.parseInt(scanner.nextLine());

        ArrayList<? extends Parcel> parcels = new ArrayList<>();

        switch (userChoice) {
            case 1 -> parcels = packedStandardParcels.getAllParcels();
            case 2 -> parcels = packedFragileParcels.getAllParcels();
            case 3 -> parcels = packedPerishableParcels.getAllParcels();
            default -> System.out.println("Неверный выбор.");
        }

        parcels.forEach(parcel -> System.out.println());
    }
}
