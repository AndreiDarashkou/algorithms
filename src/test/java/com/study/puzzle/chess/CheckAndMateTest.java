package com.study.puzzle.chess;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CheckAndMateTest {

    @Test
    public void test_isCheck_PawnThreatensKing() {
        PieceConfig[] game = new PieceConfig[]{new PieceConfig("king", 1, 4, 0),
                new PieceConfig("king", 0, 4, 7),
                new PieceConfig("pawn", 1, 5, 6)};
        Set<PieceConfig> expected = new HashSet<>(Collections.singletonList(game[2]));
        assertEquals("Pawn threatens king", expected, CheckAndMate.isCheck(game, 0));
    }

    @Test
    public void test_isCheck_RookThreatensKing() {
        PieceConfig[] game = new PieceConfig[]{new PieceConfig("king", 1, 4, 0),
                new PieceConfig("king", 0, 4, 7),
                new PieceConfig("rook", 1, 4, 1)};
        Set<PieceConfig> expected = new HashSet<PieceConfig>(Collections.singletonList(game[2]));
        assertEquals("Rook threatens king", expected, CheckAndMate.isCheck(game, 0));
    }

    @Test
    public void test_isCheck_KnightThreatensKing() {
        PieceConfig[] game = new PieceConfig[]{new PieceConfig("king", 1, 4, 0),
                new PieceConfig("king", 0, 4, 7),
                new PieceConfig("knight", 1, 2, 6)};
        Set<PieceConfig> expected = new HashSet<>(Collections.singletonList(game[2]));
        assertEquals("Knight threatens king", expected, CheckAndMate.isCheck(game, 0));
    }

    @Test
    public void test_isCheck_BishopThreatensKing() {
        PieceConfig[] game = new PieceConfig[]{new PieceConfig("king", 1, 4, 0),
                new PieceConfig("king", 0, 4, 7),
                new PieceConfig("bishop", 1, 0, 3)};
        Set<PieceConfig> expected = new HashSet<>(Collections.singletonList(game[2]));
        assertEquals("Bishop threatens king", expected, CheckAndMate.isCheck(game, 0));
    }

    @Test
    public void test_isCheck_QueenThreatensKing1() {
        PieceConfig[] game = new PieceConfig[]{new PieceConfig("king", 1, 4, 0),
                new PieceConfig("king", 0, 4, 7),
                new PieceConfig("queen", 1, 4, 1)};
        Set<PieceConfig> expected = new HashSet<>(Collections.singletonList(game[2]));
        assertEquals("Queen threatens king", expected, CheckAndMate.isCheck(game, 0));
    }

    @Test
    public void test_isCheck_QueenThreatensKing2() {
        PieceConfig[] game = new PieceConfig[]{new PieceConfig("king", 1, 4, 0),
                new PieceConfig("king", 0, 4, 7),
                new PieceConfig("queen", 1, 7, 4)};
        Set<PieceConfig> expected = new HashSet<>(Collections.singletonList(game[2]));
        assertEquals("Queen threatens king", expected, CheckAndMate.isCheck(game, 0));
    }

    @Test
    public void test_isCheck_DoubleThreat() {
        PieceConfig[] game = new PieceConfig[]{new PieceConfig("king", 1, 4, 0),
                new PieceConfig("pawn", 0, 4, 6),
                new PieceConfig("pawn", 0, 5, 6),
                new PieceConfig("king", 0, 4, 7),
                new PieceConfig("bishop", 0, 5, 7),
                new PieceConfig("bishop", 1, 1, 4),
                new PieceConfig("rook", 1, 2, 7, 2, 5)};
        Set<PieceConfig> expected = new HashSet<>(Arrays.asList(game[5], game[6]));
        assertEquals("Double threat", expected, CheckAndMate.isCheck(game, 0));
    }

    @Test
    public void test_isCheck_DoubleThreat2() {
        PieceConfig[] game = new PieceConfig[]{
                new PieceConfig("pawn", 0, 6, 4),
                new PieceConfig("pawn", 0, 5, 5),
                new PieceConfig("pawn", 0, 3, 6),
                new PieceConfig("pawn", 0, 4, 6),
                new PieceConfig("pawn", 0, 7, 6),
                new PieceConfig("king", 0, 4, 7),
                new PieceConfig("bishop", 0, 5, 7),
                new PieceConfig("knight", 0, 6, 7),
                new PieceConfig("queen", 1, 7, 4, 3, 0),
                new PieceConfig("king", 1, 4, 0)};
        Set<PieceConfig> expected = new HashSet<>(Collections.singletonList(game[8]));
        assertEquals("Double threat", expected, CheckAndMate.isCheck(game, 0));
    }

    @Test
    void isMate() {
        PieceConfig[] game = new PieceConfig[]{
                new PieceConfig("king", 1, 4, 0),
                new PieceConfig("bishop", 1, 1, 4),
                new PieceConfig("queen", 1, 0, 7),
                new PieceConfig("pawn", 0, 4, 6),
                new PieceConfig("pawn", 0, 5, 6),
                new PieceConfig("rook", 0, 1, 7),
                new PieceConfig("king", 0, 4, 7),
                new PieceConfig("rook", 0, 5, 7),
                new PieceConfig("rook", 1, 3, 4)};
        assertTrue(CheckAndMate.isMate(game, 0));
    }

    @Test
    void isMate2() {
        PieceConfig[] game = new PieceConfig[]{
                new PieceConfig("king", 1, 4, 0),
                new PieceConfig("pawn", 0, 4, 6),
                new PieceConfig("pawn", 0, 5, 6),
                new PieceConfig("king", 0, 4, 7),
                new PieceConfig("rook", 0, 5, 7),
                new PieceConfig("rook", 1, 3, 7)};
        assertFalse(CheckAndMate.isMate(game, 0));
    }

    @Test
    void isMate3() {
        PieceConfig[] game = new PieceConfig[]{
                new PieceConfig("king", 1, 4, 0),
                new PieceConfig("bishop", 1, 1, 4),
                new PieceConfig("queen", 1, 0, 7),
                new PieceConfig("pawn", 0, 4, 6),
                new PieceConfig("pawn", 0, 5, 6),
                new PieceConfig("rook", 0, 1, 7),
                new PieceConfig("bishop", 0, 3, 7),
                new PieceConfig("king", 0, 4, 7),
                new PieceConfig("rook", 0, 5, 7)};
        assertFalse(CheckAndMate.isMate(game, 0));
    }

    @Test
    void isMate4() {
        PieceConfig[] game = new PieceConfig[]{
                new PieceConfig("king", 1, 4, 0),
                new PieceConfig("bishop", 1, 1, 4),
                new PieceConfig("queen", 1, 0, 7),
                new PieceConfig("pawn", 0, 4, 6),
                new PieceConfig("pawn", 0, 5, 6),
                new PieceConfig("bishop", 0, 3, 7),
                new PieceConfig("king", 0, 4, 7),
                new PieceConfig("queen", 0, 5, 7),
                new PieceConfig("pawn", 0, 2, 6)
        };
        assertFalse(CheckAndMate.isMate(game, 0));
    }

    @Test
    void knightShouldInterceptByMoving() {
        PieceConfig[] game = new PieceConfig[]{
                new PieceConfig("king", 1, 4, 0),
                new PieceConfig("bishop", 1, 1, 4),
                new PieceConfig("queen", 1, 0, 7),
                new PieceConfig("pawn", 0, 4, 6),
                new PieceConfig("pawn", 0, 5, 6),
                new PieceConfig("knight", 0, 1, 7),
                new PieceConfig("bishop", 0, 3, 7),
                new PieceConfig("king", 0, 4, 7),
                new PieceConfig("rook", 0, 5, 7),
        };
        assertFalse(CheckAndMate.isMate(game, 0));
    }

    @Test
    void isMate6() {
        PieceConfig[] game = new PieceConfig[]{
                new PieceConfig("king", 1, 4, 0),
                new PieceConfig("bishop", 1, 1, 4),
                new PieceConfig("queen", 1, 0, 7),
                new PieceConfig("pawn", 0, 4, 6),
                new PieceConfig("pawn", 0, 5, 6),
                new PieceConfig("knight", 0, 1, 7),
                new PieceConfig("bishop", 0, 3, 7),
                new PieceConfig("king", 0, 4, 7),
                new PieceConfig("rook", 0, 5, 7),
        };
        assertFalse(CheckAndMate.isMate(game, 0));
    }

    @Test
    void pawnShouldInterceptByDoubleMoving() {
        PieceConfig[] game = new PieceConfig[]{
                new PieceConfig("king", 1, 4, 0),
                new PieceConfig("bishop", 1, 0, 3),
                new PieceConfig("queen", 1, 0, 7),
                new PieceConfig("pawn", 0, 4, 6),
                new PieceConfig("pawn", 0, 5, 6),
                new PieceConfig("rook", 0, 3, 7),
                new PieceConfig("king", 0, 4, 7),
                new PieceConfig("bishop", 0, 5, 7),
                new PieceConfig("pawn", 0, 1, 6),
        };
        assertFalse(CheckAndMate.isMate(game, 0));
    }

    @Test
    void enPassant() {
        PieceConfig[] game = new PieceConfig[]{
                new PieceConfig("king", 1, 5, 3),
                new PieceConfig("pawn", 0, 4, 4, 4, 6),
                new PieceConfig("pawn", 0, 5,6),
                new PieceConfig("king", 0, 4, 7),
                new PieceConfig("knight", 0, 2, 5),
                new PieceConfig("pawn", 1, 3, 4),
                new PieceConfig("knight", 1, 3, 3),
                new PieceConfig("pawn", 1, 4, 3),
                new PieceConfig("bishop", 1, 4, 2),
                new PieceConfig("rook", 1, 5, 2),
                new PieceConfig("queen", 0, 6, 5),
        };
        assertFalse(CheckAndMate.isMate(game, 1));
    }

    /**
     * player: 0
     * piece: knight, owner: 0, x: 3, y: 6
     * piece: pawn, owner: 0, x: 4, y: 6
     * piece: pawn, owner: 0, x: 5, y: 6
     * piece: queen, owner: 0, x: 3, y: 7
     * piece: king, owner: 0, x: 4, y: 7
     * piece: bishop, owner: 0, x: 5, y: 7
     * piece: king, owner: 1, x: 4, y: 0
     * piece: queen, owner: 1, x: 4, y: 1
     * piece: knight, owner: 1, x: 3, y: 5, prevX: 2, prevY: 3
     */
    @Test
    void pinDoubleAttack() {
        PieceConfig[] game = new PieceConfig[]{
                new PieceConfig("knight", 0, 3, 6),
                new PieceConfig("pawn", 0, 4, 6),
                new PieceConfig("pawn", 0, 5,6),
                new PieceConfig("queen", 0, 3, 7),
                new PieceConfig("king", 0, 4, 7),
                new PieceConfig("bishop", 0, 5, 7),
                new PieceConfig("king", 1, 4, 0),
                new PieceConfig("queen", 1, 4, 1),
                new PieceConfig("knight", 1, 3, 5),
        };
        assertTrue(CheckAndMate.isMate(game, 0));
    }
}