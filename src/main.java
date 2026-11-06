import calculator.AdvCalc.eqt;
import calculator.AdvCalc.matrix;
import calculator.AdvCalc.trig;
import calculator.calc;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nScientific Calculator");
            System.out.println("1. Basic calculator");
            System.out.println("2. Trigonometry and logarithms");
            System.out.println("3. Equation solver");
            System.out.println("4. Matrix calculator");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice;
            try {
                choice = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Invalid choice.");
                continue;
            }

            switch (choice) {
                case 1 -> new calc(input).run();
                case 2 -> new trig(input).run();
                case 3 -> new eqt(input).run();
                case 4 -> new matrix(input).run();
                case 0 -> {
                    System.out.println("Goodbye.");
                    input.close();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
