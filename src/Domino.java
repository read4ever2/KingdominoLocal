/**
 * Class: CMSC495
 * Date: 23 AUG 2023
 * Creator: Alan Anderson
 * Team Members: Alan Anderson, William Feighner, Michael Wood Jr., Ibadet Mijit, Jenna Seipel
 * File: Domino.java
 * Description: The Domino class represents a domino piece in a game of Kingdomino. It is two-sided, and each side
 * has one of the 6 land types and between 0 and 3 crowns. These pieces interact with each other on the same game
 * board, increasing final scoring points for each piece of the same type that are adjacent to each other. The
 * more crowns and same types of pieces adjacent in a group, the higher score for that group of dominos. In this
 * text version, information from the Domino class is transferred to the GameBoard class, which does the scoring.
 */

public class Domino {

    // attributes
    private Side sideA, sideB; // both sides of the two-sided domino
    private int dominoNumber; // the unique number of each domino (48 total)
    private int playerOrder; // helps maintain player order after domino selection

    // constructor
    public Domino(int dominoNumber, LandType sTypeA, int numCrownsA, LandType sTypeB, int numCrownsB) {
        this.setDominoNumber(dominoNumber);
        sideA = new Side(sTypeA, numCrownsA);
        sideB = new Side(sTypeB, numCrownsB);
        this.playerOrder = 0;
    }

    // setters
    public void setDominoNumber(int dominoNumber) {
        this.dominoNumber = dominoNumber;
    }

    public void setPlayerOrder(int playerOrder) {
        this.playerOrder = playerOrder;
    }

    public int getDominoNumber() {
        return this.dominoNumber;
    }

    // getters
    public String getSideAFullName() {
        return sideA.sType.getFullName();
    }

    public String getSideBFullName() {
        return sideB.sType.getFullName();
    }

    public int getSideACrowns() {
        return sideA.getNumCrowns();
    }

    public int getSideBCrowns() {
        return sideB.getNumCrowns();
    }

    public Side getSideA() {
        return this.sideA;
    }

    public Side getSideB() {
        return this.sideB;
    }

    public int getPlayerOrder() {
        return this.playerOrder;
    }


    @Override
    public String toString() {
        String desc = "Domino # " + String.valueOf(getDominoNumber() + " "
                + getSideAFullName() + "(" + getSideACrowns() + "C) | "
                + getSideBFullName() + "(" + getSideBCrowns() + "C)");
        return desc;

    }

    // this method generates a string representation of the info on domino side a
    public String sideAToString() {
        StringBuilder desc = new StringBuilder();
        desc.append(this.sideA.getSType().toString().substring(0, 1));
        desc.append(this.sideA.getNumCrowns() + "C");
        return desc.toString();
    }

    // this method generates a string representation of the info on domino side b
    public String sideBToString() {
        StringBuilder desc = new StringBuilder();
        desc.append(this.sideB.getSType().toString().substring(0, 1));
        desc.append(this.sideB.getNumCrowns() + "C");
        return desc.toString();
    }

    // subclasses
    class Side { // represents a side of the two-sided domino

        //attributes
        private int numCrowns; // number of crowns on domino side
        private int xLoc, yLoc; // x and y location of domino side as it relates to the game board
        private LandType sType; // land type

        // constructor
        public Side(LandType sType, int numCrowns) {
            this.setSType(sType);
            this.setNumCrowns(numCrowns);
            this.setXLoc(0);
            this.setYLoc(0);
        }

        // setters
        public void setSType(LandType sType) {
            this.sType = sType;
        }

        public void setNumCrowns(int numCrowns) {
            this.numCrowns = numCrowns;
        }

        public void setXLoc(int xLoc) {
            this.xLoc = xLoc;
        }

        public void setYLoc(int yLoc) {
            this.yLoc = yLoc;
        }

        // getters
        public int getNumCrowns() {
            return this.numCrowns;
        }

        public LandType getSType() {
            return this.sType;
        }

        public int getXLoc() {
            return this.xLoc;
        }

        public int getYLoc() {
            return this.yLoc;
        }
    }
}