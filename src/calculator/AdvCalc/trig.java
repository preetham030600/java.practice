package calculator.AdvCalc;

import java.util.Scanner;

public class trig {
    private final Scanner input;

    public trig(Scanner input) {
        this.input = input;
    }

    public trig() {
        this(new Scanner(System.in));
    }

    public void run() {
        while (true) {
            System.out.println("\nTrigonometry");
            System.out.println("1. Degrees to radians");
            System.out.println("2. Radians to degrees");
            System.out.println("3. sin, cos, tan");
            System.out.println("4. Inverse sin, cos, tan");
            System.out.println("5. log10 and natural log");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            int choice;
            try {
                choice = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Invalid choice.");
                continue;
            }

            if (choice == 0) {
                return;
            }

            try {
                switch (choice) {
                    case 1 -> System.out.println("Radians: " + toRadians(readNumber("Degrees: ")));
                    case 2 -> System.out.println("Degrees: " + toDegrees(readNumber("Radians: ")));
                    case 3 -> calculateTrig(readNumber("Angle in degrees: "));
                    case 4 -> calculateInverseTrig();
                    case 5 -> calculateLogs(readNumber("Positive number: "));
                    default -> System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public double toRadians(double degrees) {
        return Math.toRadians(degrees);
    }

    public double toDegrees(double radians) {
        return Math.toDegrees(radians);
    }

    public double sinDegrees(double degrees) {
        return Math.sin(toRadians(degrees));
    }

    public double cosDegrees(double degrees) {
        return Math.cos(toRadians(degrees));
    }

    public double tanDegrees(double degrees) {
        return Math.tan(toRadians(degrees));
    }

    public double asinDegrees(double value) {
        validateInverseInput(value);
        return Math.toDegrees(Math.asin(value));
    }

    public double acosDegrees(double value) {
        validateInverseInput(value);
        return Math.toDegrees(Math.acos(value));
    }

    public double atanDegrees(double value) {
        return Math.toDegrees(Math.atan(value));
    }

    private void calculateTrig(double degrees) {
        System.out.printf("sin: %.10f%n", sinDegrees(degrees));
        System.out.printf("cos: %.10f%n", cosDegrees(degrees));
        System.out.printf("tan: %.10f%n", tanDegrees(degrees));
    }

    private void calculateInverseTrig() {
        double value = readNumber("Value: ");
        if (value >= -1 && value <= 1) {
            System.out.printf("asin: %.10f degrees%n", asinDegrees(value));
            System.out.printf("acos: %.10f degrees%n", acosDegrees(value));
        } else {
            validateInverseInput(value);
        }
        System.out.printf("atan: %.10f degrees%n", atanDegrees(value));
    }

    private void calculateLogs(double number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Logarithm requires a positive number.");
        }
        System.out.println("Natural log: " + Math.log(number));
        System.out.println("Base-10 log: " + Math.log10(number));
    }

    private void validateInverseInput(double value) {
        if (value < -1 || value > 1) {
            throw new IllegalArgumentException("asin and acos require a value between -1 and 1.");
        }
    }

    private double readNumber(String prompt) {
        System.out.print(prompt);
        try {
            double value = Double.parseDouble(input.nextLine().trim());
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Enter a finite number.");
            }
            return value;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Invalid number.");
        }
    }
}
