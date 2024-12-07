package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameBoard board = new GameBoard(5, 5); //Plansza 5x5
        board.generateBoard(); //Generowanie planszy
        board.displayBoard(); //Wyświetlanie planszy

        //Pozycja startowa
        int currentX = 0;
        int currentY = 0;

        //Sprawdzienie, gdzie znajduje się start na planszy
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board.getBoard()[i][j] == 'A') {
                    currentX = i;
                    currentY = j;
                }
            }
        }

        //Sprawdzienie, gdzie znajduje się stop na planszy
        int targetX = 0;
        int targetY = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board.getBoard()[i][j] == 'B') {
                    targetX = i;
                    targetY = j;
                }
            }
        }

        Scanner scanner = new Scanner(System.in);

        //Symulacja gry
        while (true) {
            System.out.println("Podaj kierunek ruchu (w = góra, s = dół, a = lewo, d = prawo, q = wyjście): ");
            char move = scanner.next().charAt(0);

            int[] newPosition;
            switch (move) {
                case 'w' -> newPosition = board.moveUp(currentX, currentY);
                case 's' -> newPosition = board.moveDown(currentX, currentY);
                case 'a' -> newPosition = board.moveLeft(currentX, currentY);
                case 'd' -> newPosition = board.moveRight(currentX, currentY);
                case 'q' -> {
                    System.out.println("Koniec gry!");
                    return;
                }
                default -> {
                    System.out.println("Wprowadź poprawny kierunek ruchu.");
                    continue;
                }
            }

            //Aktualizacja pozycji gracza
            if (newPosition[0] != currentX || newPosition[1] != currentY) {
                System.out.println("Ruch wykonany!");
            } else {
                System.out.println("Kolizja! Ruch nie został wykonany.");
            }

            currentX = newPosition[0];
            currentY = newPosition[1];

            //Sprawdzenie, czy gracz dotarł do punktu stop
            if (currentX == targetX && currentY == targetY) {
                System.out.println("Dotarłeś do punktu B. Koniec gry!");
                break;
            }

            //Wyświetlenie planszy po ruchu
            board.displayBoard();
        }
    }
}
