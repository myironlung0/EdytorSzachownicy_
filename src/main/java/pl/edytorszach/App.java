package pl.edytorszach;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        KnightInterface knight = new FakeKnight(Set.of(new Point(2,2), new Point(3,4), new Point(5,5)));// sztywno ustawione pola atakowane
        ChessBoard board = new ChessBoard(8, knight);
        FileManager fm = new FileManager();

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        PrintStream output = System.out;

        Editor editor = new Editor(board, fm, input, output);
        editor.run();
    }
}
