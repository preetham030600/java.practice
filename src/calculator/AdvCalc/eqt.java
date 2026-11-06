package calculator.AdvCalc;

import java.util.Scanner;

public class eqt {
    private final Scanner input;

    public eqt(Scanner input) {
        this.input = input;
    }

    public eqt() {
        this(new Scanner(System.in));
    }

    public void run() {
        while (true) {
            System.out.println("\nEquation Solver");
            System.out.println("1. Linear equation: ax + b = 0");
            System.out.println("2. Quadratic equation: ax^2 + bx + c = 0");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            try {
                int choice = Integer.parseInt(input.nextLine().trim());
                if (choice == 0) {
                    return;
                }
                switch (choice) {
                    case 1 -> printRoots(solveLinear(readNumber("a: "), readNumber("b: ")));
                    case 2 -> printRoots(solveQuadratic(readNumber("a: "), readNumber("b: "), readNumber("c: ")));
                    default -> System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Invalid choice.");
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public double[] solveLinear(double a, double b) {
        if (a == 0) {
            if (b == 0) {
                throw new IllegalArgumentException("Every number is a solution.");
            }
            throw new IllegalArgumentException("The equation has no solution.");
        }
        return new double[] {-b / a};
    }

    public double[] solveQuadratic(double a, double b, double c) {
        if (a == 0) {
            return solveLinear(b, c);
        }

        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            throw new IllegalArgumentException("No real roots (discriminant = " + discriminant + ").");
        }
        if (discriminant == 0) {
            return new double[] {-b / (2 * a)};
        }

        double root = Math.sqrt(discriminant);
        return new double[] {(-b + root) / (2 * a), (-b - root) / (2 * a)};
    }

    private void printRoots(double[] roots) {
        System.out.print("Root(s):");
        for (double root : roots) {
            System.out.printf(" %.10f", root);
        }
        System.out.println();
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
