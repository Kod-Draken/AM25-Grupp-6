package se.yrgo.g6.jumpybirb;

public enum DifficultyModifer {
    EASY (1f),
    MEDIUM (2f),
    HARD (3f);

    private final float VALUE;

    private DifficultyModifer(float VALUE) {
        this.VALUE = VALUE;
    }
}
