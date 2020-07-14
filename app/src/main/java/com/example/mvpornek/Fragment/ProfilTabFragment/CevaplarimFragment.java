package com.example.mvpornek.Fragment.ProfilTabFragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvpornek.Adapter.AdapterProfilAnswers;
import com.example.mvpornek.Models.AnswersModel;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Presenter.LikesPresenterImpl;
import com.example.mvpornek.Presenter.ProfilCevaplarim.ProfilAnswersPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.Response.LikeModel;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.LikesView;
import com.example.mvpornek.View.ProfilAnswersView;

import java.util.List;


public class CevaplarimFragment extends Fragment implements ProfilAnswersView,LikesView {

    private static final String ARG_PARAM1 = "param1";


    private int mParam1;
    List<AnswersModel> answersModels;
    AdapterProfilAnswers adapterProfilAnswers;
    AdapterProfilAnswers.ItemClickListener itemClickListener;
    ProfilAnswersPresenterImpl profilAnswersPresenter;
    Kullanici kullanici;
    LikesPresenterImpl likesPresenter;
    LikeModel likeModel;
    private RecyclerView cevaplarimRecyclerView;



    public CevaplarimFragment() {

    }

    public static CevaplarimFragment newInstance(int param1) {
        CevaplarimFragment fragment = new CevaplarimFragment();
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
        View view = inflater.inflate(R.layout.fragment_cevaplarim, container, false);
        profilAnswersPresenter=new ProfilAnswersPresenterImpl(this);
        kullanici= SharedPrefManager.getInstance(getActivity()).getKullanici();
        profilAnswersPresenter.loadData(mParam1);
        cevaplarimRecyclerView=view.findViewById(R.id.profilCevaplarimRecyclerView);
        cevaplarimRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cevaplarimRecyclerView.setAdapter(adapterProfilAnswers);
        likesPresenter=new LikesPresenterImpl(this);
        itemClickListener =((vw,position)-> {
            ImageView begeniButonu=vw.findViewById(R.id.profilCevapImageButton);
            Object durum=begeniButonu.getTag();
            View textViever = cevaplarimRecyclerView.getLayoutManager().findViewByPosition(position);
            TextView begeniSayisiTxt=textViever.findViewById(R.id.yorumCevapBegeniSayisiTxt);
            if(durum == "secilmedi") {
                begeniButonu.setColorFilter(Color.argb(255, 255, 172, 51));
                likesPresenter.loadLikes(answersModels.get(position).getCevap_id(),kullanici.getId(),1,1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        begeniSayisiTxt.setText(String.valueOf(likeModel.getBegeniSayisi()));
                    }
                },300);
                begeniButonu.setTag("secildi");
            } else if(durum == "secildi"){
                begeniButonu.setColorFilter(Color.argb(255, 223, 225, 229));
               likesPresenter.loadLikes(answersModels.get(position).getCevap_id(),kullanici.getId(),1,0);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        begeniSayisiTxt.setText(String.valueOf(likeModel.getBegeniSayisi()));
                    }
                },500);
                begeniButonu.setTag("secilmedi");
            }
        });

        return view;
    }

    @Override
    public void onGetResult(List<AnswersModel> data) {
        adapterProfilAnswers= new AdapterProfilAnswers(data,getActivity(),itemClickListener);
        adapterProfilAnswers.notifyDataSetChanged();
        cevaplarimRecyclerView.setAdapter(adapterProfilAnswers);
        answersModels=data;
    }

    @Override
    public void onErrorLoading() {
        System.out.println("Bağlantı Hatası(Sorularım Fragment)");
    }

    @Override
    public void onGetLike(LikeModel items) {
        this.likeModel=items;
    }
}
