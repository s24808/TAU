package game;

import java.util.Random;

public class GameBoard {
    private final int rows; //Wiersze na planszy
    private final int cols; //Kolumny na planszy
    private final char[][] board; //Tablica - plansza
    private final Random random;

    // Konstruktor klasy GameBoard
    public GameBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new char[rows][cols];
        this.random = new Random();
    }

    //Generowanie planszy
    public void generateBoard() {
        //Wypełnienie planszy pustymi polami
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '.'; //Puste pole
            }
        }

        //Umieszczenie start i stop na krawędziach planszy
        int[] start = placeEdgeMarker('A'); //Start
        int[] stop;
        do {
            stop = placeEdgeMarker('B'); //Stop
        } while (isTooClose(start, stop)); //Sprawdzenie, czy start i stop nie są obok siebie

        //Dodawanie przeszkód
        addObstacles(5); //Ilość przeszkód
    }

    //Losowanie pozycji start i stop na krawędziach planszy
    private int[] placeEdgeMarker(char marker) {
        int x = 0, y = 0;
        boolean placed = false;

        while (!placed) {
            //Losowanie krawędzi
            int edge = random.nextInt(4);
            int pos = random.nextInt(rows); //Losowanie pozycji na krawędzi według wierszów

            //Przypisanie współrzędnych w zależności od krawędzi
            switch (edge) {
                case 0 -> { x = 0; y = pos; } //Górna krawędź
                case 1 -> { x = rows - 1; y = pos; } //Dolna krawędź
                case 2 -> { x = pos; y = 0; } //Lewa krawędź
                case 3 -> { x = pos; y = cols - 1; } //Prawa krawędź
            }

            //Sprawdzenie, czy pole jest puste
            if (board[x][y] == '.') {
                board[x][y] = marker; //Umieszczamy znacznik
                placed = true;
            }
        }
        return new int[]{x, y};
    }

    //Sprawdzenie, czy start i stop nie są obok siebie
    boolean isTooClose(int[] pos1, int[] pos2) {
        //Różnica według współrzędnych
        int dx = Math.abs(pos1[0] - pos2[0]); //Wiersze
        int dy = Math.abs(pos1[1] - pos2[1]); //Kolumny

        //Start i stop są za blisko
        return (dx == 0 && dy == 1) || (dx == 1 && dy == 0);
    }

    //Dodawanie przeszkód losowo
    private void addObstacles(int count) {
        int added = 0; //Licznik przeszkód
        while (added < count) {
            int x = random.nextInt(rows); //Losowanie wiersza
            int y = random.nextInt(cols); //Losowanie kolumny

            //Dodanie przeszkody, jeśli pole jest wolne
            if (board[x][y] == '.') {
                board[x][y] = 'X'; //Przeszkoda
                added++; //Zwiększenie licznika
            }
        }
    }

    //Zwracanie planszy
    public char[][] getBoard() {
        return board;
    }

    //Wyświetlanie planszy
    public void displayBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Ruch w górę
    public int[] moveUp(int currentX, int currentY) {
        if (currentX > 0 && board[currentX - 1][currentY] != 'X') {
            return new int[]{currentX - 1, currentY}; //Ruch w górę, jeśli nie ma przeszkody lub końca planszy
        }
        return new int[]{currentX, currentY}; //Zostajemy w miejscu (ruch niemożliwy do wykonania)
    }

    //Ruch w dół
    public int[] moveDown(int currentX, int currentY) {
        if (currentX < rows - 1 && board[currentX + 1][currentY] != 'X') {
            return new int[]{currentX + 1, currentY}; //Ruch w dół, jeśli nie ma przeszkody lub końca planszy
        }
        return new int[]{currentX, currentY}; //Zostajemy w miejscu (ruch niemożliwy do wykonania)
    }

    //Ruch w lewo
    public int[] moveLeft(int currentX, int currentY) {
        if (currentY > 0 && board[currentX][currentY - 1] != 'X') {
            return new int[]{currentX, currentY - 1}; //Ruch w lewo, jeśli nie ma przeszkody lub końca planszy
        }
        return new int[]{currentX, currentY}; //Zostajemy w miejscu (ruch niemożliwy do wykonania)
    }

    //Ruch w prawo
    public int[] moveRight(int currentX, int currentY) {
        if (currentY < cols - 1 && board[currentX][currentY + 1] != 'X') {
            return new int[]{currentX, currentY + 1}; //Ruch w prawo, jeśli nie ma przeszkody lub końca planszy
        }
        return new int[]{currentX, currentY}; //Zostajemy w miejscu (ruch niemożliwy do wykonania)
    }
}