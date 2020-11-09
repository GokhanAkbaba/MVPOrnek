package com.birinesor.mvpornek.Fragment.NavBarFragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.birinesor.mvpornek.Activity.Ayarlar.SifreDuzenleActivity;
import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Adapter.EtiketlerAdapter;
import com.birinesor.mvpornek.Adapter.SearchAdapterQuestion;
import com.birinesor.mvpornek.Fragment.Comment.CommentBottomDialogFragment;
import com.birinesor.mvpornek.Fragment.KullaniciIcerikFragment;
import com.birinesor.mvpornek.Fragment.Search.AramaAyarlariActivity;
import com.birinesor.mvpornek.Fragment.Search.AramaIcerikFragment;
import com.birinesor.mvpornek.Model.InternetBaglantiKontrol.InternetConnectionInteractorImpl;
import com.birinesor.mvpornek.Models.EtiketlerModel;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Models.SearchQuestionModel;
import com.birinesor.mvpornek.Presenter.Etketler.EtiketlerPresenterImpl;
import com.birinesor.mvpornek.Presenter.InternetBaglantiKontrol.InternetConnectionPresenterImpl;
import com.birinesor.mvpornek.Presenter.SearchQuestionPresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.View.EtiketlerView;
import com.birinesor.mvpornek.View.InternetConnectionView;
import com.birinesor.mvpornek.View.SearchQuestionView;

import java.util.List;


public class SearchFragment extends Fragment implements View.OnClickListener, EtiketlerView,InternetConnectionView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private boolean shouldRefreshOnResume = false;

    private RecyclerView aramaSayfasiRecyclerView;

    SearchAdapterQuestion searchAdapterQuestion;
    SearchAdapterQuestion.ItemClickListener itemClickListener;
    EtiketlerAdapter.ItemClickListener itemEtiketlerClickListener;
    List<EtiketlerModel> etiketlerModels;
    EtiketlerAdapter etiketlerAdapter;
    List<SearchQuestionModel> searchQuestionModels;
    ImageView search_content_search_settings;
    SearchQuestionPresenterImpl searchQuestionPresenter;
    InternetConnectionPresenterImpl internetConnectionPresenter;
    Kullanici kullanici;
    SwipeRefreshLayout swipeRefreshLayout;
    EtiketlerPresenterImpl etiketlerPresenter;
    TextView aramaRecyclerViewTxt;

    private int mParam1;
    private int mParam2;



    public SearchFragment() {

    }

    public static SearchFragment newInstance(int param1, int param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        getActivity().findViewById(R.id.anasayfa_nav_view).setVisibility(View.VISIBLE);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
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
        etiketlerPresenter=new EtiketlerPresenterImpl(this);
        kullanici= SharedPrefManager.getInstance(getActivity()).getKullanici();
        etiketlerPresenter.etiketLoad(kullanici.getId());

        search_content_search_settings=view.findViewById(R.id.search_content_search_settings);
        search_content_search_settings.setOnClickListener(this);

        aramaSayfasiRecyclerView.setAdapter(etiketlerAdapter);
        aramaSayfasiRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        aramaSayfasiRecyclerView.setOnClickListener(this);

        internetConnectionPresenter=new InternetConnectionPresenterImpl(this,new InternetConnectionInteractorImpl(getActivity()));

        aramaRecyclerViewTxt=view.findViewById(R.id.aramaRecyclerViewTxt);
        swipeRefreshLayout=view.findViewById(R.id.aramaSwipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.uygulamaMavisi);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                internetConnectionPresenter.internetBaglantiKontrolu();

                    etiketlerPresenter.etiketLoad(kullanici.getId());

            }
        });

        itemEtiketlerClickListener =((vw,position)-> {
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
            case R.id.search_content_search_settings:
                startActivity(new Intent(getActivity().getApplicationContext(), AramaAyarlariActivity.class));
                getActivity().overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
                break;
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
    public void onGetEtiketlerResult(List<EtiketlerModel> etiketlerModels) {
    etiketlerAdapter=new EtiketlerAdapter(etiketlerModels,getActivity(),itemEtiketlerClickListener);
    etiketlerAdapter.notifyDataSetChanged();
    aramaSayfasiRecyclerView.setAdapter(etiketlerAdapter);
    this.etiketlerModels=etiketlerModels;
    aramaRecyclerViewTxt.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onEtiketlerErrorLoading(String message) {
        Toast.makeText(getActivity(),"Bir Hata Oluştu",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEtiketlerLoading() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideEtiketlerLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void etiketlerKontrol() {
        aramaRecyclerViewTxt.setVisibility(View.VISIBLE);
    }

    @Override
    public void internetBaglantiHatasi() {
        Toast.makeText(getActivity(),"İnternet Bağlantınızı Kontrol Ediniz.",Toast.LENGTH_LONG).show();
    }

    @Override
    public void internetBaglantisi() {

    }
}

