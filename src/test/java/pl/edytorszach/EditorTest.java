package pl.edytorszach;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditorTest {

    // https://stackoverflow.com/questions/13329282/test-java-programs-that-read-from-stdin-and-write-to-stdout
    @Test
    void shouldPrintUnknownCommand(){
        BufferedReader in = new BufferedReader(new StringReader("udawana_komenda\n"));
        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);
        FakeKnight fakeKnight = new FakeKnight();
        ChessBoard board = new ChessBoard(fakeKnight);
        FileManager fileManager = new FileManager();

        Editor editor = new Editor(board, fileManager, in, out);
        editor.run();

        String result = outputStream.toString();
        assertTrue(result.contains("Unknown command."));

    }

    @Test
    void shouldAddObstacleAtCommand(){
        BufferedReader in = new BufferedReader(new StringReader("add_obstacle 3 3\n"));
        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);
        FakeKnight fakeKnight = new FakeKnight();
        ChessBoard board = new ChessBoard(fakeKnight);
        FileManager fileManager = new FileManager();

        Editor editor = new Editor(board, fileManager, in, out);
        editor.run();

        assertTrue(board.getObstacles().contains(new Point(3,3)));

    }

    @Test
    void shouldPrintErrorWhenKnightNotPlaced(){
        BufferedReader in = new BufferedReader(new StringReader("show_attacks\n"));
        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);

        Editor editor = new Editor(new ChessBoard(new FakeKnight()), new FileManager(), in, out);
        editor.run();

        String result = outputStream.toString();
        assertTrue(result.contains("Error: place knight first (move_knight [x] [y])"));
    }

//    4 DUBLERY
    @Test
    void theUltimateTestWith4Doubles(){
        // 1. stdin user
        BufferedReader in = new BufferedReader(new StringReader("load_from_file board.txt\n" +
                                                                    "show_attacks\n"));
        // 2. stdout
        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);

        // 3. knight (logic)
        Set<Point> attacks = Set.of(new Point(2,1));
        FakeKnight fakeKnight = new FakeKnight(attacks);

        // 4. file manager dubler
        FakeFileManager fakeFm = new FakeFileManager();

        ChessBoard boardToLoad = new ChessBoard(fakeKnight);
        boardToLoad.placeKnight(3,3);
        fakeFm.setBoardToLoad(boardToLoad);

        Editor editor = new Editor(new ChessBoard(fakeKnight), fakeFm, in, out);

        editor.run();

        String result = outputStream.toString();
//        System.out.println("=== OUTPUT ===");
//        System.out.println(result);
//        System.out.println("=== END ===");
        assertTrue(result.contains("(2,1)"));
    }

    @Test
    void shouldPrintErrorWhenKnightOutsideBoard(){
        BufferedReader in = new BufferedReader(new StringReader("move_knight 99 99\n"));
        OutputStream outputStream = new ByteArrayOutputStream();
        Editor editor = new Editor(new ChessBoard(new FakeKnight()), new FileManager(), in, new PrintStream(outputStream));
        editor.run();
        assertTrue(outputStream.toString().contains("Error"));
    }

}
