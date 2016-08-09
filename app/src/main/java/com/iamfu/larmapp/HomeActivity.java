package com.iamfu.larmapp;

import android.content.Intent;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.iamfu.larmapp.databinding.HomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private HomeBinding binding;

    private int[] tabIcons = {
            R.drawable.ic_tab_larm,
            R.drawable.ic_tab_dictionary,
            R.drawable.ic_tab_lesson,
            R.drawable.ic_tab_menu
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.home);

        setupViewPager(binding.viewpager);

        binding.tabs.setupWithViewPager(binding.viewpager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        binding.tabs.getTabAt(0).setIcon(tabIcons[0]);
        binding.tabs.getTabAt(1).setIcon(tabIcons[1]);
        binding.tabs.getTabAt(2).setIcon(tabIcons[2]);
        binding.tabs.getTabAt(3).setIcon(tabIcons[3]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new LarmFragment(), "Larm");
        adapter.addFrag(new LarmFragment(), "Dictionary");
        adapter.addFrag(new LarmFragment(), "Lesson");
        adapter.addFrag(new LarmFragment(), "Menu");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
