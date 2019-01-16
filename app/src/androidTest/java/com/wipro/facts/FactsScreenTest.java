package com.wipro.facts;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.wipro.R;

import org.hamcrest.core.AllOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FactsScreenTest {

    @Rule
    public ActivityTestRule<FactsActivity> mActivityRule =
            new ActivityTestRule<>(FactsActivity.class);


    @Test
    public void testWhenInternetAvailable() {
        Espresso.onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.rv_facts)).perform(ViewActions.swipeDown());
    }

    @Test
    public void testWhenInternetNotAvailable() {
        Espresso.onView(withId(R.id.srl_facts)).perform(ViewActions.swipeDown());
        Espresso.onView(AllOf.allOf(withId(android.support.design.R.id.snackbar_text), withText("No Connection")))
                .check(matches(isDisplayed()));
    }
}
