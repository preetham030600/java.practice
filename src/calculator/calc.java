package calculator;

import java.util.Scanner;

public class calc {
    private final Scanner input;

    public calc(Scanner input) {
        this.input = input;
    }

    public calc() {
        this(new Scanner(System.in));
    }

    public void run() {
        while (true) {
            System.out.println("\nBasic Calculator");
            System.out.println("1. Add\n2. Subtract\n3. Multiply\n4. Divide");
            System.out.println("5. Power\n6. Square root\n7. Factorial\n0. Back");
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
                    case 1 -> printResult(add(readNumber("First number: "), readNumber("Second number: ")));
                    case 2 -> printResult(subtract(readNumber("First number: "), readNumber("Second number: ")));
                    case 3 -> printResult(multiply(readNumber("First number: "), readNumber("Second number: ")));
                    case 4 -> printResult(divide(readNumber("First number: "), readNumber("Second number: ")));
                    case 5 -> printResult(power(readNumber("Base: "), readNumber("Exponent: ")));
                    case 6 -> printResult(squareRoot(readNumber("Number: ")));
                    case 7 -> printResult(factorial((int) readWholeNumber("Non-negative integer: ")));
                    default -> System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public double add(double first, double second) {
        return first + second;
    }

    public double subtract(double first, double second) {
        return first - second;
    }

    public double multiply(double first, double second) {
        return first * second;
    }

    public double divide(double first, double second) {
        if (second == 0) {
            throw new IllegalArgumentException("Cannot divide by zero.");
        }
        return first / second;
    }

    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public double squareRoot(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Square root requires a non-negative number.");
        }
        return Math.sqrt(number);
    }

    public long factorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Factorial requires a non-negative integer.");
        }
        long result = 1;
        for (int i = 2; i <= number; i++) {
            if (result > Long.MAX_VALUE / i) {
                throw new IllegalArgumentException("Factorial is too large for a long integer.");
            }
            result *= i;
        }
        return result;
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

    private double readWholeNumber(String prompt) {
        double value = readNumber(prompt);
        if (value != Math.rint(value)) {
            throw new IllegalArgumentException("Enter a whole number.");
        }
        if (value < 0 || value > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Enter a non-negative integer within the supported range.");
        }
        return value;
    }

    private void printResult(double result) {
        System.out.println("Result: " + result);
    }
}
