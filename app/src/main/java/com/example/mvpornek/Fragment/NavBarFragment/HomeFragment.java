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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpornek.Activity.HomeActivity;
import com.example.mvpornek.Adapter.QuestionAdapterActivity;
import com.example.mvpornek.Fragment.Comment.CommentBottomDialogFragment;
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
    private static final int Teknoloji = 10;
    private static final int Oyun = 11;
    private static final int Saglik = 12;
    private static final int Muzik = 13;
    private static final int Egitim = 14;
    private static final int Tarih = 15;
    private static final int Moda = 16;
    private static final int Oto = 17;





    ImageButton tatilButon,adresButon,sporButon,yemekButon,filmDiziButon,alisverisButon;
    RelativeLayout anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn;
    RelativeLayout anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn;
    Boolean checkYemekEtiket =false,checkAdresEtiket = false,checkSporEtiket = false,
            checkTatilEtiket = false,checkAlisverisEtiket = false,checkFilmDiziEtiket = false;
    Boolean checkSoruAlani=false,checkTeknoEtiket=false,checkOyunEtiket=false,checkSaglikEtiket=false,checkMuzikEtiket=false,checkEgitimEtiket=false,checkTarihEtiket=false,checkModaEtiket=false,checkOtoEtiket=false,
    checkYazilimEtiket=false;
    ImageView anaSayfaYazilimButonIamge,anaSayfaOtoButonIamge,anaSayfaModaButonIamge,anaSayfaTarihButonIamge,anaSayfaEgitimButonIamge;
    ImageView anaSayfaMuzikButonIamge,anaSayfaSaglikButonIamge,anaSayfaOyunButonIamge,anaSayfaTeknoButonIamge;
    int refreshControl;


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
    TextView teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt;


    
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
                    int soruSoranKullaniciId=questionModels.get(position).getKullaniciId();
                    showBottomSheet(soruId,soruSoranKullaniciId);
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
        anasayfa_tekno_btn=view.findViewById(R.id.anasayfa_tekno_btn);
        anasayfa_tekno_btn.setOnClickListener(this);
        anasayfa_oyun_btn=view.findViewById(R.id.anasayfa_oyun_btn);
        anasayfa_oyun_btn.setOnClickListener(this);
        anasayfa_saglik_btn=view.findViewById(R.id.anasayfa_saglik_btn);
        anasayfa_saglik_btn.setOnClickListener(this);
        anasayfa_muzik_btn=view.findViewById(R.id.anasayfa_muzik_btn);
        anasayfa_muzik_btn.setOnClickListener(this);
        anasayfa_egitim_btn=view.findViewById(R.id.anasayfa_egitim_btn);
        anasayfa_egitim_btn.setOnClickListener(this);
        anasayfa_tarih_btn=view.findViewById(R.id.anasayfa_tarih_btn);
        anasayfa_tarih_btn.setOnClickListener(this);
        anasayfa_moda_btn=view.findViewById(R.id.anasayfa_moda_btn);
        anasayfa_moda_btn.setOnClickListener(this);
        anasayfa_oto_btn=view.findViewById(R.id.anasayfa_oto_btn);
        anasayfa_oto_btn.setOnClickListener(this);
        anasayfa_yazilim_btn=view.findViewById(R.id.anasayfa_yazilim_btn);
        anasayfa_yazilim_btn.setOnClickListener(this);
        anaSayfaYazilimButonIamge=view.findViewById(R.id.anaSayfaYazilimButonIamge);
        anaSayfaOtoButonIamge=view.findViewById(R.id.anaSayfaOtoButonIamge);
        anaSayfaModaButonIamge=view.findViewById(R.id.anaSayfaModaButonIamge);
        anaSayfaTarihButonIamge=view.findViewById(R.id.anaSayfaTarihButonIamge);
        anaSayfaEgitimButonIamge=view.findViewById(R.id.anaSayfaEgitimButonIamge);
        anaSayfaMuzikButonIamge=view.findViewById(R.id.anaSayfaMuzikButonIamge);
        anaSayfaSaglikButonIamge=view.findViewById(R.id.anaSayfaSaglikButonIamge);
        anaSayfaOyunButonIamge=view.findViewById(R.id.anaSayfaOyunButonIamge);
        anaSayfaTeknoButonIamge=view.findViewById(R.id.anaSayfaTeknoButonIamge);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(refreshControl != -1){
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    questionMenuPresenter.loadData(refreshControl);
                }else{
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    questionPresenter.loadData(kullanici.getId());

                }
            }
        });

        recyclerViewSoruAlani=(RecyclerView) view.findViewById(R.id.recyclerViewSoruAlani);
        recyclerViewSoruAlani.setAdapter(questionAdapterActivity);
        recyclerViewSoruAlani.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewSoruAlani.setOnClickListener(this);
        return view;
    }

    public void refreshMenuData(SwipeRefreshLayout swipeRefreshLayout, int valueId){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                internetConnectionPresenter.internetBaglantiKontrolu();
                questionMenuPresenter.loadData(valueId);

            }
        });
    }


    public void showBottomSheet(int soruId,int soruSoranKullaniciId) {
        CommentBottomDialogFragment commentBottomDialogFragment =
                CommentBottomDialogFragment.newInstance(soruId,soruSoranKullaniciId);
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
                    anaSayfaTextRenkGizle(textViewAlisveris,textViewAdres,textViewFilmDizi,textViewSpor,textViewTatil,textViewYemek,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionMenuPresenter.loadData(Alisveris);
                    checkAlisverisEtiket = true;
                    refreshControl=Alisveris;
                }
                else
                {
                    alisverisButon.setImageDrawable(getActivity().getDrawable(R.mipmap.yemek_icon));
                    anaSayfaButonGoster(yemekButon,tatilButon,adresButon,filmDiziButon,sporButon);
                    anaSayfaTextRenkGoster(textViewAlisveris,textViewAdres,textViewFilmDizi,textViewSpor,textViewTatil,textViewYemek,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionPresenter.loadData(kullanici.getId());
                    checkAlisverisEtiket=false;
                    refreshControl=-1;
                }
                break;
            case R.id.anasayfa_yemek_btn:
                if(checkYemekEtiket == false)
                {
                    yemekButon.setImageDrawable(getActivity().getDrawable(R.mipmap.mavi_kapat_icon));
                    anaSayfaButonGizle(alisverisButon,tatilButon,adresButon,filmDiziButon,sporButon);
                    anaSayfaTextRenkGizle(textViewYemek,textViewAlisveris,textViewAdres,textViewFilmDizi,textViewSpor,textViewTatil,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionMenuPresenter.loadData(Yemek);
                    checkYemekEtiket = true;
                    refreshControl=Yemek;
                }
                else
                {
                    yemekButon.setImageDrawable(getActivity().getDrawable(R.mipmap.yemek_icon));
                    anaSayfaButonGoster(alisverisButon,tatilButon,adresButon,filmDiziButon,sporButon);
                    anaSayfaTextRenkGoster(textViewYemek,textViewAlisveris,textViewAdres,textViewFilmDizi,textViewSpor,textViewTatil,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionPresenter.loadData(kullanici.getId());
                    checkYemekEtiket=false;
                    refreshControl=-1;
                }
                break;
            case R.id.anasayfa_tatil_btn:
                if(checkTatilEtiket == false)
                {
                    tatilButon.setImageDrawable(getActivity().getDrawable(R.mipmap.mavi_kapat_icon));
                    anaSayfaButonGizle(alisverisButon,adresButon,filmDiziButon,sporButon,yemekButon);
                    anaSayfaTextRenkGizle(textViewTatil,textViewYemek,textViewAlisveris,textViewAdres,textViewFilmDizi,textViewSpor,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionMenuPresenter.loadData(Tatil);
                    checkTatilEtiket = true;
                }
                else
                {
                    tatilButon.setImageDrawable(getActivity().getDrawable(R.mipmap.tatil_icon));
                    anaSayfaButonGoster(alisverisButon,adresButon,filmDiziButon,sporButon,yemekButon);
                    anaSayfaTextRenkGoster(textViewTatil,textViewYemek,textViewAlisveris,textViewAdres,textViewFilmDizi,textViewSpor,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionPresenter.loadData(kullanici.getId());
                    checkTatilEtiket=false;
                }
                break;
            case R.id.anasayfa_adres_btn:
                if(checkAdresEtiket == false)
                {
                    adresButon.setImageDrawable(getActivity().getDrawable(R.mipmap.mavi_kapat_icon));
                    anaSayfaTextRenkGizle(textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,textViewFilmDizi,textViewSpor,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    anaSayfaButonGizle(alisverisButon,filmDiziButon,sporButon,yemekButon,tatilButon);
                    questionMenuPresenter.loadData(Adres);
                    checkAdresEtiket = true;
                }
                else
                {
                    adresButon.setImageDrawable(getActivity().getDrawable(R.mipmap.adres_icon));
                    anaSayfaButonGoster(alisverisButon,filmDiziButon,sporButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGoster(textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,textViewFilmDizi,textViewSpor,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionPresenter.loadData(kullanici.getId());
                    checkAdresEtiket=false;
                }
                break;
            case R.id.anasayfa_spor_btn:
                if(checkSporEtiket == false)
                {
                    sporButon.setImageDrawable(getActivity().getDrawable(R.mipmap.mavi_kapat_icon));
                    anaSayfaButonGizle(alisverisButon,filmDiziButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGizle(textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,textViewFilmDizi,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionMenuPresenter.loadData(Spor);
                    checkSporEtiket = true;
                }
                else
                {
                    sporButon.setImageDrawable(getActivity().getDrawable(R.mipmap.spor_icon));
                    anaSayfaButonGoster(alisverisButon,filmDiziButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGoster(textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,textViewFilmDizi,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionPresenter.loadData(kullanici.getId());
                    checkSporEtiket=false;
                }
                break;
            case R.id.anasayfa_tekno_btn:
                if(checkTeknoEtiket == false)
                {
                    anaSayfaTeknoButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGizle(teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,textViewFilmDizi,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionMenuPresenter.loadData(Teknoloji);
                    checkTeknoEtiket = true;
                }
                else
                {
                    anaSayfaTeknoButonIamge.setImageResource(R.drawable.ic_teknoloji_svg);
                    anaSayfaButonGoster(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGoster(teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,textViewFilmDizi,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionPresenter.loadData(kullanici.getId());
                    checkTeknoEtiket=false;
                }
                break;
            case R.id.anasayfa_filmDizi_btn:
                if(checkFilmDiziEtiket == false)
                {
                    filmDiziButon.setImageDrawable(getActivity().getDrawable(R.mipmap.mavi_kapat_icon));
                    anaSayfaButonGizle(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGizle(textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionMenuPresenter.loadData(FilmDizi);
                    checkFilmDiziEtiket = true;
                }
                else
                {
                    filmDiziButon.setImageDrawable(getActivity().getDrawable(R.mipmap.spor_icon));
                    anaSayfaButonGoster(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGoster(textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionPresenter.loadData(kullanici.getId());
                    checkFilmDiziEtiket=false;
                }
                break;
            case R.id.anasayfa_oyun_btn:
                if(checkOyunEtiket == false)
                {
                    anaSayfaOyunButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGizle(oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionMenuPresenter.loadData(Oyun);
                    checkOyunEtiket = true;
                }
                else
                {
                    anaSayfaOyunButonIamge.setImageResource(R.drawable.ic_oyun_svg);
                    anaSayfaButonGoster(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGoster(oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionPresenter.loadData(kullanici.getId());
                    checkOyunEtiket=false;
                }
                break;
            case R.id.anasayfa_saglik_btn:
                if(checkSaglikEtiket == false)
                {

                    anaSayfaSaglikButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGizle(saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionMenuPresenter.loadData(Saglik);
                    checkSaglikEtiket = true;
                }
                else
                {
                    anaSayfaSaglikButonIamge.setImageResource(R.drawable.ic_saglik_svg);
                    anaSayfaButonGoster(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGoster(saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionPresenter.loadData(kullanici.getId());
                    checkSaglikEtiket=false;
                }
                break;
            case R.id.anasayfa_muzik_btn:
                if(checkMuzikEtiket == false)
                {
                    anaSayfaMuzikButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGizle(muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionMenuPresenter.loadData(Muzik);
                    checkMuzikEtiket = true;
                }
                else
                {
                    anaSayfaMuzikButonIamge.setImageResource(R.drawable.ic_muzik_svg);
                    anaSayfaButonGoster(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGoster(muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionPresenter.loadData(kullanici.getId());
                    checkMuzikEtiket=false;
                }
                break;
            case R.id.anasayfa_egitim_btn:
                if(checkEgitimEtiket == false)
                {
                    anaSayfaEgitimButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGizle(egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionMenuPresenter.loadData(Egitim);
                    checkEgitimEtiket = true;
                }
                else
                {
                    anaSayfaEgitimButonIamge.setImageResource(R.drawable.ic_book);
                    anaSayfaButonGoster(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGoster(egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionPresenter.loadData(kullanici.getId());
                    checkEgitimEtiket=false;
                }
                break;
            case R.id.anasayfa_tarih_btn:
                if(checkTarihEtiket == false)
                {
                    anaSayfaTarihButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGizle(tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionMenuPresenter.loadData(Tarih);
                    checkTarihEtiket = true;
                }
                else
                {
                    anaSayfaTarihButonIamge.setImageResource(R.drawable.ic_tarih_svg);
                    anaSayfaButonGoster(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGoster(tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionPresenter.loadData(kullanici.getId());
                    checkTarihEtiket=false;
                }
                break;
            case R.id.anasayfa_moda_btn:
                if(checkModaEtiket == false)
                {
                    anaSayfaModaButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGizle(modaAnaSayfaTxt,tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionMenuPresenter.loadData(Moda);
                    checkModaEtiket = true;
                }
                else
                {
                    anaSayfaModaButonIamge.setImageResource(R.drawable.ic_moda_svg);
                    anaSayfaButonGoster(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGoster(modaAnaSayfaTxt,tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,otoAnaSayfaTxt,yazilimAnaSayfaTxt);
                    questionPresenter.loadData(kullanici.getId());
                    checkModaEtiket=false;
                }
                break;
            case R.id.anasayfa_oto_btn:
                if(checkOtoEtiket == false)
                {
                    anaSayfaOtoButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGizle(otoAnaSayfaTxt,modaAnaSayfaTxt,tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,yazilimAnaSayfaTxt);
                    questionMenuPresenter.loadData(Oto);
                    checkOtoEtiket = true;
                }
                else
                {
                    anaSayfaOtoButonIamge.setImageResource(R.drawable.ic_auto_svg);
                    anaSayfaButonGoster(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGoster(otoAnaSayfaTxt,modaAnaSayfaTxt,tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,yazilimAnaSayfaTxt);
                    questionPresenter.loadData(kullanici.getId());
                    checkOtoEtiket=false;
                }
                break;
            case R.id.anasayfa_yazilim_btn:
                if(checkYazilimEtiket == false)
                {
                    anaSayfaYazilimButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGizle(yazilimAnaSayfaTxt,otoAnaSayfaTxt,modaAnaSayfaTxt,tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris);
                    questionMenuPresenter.loadData(Yazilim);
                    checkYazilimEtiket = true;
                }
                else
                {
                    anaSayfaYazilimButonIamge.setImageResource(R.drawable.ic_engineer);
                    anaSayfaButonGoster(alisverisButon,sporButon,adresButon,yemekButon,tatilButon);
                    anaSayfaTextRenkGoster(yazilimAnaSayfaTxt,otoAnaSayfaTxt,modaAnaSayfaTxt,tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris);
                    questionPresenter.loadData(kullanici.getId());
                    checkYazilimEtiket=false;
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
    public void anaSayfaTextRenkGizle(TextView textView1,TextView textView2,TextView textView3,TextView textView4,TextView textView5,TextView textView6,TextView textView7,TextView textView8,TextView textView9,TextView textView10,TextView textView11,TextView textView12,TextView textView13,TextView textView14,TextView textView15){
        textView1.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView2.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView3.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView4.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView5.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView6.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView7.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView8.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView9.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView10.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView11.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView12.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView13.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView14.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView15.setTextColor(getResources().getColor(R.color.ayarlarGrisi));

    }
    public void anaSayfaTextRenkGoster(TextView textView1,TextView textView2,TextView textView3,TextView textView4,TextView textView5,TextView textView6,TextView textView7,TextView textView8,TextView textView9,TextView textView10,TextView textView11,TextView textView12,TextView textView13,TextView textView14,TextView textView15){
        textView1.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView2.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView3.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView4.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView5.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView6.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView7.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView8.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView9.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView10.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView11.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView12.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView13.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView14.setTextColor(getResources().getColor(R.color.renkSiyah));
        textView15.setTextColor(getResources().getColor(R.color.renkSiyah));

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
        HomeActivity.getInstance().init(View.INVISIBLE);
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
       ////
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
