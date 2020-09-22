package com.example.mvpornek.Fragment.Search;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mvpornek.Activity.HomeActivity;
import com.example.mvpornek.Adapter.SearchAdapter;
import com.example.mvpornek.Fragment.KullaniciIcerikFragment;
import com.example.mvpornek.Presenter.SearchUsersPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.Response.SearchListResponse;
import com.example.mvpornek.View.SearchUsersView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AramaIcerikSecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AramaIcerikSecondFragment extends Fragment implements View.OnClickListener, SearchUsersView {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    SearchView searchView;
    List<SearchListResponse> searchListResponseList;
    Button geriButon;
    SearchUsersPresenterImpl searchUsersPresenter;
    SearchAdapter.ItemClickListener itemClickListener;
    RecyclerView aramaRecyclerView;
    SearchAdapter searchAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AramaIcerikSecondFragment() {
        // Required empty public constructor
    }

    public static AramaIcerikSecondFragment newInstance() {
        AramaIcerikSecondFragment fragment = new AramaIcerikSecondFragment();
        Bundle args = new Bundle();
        /*args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
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
        View view=inflater.inflate(R.layout.fragment_arama_icerik_second, container, false);
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
                ((HomeActivity)getActivity()).loadFragment(kullaniciIcerikFragment,"AramaAsama-4");
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