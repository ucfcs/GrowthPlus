package com.GrowthPlus;


import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Intent;
import android.util.Log;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchema;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchemaService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.realm.Realm;
import io.realm.RealmResults;

//Run the app before testing so that it works :)
@RunWith(AndroidJUnit4.class)
public class CreatingParentTest {
    public Realm realm;

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void createAndCheckParent(){
        Espresso.onView(withId(R.id.frameLayoutForBtnID)).perform(click());

        Espresso.onView(withId(R.id.parentSignUpActivityID)).check(matches(isDisplayed()));

        Espresso.onView(withId(R.id.phoneNumberText)).perform(typeText("1231231234"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.enterPin)).perform(typeText("1234"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.confirmPinInput)).perform(typeText("1234"), closeSoftKeyboard());

        Espresso.onView(withId(R.id.parentSignupBtn)).perform(click());

        Espresso.onView(withId(R.id.parentLoginActivityID)).check(matches(isDisplayed()));

        Espresso.onView(withId(R.id.deleteParentButton)).perform(click());

        Espresso.onView(withId(R.id.confirmBtn)).perform(click());
    }
}
