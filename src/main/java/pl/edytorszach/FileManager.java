package pl.edytorszach;


import java.io.*;

public class FileManager {


    public void save(String path, ChessBoard board){
        try(BufferedWriter bwriter = new BufferedWriter(new FileWriter(path))){

        }catch (IOException e){
            System.err.println("ERROR: Could not write to file " + path);
        }
    }

    public void load(String path, ChessBoard board){
        try(BufferedReader breader = new BufferedReader(new FileReader(path))){

        }catch (IOException e){
            System.err.println("ERROR: Could not load from file " + path);
        }
    }
}
