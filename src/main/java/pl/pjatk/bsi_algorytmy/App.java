package pl.pjatk.bsi_algorytmy;

import pl.pjatk.bsi_algorytmy.expectedBreakdownCost.BreakdownCostCalculator;

import java.io.PrintStream;
import java.util.Scanner;

public class App {

    private final AlgorithmCaster caster = new AlgorithmCaster();
    private final Scanner scanner = new Scanner(System.in);
    private final PrintStream printer = System.out;

    private boolean keepRunning = true;

    public void run() {
        printWelcomeMessage();

        while (keepRunning) {

            int option = getMenuOption();

            switch (option) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    caster.setAlgorithm(new BreakdownCostCalculator());
                    caster.cast();
                    break;
                case 0:
                    keepRunning = false;
                    printer.println("Goodbye!");
                    break;
                default:
                    printer.println("Invalid option");
                    break;
            }
        }
    }

    private int getMenuOption() {
        return Integer.parseInt(scanner.nextLine());
    }

    private void printWelcomeMessage() {
        printer.println("Select option: " +
                "\n\t 1 - alg1" +
                "\n\t 2 - alg2" +
                "\n\t 3 - alg3" +
                "\n\t 4 - alg4" +
                "\n\t 5 - BreakdownCostCalculator");
    }

    public static void main(String[] args) {
        new App().run();
    }
}
