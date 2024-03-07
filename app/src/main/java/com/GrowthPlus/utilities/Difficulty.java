package com.GrowthPlus.utilities;

/*
* Any seed difficulty can be added here
* */
public enum Difficulty {
    DIVISION_LOW(4),
    LEVEL_ONE(9),
    LEVEL_TWO(19),
    LEVEL_THREE(49),
    LEVEL_FOUR(99);

    private final int difficultySeed;

    Difficulty(int difficultySeed) {
        this.difficultySeed = difficultySeed;
    }

    public int getDifficultySeed() {
        return difficultySeed;
    }
}
