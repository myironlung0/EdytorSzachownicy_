package pl.edytorszach;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ChessBoard {
    private int n;
    private KnightInterface knight;
    private Point knightPosition;   // pozycja skoczka, null jesli nie ustawiony
    private Set<Point> przeszkody;
    private Set<Point> lustra;

    public ChessBoard(int n, KnightInterface knight) {
        this.n = n;
        this.knight = knight;
        this.przeszkody = new HashSet<>();
        this.lustra = new HashSet<>();
        this.knightPosition = null;
    }

    public void placeKnight(int x, int y){
        // TODO: throw exceptions when pozycja out of bounds or pozycja contains przeszkoda
        this.knightPosition = new Point(x, y);
    }

    public Set<Point> getAttackedFields(){
        return knight.calculateAttack(n, knightPosition.x, knightPosition.y, przeszkody, lustra);
    }

    public void addObstacle(int x, int y){
        przeszkody.add(new Point(x,y));
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

    public Set<Point> getPrzeszkody() {
        return przeszkody;
    }

    public Set<Point> getLustra() {
        return lustra;
    }

    public void setPrzeszkody(Set<Point> przeszkody) {
        this.przeszkody = przeszkody;
    }
}
