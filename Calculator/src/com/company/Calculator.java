package com.company;

import java.util.Scanner;

public class Calculator {
    private static final String MENU = """
                                ---------------------------------------
                                                 MENU
                                ---------------------------------------
                                Please enter one of the symbols below:
                                  "+": SUM OF TWO NUMBERS
                                  "-": DIFFERENCE BETWEEN TWO NUMBERS
                                  "*": PRODUCT OF TWO NUMBERS
                                  "/": DIVISION OF TWO NUMBERS
                                 \s
                                  "exit": EXIT THE CALCULATOR
                                """;

    public static void main() {
        Scanner sc = new Scanner(System.in);
        String operation;
        boolean active = true;
        while (active) {
            System.out.println(MENU);
            operation = sc.next();
            switch (operation.toLowerCase()) {
                case "+" -> summarize(sc);
                case "-" -> subtract(sc);
                case "/" -> divide(sc);
                case "*" -> multiply(sc);
                case "exit" -> active = false;
                default -> System.out.println("UNKNOWN OPERATION. TRY AGAIN");
            }
        }
    }

    private static void summarize(Scanner sc) {
        try {
            double firstNumber = sc.nextDouble();
            double secondNumber = sc.nextDouble();
            System.out.println("Result:\n" + (firstNumber + secondNumber));
        } catch (Exception e) {
            System.out.println("One of the inputs is not a valid integer.");
        }
    }

    private static void subtract(Scanner sc) {
        try {
            double firstNumber = sc.nextDouble();
            double secondNumber = sc.nextDouble();
            System.out.println("Result:\n" + (firstNumber - secondNumber));
        } catch (Exception e) {
            System.out.println("One of the inputs is not a valid integer.");
        }
    }

    private static void multiply(Scanner sc) {
        try {
            double firstNumber = sc.nextDouble();
            double secondNumber = sc.nextDouble();
            System.out.println("Result:\n" + (firstNumber * secondNumber));
        } catch (Exception e) {
            System.out.println("One of the inputs is not a valid integer.");
        }
    }

    private static void divide(Scanner sc) {
        try {
            double firstNumber = sc.nextDouble();
            double secondNumber = sc.nextDouble();
            System.out.println(secondNumber == 0 ?
                    "Sorry, division by 0 is not possible" :
                    "Result:\n" + (firstNumber / secondNumber));
        } catch (Exception e) {
            System.out.println("One of the inputs is not a valid integer.");
        }
    }
}
