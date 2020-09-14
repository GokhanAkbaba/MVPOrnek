package com.example.mvpornek.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mvpornek.Fragment.PagerAdapter.ViewPagerAdapter;
import com.example.mvpornek.Fragment.SearchTabFragment.SearchQuestionFragment;
import com.example.mvpornek.Fragment.SearchTabFragment.SearchUsersFragment;
import com.example.mvpornek.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class KullaniciIcerikFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    Button geriBtn;
    SearchUsersFragment searchUsersFragment;
    SearchQuestionFragment searchQuestionFragment;

    public KullaniciIcerikFragment() {
        // Required empty public constructor
    }

    public static KullaniciIcerikFragment newInstance(String param1, String param2) {
        KullaniciIcerikFragment fragment = new KullaniciIcerikFragment();
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
        View view = inflater.inflate(R.layout.arama_sayfasi_kullanici_icerik, container, false);
        geriBtn=view.findViewById(R.id.aramaSayfasiAyrintiIcerikGeriBtn);
        geriBtn.setOnClickListener(this);
        /*tabLayout=view.findViewById(R.id.kullaniciIceriktabLayout);
        TabItem tabSorular=view.findViewById(R.id.sorular);
        TabItem tabKullanicilar=view.findViewById(R.id.kullanicilar);
        viewPager=view.findViewById(R.id.kullaniciIcerikViewPager);
        setTabLayout(searchUsersFragment,searchQuestionFragment);*/
        return view;
    }
    public void setTabLayout(Fragment kullanicilar, Fragment sorular){
        viewPagerAdapter=new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPagerAdapter.AddFragment(kullanicilar,"Kullanicilar");
        viewPagerAdapter.AddFragment(sorular,"Sorular");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View view) {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}