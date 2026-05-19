package pl.edytorszach;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        KnightInterface knight = new FakeKnight();
        ChessBoard board = new ChessBoard(8, knight);
        FileManager fm = new FileManager();

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        PrintStream output = System.out;

        Editor editor = new Editor(board, fm, input, output);
        editor.run();
    }
}
