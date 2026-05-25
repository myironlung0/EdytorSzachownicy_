package pl.edytorszach;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ChessBoardTest {

    @Test
    void shouldPlaceKnight() {
        FakeKnight fakeKnight = new FakeKnight();
        ChessBoard board = new ChessBoard(fakeKnight);

        board.placeKnight(2,3);

        assertEquals(new Point(2,3), board.getKnightPosition());
    }

    @Test
    void shouldThrowExceptionWhenKnightOutsideBoard(){
        FakeKnight fakeKnight = new FakeKnight();
        ChessBoard board = new ChessBoard(fakeKnight);

        assertThrows(IllegalArgumentException.class, () -> board.placeKnight(0,-4));
    }

    @Test
    void shouldReturnAttackedFieldsFromKnight(){
        Set<Point> attacks = Set.of(new Point(1,2), new Point(2,1));
        FakeKnight fakeKnight = new FakeKnight(attacks);
        ChessBoard board = new ChessBoard(fakeKnight);
        board.placeKnight(3,3);

        Set<Point> result = board.getAttackedFields();

        assertEquals(attacks, result);
    }
}
