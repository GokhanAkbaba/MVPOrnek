package com.example.mvpornek.Activity;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.mvpornek.Fragment.ProfilTabFragment.BegendiklerimFragment;
import com.example.mvpornek.Fragment.ProfilTabFragment.CevaplarimFragment;
import com.example.mvpornek.R;
import com.example.mvpornek.Fragment.ProfilTabFragment.SorularimFragment;
import com.example.mvpornek.Fragment.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class ProfilActivity extends FragmentActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kullanici_profili);


        viewPager= (ViewPager) findViewById(R.id.profilViewPager);
        tabLayout=(TabLayout) findViewById(R.id.profilTablayout);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());




        viewPagerAdapter.AddFragment(new SorularimFragment(),"Sorularım");
        viewPagerAdapter.AddFragment(new BegendiklerimFragment(),"Beğendiklerim");
        viewPagerAdapter.AddFragment(new CevaplarimFragment(),"Cevaplarim");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


}
