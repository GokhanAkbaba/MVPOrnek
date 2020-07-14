package com.example.mvpornek.Fragment.NavBarFragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpornek.Activity.HomeActivity;
import com.example.mvpornek.Adapter.QuestionAdapterActivity;
import com.example.mvpornek.Fragment.CommentBottomDialogFragment;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Models.QuestionModel;
import com.example.mvpornek.Model.InternetBaglantiKontrol.InternetConnectionInteractorImpl;
import com.example.mvpornek.Presenter.InternetBaglantiKontrol.InternetConnectionPresenterImpl;
import com.example.mvpornek.Presenter.QuestionMenuPresenterImpl;
import com.example.mvpornek.Presenter.Soru.QuestionPresenterImpl;
import com.example.mvpornek.Presenter.QuestionsDeletePresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.InternetConnectionView;
import com.example.mvpornek.View.QuestionMenuView;
import com.example.mvpornek.View.QuestionView;
import com.example.mvpornek.View.QuestionsDeleteView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class HomeFragment extends BottomSheetDialogFragment implements View.OnClickListener, QuestionMenuView, QuestionView, InternetConnectionView, QuestionsDeleteView {

    private static final int Adres = 1;
    private static final int Yemek = 2;
    private static final int Spor = 3;
    private static final int Tatil = 4;
    private static final int Alisveris = 5;
    private static final int Sanat = 6;
    private static final int Yazilim = 7;
    private static final int Gezi = 8;
    private static final int FilmDizi = 9;


    ImageButton tatilButon,adresButon,sporButon,yemekButon,filmDiziButon,alisverisButon;
    Boolean checkYemekEtiket =false,checkAdresEtiket = false,checkSporEtiket = false,
            checkTatilEtiket = false,checkAlisverisEtiket = false,checkFilmDiziEtiket = false;
    Boolean checkSoruAlani=false;


    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerViewSoruAlani;
    List<QuestionModel> questionModels;
    QuestionAdapterActivity questionAdapterActivity;
    QuestionPresenterImpl questionPresenter;
    QuestionAdapterActivity.ItemClickListener itemClickListener;
    QuestionAdapterActivity.ItemClickListener itemLongClickListener;
    QuestionMenuPresenterImpl questionMenuPresenter;
    ProgressBar progressBarAnaSayfa;
    Kullanici kullanici;
    InternetConnectionPresenterImpl internetConnectionPresenter;
    QuestionsDeletePresenterImpl questionsDeletePresenter;
    TextView textViewAlisveris,textViewAdres,textViewFilmDizi,textViewSpor,textViewTatil,textViewYemek,soruAlaniTextView;


    
    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        kullanici= SharedPrefManager.getInstance(getActivity()).getKullanici();
        questionPresenter = new QuestionPresenterImpl(this);
        questionPresenter.loadData(kullanici.getId());
        questionMenuPresenter=new QuestionMenuPresenterImpl(this);
        internetConnectionPresenter=new InternetConnectionPresenterImpl(this,new InternetConnectionInteractorImpl(getActivity()));
        questionsDeletePresenter=new QuestionsDeletePresenterImpl(this);
        itemClickListener =((vw,position)-> {
                    int soruId=questionModels.get(position).getId();
                    showBottomSheet(soruId);
        });

        itemLongClickListener =((vw,position)-> {
            ConstraintLayout sorularIcerik=vw.findViewById(R.id.sorularIcerikLayout);
            if(kullanici.getId() == questionModels.get(position).getKullaniciId()) {
                if(checkSoruAlani == false) {
                    sorularIcerik.setBackgroundColor(getResources().getColor(R.color.colorGrayPrimay));
                    checkSoruAlani = true;
                }
                final CharSequence[] items = {"Sil"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                          questionsDeletePresenter.deleteOptions(questionModels.get(position).getId());
                    }
                });
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if(checkSoruAlani == true) {
                            sorularIcerik.setBackgroundColor(getResources().getColor(R.color.white));
                            checkSoruAlani=false;
                        }
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_anasayfa, container, false);
        alisverisButon=view.findViewById(R.id.anasayfa_alisveris_btn);
        alisverisButon.setOnClickListener(this);
        yemekButon=view.findViewById(R.id.anasayfa_yemek_btn);
        yemekButon.setOnClickListener(this);
        tatilButon=view.findViewById(R.id.anasayfa_tatil_btn);
        tatilButon.setOnClickListener(this);
        adresButon=view.findViewById(R.id.anasayfa_adres_btn);
        adresButon.setOnClickListener(this);
        sporButon=view.findViewById(R.id.anasayfa_spor_btn);
        sporButon.setOnClickListener(this);
        filmDiziButon=view.findViewById(R.id.anasayfa_filmDizi_btn);
        filmDiziButon.setOnClickListener(this);
        swipeRefreshLayout=view.findViewById(R.id.swiperefreshAnasayfa);
        textViewAlisveris=view.findViewById(R.id.alisverisAnaSayfaTxt);
        textViewAdres=view.findViewById(R.id.adresAnaSayfaTxt);
        textViewFilmDizi=view.findViewById(R.id.filmDiziAnaSayfaTxt);
        textViewSpor=view.findViewById(R.id.sporAnaSayfaTxt);
        textViewTatil=view.findViewById(R.id.tatilAnaSayfaTxt);
        textViewYemek=view.findViewById(R.id.yemekAnaSayfaTxt);
        soruAlaniTextView=view.findViewById(R.id.recyclerViewSoruAlaniText);
        progressBarAnaSayfa=getActivity().findViewById(R.id.progressBarAnaSayfa);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                internetConnectionPresenter.internetBaglantiKontrolu();
                questionPresenter.loadData(kullanici.getId());

            }
        });

        recyclerViewSoruAlani=(RecyclerView) view.findViewById(R.id.recyclerViewSoruAlani);
        recyclerViewSoruAlani.setAdapter(questionAdapterActivity);
        recyclerViewSoruAlani.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewSoruAlani.setOnClickListener(this);
        return view;
    }
    public void showBottomSheet(int soruId) {
        CommentBottomDialogFragment commentBottomDialogFragment =
                CommentBottomDialogFragment.newInstance(soruId);
        commentBottomDialogFragment.show(getActivity().getSupportFragmentManager(),
                CommentBottomDialogFragment.TAG);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

            case R.id.anasayfa_alisveris_btn:
                if(checkAlisverisEtiket == false)
                {
                    alisverisButon.setImageDrawable(getActivity().getDrawable(R.mipmap.mavi_kapat_icon));
                    anaSayfaButonGizle(yemekButon,tatilButon,adresButon,filmDiziButon,sporButon);
                    anaSayfaTextRenkGizle(textViewAlisveris,textViewAdres,textViewFilmDizi,textViewSpor,textViewTatil,textViewYemek);
                    questionMenuPresenter.loadData(Alisveris);
                    checkAlisverisEtiket = true;
                }
                else
                {
                    alisverisButon.setImageDrawable(getActivity().getDrawable(R.mipmap.yemek_icon));
                    anaSayfaButonGoster(yemekButon,tatilButon,adresButon,filmDiziButon,sporButon);
                    anaSayfaTextRenkGoster(textViewAlisveris,textViewAdres,textViewFilmDizi,textViewSpor,textViewTatil,textViewYemek);
                    questionPresenter.loadData(kullanici.getId());
                    checkAlisverisEtiket=false;
                }
                break;
            case R.id.anasayfa_yemek_btn:
                if(checkYemekEtiket == false)
                {
                    yemekButon.setImageDrawable(getActivity().getDrawable(R.mipmap.mavi_kapat_icon));
                    anaSayfaButonGizle(alisverisButon,tatilButon,adresButon,filmDiziButon,sporButon);
                    anaSayfaTextRenkGizle(textViewYemek,textViewAdres,textViewFilmDizi,textViewSpor,textViewTatil,textViewAlisveris);
                    questionMenuPresenter.loadData(Yemek);
                    checkYemekEtiket = true;
                }
                else
                {
                    yemekButon.setImageDrawable(getActivity().getDrawable(R.mipmap.yemek_icon));
                    anaSayfaButonGoster(alisverisButon,tatilButon,adresButon,filmDiziButon,sporButon);
                    anaSayfaTextRenkGoster(textViewYemek,textViewAdres,textViewFilmDizi,textViewSpor,textViewTatil,textViewAlisveris);
                    questionPresenter.loadData(kullanici.getId());
                    checkYemekEtiket=false;
                }
                break;
            case R.id.anasayfa_tatil_btn:
                if(checkTatilEtiket == false)
                {
                    tatilButon.setImageDrawable(getActivity().getDrawable(R.mipmap.mavi_kapat_icon));
                    anaSayfaButonGizle(alisverisButon,adresButon,filmDiziButon,sporButon,yemekButon);
                    anaSayfaTextRenkGizle(textViewTatil,textViewAdres,textViewFilmDizi,textViewSpor,textViewYemek,textViewAlisveris);
                    questionMenuPresenter.loadData(Tatil);
                    checkTatilEtiket = true;
                }
                else
                {
                    tatilButon.setImageDrawable(getActivity().getDrawable(R.mipmap.tatil_icon));
                    anaSayfaButonGoster(alisverisButon,adresButon,filmDiziButon,sporButon,yemekButon);
                    anaSayfaTextRenkGoster(textViewTatil,textViewAdres,textViewFilmDizi,textViewSpor,textViewYemek,textViewAlisveris);
                    questionPresenter.loadData(kullanici.getId());
                    checkTatilEtiket=false;
                }
                break;
            case R.id.anasayfa_adres_btn:
                if(checkAdresEtiket == false)
                {
                    adresButon.setImageDrawable(getActivity().getDrawable(R.mipmap.mavi_kapat_icon));
                    anaSayfaTextRenkGizle(textViewAdres,textViewTatil,textViewFilmDizi,textViewSpor,textViewYemek,textViewAlisveris);
                    anaSayfaButonGizle(alisverisButon,filmDiziButon,sporButon,yemekButon,tatilButon);
                    questionMenuPresenter.loadData(Adres);
                    checkAdresEtiket = true;
                }
                else
                {
                    adresButon.setImageDrawable(getActivity().getDrawable(R.mipmap.adres_icon));
                    anaSayfaButonGoster(alisverisButon,filmDiziButon,sporButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGoster(textViewAdres,textViewTatil,textViewFilmDizi,textViewSpor,textViewYemek,textViewAlisveris);
                    questionPresenter.loadData(kullanici.getId());
                    checkAdresEtiket=false;
                }
                break;
            case R.id.anasayfa_spor_btn:
                if(checkSporEtiket == false)
                {
                    sporButon.setImageDrawable(getActivity().getDrawable(R.mipmap.mavi_kapat_icon));
                    anaSayfaButonGizle(alisverisButon,filmDiziButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGizle(textViewSpor,textViewAdres,textViewTatil,textViewFilmDizi,textViewYemek,textViewAlisveris);
                    questionMenuPresenter.loadData(Spor);
                    checkSporEtiket = true;
                }
                else
                {
                    sporButon.setImageDrawable(getActivity().getDrawable(R.mipmap.spor_icon));
                    anaSayfaButonGoster(alisverisButon,filmDiziButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGoster(textViewSpor,textViewAdres,textViewTatil,textViewFilmDizi,textViewYemek,textViewAlisveris);
                    questionPresenter.loadData(kullanici.getId());
                    checkSporEtiket=false;
                }
                break;
            case R.id.anasayfa_filmDizi_btn:
                if(checkFilmDiziEtiket == false)
                {
                    filmDiziButon.setImageDrawable(getActivity().getDrawable(R.mipmap.mavi_kapat_icon));
                    anaSayfaButonGizle(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGizle(textViewFilmDizi,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris);
                    questionMenuPresenter.loadData(FilmDizi);
                    checkFilmDiziEtiket = true;
                }
                else
                {
                    filmDiziButon.setImageDrawable(getActivity().getDrawable(R.mipmap.spor_icon));
                    anaSayfaButonGoster(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGoster(textViewFilmDizi,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris);
                    questionPresenter.loadData(kullanici.getId());
                    checkFilmDiziEtiket=false;
                }
                break;

        }
    }

    public void anaSayfaButonGizle(ImageButton button1,ImageButton button2,ImageButton button3,ImageButton button4,ImageButton button5){
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
    }
    public void anaSayfaButonGoster(ImageButton button1,ImageButton button2,ImageButton button3,ImageButton button4,ImageButton button5){
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
    }
    public void anaSayfaTextRenkGizle(TextView textView1,TextView textView2,TextView textView3,TextView textView4,TextView textView5,TextView textView6){
        textView1.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView2.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView3.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView4.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView5.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView6.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
    }
    public void anaSayfaTextRenkGoster(TextView textView1,TextView textView2,TextView textView3,TextView textView4,TextView textView5,TextView textView6){
        textView1.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView2.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView3.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView4.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView5.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView6.setTextColor(getResources().getColor(R.color.renkSiyah));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_items,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onGetResult(List<QuestionModel> data) {
        questionAdapterActivity= new QuestionAdapterActivity(data,getActivity(),itemClickListener,itemLongClickListener);
        questionAdapterActivity.notifyDataSetChanged();
        recyclerViewSoruAlani.setAdapter(questionAdapterActivity);
        questionModels=data;
        soruAlaniTextView.setVisibility(View.INVISIBLE);
        ((HomeActivity)getActivity()).init(View.INVISIBLE);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getActivity(),"Sorular Yüklenirken Hata oluştu.",Toast.LENGTH_LONG).show();
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
        soruAlaniTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void internetBaglantiHatasi() {
        Toast.makeText(getActivity(),"İnternet Bağlantınızı Kontrol Ediniz",Toast.LENGTH_LONG).show();
    }

    @Override
    public void internetBaglantisi() {

    }

    @Override
    public void showSuccesMessage() {
        Toast.makeText(getActivity(),"Sorunuz Silinidi.",Toast.LENGTH_LONG).show();
        questionPresenter.loadData(kullanici.getId());
    }

    @Override
    public void showFailedMessage() {
        Toast.makeText(getActivity(),"Sorunuz Silinirken Bir Hata Oluştu",Toast.LENGTH_LONG).show();
        questionPresenter.loadData(kullanici.getId());
    }
}
