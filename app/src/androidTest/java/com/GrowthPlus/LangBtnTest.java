package com.GrowthPlus;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LangBtnTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void langBtnTest() {
        Espresso.onView(withId(R.id.mainActivityID)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.langBtn)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.langBtn)).perform(click());

        Espresso.onView(withId(R.id.languageSettingsID)).check(matches(isDisplayed()));

        Espresso.onView(withId(R.id.englishBtn)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.frenchBtn)).check(matches(isDisplayed()));

        Espresso.onView(withId(R.id.languageText)).check(matches(withText("Langue")));
        Espresso.onView(withId(R.id.englishText)).check(matches(withText("Anglais")));
        Espresso.onView(withId(R.id.frenchText)).check(matches(withText("Français")));

        Espresso.onView(withId(R.id.englishBtn)).perform(click());

        Espresso.onView(withId(R.id.englishText)).check(matches(withText("English")));
        Espresso.onView(withId(R.id.frenchText)).check(matches(withText("French")));
        Espresso.onView(withId(R.id.languageText)).check(matches(withText("Language")));

        Espresso.onView(withId(R.id.frenchBtn)).perform(click());

        Espresso.onView(withId(R.id.languageText)).check(matches(withText("Langue")));
        Espresso.onView(withId(R.id.englishText)).check(matches(withText("Anglais")));
        Espresso.onView(withId(R.id.frenchText)).check(matches(withText("Français")));
    }
}
