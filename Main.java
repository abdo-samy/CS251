import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;

class Car {
    String brand;
    String model;
    int year;
    double price;
    String details;

    public Car(String brand, String model, int year, double price, String details) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.details = details;
    }
    public String toString() {
        return brand + "," + model + "," + year + "," + price + "," + details;
    }
    public String getFormatted() {
        return "Brand: " + brand + " , Model: " + model + " , Year: " + year +
         " , Price: " + price + " , Details: " + details;
    }
}

public class Main {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Car> cars = new ArrayList<>();
    static String fileName = "data.txt";

    public static void main(String[] args) throws Exception {
        loadData();
        System.out.println("Welcome to you in the showroom ");
        System.out.println("=============================");

        while (true) {
            System.out.println("press 1) to login as manager");
            System.out.println("press 2) to view the list of cars");
            System.out.println("press 3) to exit the program");
            System.out.println("=============================");
            System.out.print("Chooose an option: ");
            int option = input.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter password: ");
                    String password = input.next();
                    if (password.equals("1234")) {
                        handleManagerMenu();
                    } else {
                        System.out.println("Invalid password!");
                    }
                    break;
                case 2:
                    displayCars();
                    break;
                case 3:
                    saveData();
                    System.out.println(" Program Exited Successfully ");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    public static void handleManagerMenu() throws Exception {
        while (true) {
            System.out.println("\n------- Manager Menu -------");
            System.out.println("1) Add a new car");
            System.out.println("2) Remove a car");
            System.out.println("3) View cars list");
            System.out.println("4) Back to Main Menu");
            System.out.print("Choose: ");
            int choose = input.nextInt();

            if (choose == 1) {
                System.out.print("Enter the brand of the car: "); String b = input.next();
                System.out.print("Enter the model of the car: "); String m = input.next();
                System.out.print("Enter the year of the car: "); int y = input.nextInt();
                System.out.print("Enter the price of the car: "); double p = input.nextDouble();
                input.nextLine(); 
                System.out.print("Enter more details if exist: "); String d = input.nextLine();

                cars.add(new Car(b, m, y, p, d));
                saveData();
                System.out.println("\n Car added successfully!");
            } else if (choose == 2) {
                displayCars();
                if (!cars.isEmpty()) {
                    System.out.print("Enter car number to remove: ");
                    int index = input.nextInt();
                    if (index > 0 && index <= cars.size()) {
                        cars.remove(index - 1);
                        saveData();
                        System.out.println(" Car removed successfully!");
                    } else {
                        System.out.println("Invalid number!");
                    }
                }
            } else if (choose == 3) {
                displayCars();
            } else if (choose == 4) {
                break;
            }
        }
    }

    public static void displayCars() {
        if (cars.isEmpty()) {
            System.out.println("\n[ No cars available in the showroom ] \n");
        } else {
            System.out.println("\n========== The List of Cars ==========");
            for (int i = 0; i < cars.size(); i++) {
                System.out.println((i + 1) + ". " + cars.get(i).getFormatted());
            }
            System.out.println("======================================");
        }
    }

    public static void saveData() throws Exception {
        PrintWriter writer = new PrintWriter(fileName);
        for (Car c : cars) {
            writer.println(c.toString());
        }
        writer.close();
    }

    public static void loadData() throws Exception {
        File f = new File(fileName);
        if (!f.exists()) return;
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 5) {
                cars.add(new Car(parts[0], parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]), parts[4]));
            }
        }
        s.close();
    }
}