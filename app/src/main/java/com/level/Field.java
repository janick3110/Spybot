package com.level;

import com.pawns.PawnSegment;

/**
 *  Class representing one field of the game board.
 */
public class Field{

    public final short x;
    public final short y;

    public Board board;

    private Highlighting highlighting = Highlighting.Empty;

    public final int background;

    //active or inactive
    private boolean enabled;

    //id of button
    private final int id;

    // Segment of a pawn
    private PawnSegment segment = null;

    public PawnSegment getSegment() {
        return segment;
    }

    public void setSegment(PawnSegment segment) {
        this.segment = segment;
    }


    public Field(int id, boolean enabled, short x, short y, int background, Board board) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.enabled = enabled;
        this.background = background;
        this.board = board;
    }

    public boolean getStatus() {
        return enabled;
    }

    public void setStatus(boolean newStatus) {
        this.enabled = newStatus;
    }

    public int getId() {return id; }

    public Highlighting getHighlighting() {
        return highlighting;
    }

    public void setHighlighting(Highlighting highlighting) {
        this.highlighting = highlighting;
    }
}
