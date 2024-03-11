
package org.example;

import org.example.models.Car;
import org.example.models.CarRental;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static CarRental car = new CarRental();

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addCar();
                case 2 -> displayCars();
                case 3 -> deleteCar();
                case 4 -> rentCar();
                case 5 -> returnCar();
                case 6 -> {
                    System.out.println("Exiting the Car Rental System.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nCar Rental System Menu:");
        System.out.println("1. Add a Car");
        System.out.println("2. Display All Cars");
        System.out.println("3. Delete a Car");
        System.out.println("4. Rent a Car");
        System.out.println("5. Return a Car");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addCar() {
        System.out.print("Enter the brand: ");
        String brand = scanner.nextLine();

        System.out.print("Enter the price: ");
        int price = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Is the car available? (true/false): ");
        boolean available = scanner.nextBoolean();
        scanner.nextLine();

        car.addCar(brand, price, available);
        System.out.println("Car added successfully!");
    }private static void rentCar() {
        displayCars();

        System.out.print("Enter the ID of the car you want to rent: ");
        int carId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        double totalPrice = car.rentCar(carId, customerName);
        if (totalPrice == -1) {
            System.out.println("Cannot rent the car. Car not found or not available.");
        } else {
            System.out.println("Car rented successfully!");
            System.out.println("Total Price: $" + totalPrice);
        }
    }

    private static void returnCar() {
        System.out.print("Enter the ID of the car you want to return: ");
        int carId = scanner.nextInt();
        scanner.nextLine();

        car.returnCar(carId);
        System.out.println("Car returned successfully!");
    }

    private static void deleteCar() {
        System.out.print("Enter the car ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        car.deleteCar(id);
        System.out.println("Car deleted successfully!");
    }
    private static void displayCars() {
        List<Car> cars = car.getAllCars();

        if (cars.isEmpty()) {
            System.out.println("No cars available in the rental system.");
        } else {
            System.out.println("Cars in the Rental System:");
            System.out.printf("%-5s %-15s %-10s %s%n", "ID", "Brand", "Price", "Available");

            for (Car car : cars) {
                System.out.printf("%-5d %-15s $%-10d %s%n", car.getId(), car.getBrand(), car.getPrice(), car.isAvailable() ? "Yes" : "No");
            }
        }
    }

    }


