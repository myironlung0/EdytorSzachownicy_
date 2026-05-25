package pl.edytorszach;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ChessBoard {
    private int n;
    private KnightInterface knight;
    private Point knightPosition;   // pozycja skoczka, null jesli nie ustawiony
    private Set<Point> obstacles;
    private Set<Point> mirrors;

    public ChessBoard(KnightInterface knight) {
        this.n = 8;
        this.knight = knight;
        this.obstacles = new HashSet<>();
        this.mirrors = new HashSet<>();
        this.knightPosition = null;
    }

    public ChessBoard(int n, KnightInterface knight) {
        this.n = n;
        this.knight = knight;
        this.obstacles = new HashSet<>();
        this.mirrors = new HashSet<>();
        this.knightPosition = null;
    }

    public void placeKnight(int x, int y){
        if(!isInsideBoard(x, y)){
            throw new IllegalArgumentException("Can't place knight outside the board.");
        }

        Point p = new Point(x,y);

        if(obstacles.contains(p)){
            throw new IllegalArgumentException("Field is occupied by an obstacle.");
        }

        this.knightPosition = p;
    }

    public Set<Point> getAttackedFields(){
        if(knightPosition == null){
            throw new IllegalStateException("Knight is not placed.");
        }

        return knight.calculateAttack(n, knightPosition.x, knightPosition.y, obstacles, mirrors);
    }

    public void addObstacle(int x, int y){
        if(!isInsideBoard(x,y)){
            throw new IllegalArgumentException("Can't place obstacle outside the board.");
        }
        Point p = new Point(x,y);

        if(p.equals(knightPosition)){
            throw new IllegalArgumentException("Field is occupied by knight.");
        }

        obstacles.add(p);
    }

//    GETTERS AND SETTERS
    public int getN() {
        return n;
    }

    public KnightInterface getKnight() {
        return knight;
    }

    public Point getKnightPosition() {
        return knightPosition;
    }

    public Set<Point> getObstacles() {
        return obstacles;
    }

    public Set<Point> getMirrors() {
        return mirrors;
    }

    public void setObstacles(Set<Point> przeszkody) {
        this.obstacles = przeszkody;
    }

    private boolean isInsideBoard(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
