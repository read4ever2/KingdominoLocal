/**
 * Class: CMSC495
 * Date: 23 AUG 2023
 * Creator: Alan Anderson
 * Team Members: Alan Anderson, William Feighner, Michael Wood Jr., Ibadet Mijit, Jenna Seipel
 * File: GameBoard.java
 * Description: This java file contains the GameBoard class that represents the players game board in a game of
 * Kingdomino. The board is laid out in a grid upon which domino tiles will be placed. The game board is scored
 * at the end of the game determine the game's winner.
 */

import java.util.ArrayList;

public class GameBoard {

    // attributes
    private final int WIDTH = 5; // max width of game board
    private final int HEIGHT = 5; // max height of game board
    private Space[][] spaces; // representation of grid spaces on game board
    private ArrayList<ArrayList<Space>> scoredSpaces; // array list of spaces scored
    private int scoredSpacesCurrentLevel = 0; // current scoring level; used to group spaces for scoring
    private int currentScore = 0; // current score for the game board

    // constructor
    public GameBoard() {
        spaces = new Space[WIDTH][HEIGHT]; // create matrix of spaces based on max height and width
        for (int i = 0; i < WIDTH; i++) {
            for (int k = 0; k < HEIGHT; k++) {
                if (i == (WIDTH / 2) && (k == (HEIGHT / 2))) {
                    spaces[i][k] = new Space(LandType.CASTLE, i, k); // Place starting castle in center of game board
                } else {
                    spaces[i][k] = new Space(LandType.EMPTY, i, k); // set each space on board to empty type
                }
            }
        }
    }

    // methods

    // resets scored flag of each game board space to false; called after checking scoring
    public void resetSpacesScoredBooleanCheck() {
        for (int i = 0; i < WIDTH; i++) {
            for (int k = 0; k < HEIGHT; k++) {
                spaces[i][k].setScored(false);
            }
        }
    }

    // calculates current game board score through recursively calling scoreAll(x, y); returns score
    public void calculateCurrentScore(boolean debug) {
        scoredSpaces = new ArrayList<ArrayList<Space>>(); // reset array list
        scoredSpacesCurrentLevel = 0; // reset scoring level
        for (int k = 0; k < WIDTH; k++) {
            for (int i = 0; i < HEIGHT; i++) {
                if (!this.getGameBoardSpace(i, k).getScored()) {
                    scoredSpaces.add(new ArrayList<Space>());
                    //System.out.println("Size: " + scoredSpaces.size());
                    scoreAll(i, k);
                    scoredSpacesCurrentLevel++;
                }
            }
        }

        if (debug) this.debugGameBoardPrint(); // print game board tile info if in debug mode

        int totalScore = 0; // capture total score
        for (ArrayList<Space> a : scoredSpaces) { // for each group of tiles grouped together in a score group
            int totalCrowns = 0; // prepare to capture the total number of crowns
            int totalSpaces = a.size(); // capture the total number of spaces in this group
            for (Space s : a) {
                totalCrowns += s.getNumCrowns(); // get all crowns from each space in this score group
            }
            totalScore += totalCrowns * totalSpaces; // multiply crowns and spaces in group; add to total score
        }

        this.currentScore = totalScore; // set this game board's score as the calculated score
        this.resetSpacesScoredBooleanCheck(); // reset "scored" flag on each game board space
    }

    // returns the current score of this game board
    public int getCurrentScore() {
        calculateCurrentScore(false);
        return this.currentScore;
    }

    // returns the current score of this game board and extra debug information
    public int getCurrentScoreDebug() {
        calculateCurrentScore(true);
        return this.currentScore;
    }

    // debug printing to show game board tile groupings
    public void debugGameBoardPrint() {
        int groupNumber = 1; // current tile score group #
        for (ArrayList<Space> a : scoredSpaces) { // for each group of score group tiles
            int totalCrowns = 0; // prepare to capture the total crowns
            int totalSpaces = a.size(); // capture the total number of spaces in this score group
            System.out.println("Group # " + groupNumber); // print debug info
            for (Space s : a) { // then for each space in this score group
                System.out.println(s.toString() + "(" + s.getXLoc() + "," + s.getYLoc() + ")");
                totalCrowns += s.getNumCrowns(); // print info and add crowns to total crowns
            }
            System.out.println("Group Score: " + (totalCrowns * totalSpaces));
            System.out.println("");
            groupNumber++; // increase group number for print purposes
        }
    }

    public void scoreAll(int startX, int startY) {
        // if space to be scored is outside of game board, don't check it
        if (startX > WIDTH - 1 || startX < 0 || startY > HEIGHT - 1 || startY < 0) {
            return;
        }

        // if space is already scored, don't check it
        if (this.getGameBoardSpace(startX, startY).getScored()) {
            return;
        }

        // if this space type is different than other space types in this score group, don't check it
        for (Space s : scoredSpaces.get(scoredSpacesCurrentLevel)) {
            if (scoredSpaces.get(scoredSpacesCurrentLevel).size() > 0 &&
                    this.getGameBoardSpace(startX, startY).getSType() != s.getSType()) {
                return;
            }
        }

        this.getGameBoardSpace(startX, startY).setScored(true); // flag this space as scored
        // add this space to the current score group
        this.scoredSpaces.get(scoredSpacesCurrentLevel).add(this.getGameBoardSpace(startX, startY));
        // then spread out and check each space above, below, to the left and right of this space
        this.scoreAll(startX + 1, startY);
        this.scoreAll(startX, startY + 1);
        this.scoreAll(startX - 1, startY);
        this.scoreAll(startX, startY - 1);
    }

    // getters
    public Space[][] getGameBoardSpaces() {
        return spaces;
    }

    public Space getGameBoardSpace(int xLoc, int yLoc) {
        return spaces[xLoc][yLoc];
    }

    public int getWidth() {
        return this.WIDTH;
    }

    public int getHeight() {
        return this.HEIGHT;
    }

    // subclasses
    class Space {
        private LandType sType; // type of land on this space
        private int xLoc; // x location of this space on game board
        private int yLoc; // y location of this sapce on game board
        private int numCrowns; // number of crowns this space has
        private boolean scored; // whether this space has been scored

        public Space(LandType sType, int xLoc, int yLoc) {
            this.setXLoc(xLoc);
            this.setYLoc(yLoc);
            this.setSType(sType);
            this.setNumCrowns(0);
            this.setScored(false);
        }

        // getters
        public int getXLoc() {
            return this.xLoc;
        }

        public int getYLoc() {
            return this.yLoc;
        }

        public LandType getSType() {
            return sType;
        }

        public int getNumCrowns() {
            return this.numCrowns;
        }

        public boolean getScored() {
            return this.scored;
        }

        // setters
        public void setXLoc(int xLoc) {
            this.xLoc = xLoc;
        }

        public void setYLoc(int yLoc) {
            this.yLoc = yLoc;
        }

        public void setSType(LandType sType) {
            this.sType = sType;
        }

        public void setNumCrowns(int numCrowns) {
            this.numCrowns = numCrowns;
        }

        public void setScored(boolean scored) {
            this.scored = scored;
        }

        @Override
        public String toString() {
            return this.getSType().getInitialName() + this.getNumCrowns() + "C";
        }
    }
}