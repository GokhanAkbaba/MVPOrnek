package com.example.mvpornek.Fragment.NavBarFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvpornek.Fragment.ProfilTabFragment.BegendiklerimFragment;
import com.example.mvpornek.Fragment.ProfilTabFragment.CevaplarimFragment;
import com.example.mvpornek.Fragment.ProfilTabFragment.SorularimFragment;
import com.example.mvpornek.Fragment.ViewPagerAdapter;
import com.example.mvpornek.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfilFragment() {
        // Required empty public constructor
    }

    public static ProfilFragment newInstance(String param1, String param2) {
        ProfilFragment fragment = new ProfilFragment();
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
        View view=inflater.inflate(R.layout.fragment_profil, container, false);
                viewPager= (ViewPager) view.findViewById(R.id.profilViewPager);
        tabLayout=(TabLayout) view.findViewById(R.id.profilTablayout);
        viewPagerAdapter=new ViewPagerAdapter(getActivity().getSupportFragmentManager());


        viewPagerAdapter.AddFragment(new SorularimFragment(),"Sorularım");
        viewPagerAdapter.AddFragment(new BegendiklerimFragment(),"Beğendiklerim");
        viewPagerAdapter.AddFragment(new CevaplarimFragment(),"Cevaplarim");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
