package com.birinesor.mvpornek.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Fragment.PagerAdapter.SearchViewPagerAdapter;
import com.birinesor.mvpornek.Fragment.Search.AramaIcerikSecondFragment;
import com.birinesor.mvpornek.Fragment.SearchTabFragment.SearchQuestionFragment;
import com.birinesor.mvpornek.Fragment.SearchTabFragment.SearchUsersFragment;
import com.birinesor.mvpornek.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class KullaniciIcerikFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private int mParam2;
    private int mParam3;
    private SearchViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    SearchView searchView;
    Button geriBtn;
    SearchUsersFragment searchUsersFragment;
    SearchQuestionFragment searchQuestionFragment;

    public KullaniciIcerikFragment() {
        // Required empty public constructor
    }

    public static KullaniciIcerikFragment newInstance(String param1, int param2, int param3) {
        KullaniciIcerikFragment fragment = new KullaniciIcerikFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2,param2);
        args.putInt(ARG_PARAM3,param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
            mParam3 = getArguments().getInt(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.arama_sayfasi_kullanici_icerik, container, false);
        geriBtn=view.findViewById(R.id.aramaSayfasiAyrintiIcerikGeriBtn);
        geriBtn.setOnClickListener(this);
        searchView=view.findViewById(R.id.kullaniciIcerikSearchView);
        searchView.onActionViewExpanded();
        searchView.setQuery(mParam1,false);
        tabLayout=view.findViewById(R.id.kullaniciIceriktabLayout);
        TabItem tabSorular=view.findViewById(R.id.sorular);
        TabItem tabKullanicilar=view.findViewById(R.id.kullanicilar);
        viewPager=view.findViewById(R.id.kullaniciIcerikViewPager);
        if(mParam3 == -1){
            searchUsersFragment=SearchUsersFragment.newInstance(mParam1,mParam2,mParam3);
            searchQuestionFragment=SearchQuestionFragment.newInstance(mParam1,mParam2,mParam3);
        }else{
            searchUsersFragment=SearchUsersFragment.newInstance(mParam1,mParam2,mParam3);
            searchQuestionFragment=SearchQuestionFragment.newInstance(mParam1,mParam2,mParam3);
        }

        setTabLayout(searchUsersFragment,searchQuestionFragment);
        searchView.clearFocus();
        EditText searchEditText = (EditText) searchView.findViewById(R.id.search_src_text);
        searchEditText.setTextSize(14);
       searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View view, boolean b) {
               if (b){

                   AramaIcerikSecondFragment aramaIcerikFragment=AramaIcerikSecondFragment.newInstance();
                   ((HomeActivity)getActivity()).loadFragment(aramaIcerikFragment,"AramaAsama-3");
                   searchView.clearFocus();
               }

           }
       });

        ImageView closeButton = (ImageView)searchView.findViewById(R.id.search_close_btn);
        closeButton.setOnClickListener(view1 -> {
           AramaIcerikSecondFragment aramaIcerikFragment=AramaIcerikSecondFragment.newInstance();
            ((HomeActivity)getActivity()).loadFragment(aramaIcerikFragment,"AramaAsama-3");
        });

        return view;
    }
    public void setTabLayout(Fragment kullanicilar, Fragment sorular){
        viewPagerAdapter=new SearchViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.AddFragment(kullanicilar,"Kullanicilar");
        viewPagerAdapter.AddFragment(sorular,"Sorular");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.uygulamaGrisi),getResources().getColor(R.color.uygulamaMavisi));
    }

    @Override
    public void onClick(View view) {
        getActivity().getSupportFragmentManager().popBackStack("Fragment",0);
    }

}