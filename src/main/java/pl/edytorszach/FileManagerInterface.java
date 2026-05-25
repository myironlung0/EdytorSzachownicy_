package pl.edytorszach;

public interface FileManagerInterface {
    void save(String path, ChessBoard board);
    ChessBoard load(String path, KnightInterface knight);
}