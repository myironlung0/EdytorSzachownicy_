package pl.edytorszach;

// obsluga stdin stdout
// MAIN CLASS

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Set;

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

    public void run() {
        output.println("CHESSBOARD EDITOR. What do you want to do?:");
        output.println("Please choose one:\n" +
                "create_chessboard " + "[size] \n" +
                "add_obstacle " + "[x] [y] \n" +
                "move_knight " + "[x] [y] \n" +
                "show_attacks\n" +
                "save_to_file " + "[filepath] \n" +
                "load_from_file " + "[filepath] \n" +
                "exit");
        try {
            String line;
            while ((line = input.readLine()) != null) {
                processCommand(line.trim());
            }
        } catch (IOException e) {
            System.err.print("IO exception in editor.");
        }
    }

    private void processCommand(String userInput) {
        // TODO: co jesli probuje dodac obtacles bez create chessboard? czy knight powinien miec default placement?
        String[] fromInput = userInput.toLowerCase().split(" ");
        String command = fromInput[0];
        switch (command) {
            case "create_chessboard":
                board = new ChessBoard(Integer.parseInt(fromInput[1]), board.getKnight());
                break;
            case "add_obstacle":
                int x = Integer.parseInt(fromInput[1]);
                int y = Integer.parseInt(fromInput[2]);
                board.addObstacle(x, y);
                break;
            case "move_knight":
                int kX = Integer.parseInt(fromInput[1]);
                int kY = Integer.parseInt(fromInput[2]);
                board.placeKnight(kX, kY);
                break;
            case "show_attacks":
                if (board.getKnightPosition() == null) {
                    output.println("Error: place knight first (move_knight [x] [y])");
                    break;
                }
                Set<Point> attacked = board.getAttackedFields();
                output.println("Attacked fields:");
                for(Point p : attacked){ output.println("  (" + p.x + "," + p.y + ")"); }
                break;
            case "save_to_file":
                output.println("Saving chessboard to file...");
                fileManager.save(fromInput[1], board);
                break;
            case "load_from_file":
                output.println("Loading chessboard from file...");
                ChessBoard loaded = fileManager.load(fromInput[1], board.getKnight());
                if(loaded != null){
                    board = loaded;
                    output.println("Loaded from: " + fromInput[1]);
                } else {
                    output.println("Error: couldn't load from file.");
                }
                break;
            case "exit":
                //System.exit(0);
                output.println("CYA!");
                System.exit(0);
                break;
            default:
                output.println("Unknown command.");
                break;
        }
    }
}
