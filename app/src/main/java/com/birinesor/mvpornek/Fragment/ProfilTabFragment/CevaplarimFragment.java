package com.birinesor.mvpornek.Fragment.ProfilTabFragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import android.widget.Toast;

import com.birinesor.mvpornek.Adapter.AdapterProfilAnswers;
import com.birinesor.mvpornek.Models.AnswersModel;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Presenter.Like.LikesPresenterImpl;
import com.birinesor.mvpornek.Presenter.ProfilCevaplarim.ProfilAnswersPresenterImpl;
import com.birinesor.mvpornek.Presenter.YorumSil.CommentDeletePresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.Response.LikeModel;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.View.CommentDeleteView;
import com.birinesor.mvpornek.View.LikesView;
import com.birinesor.mvpornek.View.ProfilAnswersView;

import java.util.List;


public class CevaplarimFragment extends Fragment implements ProfilAnswersView,LikesView, CommentDeleteView {

    private static final String ARG_PARAM1 = "param1";


    private int mParam1;
    List<AnswersModel> answersModels;
    AdapterProfilAnswers adapterProfilAnswers;
    CommentDeletePresenterImpl commentDeletePresenter;
    AdapterProfilAnswers.ItemClickListener itemClickListener;
    AdapterProfilAnswers.ItemClickListener cevabımSilClickListener;
    ProfilAnswersPresenterImpl profilAnswersPresenter;
    RelativeLayout cevaplarimContent;
    Boolean checkSoruAlani=false;
    public static TextView begeniSayisiTxt;
    public Object durum;
    Kullanici kullanici;
    SwipeRefreshLayout swipeRefreshLayout;
    LikesPresenterImpl likesPresenter;
    LikeModel likeModel;
    private RecyclerView cevaplarimRecyclerView;

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
        commentDeletePresenter=new CommentDeletePresenterImpl(this);
        swipeRefreshLayout=view.findViewById(R.id.swiperefreshFragmentCevaplarim);
        swipeRefreshLayout.setColorSchemeResources(R.color.uygulamaMavisi);
        cevaplarimRecyclerView=view.findViewById(R.id.profilCevaplarimRecyclerView);
        cevaplarimContent=view.findViewById(R.id.profilCevaplarimAlaniContent);
        cevaplarimRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cevaplarimRecyclerView.setAdapter(adapterProfilAnswers);
        likesPresenter=new LikesPresenterImpl(this);
        itemClickListener =((vw,position)-> {
            ImageView begeniButonu=vw.findViewById(R.id.profilCevapImageButton);
            durum=begeniButonu.getTag();
            View textViever = cevaplarimRecyclerView.getLayoutManager().findViewByPosition(position);
            begeniSayisiTxt=textViever.findViewById(R.id.yorumCevapBegeniSayisiTxt);
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
        cevabımSilClickListener =((vw,position)-> {
            ConstraintLayout sorularIcerik=vw.findViewById(R.id.profilCevaplarIcerikLayout);
            if(kullanici.getId() == answersModels.get(position).getKullaniciId()) {
                if(!checkSoruAlani) {
                    sorularIcerik.setBackgroundColor(getResources().getColor(R.color.colorGrayPrimay));
                    checkSoruAlani = true;
                }
                final CharSequence[] items = {"Sil"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setItems(items, (dialog, item) -> commentDeletePresenter.deleteOptions(answersModels.get(position).getCevap_id()));
                builder.setOnDismissListener(dialogInterface -> {
                    if(checkSoruAlani) {
                        sorularIcerik.setBackgroundColor(getResources().getColor(R.color.white));
                        checkSoruAlani=false;
                    }
                    profilAnswersPresenter.loadData(mParam1);
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(() -> {
            profilAnswersPresenter.loadData(mParam1);
        });
        return view;
    }

    @Override
    public void onGetResult(List<AnswersModel> data) {
        adapterProfilAnswers= new AdapterProfilAnswers(data,getActivity(),itemClickListener,cevabımSilClickListener);
        adapterProfilAnswers.notifyDataSetChanged();
        cevaplarimRecyclerView.setAdapter(adapterProfilAnswers);
        cevaplarimContent.setVisibility(View.INVISIBLE);
        answersModels=data;
    }

    @Override
    public void onGetResultControl() {
        cevaplarimContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void onErrorLoading() {
        System.out.println("Bağlantı Hatası(Sorularım Fragment)");
    }

    @Override
    public void onProfilAnswersShow() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void onProfilAnswersHide() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetLike(LikeModel items) {
        this.likeModel=items;
        if(durum == "secilmedi") {
            begeniSayisiTxt.setText(String.valueOf(likeModel.getBegeniSayisi()));
        } else if(durum == "secildi"){
            begeniSayisiTxt.setText(String.valueOf(likeModel.getBegeniSayisi()));
        }
    }

    @Override
    public void showSuccesMessage() {
        Toast.makeText(getActivity(),"Cevabınız Başarı ile Silindi",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedMessage() {
        Toast.makeText(getActivity(),"Cevabınız Silinirken Hata Oluştu",Toast.LENGTH_SHORT).show();
    }
}
