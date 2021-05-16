package com.birinesor.mvpornek.Fragment.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragmentList;
    private final List<String> listTitle = new ArrayList<>();

    public SearchViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentList =new ArrayList<>();
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return listTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }

    public void AddFragment(Fragment fragment, String title)
    {
        fragmentList.add(fragment);
        listTitle.add(title);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}