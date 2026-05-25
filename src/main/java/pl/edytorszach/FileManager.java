package pl.edytorszach;


import java.awt.*;
import java.io.*;
import java.util.Set;
import java.util.StringJoiner;

public class FileManager implements FileManagerInterface {

    @Override
    public void save(String path, ChessBoard board){
        try(BufferedWriter bwriter = new BufferedWriter(new FileWriter(path))){
            bwriter.write("n=" + board.getN());
            bwriter.newLine();

            Point knightPos = board.getKnightPosition();
            if(knightPos != null){
                bwriter.write("knight="+ knightPos.x + "," + knightPos.y);
            }else{
                bwriter.write("knight=null");
            }
            bwriter.newLine();

            bwriter.write("obstacles=");
            Set<Point> obstaclesPos = board.getObstacles();
            StringJoiner sj = new StringJoiner(";");
            for(Point obstacle : obstaclesPos){
                sj.add(obstacle.x + "," + obstacle.y);
            }
            bwriter.write(sj.toString());
            bwriter.newLine();

        }catch (IOException e){
            System.err.println("ERROR: Could not write to file " + path);
        }
    }

    @Override
    public ChessBoard load(String path, KnightInterface knight){
        try(BufferedReader breader = new BufferedReader(new FileReader(path))){
            String[] sizeLines = breader.readLine().split("=");
            int boardSize = Integer.parseInt(sizeLines[1]);

            ChessBoard board = new ChessBoard(boardSize, knight);
            String[] knightLines = breader.readLine().split("=");
            if(!knightLines[1].equals("null")){
                String[] coords = knightLines[1].split(",");
                board.placeKnight(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
            }

            String[] obstacleLines = breader.readLine().split("="); // obstacles=x,y;x1,y1;...
            if(!obstacleLines[1].isEmpty()){
                for(String points : obstacleLines[1].split(";")){
                    String[] obCoords = points.split(",");
                    board.addObstacle(Integer.parseInt(obCoords[0]), Integer.parseInt(obCoords[1]));
                }
            }

            return board;
        }catch (IOException e){
            System.err.println("ERROR: Could not load from file " + path);
            return null;
        }
    }
}
