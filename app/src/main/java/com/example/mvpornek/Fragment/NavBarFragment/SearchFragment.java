package com.example.mvpornek.Fragment.NavBarFragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.SearchView;

import com.example.mvpornek.Fragment.Search.AramaIcerikFragment;
import com.example.mvpornek.Models.SearchModel;
import com.example.mvpornek.R;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView aramaSayfasiRecyclerView;
     List<SearchModel>searchModelList=new ArrayList<>();
    private SearchView.OnQueryTextListener queryTextListener;
    private SearchView searchView = null;
    private String mParam1;
    private String mParam2;

    public SearchFragment() {

    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        View view=inflater.inflate(R.layout.arama_sayfasi, container, false);
        RelativeLayout relativeLayoutSearchBar=(RelativeLayout) view.findViewById(R.id.search_content_search_bar);
        relativeLayoutSearchBar.setOnClickListener(this);
        aramaSayfasiRecyclerView=(RecyclerView) view.findViewById(R.id.arama_sayfasi_recyclerView);
        aramaSayfasiRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int myInt = bundle.getInt("sayi");
            System.out.println("SONUÇ"+myInt);
        }else{
            System.out.println("BOŞŞŞŞŞŞŞŞŞ");
        }

        return view;
    }

    @Override
    public void onClick(View view)
    {
        Fragment fragment;
        switch (view.getId())
        {
            case R.id.search_content_search_bar:
                fragment=new AramaIcerikFragment();
                loadFragment(fragment,"AramaIcerik");
                break;
            case R.id.search_content_search_text:

        }

    }
    
    private boolean loadFragment(Fragment fragment,String fragmentTag)
    {
        if(fragment != null){
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(fragmentTag)
                    .replace(R.id.anaSayfaFrameLayout,fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
