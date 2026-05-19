package pl.edytorszach;

// obsluga stdin stdout
// MAIN CLASS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class Editor {
    private ChessBoard board;
    private FileManager fileManager;

    // for io streams
    private final BufferedReader input;
    private final PrintStream output;

    public Editor(ChessBoard board, FileManager fileManager, BufferedReader input, PrintStream output) {
        this.board = board;
        this.fileManager = fileManager;
        this.input = input;
        this.output = output;
    }

    public void run(){
        output.println("CHESSBOARD EDITOR. What do you want to do?:");
        try{
            String line;
            while ((line = input.readLine()) != null) {
                processCommand(line.trim());
            }
        }catch(IOException e){
            System.err.print("IO exception in editor.");
        }
    }

    private void processCommand(String input){
        // TODO: switch case z komendami


    }
}
