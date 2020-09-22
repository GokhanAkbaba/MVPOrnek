package com.example.mvpornek.Fragment.Search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mvpornek.Activity.HomeActivity;
import com.example.mvpornek.Adapter.AdapterProfilQuestion;
import com.example.mvpornek.Adapter.SearchAdapter;
import com.example.mvpornek.Fragment.KullaniciIcerikFragment;
import com.example.mvpornek.Models.AnswersModel;
import com.example.mvpornek.Presenter.SearchUsersPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.Response.SearchListResponse;
import com.example.mvpornek.View.SearchUsersView;

import java.util.List;

public class AramaIcerikFragment extends Fragment implements View.OnClickListener, SearchUsersView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    SearchView searchView;
    List<SearchListResponse> searchListResponseList;
    Button geriButon;
    SearchUsersPresenterImpl searchUsersPresenter;
    SearchAdapter.ItemClickListener itemClickListener;
    RecyclerView aramaRecyclerView;
    SearchAdapter searchAdapter;

    private String mParam1;
    private String mParam2;

    public AramaIcerikFragment() {

    }

    public static AramaIcerikFragment newInstance() {
        AramaIcerikFragment fragment = new AramaIcerikFragment();
        Bundle args = new Bundle();
        /*args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getActivity().findViewById(R.id.anasayfa_nav_view).setVisibility(View.GONE);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.arama_sayfasi_icerik, container, false);
        searchView=(SearchView) view.findViewById(R.id.searchView_page);
        aramaRecyclerView=view.findViewById(R.id.aramaRecyclerView);
        aramaRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        aramaRecyclerView.setAdapter(searchAdapter);
        searchUsersPresenter=new SearchUsersPresenterImpl(this);
        geriButon=(Button) view.findViewById(R.id.aramaSayfasiIcerikGeriBtn);
        geriButon.setOnClickListener(this);
        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                KullaniciIcerikFragment kullaniciIcerikFragment=KullaniciIcerikFragment.newInstance(query);
                ((HomeActivity)getActivity()).loadFragment(kullaniciIcerikFragment,"AramaAsama-2");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchUsersPresenter.loadData(newText);
                return false;
            }
        });

        itemClickListener =((vw,position)-> {

        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.aramaSayfasiIcerikGeriBtn:
                getActivity().getSupportFragmentManager().popBackStack();
            break;
        }
    }

    @Override
    public void onGetResult(List<SearchListResponse> data) {
        searchAdapter=new SearchAdapter(data,getActivity(),itemClickListener);
        searchAdapter.notifyDataSetChanged();
        aramaRecyclerView.setAdapter(searchAdapter);
        searchListResponseList=data;

    }

    @Override
    public void onErrorLoading(String message) {

    }
}
