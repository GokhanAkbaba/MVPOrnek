package com.birinesor.mvpornek.Fragment.NavBarFragment;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Activity.RecyclerItemTouchHelper;
import com.birinesor.mvpornek.Adapter.QuestionAdapterActivity;
import com.birinesor.mvpornek.Fragment.Comment.CommentBottomDialogFragment;
import com.birinesor.mvpornek.Model.InternetBaglantiKontrol.InternetConnectionInteractorImpl;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Models.QuestionModel;
import com.birinesor.mvpornek.Presenter.InternetBaglantiKontrol.InternetConnectionPresenterImpl;
import com.birinesor.mvpornek.Presenter.QuestionMenuPresenterImpl;
import com.birinesor.mvpornek.Presenter.QuestionsDeletePresenterImpl;
import com.birinesor.mvpornek.Presenter.Soru.QuestionPresenterImpl;
import com.birinesor.mvpornek.Presenter.SoruGorunum.SoruGorunumPresenterImpl;
import com.birinesor.mvpornek.Presenter.SoruOnay.SoruOnayDurumGuncellePresenter;
import com.birinesor.mvpornek.Presenter.SoruOnay.SoruOnayDurumGuncellePresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.View.InternetConnectionView;
import com.birinesor.mvpornek.View.QuestionMenuView;
import com.birinesor.mvpornek.View.QuestionView;
import com.birinesor.mvpornek.View.QuestionsDeleteView;
import com.birinesor.mvpornek.View.SoruGorunumView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeFragment extends BottomSheetDialogFragment implements View.OnClickListener, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, QuestionMenuView, QuestionView, InternetConnectionView, QuestionsDeleteView, SoruGorunumView {

    private static final int Adres = 1;
    private static final int Yemek = 2;
    private static final int Spor = 3;
    private static final int Tatil = 4;
    private static final int Alisveris = 5;
    private static final int Yazilim = 7;
    private static final int FilmDizi = 9;
    private static final int Teknoloji = 10;
    private static final int Oyun = 11;
    private static final int Saglik = 12;
    private static final int Muzik = 13;
    private static final int Egitim = 14;
    private static final int Tarih = 15;
    private static final int Moda = 16;
    private static final int Oto = 17;
    private static final int Kripto = 18;
    private static final int Diger = 19;



    public Boolean internetKontrol=false;


    RelativeLayout anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_kripto_btn,anasayfa_diger_btn;
    RelativeLayout anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon;
    Boolean checkYemekEtiket =false,checkAdresEtiket = false,checkSporEtiket = false,
            checkTatilEtiket = false,checkAlisverisEtiket = false,checkFilmDiziEtiket = false;
    Boolean checkSoruAlani=false,checkTeknoEtiket=false,checkOyunEtiket=false,checkSaglikEtiket=false,checkMuzikEtiket=false,checkEgitimEtiket=false,checkTarihEtiket=false,checkModaEtiket=false,checkOtoEtiket=false,
    checkYazilimEtiket=false,checkKriptoEtiket=false,checkDigerEtiket=false;
    ImageView anaSayfaYazilimButonIamge,anaSayfaOtoButonIamge,anaSayfaModaButonIamge,anaSayfaTarihButonIamge,anaSayfaEgitimButonIamge,anaSayfaKriptoButonIamge;
    ImageView anaSayfaMuzikButonIamge,anaSayfaSaglikButonIamge,anaSayfaOyunButonIamge,anaSayfaTeknoButonIamge,anaSayfaFilmDiziButonIamge;
    ImageView anaSayfaAlisverisButonIamge,anaSayfaDigerButonIamge,anaSayfaYemekButonIamge,anaSayfaTatilButonIamge,anaSayfaAdresButonIamge,anaSayfaSporButonIamge;
    int refreshControl=0;
    CoordinatorLayout coordinatorLayout;

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerViewSoruAlani;
    List<QuestionModel> questionModels;
    QuestionAdapterActivity questionAdapterActivity;
    QuestionPresenterImpl questionPresenter;
    QuestionAdapterActivity.ItemClickListener itemClickListener;
    QuestionAdapterActivity.ItemClickListener itemLongClickListener;
    QuestionMenuPresenterImpl questionMenuPresenter;
    SoruGorunumPresenterImpl soruGorunumPresenter;
    ProgressBar progressBarAnaSayfa;
    Kullanici kullanici;

    InternetConnectionPresenterImpl internetConnectionPresenter;
    QuestionsDeletePresenterImpl questionsDeletePresenter;


    TextView textViewAlisveris,textViewDiger,textViewAdres,textViewFilmDizi,textViewSpor,textViewTatil,textViewYemek,soruAlaniTextView;
    TextView teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt;

    ConstraintLayout soruAlaniLayoutContent;

    
    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

        kullanici= SharedPrefManager.getInstance(getActivity()).getKullanici();
        questionPresenter = new QuestionPresenterImpl(this);
        questionMenuPresenter=new QuestionMenuPresenterImpl(this);
        internetConnectionPresenter=new InternetConnectionPresenterImpl(this,new InternetConnectionInteractorImpl(getActivity()));
        questionsDeletePresenter=new QuestionsDeletePresenterImpl(this);
        internetConnectionPresenter.internetBaglantiKontrolu();
        questionPresenter.loadData(kullanici.getId());
        itemClickListener =((vw,position)-> {
                    int soruId=questionModels.get(position).getId();
                    int soruSoranKullaniciId=questionModels.get(position).getKullaniciId();
                    showBottomSheet(soruId,soruSoranKullaniciId);
        });

        itemLongClickListener =((vw,position)-> {
            ConstraintLayout sorularIcerik=vw.findViewById(R.id.sorularIcerikLayout);
            if(kullanici.getId() == questionModels.get(position).getKullaniciId()) {
                if(!checkSoruAlani) {
                    sorularIcerik.setBackgroundColor(getResources().getColor(R.color.colorGrayPrimay));
                    checkSoruAlani = true;
                }
                final CharSequence[] items = {"Sil"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setItems(items, (dialog, item) -> questionsDeletePresenter.deleteOptions(questionModels.get(position).getId()));
                builder.setOnDismissListener(dialogInterface -> {
                    if(checkSoruAlani) {
                        sorularIcerik.setBackgroundColor(getResources().getColor(R.color.white));
                        checkSoruAlani=false;
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
        swipeRefreshLayout=view.findViewById(R.id.swiperefreshAnasayfa);
        coordinatorLayout=view.findViewById(R.id.coordinatorLayout);
        textViewAlisveris=view.findViewById(R.id.alisverisAnaSayfaTxt);
        textViewAdres=view.findViewById(R.id.adresAnaSayfaTxt);
        textViewFilmDizi=view.findViewById(R.id.filmDiziAnaSayfaTxt);
        textViewSpor=view.findViewById(R.id.sporAnaSayfaTxt);
        textViewTatil=view.findViewById(R.id.tatilAnaSayfaTxt);
        textViewYemek=view.findViewById(R.id.yemekAnaSayfaTxt);
        kriptoAnaSayfaTxt=view.findViewById(R.id.kriptoAnaSayfaTxt);
        soruAlaniTextView=view.findViewById(R.id.recyclerViewSoruAlaniText);
        soruAlaniLayoutContent=view.findViewById(R.id.soruAlaniContent);
        soruGorunumPresenter=new SoruGorunumPresenterImpl(this);
        progressBarAnaSayfa=getActivity().findViewById(R.id.progressBarAnaSayfa);



        filmDiziButon=view.findViewById(R.id.anasayfa_filmDizi_btn);
        filmDiziButon.setOnClickListener(this);
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
        anasayfa_kripto_btn=view.findViewById(R.id.anasayfa_kripto_btn);
        anasayfa_kripto_btn.setOnClickListener(this);
        anasayfa_diger_btn=view.findViewById(R.id.anasayfa_diger_btn);
        anasayfa_diger_btn.setOnClickListener(this);

        anaSayfaKriptoButonIamge=view.findViewById(R.id.anaSayfaKriptoButonIamge);
        anaSayfaYazilimButonIamge=view.findViewById(R.id.anaSayfaYazilimButonIamge);
        anaSayfaOtoButonIamge=view.findViewById(R.id.anaSayfaOtoButonIamge);
        anaSayfaModaButonIamge=view.findViewById(R.id.anaSayfaModaButonIamge);
        anaSayfaTarihButonIamge=view.findViewById(R.id.anaSayfaTarihButonIamge);
        anaSayfaEgitimButonIamge=view.findViewById(R.id.anaSayfaEgitimButonIamge);
        anaSayfaMuzikButonIamge=view.findViewById(R.id.anaSayfaMuzikButonIamge);
        anaSayfaSaglikButonIamge=view.findViewById(R.id.anaSayfaSaglikButonIamge);
        anaSayfaOyunButonIamge=view.findViewById(R.id.anaSayfaOyunButonIamge);
        anaSayfaTeknoButonIamge=view.findViewById(R.id.anaSayfaTeknoButonIamge);
        anaSayfaFilmDiziButonIamge=view.findViewById(R.id.anaSayfaFilmDiziButonIamge);
        anaSayfaAlisverisButonIamge=view.findViewById(R.id.anaSayfaAlisverisButonIamge);
        anaSayfaYemekButonIamge=view.findViewById(R.id.anaSayfaYemekButonIamge);
        anaSayfaTatilButonIamge=view.findViewById(R.id.anaSayfaTatilButonIamge);
        anaSayfaAdresButonIamge=view.findViewById(R.id.anaSayfaAdresButonIamge);
        anaSayfaSporButonIamge=view.findViewById(R.id.anaSayfaSporButonIamge);
        anaSayfaDigerButonIamge=view.findViewById(R.id.anaSayfaDigerButonIamge);

        teknoAnaSayfaTxt=view.findViewById(R.id.teknoAnaSayfaTxt);
        oyunAnaSayfaTxt=view.findViewById(R.id.oyunAnaSayfaTxt);
        saglikAnaSayfaTxt=view.findViewById(R.id.saglikAnaSayfaTxt);
        muzikAnaSayfaTxt=view.findViewById(R.id.muzikAnaSayfaTxt);
        egitimAnaSayfaTxt=view.findViewById(R.id.egitimAnaSayfaTxt);
        tarihAnaSayfaTxt=view.findViewById(R.id.tarihAnaSayfaTxt);
        modaAnaSayfaTxt=view.findViewById(R.id.modaAnaSayfaTxt);
        otoAnaSayfaTxt=view.findViewById(R.id.otoAnaSayfaTxt);
        textViewDiger=view.findViewById(R.id.digerAnaSayfaTxt);
        yazilimAnaSayfaTxt=view.findViewById(R.id.yazilimAnaSayfaTxt);

        swipeRefreshLayout.setColorSchemeResources(R.color.uygulamaMavisi);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            if(refreshControl==0 || refreshControl==-1){
                internetConnectionPresenter.internetBaglantiKontrolu();
                if(internetKontrol){
                    questionPresenter.loadData(kullanici.getId());
                }
            }else if (refreshControl != -1){
                internetConnectionPresenter.internetBaglantiKontrolu();
                if(internetKontrol){
                    questionMenuPresenter.loadData(refreshControl);
                }
            }
        });

        recyclerViewSoruAlani=(RecyclerView) view.findViewById(R.id.recyclerViewSoruAlani);
        recyclerViewSoruAlani.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewSoruAlani.setOnClickListener(this);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerViewSoruAlani);



        return view;
    }

    public void showBottomSheet(int soruId,int soruSoranKullaniciId) {
        CommentBottomDialogFragment commentBottomDialogFragment =
                CommentBottomDialogFragment.newInstance(soruId,soruSoranKullaniciId);
        commentBottomDialogFragment.show(getActivity().getSupportFragmentManager(),
                CommentBottomDialogFragment.TAG);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

            case R.id.anasayfa_alisveris_btn:
                if(!checkAlisverisEtiket)
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaAlisverisButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGizle(textViewAlisveris,textViewAdres,textViewFilmDizi,textViewSpor,textViewTatil,textViewYemek,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionMenuPresenter.loadData(Alisveris);
                        refreshControl=Alisveris;
                    }
                    checkAlisverisEtiket = true;

                }
                else
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaAlisverisButonIamge.setImageResource(R.drawable.ic_alisveris);
                    anaSayfaButonGoster(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGoster(textViewAlisveris,textViewAdres,textViewFilmDizi,textViewSpor,textViewTatil,textViewYemek,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionPresenter.loadData(kullanici.getId());
                        refreshControl=-1;
                    }
                    checkAlisverisEtiket=false;

                }
                break;
            case R.id.anasayfa_yemek_btn:
                if(!checkYemekEtiket)
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaYemekButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGizle(textViewYemek,textViewAlisveris,textViewAdres,textViewFilmDizi,textViewSpor,textViewTatil,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionMenuPresenter.loadData(Yemek);
                    }
                    checkYemekEtiket = true;
                    refreshControl=Yemek;
                }
                else
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaYemekButonIamge.setImageResource(R.drawable.ic_yemek);
                    anaSayfaButonGoster(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGoster(textViewYemek,textViewAlisveris,textViewAdres,textViewFilmDizi,textViewSpor,textViewTatil,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionPresenter.loadData(kullanici.getId());
                    }
                    checkYemekEtiket=false;
                    refreshControl=-1;
                }
                break;
            case R.id.anasayfa_tatil_btn:
                if(!checkTatilEtiket)
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaTatilButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGizle(textViewTatil,textViewYemek,textViewAlisveris,textViewAdres,textViewFilmDizi,textViewSpor,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionMenuPresenter.loadData(Tatil);
                    }
                    checkTatilEtiket = true;
                    refreshControl=Tatil;
                }
                else
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaTatilButonIamge.setImageResource(R.drawable.ic_tatil);
                    anaSayfaButonGoster(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGoster(textViewTatil,textViewYemek,textViewAlisveris,textViewAdres,textViewFilmDizi,textViewSpor,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionPresenter.loadData(kullanici.getId());
                    }
                    checkTatilEtiket=false;
                    refreshControl=-1;
                }
                break;
            case R.id.anasayfa_adres_btn:
                if(!checkAdresEtiket)
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaAdresButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGizle(textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,textViewFilmDizi,textViewSpor,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionMenuPresenter.loadData(Adres);
                    }
                    checkAdresEtiket = true;
                    refreshControl=Adres;
                }
                else
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaAdresButonIamge.setImageResource(R.drawable.ic_location);
                    anaSayfaButonGoster(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGoster(textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,textViewFilmDizi,textViewSpor,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionPresenter.loadData(kullanici.getId());
                    }
                    checkAdresEtiket=false;
                    refreshControl=-1;
                }
                break;
            case R.id.anasayfa_spor_btn:
                if(!checkSporEtiket)
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaSporButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGizle(textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,textViewFilmDizi,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionMenuPresenter.loadData(Spor);
                    }
                    checkSporEtiket = true;
                    refreshControl=Spor;
                }
                else
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaSporButonIamge.setImageResource(R.drawable.ic_trophy);
                    anaSayfaButonGoster(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGoster(textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,textViewFilmDizi,teknoAnaSayfaTxt,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionPresenter.loadData(kullanici.getId());
                    }
                    checkSporEtiket=false;
                    refreshControl=-1;
                }
                break;
            case R.id.anasayfa_tekno_btn:
                if(!checkTeknoEtiket)
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaTeknoButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGizle(teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,textViewFilmDizi,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionMenuPresenter.loadData(Teknoloji);
                    }
                    checkTeknoEtiket = true;
                    refreshControl=Teknoloji;
                }
                else
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaTeknoButonIamge.setImageResource(R.drawable.ic_teknoloji_svg);
                    anaSayfaButonGoster(anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGoster(teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,textViewFilmDizi,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionPresenter.loadData(kullanici.getId());
                    }
                    checkTeknoEtiket=false;
                    refreshControl=-1;
                }
                break;
            case R.id.anasayfa_filmDizi_btn:
                if(!checkFilmDiziEtiket)
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaFilmDiziButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGizle(textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);

                    if(internetKontrol){
                        questionMenuPresenter.loadData(FilmDizi);
                    }
                    checkFilmDiziEtiket = true;
                    refreshControl=FilmDizi;
                }
                else
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaFilmDiziButonIamge.setImageDrawable(getActivity().getDrawable(R.mipmap.spor_icon));
                    anaSayfaButonGoster(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGoster(textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,oyunAnaSayfaTxt,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionPresenter.loadData(kullanici.getId());
                    }
                    checkFilmDiziEtiket=false;
                    refreshControl=-1;
                }
                break;
            case R.id.anasayfa_oyun_btn:
                if(!checkOyunEtiket)
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaOyunButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(anasayfa_tekno_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGizle(oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionMenuPresenter.loadData(Oyun);
                    }
                    checkOyunEtiket = true;
                    refreshControl=Oyun;
                }
                else
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaOyunButonIamge.setImageResource(R.drawable.ic_oyun_svg);
                    anaSayfaButonGoster(anasayfa_tekno_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGoster(oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,saglikAnaSayfaTxt,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionPresenter.loadData(kullanici.getId());
                    }
                    checkOyunEtiket=false;
                    refreshControl=-1;
                }
                break;
            case R.id.anasayfa_saglik_btn:
                if(!checkSaglikEtiket)
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaSaglikButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGizle(saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionMenuPresenter.loadData(Saglik);
                    }
                    checkSaglikEtiket = true;
                    refreshControl=Saglik;
                }
                else
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaSaglikButonIamge.setImageResource(R.drawable.ic_saglik_svg);
                    anaSayfaButonGoster(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGoster(saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,muzikAnaSayfaTxt,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionPresenter.loadData(kullanici.getId());
                    }
                    checkSaglikEtiket=false;
                    refreshControl=-1;
                }
                break;
            case R.id.anasayfa_muzik_btn:
                if(!checkMuzikEtiket)
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaMuzikButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGizle(muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionMenuPresenter.loadData(Muzik);
                    }
                    checkMuzikEtiket = true;
                    refreshControl=Muzik;
                }
                else
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaMuzikButonIamge.setImageResource(R.drawable.ic_muzik_svg);
                    anaSayfaButonGoster(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGoster(muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,egitimAnaSayfaTxt,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionPresenter.loadData(kullanici.getId());
                    }
                    checkMuzikEtiket=false;
                    refreshControl=-1;
                }
                break;
            case R.id.anasayfa_egitim_btn:
                if(!checkEgitimEtiket)
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaEgitimButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGizle(egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionMenuPresenter.loadData(Egitim);
                    }
                    checkEgitimEtiket = true;
                    refreshControl=Egitim;
                }
                else
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaEgitimButonIamge.setImageResource(R.drawable.ic_book);
                    anaSayfaButonGoster(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGoster(egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,tarihAnaSayfaTxt,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionPresenter.loadData(kullanici.getId());
                    }
                    checkEgitimEtiket=false;
                    refreshControl=-1;
                }
                break;
            case R.id.anasayfa_tarih_btn:
                if(!checkTarihEtiket)
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaTarihButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGizle(tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionMenuPresenter.loadData(Tarih);
                    }
                    checkTarihEtiket = true;
                    refreshControl=Tarih;
                }
                else
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaTarihButonIamge.setImageResource(R.drawable.ic_tarih_svg);
                    anaSayfaButonGoster(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_moda_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGoster(tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,modaAnaSayfaTxt,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionPresenter.loadData(kullanici.getId());
                    }
                    checkTarihEtiket=false;
                    refreshControl=-1;
                }
                break;
            case R.id.anasayfa_moda_btn:
                if(!checkModaEtiket)
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaModaButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGizle(modaAnaSayfaTxt,tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionMenuPresenter.loadData(Moda);
                    }
                    checkModaEtiket = true;
                    refreshControl=Moda;
                }
                else
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaModaButonIamge.setImageResource(R.drawable.ic_moda_svg);
                    anaSayfaButonGoster(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_oto_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGoster(modaAnaSayfaTxt,tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,otoAnaSayfaTxt,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionPresenter.loadData(kullanici.getId());
                    }
                    checkModaEtiket=false;
                    refreshControl=-1;
                }
                break;
            case R.id.anasayfa_oto_btn:
                if(!checkOtoEtiket)
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaOtoButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGizle(otoAnaSayfaTxt,modaAnaSayfaTxt,tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionMenuPresenter.loadData(Oto);
                    }
                    checkOtoEtiket = true;
                    refreshControl=Oto;
                }
                else
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaOtoButonIamge.setImageResource(R.drawable.ic_auto_svg);
                    anaSayfaButonGoster(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_yazilim_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGoster(otoAnaSayfaTxt,modaAnaSayfaTxt,tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionPresenter.loadData(kullanici.getId());
                    }
                    checkOtoEtiket=false;
                    refreshControl=-1;
                }
                break;
            case R.id.anasayfa_yazilim_btn:
                if(!checkYazilimEtiket)
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaYazilimButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGizle(yazilimAnaSayfaTxt,otoAnaSayfaTxt,modaAnaSayfaTxt,tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,kriptoAnaSayfaTxt,textViewDiger);

                    if(internetKontrol){
                        questionMenuPresenter.loadData(Yazilim);
                    }
                    checkYazilimEtiket = true;
                    refreshControl=Yazilim;
                }
                else
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaYazilimButonIamge.setImageResource(R.drawable.ic_engineer);
                    anaSayfaButonGoster(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_kripto_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGoster(yazilimAnaSayfaTxt,otoAnaSayfaTxt,modaAnaSayfaTxt,tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,kriptoAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionPresenter.loadData(kullanici.getId());
                    }
                    checkYazilimEtiket=false;
                    refreshControl=-1;
                }
                break;
            case R.id.anasayfa_kripto_btn:
                if(!checkKriptoEtiket)
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaKriptoButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_yazilim_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGizle(yazilimAnaSayfaTxt,otoAnaSayfaTxt,modaAnaSayfaTxt,tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,yazilimAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionMenuPresenter.loadData(Kripto);
                    }
                    checkKriptoEtiket = true;
                    refreshControl=Kripto;
                }
                else
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaKriptoButonIamge.setImageResource(R.drawable.ic_crypto_vault);
                    anaSayfaButonGoster(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_yazilim_btn,anasayfa_diger_btn);
                    anaSayfaTextRenkGoster(yazilimAnaSayfaTxt,otoAnaSayfaTxt,modaAnaSayfaTxt,tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,yazilimAnaSayfaTxt,textViewDiger);
                    if(internetKontrol){
                        questionPresenter.loadData(kullanici.getId());
                    }
                    checkKriptoEtiket=false;
                    refreshControl=-1;
                }
                break;
            case R.id.anasayfa_diger_btn:
                if(!checkDigerEtiket)
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaDigerButonIamge.setImageResource(R.mipmap.mavi_kapat_icon);
                    anaSayfaButonGizle(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_yazilim_btn,anasayfa_kripto_btn);
                    anaSayfaTextRenkGizle(yazilimAnaSayfaTxt,otoAnaSayfaTxt,modaAnaSayfaTxt,tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt);
                    if(internetKontrol){
                        questionMenuPresenter.loadData(Diger);
                    }
                    checkDigerEtiket = true;
                    refreshControl=Diger;
                }
                else
                {
                    internetConnectionPresenter.internetBaglantiKontrolu();
                    anaSayfaDigerButonIamge.setImageResource(R.drawable.ic_diger);
                    anaSayfaButonGoster(anasayfa_tekno_btn,anasayfa_oyun_btn,anasayfa_saglik_btn,anasayfa_muzik_btn,anasayfa_egitim_btn,anasayfa_tarih_btn,anasayfa_moda_btn,anasayfa_oto_btn,filmDiziButon,alisverisButon,yemekButon,tatilButon,adresButon,sporButon,anasayfa_yazilim_btn,anasayfa_kripto_btn);
                    anaSayfaTextRenkGoster(yazilimAnaSayfaTxt,otoAnaSayfaTxt,modaAnaSayfaTxt,tarihAnaSayfaTxt,egitimAnaSayfaTxt,muzikAnaSayfaTxt,saglikAnaSayfaTxt,oyunAnaSayfaTxt,textViewFilmDizi,teknoAnaSayfaTxt,textViewSpor,textViewAdres,textViewTatil,textViewYemek,textViewAlisveris,yazilimAnaSayfaTxt,kriptoAnaSayfaTxt);
                    if(internetKontrol){
                        questionPresenter.loadData(kullanici.getId());
                    }
                    checkDigerEtiket=false;
                    refreshControl=-1;
                }
                break;
        }
    }

    public void anaSayfaButonGizle(RelativeLayout button1,RelativeLayout button2,RelativeLayout button3,RelativeLayout button4,RelativeLayout button5,RelativeLayout button6,RelativeLayout button7,RelativeLayout button8,RelativeLayout button9,RelativeLayout button10,RelativeLayout button11,RelativeLayout button12,RelativeLayout button13,RelativeLayout button14,RelativeLayout button15,RelativeLayout button16){
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        button6.setEnabled(false);
        button7.setEnabled(false);
        button8.setEnabled(false);
        button9.setEnabled(false);
        button10.setEnabled(false);
        button11.setEnabled(false);
        button12.setEnabled(false);
        button13.setEnabled(false);
        button14.setEnabled(false);
        button15.setEnabled(false);
        button16.setEnabled(false);


    }
    public void anaSayfaButonGoster(RelativeLayout button1,RelativeLayout button2,RelativeLayout button3,RelativeLayout button4,RelativeLayout button5,RelativeLayout button6,RelativeLayout button7,RelativeLayout button8,RelativeLayout button9,RelativeLayout button10,RelativeLayout button11,RelativeLayout button12,RelativeLayout button13,RelativeLayout button14,RelativeLayout button15,RelativeLayout button16){
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);
        button7.setEnabled(true);
        button8.setEnabled(true);
        button9.setEnabled(true);
        button10.setEnabled(true);
        button11.setEnabled(true);
        button12.setEnabled(true);
        button13.setEnabled(true);
        button14.setEnabled(true);
        button15.setEnabled(true);
        button16.setEnabled(true);

    }

    public void anaSayfaTextRenkGizle(TextView textView1,TextView textView2,TextView textView3,TextView textView4,TextView textView5,TextView textView6,TextView textView7,TextView textView8,TextView textView9,TextView textView10,TextView textView11,TextView textView12,TextView textView13,TextView textView14,TextView textView15,TextView textView16,TextView textView17){
        textView1.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));
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
        textView16.setTextColor(getResources().getColor(R.color.ayarlarGrisi));
        textView17.setTextColor(getResources().getColor(R.color.ayarlarGrisi));

    }
    public void anaSayfaTextRenkGoster(TextView textView1,TextView textView2,TextView textView3,TextView textView4,TextView textView5,TextView textView6,TextView textView7,TextView textView8,TextView textView9,TextView textView10,TextView textView11,TextView textView12,TextView textView13,TextView textView14,TextView textView15,TextView textView16,TextView textView17){
        textView1.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));
        textView2.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));
        textView3.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));
        textView4.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));
        textView5.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));
        textView6.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));
        textView7.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));
        textView8.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));
        textView9.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));
        textView10.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));
        textView11.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));
        textView12.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));
        textView13.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));
        textView14.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));
        textView15.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));
        textView16.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));
        textView17.setTextColor(getResources().getColor(R.color.uygulamaYaziColor));

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
        soruAlaniLayoutContent.setVisibility(View.INVISIBLE);
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
    public void onGetQuestionResultControl(String string) {
        soruAlaniLayoutContent.setVisibility(View.VISIBLE);
    }
    @Override
    public void onGetResultControl() {
        soruAlaniLayoutContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void internetBaglantiHatasi() {
        Toast.makeText(getActivity(),"İnternet Bağlantınızı Kontrol Ediniz",Toast.LENGTH_LONG).show();
        internetKontrol=false;
        hideLoading();
    }

    @Override
    public void internetBaglantisi() {
        if(refreshControl==0 || refreshControl==-1){
                //questionPresenter.loadData(kullanici.getId());

        }else if (refreshControl != -1){
                //questionMenuPresenter.loadData(refreshControl);
        }
        internetKontrol=true;
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


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof RecyclerView.ViewHolder) {
            // get the removed item name to display it in snack bar
            //String name = cartList.get(viewHolder.getAdapterPosition()).getName();

            // backup of removed item for undo purpose
            //final Item deletedItem = cartList.get(viewHolder.getAdapterPosition());
            //final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            //mAdapter.removeItem(viewHolder.getAdapterPosition());
            soruGorunumPresenter.soruGorunumDurumGuncelle(kullanici.getId(),questionModels.get(position).getId(),1);

            // showing snack bar with Undo option

        }
    }

    @Override
    public void showSoruGorunumSuccesMessage() {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Soruyu Artık Görmeyeceksiniz.", Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();
    }

    @Override
    public void showSoruGorunumFailedMessage() {

    }
}
