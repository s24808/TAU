package game;

public class Main {
    public static void main(String[] args) {
        GameBoard board = new GameBoard(5, 5); //Plansza 5x5
        board.generateBoard(); //Generowanie planszy
        board.displayBoard(); //Wyświetlanie planszy
    }
}