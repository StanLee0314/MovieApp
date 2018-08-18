package com.Steven.movieApplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.Steven.movieApplication.fragment.FavoriteFragment;
import com.Steven.movieApplication.fragment.CinemasDisplayFragment;
import com.Steven.movieApplication.fragment.SearchFragment;
import com.Steven.movieApplication.fragment.NowPlayingFragment;
import com.Steven.movieApplication.fragment.UpcomingFragment;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Steven on 6/2/2018.
 * The menu for the application which contains five fragments and one activity to the account profile.
 */
public class HomeMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.viewPagers);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabsLayout);
        tabLayout.setupWithViewPager(viewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    //tab
    public void setupViewPager(ViewPager upViewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new NowPlayingFragment(), getResources().getString(R.string.now_playing));
        adapter.addFrag(new UpcomingFragment(), getResources().getString(R.string.up_coming));
        adapter.addFrag(new SearchFragment(), getResources().getString(R.string.search));
        adapter.addFrag(new FavoriteFragment(), getResources().getString(R.string.favorite));
        adapter.addFrag(new CinemasDisplayFragment(),"Cinema");
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(adapter);
    }
//initial
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> stringList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        public int getCount() {
            return fragmentList.size();
        }

        void addFrag(Fragment fragment, String title) {
            fragmentList.add(fragment);
            stringList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return stringList.get(position);
        }
    }
    //tab


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.now_playing) {
            viewPager.setCurrentItem(0);
        } else if (id == R.id.up_coming) {
            viewPager.setCurrentItem(1);
        } else if (id == R.id.search) {
            viewPager.setCurrentItem(2);
        } else if (id == R.id.favorite) {
            viewPager.setCurrentItem(3);
        } else if (id == R.id.cinema) {
            viewPager.setCurrentItem(4);
        } else if (id == R.id.userAccount) {
            Intent mIntent = new Intent(HomeMainActivity.this, UserAccountActivity.class);
            startActivity(mIntent);
        } else if (id == R.id.about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
