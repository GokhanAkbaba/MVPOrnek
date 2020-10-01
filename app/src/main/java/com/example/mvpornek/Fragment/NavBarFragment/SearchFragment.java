package com.example.mvpornek.Fragment.NavBarFragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mvpornek.Adapter.SearchAdapterQuestion;
import com.example.mvpornek.Fragment.Comment.CommentBottomDialogFragment;
import com.example.mvpornek.Fragment.Search.AramaIcerikFragment;
import com.example.mvpornek.Model.InternetBaglantiKontrol.InternetConnectionInteractorImpl;
import com.example.mvpornek.Models.SearchQuestionModel;
import com.example.mvpornek.Presenter.InternetBaglantiKontrol.InternetConnectionPresenterImpl;
import com.example.mvpornek.Presenter.SearchQuestionPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.View.InternetConnectionView;
import com.example.mvpornek.View.SearchQuestionView;

import java.util.List;


public class SearchFragment extends Fragment implements View.OnClickListener, SearchQuestionView,InternetConnectionView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView aramaSayfasiRecyclerView;

    SearchAdapterQuestion searchAdapterQuestion;
    SearchAdapterQuestion.ItemClickListener itemClickListener;

    List<SearchQuestionModel> searchQuestionModels;

    SearchQuestionPresenterImpl searchQuestionPresenter;
    InternetConnectionPresenterImpl internetConnectionPresenter;

    SwipeRefreshLayout swipeRefreshLayout;

    TextView aramaRecyclerViewTxt;

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
        getActivity().findViewById(R.id.anasayfa_nav_view).setVisibility(View.VISIBLE);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().findViewById(R.id.anasayfa_nav_view).setVisibility(View.VISIBLE);
        View view=inflater.inflate(R.layout.arama_sayfasi, container, false);
        RelativeLayout relativeLayoutSearchBar=view.findViewById(R.id.search_content_search_bar);
        relativeLayoutSearchBar.setOnClickListener(this);

        aramaSayfasiRecyclerView=view.findViewById(R.id.arama_sayfasi_recyclerView);
        searchQuestionPresenter = new SearchQuestionPresenterImpl(this);
        searchQuestionPresenter.loadData();

        aramaSayfasiRecyclerView.setAdapter(searchAdapterQuestion);
        aramaSayfasiRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        aramaSayfasiRecyclerView.setOnClickListener(this);

        internetConnectionPresenter=new InternetConnectionPresenterImpl(this,new InternetConnectionInteractorImpl(getActivity()));

        aramaRecyclerViewTxt=view.findViewById(R.id.aramaRecyclerViewTxt);

        swipeRefreshLayout=view.findViewById(R.id.aramaSwipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                internetConnectionPresenter.internetBaglantiKontrolu();
                searchQuestionPresenter.loadData();
            }
        });

        itemClickListener =((vw,position)-> {
            int soruId=searchQuestionModels.get(position).getSoru_id();
            showBottomSheet(soruId);
        });


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
                loadFragment(fragment,"AramaAsama-1");
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

    @Override
    public void onGetResult(List<SearchQuestionModel> data) {
    searchAdapterQuestion=new SearchAdapterQuestion(data,getActivity(),itemClickListener);
    searchAdapterQuestion.notifyDataSetChanged();
    aramaSayfasiRecyclerView.setAdapter(searchAdapterQuestion);
    searchQuestionModels=data;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getActivity(),"Bir Hata Oluştu.",Toast.LENGTH_LONG).show();
        System.out.println("Bağlantı Hatası(Search Fragment) "+message);
    }

    @Override
    public void showLoading() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetResultControl() {
        aramaRecyclerViewTxt.setVisibility(View.VISIBLE);
    }

    @Override
    public void internetBaglantiHatasi() {
        Toast.makeText(getActivity(),"İnternet Bağlantınızı Kontrol Ediniz.",Toast.LENGTH_LONG).show();
    }

    @Override
    public void internetBaglantisi() {

    }
    public void showBottomSheet(int soruId) {
        CommentBottomDialogFragment commentBottomDialogFragment =
                CommentBottomDialogFragment.newInstance(soruId);
        commentBottomDialogFragment.show(getActivity().getSupportFragmentManager(),
                CommentBottomDialogFragment.TAG);
    }
}
