package com.Steven.movieApplication;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.Gravity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Created by steven on 6/8/2018.
 */
public class MapsShowTest {
    private MainActivity mActivity;
    private static final String RightEmail = "abcde@gmail.com";
    private static final String RightPassword = "abcdefg";
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(HomeMainActivity.class.getName(), null, false);

    @Before
    public void setUp() {



    }
    @Test
    public void purchaseTest() {
        onView(withId(R.id.input_username)).perform(ViewActions.typeText(RightEmail));
        onView(withId(R.id.input_password)).perform(ViewActions.typeText(RightPassword));
        onView(withId(R.id.btn_login)).perform(click());
        Activity mActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,3000);
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(open()); // Open Drawer

        // Start new fragment
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.cinema));
        Espresso.onView(allOf(isDisplayed(),withId(R.id.cinemas_recycler_view))).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.mapView))
                .check(matches(isDisplayed()));


    }


}