package com.birinesor.mvpornek.Fragment.NavBarFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Fragment.BildirimlerTabFragment.BildirimlerBegenilerFragment;
import com.birinesor.mvpornek.Fragment.BildirimlerTabFragment.BildirimlerCevaplarFragment;
import com.birinesor.mvpornek.Fragment.BildirimlerTabFragment.BildirimlerFragmentViewPagerAdapter;
import com.birinesor.mvpornek.Fragment.ProfilTabFragment.BegendiklerimFragment;
import com.birinesor.mvpornek.R;
import com.google.android.material.tabs.TabLayout;

public class BildirimlerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TabLayout tabLayout;
    private ViewPager bildirimlerViewPager;
    private BildirimlerFragmentViewPagerAdapter bildirimlerFragmentViewPagerAdapter;
    private String mParam1;
    private String mParam2;

    public BildirimlerFragment() {

    }


    public static BegendiklerimFragment newInstance(String param1, String param2) {
        BegendiklerimFragment fragment = new BegendiklerimFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bildirimler_sayfasi, container, false);

        tabLayout=view.findViewById(R.id.bildirimCevaplarTabLayout);
        bildirimlerViewPager=view.findViewById(R.id.bildirimlerViewPager);
        bildirimlerFragmentViewPagerAdapter=new BildirimlerFragmentViewPagerAdapter(getActivity().getSupportFragmentManager());

        bildirimlerFragmentViewPagerAdapter.AddFragment(new BildirimlerCevaplarFragment(),"Cevaplar");
        bildirimlerFragmentViewPagerAdapter.AddFragment(new BildirimlerBegenilerFragment(),"Beğeniler");

        bildirimlerViewPager.setAdapter(bildirimlerFragmentViewPagerAdapter);
        tabLayout.setupWithViewPager(bildirimlerViewPager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.uygulamaGrisi),getResources().getColor(R.color.uygulamaMavisi));
        HomeActivity.getInstance().startAds();
        return view;
    }


}
