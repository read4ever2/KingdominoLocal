public enum SideType {
    WHEATFIELD("WHEATFIELD"), FOREST("FOREST"), LAKE("LAKE"), GRASSLAND("GRASSLAND"), SWAMP("SWAMP"), MINE("MINE"), CASTLE("CASTLE"), EMPTY("EMPTY");

    private String sideDesc;

    SideType(String sideDesc) {
        this.sideDesc = sideDesc;
    }

    public String getSideDesc() {
        return this.sideDesc;
    }
}