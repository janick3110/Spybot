package com.level;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.example.spybot.R;
import com.model.AdjacencyList;
import com.model.SelectedInfo;
import com.model.LevelState;
import com.pawns.BodyType;
import com.pawns.Bug;
import com.pawns.Dumbbell;
import com.pawns.Pawn;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Board {

    int idCount = 0;

    public ArrayList<Pawn> pawnsOnBoard = new ArrayList<>();
    public ArrayList<Pawn> pawnsInTeam1 = new ArrayList<>();
    public ArrayList<Pawn> pawnsInTeam2 = new ArrayList<>();

    public byte currentPlayer = 0;
    public LevelState currentState = LevelState.Preparation;

    private int sizeY; //vertical axis
    private int sizeX; //horizontal axis


    private Field[][] board;
    private AdjacencyList<Field> graph;


    private SelectedInfo selectedInfo = new SelectedInfo();

    Drawable[] backgrounds;

    Resources r;

    public Board(int[][] level) {

        initBoard(level);

        initGraph();
    }

    public AdjacencyList<Field> getGraph() {
        return graph;
    }

    private void initBoard(int[][] fieldDef) {
        sizeY = fieldDef.length;
        sizeX = fieldDef[0].length;

        board = new Field[sizeX][sizeY];

        for (short y = 0; y < sizeY; y++) {
            for (short x = 0; x < sizeX; x++) {
                board[x][y] = getField(fieldDef[y][x],x,y);
            }
        }
    }


    /**
     * Initalisation of a field.
     * @param value Field definition as specified in Level Data
     * @param x coordinate on board
     * @param y coordinate on board
     * @return New field with specified data like background, highlighting,
     */
    private Field getField(int value, short x, short y) {
        Field outField = null;

        boolean enabled = (value & 0x000F) % 2 == 1;
        int background = (value & 0x000F) / 2;

        switch (background) {
            case 0:
                background = R.drawable.field_clean;
                break;
            case 1:
                background = R.drawable.field_classroom;
                break;
            case 2:
                background = R.drawable.field_tiled;
                break;

            default:
                throw new NoSuchElementException("Error, background " + background + " not implemented yet!");
        }
        outField = new Field(idCount, enabled, x, y, background);

        int player = (value & 0x00F0)>>4;

        switch (player) {
            case 1:
                outField.setHighlighting(Highlighting.SpawnableP1);
                break;
            case 2:
                outField.setHighlighting(Highlighting.SpawnableP2);
                break;
            default:

        }

        idCount++;
        return outField;
    }


    private void initGraph() {
        graph = new AdjacencyList<>();

        int top, bottom, left, right;
        Field middle, neighbour;

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                middle = board[x][y];
                if (middle.getStatus()) {
                    graph.addVertex(middle);

                    top = y - 1;
                    if (top >= 0 && top < sizeY) {
                        neighbour = board[x][top];
                        if (neighbour.getStatus()) {
                            graph.addEdge(middle, neighbour);
                        }
                    }

                    bottom = y + 1;
                    if (bottom >= 0 && bottom < sizeY) {
                        neighbour = board[x][bottom];
                        if (neighbour.getStatus()) {
                            graph.addEdge(middle, neighbour);
                        }
                    }

                    left = x - 1;
                    if (left >= 0 && left < sizeX) {
                        neighbour = board[left][y];
                        if (neighbour.getStatus()) {
                            graph.addEdge(middle, neighbour);
                        }
                    }

                    right = x + 1;
                    if (right >= 0 && right < sizeX) {
                        neighbour = board[right][y];
                        if (neighbour.getStatus()) {
                            graph.addEdge(middle, neighbour);
                        }
                    }
                }
            }
        }
    }

    public Field getField(short x, short y) {
        if(x >= 0 && x < sizeX && y >= 0 && y < sizeY) {
            return board[x][y];
        } else{
            return null;
        }
    }

    public Field getFieldById(int id) {
        return board[id % sizeX][id / sizeX];
    }

    public int getXofField(int id) {
        return 0;
    }

    public int getSizeX() {
        return sizeX;
    }


    public int getSizeY() {
        return sizeY;
    }

    public LevelState getState() {
        return currentState;
    }

    public void setState(LevelState state) {
        this.currentState = state;
    }

    public SelectedInfo getSelectedInfo() {
        return selectedInfo;
    }

    public void updateSelectedInfo() {
        selectedInfo.setFieldId(selectedInfo.getPawn().getSegments().getFirst().getField().getId());
    }
}
