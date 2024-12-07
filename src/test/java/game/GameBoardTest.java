package game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void testGenerateBoard() {
        GameBoard board = new GameBoard(5, 5);
        board.generateBoard();

        //Sprawdzenie, czy start i stop pojawił się na planszy
        boolean startExists = false;
        boolean stopExists = false;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board.getBoard()[i][j] == 'A') {
                    startExists = true;
                }
                if (board.getBoard()[i][j] == 'B') {
                    stopExists = true;
                }
            }
        }
        assertTrue(startExists, "START(A) powinien znajdować się na planszy.");
        assertTrue(stopExists, "STOP(B) powinien znajdować się na planszy.");
    }

    @Test
    void testStartAndStopNotTooClose() {
        GameBoard board = new GameBoard(5, 5);
        board.generateBoard();

        //Szukanie start i stop
        int startX = 0, startY = 0, stopX = 0, stopY = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board.getBoard()[i][j] == 'A') {
                    startX = i;
                    startY = j;
                }
                if (board.getBoard()[i][j] == 'B') {
                    stopX = i;
                    stopY = j;
                }
            }
        }

        //Sprawdzenie, czy start i stop nie są za blisko siebie.
        assertFalse(board.isTooClose(new int[]{startX, startY}, new int[]{stopX, stopY}),
                "START(A) i STOP(B) nie powinny być za blisko.");
    }

    @Test
    void testMoveUp() {
        GameBoard board = new GameBoard(5, 5);
        board.generateBoard();

        //Symulacja pozycji gracza
        int currentX = 1, currentY = 1;

        //Sprawdzenie, czy ruch w górę się wykonał
        int[] newPosition = board.moveUp(currentX, currentY);
        assertEquals(currentX - 1, newPosition[0]);
        assertEquals(currentY, newPosition[1]);
    }

    @Test
    void testMoveDown() {
        GameBoard board = new GameBoard(5, 5);
        board.generateBoard();

        //Symulacja pozycji gracza
        int currentX = 3, currentY = 1;

        //Sprawdzenie, czy ruch w dół się wykonał
        int[] newPosition = board.moveDown(currentX, currentY);
        assertEquals(currentX + 1, newPosition[0]);
        assertEquals(currentY, newPosition[1]);
    }

    @Test
    void testMoveLeftBlockedByObstacle() {
        GameBoard board = new GameBoard(5, 5);
        board.generateBoard();

        //Umieszczenie przeszkody na planszy
        board.getBoard()[2][1] = 'X'; //Przeszkoda po lewej od gracza

        //Symulacja pozycji gracza
        int currentX = 2, currentY = 2;

        //Sprawdzenie, czy ruch w lewo się nie wykonał ze względu na przeszkodę
        int[] newPosition = board.moveLeft(currentX, currentY);
        assertEquals(currentX, newPosition[0]);
        assertEquals(currentY, newPosition[1]);
    }

    @Test
    void testMoveRightBlockedByBoundary() {
        GameBoard board = new GameBoard(5, 5);
        board.generateBoard();

        //Symulacja pozycji gracza
        int currentX = 2, currentY = 4;

        //Sprawdzenie czy ruch w prawo jest zablokowany ze względu na koniec planszy
        int[] newPosition = board.moveRight(currentX, currentY);
        assertEquals(currentX, newPosition[0]);
        assertEquals(currentY, newPosition[1]);
    }

    @Test
    void testStartAndStopOnEdges() {
        GameBoard board = new GameBoard(5, 5);
        board.generateBoard();

        //Szukanie pozycji start i stop
        int startX = 0, startY = 0, stopX = 0, stopY = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board.getBoard()[i][j] == 'A') {
                    startX = i;
                    startY = j;
                }
                if (board.getBoard()[i][j] == 'B') {
                    stopX = i;
                    stopY = j;
                }
            }
        }

        //Sprawdzenie czy start i stop są na krawędziach planszy
        boolean startOnEdge = (startX == 0 || startX == 4 || startY == 0 || startY == 4);
        boolean stopOnEdge = (stopX == 0 || stopX == 4 || stopY == 0 || stopY == 4);

        assertTrue(startOnEdge, "START(A) powinien znajdować się na krawędzi planszy.");
        assertTrue(stopOnEdge, "STOP (B) powinien znajdować się na krawędzi planszy.");
    }
}