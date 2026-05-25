package pl.edytorszach;

public class FakeFileManager implements FileManagerInterface{
    private ChessBoard savedBoard;
    private ChessBoard boardToLoad;

    private String savedPath;

    @Override
    public void save(String path, ChessBoard board) {
        this.savedPath = path;
        this.savedBoard = board;
    }

    @Override
    public ChessBoard load(String path, KnightInterface knight) {
        return boardToLoad;
    }

    public void setBoardToLoad(ChessBoard board) {
        this.boardToLoad = board;
    }

    public ChessBoard getSavedBoard() {
        return savedBoard;
    }

    public String getSavedPath() {
        return savedPath;
    }

}
