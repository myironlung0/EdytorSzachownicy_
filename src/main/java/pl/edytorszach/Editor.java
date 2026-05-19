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
        output.println("Please choose one:\n" +
                "create chessboard\n" +
                "create obstacles\n" +
                "move knight\n" +
                "show attacks\n" +
                "save to file\n" +
                "load from file\n" +
                "exit");
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
        switch(input.toLowerCase()){
            case "create chessboard":
                output.println("Creating new chessboard. Please provide size:");
                break;
            case "create obstacles":
                output.println("Creating obstacles. Please provide position:");
                break;
            case "move knight":
                output.println("Moving knight. Please provide new position:");
                break;
            case "show attacks":
                output.println("Attacked fields:");
                break;
            case "save to file":
                output.println("Saving chessboard to file.");
                fileManager.save();
                break;
            case "load from file":
                output.println("Loading chessboard from file.");
                fileManager.load();
                break;
            case "exit":
                System.exit(0);
            default:
                output.println("Unknown command.");
                break;
        }
    }
}
