package pl.edytorszach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {

    @Test
    void shouldSaveFile(@TempDir Path tempDir) {
        Path file = tempDir.resolve("board.txt");

        ChessBoard board = new ChessBoard(new FakeKnight());
        board.placeKnight(2,3);
        board.addObstacle(1,1);

        FileManager fm = new FileManager();

        fm.save(file.toString(), board);

        assertTrue(Files.exists(file));
    }

    @Test
    void shouldLoadBoardFromFile(@TempDir Path tempDir){
        Path file = tempDir.resolve("loadboard.txt");

        ChessBoard board = new ChessBoard(new FakeKnight());
        board.placeKnight(2,3);
        board.addObstacle(1,1);

        FileManager fm = new FileManager();

        fm.save(file.toString(), board);

        ChessBoard loadedBoard = fm.load(file.toString(),new FakeKnight());

        assertNotNull(loadedBoard);
        assertEquals(new Point(2,3), loadedBoard.getKnightPosition());
        assertTrue(loadedBoard.getObstacles().contains(new Point(1,1)));
    }

    @Test
    void shouldReturnNullWhenFileNotFound(){
        FileManager fm = new FileManager();
        ChessBoard result = fm.load("nieistniejacy_plik.txt", new FakeKnight());
        assertNull(result);
    }
}
