package com.study.puzzle.chess;

public class PieceConfig {

    private String piece;
    private int owner;
    private int x;
    private int y;
    private Integer prevX;
    private Integer prevY;

    public PieceConfig(final String piece, final int owner, final int x, final int y) {
        this.piece = piece;
        this.owner = owner;
        this.x = x;
        this.y = y;
    }

    public PieceConfig(final String piece, final int owner, final int x, final int y, final int prevX, final int prevY) {
        this(piece, owner, x, y);
        this.prevX = prevX;
        this.prevY = prevY;
    }

    public String getPiece() {
        return piece;
    }

    public int getOwner() {
        return owner;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean hasPrevious() {
        return prevX != null && prevY != null;
    }

    public int getPrevX() {
        if (!hasPrevious()) {
            throw new RuntimeException();
        }
        return prevX;
    }

    public int getPrevY() {
        if (!hasPrevious()) {
            throw new RuntimeException();
        }
        return prevY;
    }
}
