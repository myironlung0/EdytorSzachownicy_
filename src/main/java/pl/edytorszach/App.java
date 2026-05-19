package pl.edytorszach;

/**
 * Hello world!
 *
 */
public class App 
{
    public static Editor editor;
    public static ChessBoard board;
    public static FileManager fm;

    public static void main( String[] args )
    {
        editor = new Editor(board, fm, );
        System.out.println( "Hello World!" );
    }
}
