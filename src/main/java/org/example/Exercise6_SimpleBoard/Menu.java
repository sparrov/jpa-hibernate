package org.example.Exercise6_SimpleBoard;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private void printMenu() {
        System.out.println("Wybierz opcję z menu:");
        System.out.println("1 - Dodaj nowy post");
        System.out.println("2 - Odczytaj post");
        System.out.println("3 - Zakończ program");
        System.out.print("Wprowadź numer opcji z menu: ");
    }

    public void runMenu() {
        Post post = new Post();
        Scanner scanner = new Scanner(System.in);
        int selectedOption;
        try {
            do {
                printMenu();
                selectedOption = scanner.nextInt();
                scanner.nextLine();
                switch (selectedOption) {
                    case 1:
                        post.addNewPost();
                        break;
                    case 2:
                        post.readPost();
                        break;
                    default:
                        if (selectedOption == 3) {
                            System.out.println("Koniec programu!");
                        } else {
                            System.out.println("Nieprawidłowy wybór! Spróbuj jeszcze raz...");
                        }
                }
            }
            while (selectedOption != 3);
        } catch (InputMismatchException e) {
            System.out.println("Nieprawidłowy znak lub ciąg znaków. Spróbuj jeszcze raz...");
            runMenu();
        }
    }
}
