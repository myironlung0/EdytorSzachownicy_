package pl.edytorszach;

import java.awt.*;
import java.util.Set;

public class FakeKnight implements KnightInterface{
    private Set<Point> attacksToReturn;

    public FakeKnight() {
        attacksToReturn = null;
    }

    public FakeKnight(Set<Point> attacksToReturn) {
        this.attacksToReturn = attacksToReturn;
    }

    @Override
    public Set<Point> calculateAttack(int n, int knightX, int knightY, Set<Point> przeszkody, Set<Point> lustra){
        return attacksToReturn;
    }

//    @Override
//    public Point calculateBouncePosition(int n, int knightX, int knightY, int dx, int dy){
//        return null;
//    }
//
//    @Override
//    public Point calculateMirrorPosition(int n, int lustroX, int lustroY, int dx, int dy, Set<Point> lustra, Set<Point> odwiedzone){
//        return null;
//    }

    public void setAttacksToReturn(Set<Point> attacksToReturn) {
        this.attacksToReturn = attacksToReturn;
    }

    public Set<Point> getAttacksToReturn() {
        return attacksToReturn;
    }
}
