package com.GrowthPlus.utilities;

import java.util.Random;

public class MathProblemGenerator {
    private String numOne;
    private String operator;
    private String numTwo;
    private String equalSign;
    private Random random;
    private String[] mathProblem;

    public MathProblemGenerator() {
        random = new Random();
        mathProblem = new String[5];
        equalSign = "=";
    }

    @SuppressWarnings("DuplicateBranchesInSwitch")
    public String[] generateMathProblem(MathOperation operation){
        switch (operation){
            case ADDITION:{
                int num1 = random.nextInt(10); // Generates a random number between 0 and 10
                int num2 = random.nextInt(10);
                int result = num1 + num2;
                this.mathProblem[0] = String.valueOf(num1);
                this.mathProblem[1] = "+";
                this.mathProblem[2] = String.valueOf(num2);
                this.mathProblem[3] = "=";
                this.mathProblem[4] = String.valueOf(result);
                // generate addition
                break;
            }
            case SUBTRACTION:{
                int num1 = random.nextInt(10); // Generates a random number between 0 and 10
                int num2 = random.nextInt(num1 + 1); // Ensures second number is smaller than first so there is no negative number
                int result = num1 - num2;
                this.mathProblem[0] = String.valueOf(num1);
                this.mathProblem[1] = ":";
                this.mathProblem[2] = String.valueOf(num2);
                this.mathProblem[3] = "=";
                this.mathProblem[4] = String.valueOf(result);
                break;
            }
            case DIVISION:{
                // Need to think about this a little bit to make sure result is a whole number
                break;
            }
            case MULTIPLICATION:{
                int num1 = random.nextInt(10); // Generates a random number between 0 and 10
                int num2 = random.nextInt(10);
                int result = num1 * num2;
                this.mathProblem[0] = String.valueOf(num1);
                this.mathProblem[1] = "x";
                this.mathProblem[2] = String.valueOf(num2);
                this.mathProblem[3] = "=";
                this.mathProblem[4] = String.valueOf(result);
                break;
            }
        }
        return this.mathProblem;
    }
}
