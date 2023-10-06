package com.GrowthPlus;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.GrowthPlus.utilities.MathOperation;
import com.GrowthPlus.utilities.MathProblemGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

@RunWith(AndroidJUnit4.class)
public class MathProblemGeneratorTest {
    @Test
    public void testGenerateRandomMathProblem() {
        MathProblemGenerator generator = new MathProblemGenerator();
        String[] mathProblem = generator.generateMathProblem(MathOperation.ADDITION);
        Log.i("MATH PROBLEM ADDITION", Arrays.toString(mathProblem));
        //TODO: ASSERT THE RESULT OF THE MATH PROBLEM
    }
}
