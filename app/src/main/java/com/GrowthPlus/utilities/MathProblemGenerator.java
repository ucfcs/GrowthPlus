package com.GrowthPlus.utilities;

import java.util.Random;

public class MathProblemGenerator {
    private int numOne;
    private int numTwo;
    private int result;
    private Random random;
    private String[] mathProblem;
    private String problem;

    public MathProblemGenerator() {
        random = new Random();
        mathProblem = new String[5];
    }

    public int getNumOne() {
        return numOne;
    }

    public int getNumTwo() {
        return numTwo;
    }

    public int getResult() {
        return result;
    }

    public void generateMathProblem(MathOperation operation, Difficulty difficulty){
        switch (operation){
            case ADDITION:{
                numOne = random.nextInt(difficulty.getDifficultySeed()) + 1;
                numTwo = random.nextInt(difficulty.getDifficultySeed()) + 1;
                result = numOne + numTwo;
                problem = numOne + " + " + numTwo;
                break;
            }
            case SUBTRACTION:{
                numOne = random.nextInt(difficulty.getDifficultySeed()) + 1;
                numTwo = random.nextInt(numOne + 1);
                result = numOne - numTwo;
                problem = numOne + " - " + numTwo;
                break;
            }
            case DIVISION:{
                int tempOne = random.nextInt(difficulty.getDifficultySeed()) + 1;
                int tempTwo = random.nextInt(difficulty.getDifficultySeed()) + 1;

                numOne = tempOne * tempTwo;
                numTwo = tempOne;
                result = tempTwo;
                problem = numOne + " : " + numTwo;
                break;
            }
            case MULTIPLICATION:{
                numOne = random.nextInt(difficulty.getDifficultySeed()) + 1;
                numTwo = random.nextInt(difficulty.getDifficultySeed()) + 1;
                result = numOne * numTwo;
                problem = numOne + " * " + numTwo;
                break;
            }
        }
    }
}
