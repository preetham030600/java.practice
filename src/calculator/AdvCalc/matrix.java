package calculator.AdvCalc;

import java.util.Scanner;

public class matrix {
    private final Scanner input;

    public matrix(Scanner input) {
        this.input = input;
    }

    public matrix() {
        this(new Scanner(System.in));
    }

    public void run() {
        while (true) {
            System.out.println("\nMatrix Calculator");
            System.out.println("1. Add matrices");
            System.out.println("2. Subtract matrices");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("5. Determinant of 2x2 matrix");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            try {
                int choice = Integer.parseInt(input.nextLine().trim());
                if (choice == 0) {
                    return;
                }

                switch (choice) {
                    case 1 -> printMatrix(add(readMatrix("First matrix"), readMatrix("Second matrix")));
                    case 2 -> printMatrix(subtract(readMatrix("First matrix"), readMatrix("Second matrix")));
                    case 3 -> printMatrix(multiply(readMatrix("First matrix"), readMatrix("Second matrix")));
                    case 4 -> printMatrix(transpose(readMatrix("Matrix")));
                    case 5 -> {
                        double[][] matrix2x2 = readMatrix("2x2 matrix");
                        if (matrix2x2.length != 2 || matrix2x2[0].length != 2 || matrix2x2[1].length != 2) {
                            throw new IllegalArgumentException("A 2x2 matrix is required.");
                        }
                        System.out.println("Determinant: " + determinant2x2(matrix2x2));
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Invalid choice or matrix value.");
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public double[][] add(double[][] first, double[][] second) {
        validateSameDimensions(first, second);
        double[][] result = new double[first.length][first[0].length];
        for (int row = 0; row < first.length; row++) {
            for (int column = 0; column < first[row].length; column++) {
                result[row][column] = first[row][column] + second[row][column];
            }
        }
        return result;
    }

    public double[][] subtract(double[][] first, double[][] second) {
        validateSameDimensions(first, second);
        double[][] result = new double[first.length][first[0].length];
        for (int row = 0; row < first.length; row++) {
            for (int column = 0; column < first[row].length; column++) {
                result[row][column] = first[row][column] - second[row][column];
            }
        }
        return result;
    }

    public double[][] multiply(double[][] first, double[][] second) {
        validateMatrix(first);
        validateMatrix(second);
        if (first[0].length != second.length) {
            throw new IllegalArgumentException("Columns in the first matrix must equal rows in the second matrix.");
        }

        double[][] result = new double[first.length][second[0].length];
        for (int row = 0; row < first.length; row++) {
            for (int column = 0; column < second[0].length; column++) {
                for (int index = 0; index < first[0].length; index++) {
                    result[row][column] += first[row][index] * second[index][column];
                }
            }
        }
        return result;
    }

    public double[][] transpose(double[][] source) {
        validateMatrix(source);
        double[][] result = new double[source[0].length][source.length];
        for (int row = 0; row < source.length; row++) {
            for (int column = 0; column < source[row].length; column++) {
                result[column][row] = source[row][column];
            }
        }
        return result;
    }

    public double determinant2x2(double[][] source) {
        validateMatrix(source);
        if (source.length != 2 || source[0].length != 2 || source[1].length != 2) {
            throw new IllegalArgumentException("A 2x2 matrix is required.");
        }
        return source[0][0] * source[1][1] - source[0][1] * source[1][0];
    }

    private double[][] readMatrix(String label) {
        System.out.println(label);
        System.out.print("Rows: ");
        int rows = Integer.parseInt(input.nextLine().trim());
        System.out.print("Columns: ");
        int columns = Integer.parseInt(input.nextLine().trim());
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive.");
        }

        double[][] values = new double[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                System.out.print("Value [" + (row + 1) + "," + (column + 1) + "]: ");
                values[row][column] = Double.parseDouble(input.nextLine().trim());
                if (!Double.isFinite(values[row][column])) {
                    throw new IllegalArgumentException("Matrix values must be finite numbers.");
                }
            }
        }
        return values;
    }

    private void printMatrix(double[][] values) {
        for (double[] row : values) {
            for (double value : row) {
                System.out.printf("%.10f ", value);
            }
            System.out.println();
        }
    }

    private void validateSameDimensions(double[][] first, double[][] second) {
        validateMatrix(first);
        validateMatrix(second);
        if (first.length != second.length || first[0].length != second[0].length) {
            throw new IllegalArgumentException("Matrices must have the same dimensions.");
        }
    }

    private void validateMatrix(double[][] values) {
        if (values == null || values.length == 0 || values[0] == null || values[0].length == 0) {
            throw new IllegalArgumentException("Matrix cannot be empty.");
        }
        for (double[] row : values) {
            if (row == null || row.length != values[0].length) {
                throw new IllegalArgumentException("Matrix rows must have equal length.");
            }
        }
    }
}
