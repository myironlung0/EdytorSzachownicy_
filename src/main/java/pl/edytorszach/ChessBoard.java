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

    public ChessBoard(int n, KnightInterface knight) {
        this.n = n;
        this.knight = knight;
        this.obstacles = new HashSet<>();
        this.mirrors = new HashSet<>();
        this.knightPosition = null;
    }

    public void placeKnight(int x, int y){
        // TODO: throw exceptions when pozycja out of bounds or pozycja contains przeszkoda
        this.knightPosition = new Point(x, y);
    }

    public Set<Point> getAttackedFields(){
        return knight.calculateAttack(n, knightPosition.x, knightPosition.y, obstacles, mirrors);
    }

    public void addObstacle(int x, int y){
        obstacles.add(new Point(x,y));
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
}
