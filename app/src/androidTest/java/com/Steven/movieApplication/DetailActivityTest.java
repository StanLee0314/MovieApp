package com.Steven.movieApplication;
import android.support.test.espresso.Espresso;
import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.Gravity;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.closeDrawer;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerActions.openDrawer;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;

import com.Steven.movieApplication.fragment.FavoriteFragment;
import com.Steven.movieApplication.model.Favorite;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.contrib.NavigationViewActions.navigateTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.Assert.*;

/**
 * Created by steven on 6/8/2018.
 */
public class DetailActivityTest {
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
    public void DetailShow() {
        onView(withId(R.id.input_username)).perform(ViewActions.typeText(RightEmail));
        onView(withId(R.id.input_password)).perform(ViewActions.typeText(RightPassword));
        onView(withId(R.id.btn_login)).perform(click());
        Activity mActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,3000);
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(open()); // Open Drawer

        // Start new fragment
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.now_playing));
        Espresso.onView(allOf(isDisplayed(),withId(R.id.movie_recycler))).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        mActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,3000);
        onView(withId(R.id.overview))
                .check(matches(isDisplayed()));
    }


}