package com.trichashapps.surveywithfirebase.home.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.trichashapps.surveywithfirebase.home.utils.HomeDataView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashish on 09/10/17.
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    List<HomeDataView> homeViewData;


    public HomeViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.homeViewData = new ArrayList<>();
    }

    public void setHomeViewData(List<HomeDataView> homeViewData) {
        this.homeViewData = homeViewData;
    }

    @Override
    public int getCount() {
        return homeViewData.size();
    }

    @Override
    public Fragment getItem(int position) {
        return homeViewData.get(position).getFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return homeViewData.get(position).getTitle();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
