package com.jwd.console;

import com.jwd.entity.Pair;
import com.jwd.exception.EmptyWordException;
import com.jwd.exception.NullPairException;
import com.jwd.service.EnRuDictionary;

import java.io.Closeable;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class DictionaryConsoleApplication {
    public static final String MENU = "Press:" +
                                      "\n 0 - Exit" +
                                      "\n 1 - Save a new pair" +
                                      "\n 2 - Find EN word" +
                                      "\n 3 - Find RU word" +
                                      "\n 4 - Get the number of pairs" +
                                      "\n 5 - Display the dictionary" +
                                      "\n 6 - Take a quiz";
    public static final int EXIT = 0, SAVE = 1, EN_FIND = 2, RU_FIND = 3, SIZE = 4, DISPLAY = 5,
            QUIZ = 6;
    public static final String DELIMITER = "\n==============================================\n";

    private final EnRuDictionary dictionary;
    private final Scanner scanner;
    {
        scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US); // поменяем формат ввода double чисел на 1.3
    }
    private Closeable[] closeables;

    public DictionaryConsoleApplication(final EnRuDictionary dict) {
        this.dictionary = dict;
        this.closeables = new Closeable[]{scanner};
    }

    public void start() {
        processMenu();
        cleanUpCloseables();
    }

    private void processMenu() {
        boolean gameOn = true;
        while (gameOn) {
            print(MENU);
            print(DELIMITER);
            int consoleChoice = getChoice();
            switch (consoleChoice) {
                case EXIT:
                    gameOn = false;
                    print("closing the app...");
                    break;
                case SAVE:
                    savePair();
                    break;
                case EN_FIND:
                    findEnglish();
                    break;
                case RU_FIND:
                    findRussian();
                    break;
                case SIZE:
                    findSize();
                    break;
                case DISPLAY:
                    display();
                    break;
                case QUIZ:
                    takeQuiz();
                    break;
                default:
                    print("Invalid choice. Restarting app." + DELIMITER);
            }
        }
    }

    private int getChoice() {
        int choice;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        } else {
            cleanScannerNextEnteredValue();
            choice = -1;
        }
        return choice;
    }

    private void cleanScannerNextEnteredValue() {
        final String next = scanner.next();
        //print("Entered next=[" + next + "].");
    }

    private void savePair() {
        try {
            print("Ready to save a new pair...\nEnter:\n");
            final Pair parameter= preparePair();
            dictionary.addPair(parameter);
            print("The pair " + parameter + " has been added." + DELIMITER);
        } catch (NullPairException | EmptyWordException e) {
            printCaughtException(e);
        }
    }

    private void findEnglish() {
        try {
            print("Input the russian word:");
            final String russian = scanner.next();
            dictionary.findPair(russian, false);
        } catch (EmptyWordException e) {
            printCaughtException(e);
        }
    }

    private void findRussian() {
        try {
            print("Input the english word:");
            final String english = scanner.next();
            dictionary.findPair(english, true);
        } catch (EmptyWordException e) {
            printCaughtException(e);
        }
    }

    private void findSize() {
        print("The size is: " + this.dictionary.getDictionarySize());
    }

    private void display() {
        this.dictionary.printWords();
    }


    private void takeQuiz() {
        try {
            String english;
            double count = 0;
            for (int i = 0; i < 5; i++) {
                english = dictionary.getRandomWord();
                print("Input the translation for the next word:\n" + english);
                final String russian = scanner.next();
                count += dictionary.takeTry(english, russian);
            }
            System.out.println("Your score is: " + count / 5 * 100 + "%");
        } catch (EmptyWordException e) {
            printCaughtException(e);
        }
    }

    private Pair preparePair() {
        print("Enter a pair:");
        String parameterX = getParameter();
        String parameterY = getParameter();
        return new Pair(parameterX, parameterY);
    }


    private String getParameter() {
        String parameter = null;
        if (scanner.hasNext()) {
            parameter = scanner.next();
        } else {
            cleanScannerNextEnteredValue();
        }
        return parameter;
    }

    private void print(final String message) {
        System.out.println(message);
    }

    private void printCaughtException(Exception e) {
        print(e.getMessage());
    }

    private void cleanUpCloseables() {
        for (final Closeable closeable : closeables) {
            try {
                closeable.close();
                System.out.println("The resource is now closed: " + closeable.getClass());

            } catch (final IOException e) {
                System.out.println("Something went wrong during closing " + closeable.getClass());
                e.printStackTrace();
            }
        }
    }


}
