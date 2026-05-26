package pl.edytorszach;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.*;

//
public class EditorTestMockito {
    @Test
    void shouldPrintUnknownCommand() throws IOException{
//        BufferedReader in = new BufferedReader(new StringReader("udawana_komenda\n"));

        BufferedReader mockIn = mock(BufferedReader.class);
        when(mockIn.readLine())
                .thenReturn("udawana_komenda\n")
                .thenReturn(null); // null konczy petle w run()

//        OutputStream outputStream = new ByteArrayOutputStream();
//       ByteArrayOutputStream outputStream = mock(ByteArrayOutputStream.class);
//        PrintStream out = new PrintStream(outputStream);
        PrintStream out = mock(PrintStream.class);

        KnightInterface mockKnight = mock(KnightInterface.class);
        ChessBoard board = new ChessBoard(mockKnight);
        FileManager fileManager = new FileManager();

        Editor editor = new Editor(board, fileManager, mockIn, out);
        editor.run();

        //assertTrue(result.contains("Unknown command."));
        verify(out).println("Unknown command.");
    }

    @Test
    void shouldCallCalculateAttackWhenShowAttack() throws IOException{
        KnightInterface mockKnight = mock(KnightInterface.class);
        when(mockKnight.calculateAttack(anyInt(), anyInt(), anyInt(), anySet(), anySet()))
                .thenReturn(Set.of(new Point(2,1)));

        ChessBoard board = new ChessBoard(mockKnight);
        board.placeKnight(3, 3);

        BufferedReader mockIn = mock(BufferedReader.class);
        when(mockIn.readLine())
                .thenReturn("show_attacks\n")
                .thenReturn(null);

        PrintStream mockOut = mock(PrintStream.class);

        Editor editor = new Editor(board, new FileManager(), mockIn, mockOut);
        editor.run();

        // The verify() method in Mockito is used to check if certain methods on mock objects
        // were called with specific arguments
        verify(mockKnight).calculateAttack(anyInt(),anyInt(),anyInt(), anySet(), anySet());
        verify(mockOut).println("(2,1)");
    }

    @Test
    void shouldAddObstacleAtCommand() throws IOException{
        BufferedReader mockIn = mock(BufferedReader.class);
        when(mockIn.readLine())
                .thenReturn("add_obstacle 3 3\n")
                .thenReturn(null);

        PrintStream mockOut = mock(PrintStream.class);
        ChessBoard mockBoard = mock(ChessBoard.class);
        Editor editor = new Editor(mockBoard, new FileManager(), mockIn, mockOut);
        editor.run();

        verify(mockBoard).addObstacle(3, 3);
    }

    @Test
    void shouldPrintErrorWhenKnightNotPlaced() throws IOException{
        BufferedReader mockIn = mock(BufferedReader.class);
        when(mockIn.readLine())
                .thenReturn("show_attacks\n")
                .thenReturn(null);

        PrintStream mockOut = mock(PrintStream.class);
        Editor editor = new Editor(new ChessBoard(new FakeKnight()), new FileManager(), mockIn, mockOut);
        editor.run();

        verify(mockOut).println("Error: place knight first (move_knight [x] [y])");
    }

    //    4 DUBLERY
    @Test
    void theUltimateTestWith4Doubles() throws IOException{
        // 1. stdin user
        BufferedReader mockIn = mock(BufferedReader.class);
        when(mockIn.readLine())
                .thenReturn("load_from_file board.txt")
                .thenReturn("show_attacks")
                .thenReturn(null);
        // 2. stdout
        PrintStream mockOut = mock(PrintStream.class);

        // 3. knight, chessboard (logic)
        KnightInterface mockKnight = mock(KnightInterface.class);
        when(mockKnight.calculateAttack(anyInt(), anyInt(), anyInt(), anySet(), anySet()))
                .thenReturn(Set.of(new Point(2,1)));

        // 4. file manager dubler
        FileManager mockFm = mock(FileManager.class);

        ChessBoard boardToLoad = new ChessBoard(mockKnight);
        boardToLoad.placeKnight(3,3);
        when(mockFm.load(anyString(), any())).thenReturn(boardToLoad);

        Editor editor = new Editor(new ChessBoard(mockKnight), mockFm, mockIn, mockOut);
        editor.run();

        verify(mockFm).load(anyString(), any());
        verify(mockKnight).calculateAttack(anyInt(),anyInt(),anyInt(), anySet(), anySet());
        verify(mockOut).println("(2,1)");
    }

    @Test
    void shouldPrintErrorWhenKnightOutsideBoard() throws IOException{
        BufferedReader mockIn = mock(BufferedReader.class);
        when(mockIn.readLine())
                .thenReturn("move_knight 99 99")
                .thenReturn(null);

        PrintStream mockOut = mock(PrintStream.class);
        Editor editor = new Editor(new ChessBoard(new FakeKnight()), new FileManager(), mockIn, mockOut);
        editor.run();

        verify(mockOut).println("Error: Can't place knight outside the board.");
    }

}
