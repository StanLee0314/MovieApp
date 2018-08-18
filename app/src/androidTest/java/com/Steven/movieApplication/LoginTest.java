package com.Steven.movieApplication;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;


/**
 * Created by steven on 6/6/2018.
 * Test for the login validation.
 */
@RunWith(AndroidJUnit4.class)
public class LoginTest {
    private static final String WrongEmail = "abcdefg";
    private static final String ShortPassword = "abc";
    private static final String RightEmail = "abcde@gmail.com";
    private static final String RightPassword = "abcdefg";
    private MainActivity mActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(HomeMainActivity.class.getName(),null,false);
@Before
public void setUp(){
    mActivity = mActivityRule.getActivity();
}


    @Test
    public void testWrongEmailFormatLogin() {

        onView(withId(R.id.input_username)).perform(ViewActions.typeText(WrongEmail ));
        onView(withId(R.id.input_password)).perform(ViewActions.typeText(RightPassword));
        onView(withId(R.id.btn_login)).perform(ViewActions.click());
        onView(withText(R.string.toast_test)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void testWrongEmailFormatRegister() {

        onView(withId(R.id.input_username)).perform(ViewActions.typeText(WrongEmail ));
        onView(withId(R.id.input_password)).perform(ViewActions.typeText(RightPassword));
        onView(withId(R.id.link_signup)).perform(ViewActions.click());
        onView(withText(R.string.toast_test)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
    @Test
    public void testWrongPasswordFormatLogin() {

        onView(withId(R.id.input_username)).perform(ViewActions.typeText(RightEmail));
        onView(withId(R.id.input_password)).perform(ViewActions.typeText(ShortPassword));
        onView(withId(R.id.btn_login)).perform(ViewActions.click());
        onView(withText(R.string.toast_test)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
    @Test
    public void testWrongPasswordFormatRegister() {

        onView(withId(R.id.input_username)).perform(ViewActions.typeText(RightEmail));
        onView(withId(R.id.input_password)).perform(ViewActions.typeText(ShortPassword));
        onView(withId(R.id.link_signup)).perform(ViewActions.click());
        onView(withText(R.string.toast_test)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
    @Test
    public void testRightPasswordFormatRegisterWithSameUser() {

        onView(withId(R.id.input_username)).perform(ViewActions.typeText(RightEmail));
        onView(withId(R.id.input_password)).perform(ViewActions.typeText(RightPassword));
        onView(withId(R.id.link_signup)).perform(ViewActions.click());
        onView(withText(R.string.toast_test_sameUser)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
    @Test
    public void testRightPasswordFormatLogin() {

        onView(withId(R.id.input_username)).perform(ViewActions.typeText(RightEmail));
        onView(withId(R.id.input_password)).perform(ViewActions.typeText(RightPassword));
        onView(withId(R.id.btn_login)).perform(ViewActions.click());
        Activity secondActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(secondActivity);
        secondActivity.finish();
    }
    }


