package pl.edytorszach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FileManagerTestMockito {
    @Test
    void shouldSaveFile(@TempDir Path tempDir) {
        Path file = tempDir.resolve("board.txt");

        ChessBoard mockBoard = mock(ChessBoard.class);
        when(mockBoard.getN()).thenReturn(8);
        when(mockBoard.getKnightPosition()).thenReturn(new Point(2, 3));
        when(mockBoard.getObstacles()).thenReturn(new HashSet<>());


        FileManager fm = new FileManager();
        fm.save(file.toString(), mockBoard);

        assertTrue(Files.exists(file));
        verify(mockBoard).getN(); // weryfikuje, ze zapytano o getN() z mockboard
        verify(mockBoard).getKnightPosition();
        verify(mockBoard).getObstacles();
    }

    @Test
    void shouldLoadBoardFromFile(@TempDir Path tempDir){
        Path file = tempDir.resolve("loadboard.txt");

        ChessBoard mockBoard = mock(ChessBoard.class);
        when(mockBoard.getN()).thenReturn(8);
        when(mockBoard.getKnightPosition()).thenReturn(new Point(2,3));
        when(mockBoard.getObstacles()).thenReturn(Set.of(new Point(1,1)));

        FileManager fm = new FileManager();
        fm.save(file.toString(), mockBoard);

        KnightInterface mockKnight = mock(KnightInterface.class);
        ChessBoard loadedBoard = fm.load(file.toString(), mockKnight);

        assertNotNull(loadedBoard);
        assertEquals(new Point(2,3), loadedBoard.getKnightPosition());
        assertTrue(loadedBoard.getObstacles().contains(new Point(1,1)));

        verify(mockBoard).getN();
        verify(mockBoard).getKnightPosition();
        verify(mockBoard).getObstacles();
    }
}
