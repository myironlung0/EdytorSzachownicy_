package pl.edytorszach;

import java.awt.*;
import java.util.Set;

public interface KnightInterface {
    public Set<Point> calculateAttack(int n, int knightX, int knightY, Set<Point> przeszkody, Set<Point> lustra);
//    public Point calculateBouncePosition(int n, int knightX, int knightY, int dx, int dy);
//    public Point calculateMirrorPosition(int n, int lustroX, int lustroY, int dx, int dy, Set<Point> lustra, Set<Point> odwiedzone);
}
