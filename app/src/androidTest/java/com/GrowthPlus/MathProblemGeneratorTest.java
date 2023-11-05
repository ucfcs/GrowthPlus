package com.GrowthPlus;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.GrowthPlus.utilities.Difficulty;
import com.GrowthPlus.utilities.MathOperation;
import com.GrowthPlus.utilities.MathProblemGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MathProblemGeneratorTest {
    int num1;
    int num2;
    int testResult;
    @Test
    public void testGenerateRandomMathProblem() {
        MathProblemGenerator generator = new MathProblemGenerator();
        /*
        * Test addition
        * */
        generator.generateMathProblem(MathOperation.ADDITION, Difficulty.LEVEL_ONE);
        num1 = generator.getNumOne();
        num2 = generator.getNumTwo();
        testResult = num1 + num2;
        assertEquals(testResult, generator.getResult());

    }

    @Test
    public void testGenerateSubtractionProblem(){
        MathProblemGenerator generator = new MathProblemGenerator();
        /*
         * Test subtraction
         * */
        generator.generateMathProblem(MathOperation.SUBTRACTION, Difficulty.LEVEL_ONE);
        num1 = generator.getNumOne();
        num2 = generator.getNumTwo();
        testResult = num1 - num2;
        assertEquals(testResult, generator.getResult());
    }
}
