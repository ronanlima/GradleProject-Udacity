package com.udacity.gradle.builditbigger;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by rlima on 19/09/18.
 */

@RunWith(AndroidJUnit4.class)
public class CheckExecutionEndpointAsyncTaskTest {
    private IdlingResource mIdlingResource;
    private static final String sStaticJoke = "Ei, estou contando uma piada. Acredita? ;D";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void checkExecutionOfAsyncTask() {
        onView(withId(R.id.btn_tell_joke)).perform(click());
        onView(withId(R.id.tv_joke_another_project)).check(matches(withText(sStaticJoke)));
    }

    @After
    public void unregisterIdlingResources() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }
}
