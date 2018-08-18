package com.Steven.movieApplication;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.Gravity;

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
import static org.junit.Assert.*;

/**
 * Created by steven on 6/8/2018.
 */
public class HomeMainActivityTest {
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
    public void transactionToNowPlayingFragment() {
        onView(withId(R.id.input_username)).perform(ViewActions.typeText(RightEmail));
        onView(withId(R.id.input_password)).perform(ViewActions.typeText(RightPassword));
        onView(withId(R.id.btn_login)).perform(ViewActions.click());
        Activity mActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,3000);
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(open()); // Open Drawer

        // Start new fragment
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.now_playing));
        onView(withId(R.id.movie_recycler))
                .check(matches(isDisplayed()));
    }
    @Test
    public void transactionToUpcomingFragment() {
        onView(withId(R.id.input_username)).perform(ViewActions.typeText(RightEmail));
        onView(withId(R.id.input_password)).perform(ViewActions.typeText(RightPassword));
        onView(withId(R.id.btn_login)).perform(ViewActions.click());
        Activity mActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,3000);
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(open()); // Open Drawer

        // Start new fragment
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.up_coming));
        onView(withId(R.id.movie_now_playing))
                .check(matches(isDisplayed()));
    }
    @Test
    public void transactionToSearchFragment() {
        onView(withId(R.id.input_username)).perform(ViewActions.typeText(RightEmail));
        onView(withId(R.id.input_password)).perform(ViewActions.typeText(RightPassword));
        onView(withId(R.id.btn_login)).perform(ViewActions.click());
        Activity mActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,3000);
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(open()); // Open Drawer

        // Start new fragment
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.search));
        onView(withId(R.id.search))
                .check(matches(isDisplayed()));
    }
    @Test
    public void transactionToFavoriteFragment() {
        onView(withId(R.id.input_username)).perform(ViewActions.typeText(RightEmail));
        onView(withId(R.id.input_password)).perform(ViewActions.typeText(RightPassword));
        onView(withId(R.id.btn_login)).perform(ViewActions.click());
        Activity mActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,3000);
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(open()); // Open Drawer

        // Start new fragment
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.favorite));
        onView(withId(R.id.rv_favorite))
                .check(matches(isDisplayed()));
    }
    @Test
    public void transactionToCinemaFragment() {
        onView(withId(R.id.input_username)).perform(ViewActions.typeText(RightEmail));
        onView(withId(R.id.input_password)).perform(ViewActions.typeText(RightPassword));
        onView(withId(R.id.btn_login)).perform(ViewActions.click());
        Activity mActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,3000);
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(open()); // Open Drawer

        // Start new fragment
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.cinema));
        onView(withId(R.id.cinemas_recycler_view))
                .check(matches(isDisplayed()));
    }
    @Test
    public void transactionToUserAccount() {
        onView(withId(R.id.input_username)).perform(ViewActions.typeText(RightEmail));
        onView(withId(R.id.input_password)).perform(ViewActions.typeText(RightPassword));
        onView(withId(R.id.btn_login)).perform(ViewActions.click());
        Activity mActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,3000);
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(open()); // Open Drawer

        // Start new fragment
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.userAccount));
        onView(withId(R.id.userAddressText))
                .check(matches(isDisplayed()));
    }
}