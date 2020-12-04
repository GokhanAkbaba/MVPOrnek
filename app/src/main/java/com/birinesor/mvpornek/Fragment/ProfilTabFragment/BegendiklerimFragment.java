package com.birinesor.mvpornek.Fragment.ProfilTabFragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.birinesor.mvpornek.Adapter.AdapterProfilLikes;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Models.LikesModel;
import com.birinesor.mvpornek.Presenter.Like.LikesPresenterImpl;
import com.birinesor.mvpornek.Presenter.ProfilBegeni.ProfilLikesPresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.Response.LikeModel;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.View.LikesView;
import com.birinesor.mvpornek.View.ProfilLikeView;

import java.util.List;

public class BegendiklerimFragment extends Fragment implements ProfilLikeView,LikesView {

    private int mParam1;
    private static final String ARG_PARAM1 = "param1";
    RecyclerView profilBegendiklerimRecyclerView;
    RelativeLayout profilBegendiklerimAlaniContent;
    List<LikesModel> likesModels;
    LikesPresenterImpl likesPresenter;
    AdapterProfilLikes adapterProfilLikes;
    AdapterProfilLikes.ItemClickListener itemClickListener;
    ProfilLikesPresenterImpl profilLikesPresenter;
    Kullanici kullanici;
    LikeModel likeModel;
    TextView recyclerViewProfilBegendiklerimText;
    SwipeRefreshLayout swipeRefreshLayout;



    public BegendiklerimFragment() {

    }

    public static BegendiklerimFragment newInstance(int param1) {
        BegendiklerimFragment fragment = new BegendiklerimFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_begendiklerim, container, false);
        profilLikesPresenter=new ProfilLikesPresenterImpl(this);
        kullanici= SharedPrefManager.getInstance(getActivity()).getKullanici();
        profilLikesPresenter.loadData(mParam1);
        swipeRefreshLayout=view.findViewById(R.id.swiperefreshFragmentBegendiklerim);
        swipeRefreshLayout.setColorSchemeResources(R.color.uygulamaMavisi);
        recyclerViewProfilBegendiklerimText=view.findViewById(R.id.recyclerViewProfilBegendiklerimText);
        profilBegendiklerimRecyclerView=view.findViewById(R.id.profilBegendiklerimRecyclerView);
        profilBegendiklerimAlaniContent=view.findViewById(R.id.profilBegendiklerimAlaniContent);
        profilBegendiklerimRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        profilBegendiklerimRecyclerView.setAdapter(adapterProfilLikes);
        likesPresenter=new LikesPresenterImpl(this);
        itemClickListener =((vw,position)-> {
            ImageView begeniButonu=vw.findViewById(R.id.profilBegeniImageButton);
            Object durum=begeniButonu.getTag();
            View textViever = profilBegendiklerimRecyclerView.getLayoutManager().findViewByPosition(position);
            TextView begeniSayisiTxt=textViever.findViewById(R.id.yorumCevapBegeniSayisiTxt);
            if(durum == "secilmedi") {
                begeniButonu.setColorFilter(Color.argb(255, 255, 172, 51));
                likesPresenter.loadLikes(likesModels.get(position).getCevap_id(),kullanici.getId(),1,1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        begeniSayisiTxt.setText(String.valueOf(likeModel.getBegeniSayisi()));
                    }
                },300);
                begeniButonu.setTag("secildi");
            } else if(durum == "secildi"){
                begeniButonu.setColorFilter(Color.argb(255, 223, 225, 229));
                likesPresenter.loadLikes(likesModels.get(position).getCevap_id(),kullanici.getId(),1,0);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        begeniSayisiTxt.setText(String.valueOf(likeModel.getBegeniSayisi()));
                    }
                },500);
                begeniButonu.setTag("secilmedi");
                profilLikesPresenter.loadData(mParam1);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(() -> {
            profilLikesPresenter.loadData(mParam1);
        });
        return view;
    }

    @Override
    public void onGetResult(List<LikesModel> data) {
        adapterProfilLikes= new AdapterProfilLikes(data,getActivity(),itemClickListener);
        adapterProfilLikes.notifyDataSetChanged();
        profilBegendiklerimRecyclerView.setAdapter(adapterProfilLikes);
        profilBegendiklerimAlaniContent.setVisibility(View.INVISIBLE);
        likesModels=data;
    }

    @Override
    public void onErrorLoading() {
        System.out.println("Bağlantı Hatası(Beğendiklerim Fragment)");
    }

    @Override
    public void onGetResultControl() {
        profilBegendiklerimAlaniContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProfilLikeHide() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onProfilLikeShow() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void onGetLike(LikeModel items) {
        this.likeModel=items;
    }
}
