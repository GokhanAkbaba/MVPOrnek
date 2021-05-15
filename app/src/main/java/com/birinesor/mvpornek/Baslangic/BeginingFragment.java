package com.birinesor.mvpornek.Baslangic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Model.Baslangic.BeginingInteractorImpl;
import com.birinesor.mvpornek.Presenter.Baslangic.BeginingPresenter;
import com.birinesor.mvpornek.Presenter.Baslangic.BeginingPresenterImpl;
import com.birinesor.mvpornek.Presenter.TokenCreate.TokenCreatePresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.View.BeginingView;
import com.birinesor.mvpornek.View.TokenCreateView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.installations.InstallationTokenResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.birinesor.mvpornek.Fragment.Comment.CommentBottomDialogFragment.TAG;


public class BeginingFragment extends Fragment implements BeginingView, View.OnClickListener {

    RelativeLayout adres,yemek,spor, tatil,alisveris,sanat,yrmDrtBildirim,teknoEtiketButon,muzikEtiketButon,egitimEtiketButon;
    RelativeLayout modaEtiketButon,otoEtiketButon,saglikEtiketButon,tarihEtiketButon,yazilimEtiketButon,oyunEtiketButon,yirmiDortSaatBildirimButonIc;
    Spinner spinner;
    Boolean checkYemekEtiket =false,checkAdresEtiket = false,checkSporEtiket = false,
            checkTatilEtiket = false,checkAlisverisEtiket = false,checkSanatEtiket = false,checkYirmiDortBildirim=false;
    Boolean checkSaglikEtiket=false,checkOyunEtiket=false,checkTeknoEtiket=false,checkMuzikEtiket=false,checkModaEtiket=false;
    Boolean checkEgitimEtiket=false,checkTarihEtiket=false,checkOtoEtiket=false,checkYazilimEtiket=false;
    String selectedIl;
    int selectedIlPlaka;

    TextView adresBaslarkenTxt,yemekBaslarkenTxt,sporBaslarkenTxt,sanatBaslarkenTxt,alisverisBaslarkenTxt,tatilBaslarkenTxt;
    TextView yazilimBaslarkenTxt,otoBaslarkenTxt,modaBaslarkenTxt,egitimBaslarkenTxt,tarihBaslarkenTxt,saglikBaslarkenTxt;
    TextView oyunBaslarkenTxt,teknolojiBaslarkenTxt,muzikBaslarkenTxt;
    TextView textView31;

    ImageView teknoImage,muzikImage,modaImage,sanatImage,sporImage,yemekImage,alisverisImage,otoImage;
    ImageView egitimImage,oyunImage,saglikImage,tarihImage,yazilimImage,tatilImage,adresImage,yirmiDortSaatImage;

    SearchableSpinner searchableSpinner;

    int kullaniciId;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private BeginingPresenter beginingPresenter;
    private String mParam1;
    private String mParam2;

    ArrayList<String> etiketList=new ArrayList<String>();

    public BeginingFragment() {
    }

    public static BeginingFragment newInstance(String param1, String param2) {
        BeginingFragment fragment = new BeginingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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

    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.baslarken, container, false);
        view.findViewById(R.id.baslarkenSonrakiBtn).setOnClickListener(this);
        spinner=view.findViewById(R.id.ilAutoCompleteTextView);
        searchableSpinner=view.findViewById(R.id.ilAutoCompleteTextView);
        ArrayAdapter arrayAdapter=ArrayAdapter.createFromResource(getContext(),R.array.il,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        searchableSpinner.setTitle("İl Seçiniz");
        searchableSpinner.setPositiveButton("Kapat");
        adres=view.findViewById(R.id.adresEtiketButon);
        adres.setOnClickListener(this);
        yemek=view.findViewById(R.id.yemekEtiketButon);
        yemek.setOnClickListener(this);
        spor=view.findViewById(R.id.sporEtiketButon);
        spor.setOnClickListener(this);
        tatil=view.findViewById(R.id.tatilEtiketButon);
        tatil.setOnClickListener(this);
        alisveris=view.findViewById(R.id.alisverisEtiketButon);
        alisveris.setOnClickListener(this);
        sanat=view.findViewById(R.id.sanatEtiketButon);
        sanat.setOnClickListener(this);
        teknoEtiketButon=view.findViewById(R.id.teknoEtiketButon);
        teknoEtiketButon.setOnClickListener(this);
        muzikEtiketButon=view.findViewById(R.id.muzikEtiketButon);
        muzikEtiketButon.setOnClickListener(this);
        modaEtiketButon=view.findViewById(R.id.modaEtiketButon);
        modaEtiketButon.setOnClickListener(this);
        yrmDrtBildirim=view.findViewById(R.id.yirmiDortSaatBildirimButonIc);
        yrmDrtBildirim.setOnClickListener(this);
        otoEtiketButon=view.findViewById(R.id.otoEtiketButon);
        otoEtiketButon.setOnClickListener(this);
        saglikEtiketButon=view.findViewById(R.id.saglikEtiketButon);
        saglikEtiketButon.setOnClickListener(this);
        tarihEtiketButon=view.findViewById(R.id.tarihEtiketButon);
        tarihEtiketButon.setOnClickListener(this);
        yazilimEtiketButon=view.findViewById(R.id.yazilimEtiketButon);
        yazilimEtiketButon.setOnClickListener(this);
        oyunEtiketButon=view.findViewById(R.id.oyunEtiketButon);
        oyunEtiketButon.setOnClickListener(this);
        egitimEtiketButon=view.findViewById(R.id.egitimEtiketButon);
        egitimEtiketButon.setOnClickListener(this);

        adresBaslarkenTxt=view.findViewById(R.id.adresBaslarkenTxt);
        yemekBaslarkenTxt=view.findViewById(R.id.yemekBaslarkenTxt);
        sporBaslarkenTxt=view.findViewById(R.id.sporBaslarkenTxt);
        sanatBaslarkenTxt=view.findViewById(R.id.sanatBaslarkenTxt);
        alisverisBaslarkenTxt=view.findViewById(R.id.alisverisBaslarkenTxt);
        tatilBaslarkenTxt=view.findViewById(R.id.tatilBaslarkenTxt);
        yazilimBaslarkenTxt=view.findViewById(R.id.yazilimBaslarkenTxt);
        otoBaslarkenTxt=view.findViewById(R.id.otoBaslarkenTxt);
        modaBaslarkenTxt=view.findViewById(R.id.modaBaslarkenTxt);
        egitimBaslarkenTxt=view.findViewById(R.id.egitimBaslarkenTxt);
        tarihBaslarkenTxt=view.findViewById(R.id.tarihBaslarkenTxt);
        saglikBaslarkenTxt=view.findViewById(R.id.saglikBaslarkenTxt);
        oyunBaslarkenTxt=view.findViewById(R.id.oyunBaslarkenTxt);
        teknolojiBaslarkenTxt=view.findViewById(R.id.teknolojiBaslarkenTxt);
        muzikBaslarkenTxt=view.findViewById(R.id.muzikBaslarkenTxt);
        teknoImage=view.findViewById(R.id.teknoImage);
        muzikImage=view.findViewById(R.id.muzikImage);
        modaImage=view.findViewById(R.id.modaImage);
        sanatImage=view.findViewById(R.id.sanatImage);
        sporImage=view.findViewById(R.id.sporImage);
        yemekImage=view.findViewById(R.id.yemekImage);
        alisverisImage=view.findViewById(R.id.alisverisImage);
        otoImage=view.findViewById(R.id.otoImage);
        egitimImage=view.findViewById(R.id.egitimImage);
        oyunImage=view.findViewById(R.id.oyunImage);
        saglikImage=view.findViewById(R.id.saglikImage);
        tarihImage=view.findViewById(R.id.tarihImage);
        yazilimImage=view.findViewById(R.id.yazilimImage);
        tatilImage=view.findViewById(R.id.tatilImage);
        adresImage=view.findViewById(R.id.adresImage);
        textView31 = view.findViewById(R.id.textView31);

        yirmiDortSaatImage=view.findViewById(R.id.yirmiDortSaatImage);
        yirmiDortSaatBildirimButonIc=view.findViewById(R.id.yirmiDortSaatBildirimButonIc);
        yirmiDortSaatBildirimButonIc.setOnClickListener(this);
        Kullanici kullanici= SharedPrefManager.getInstance(getContext()).getKullanici();
        kullaniciId=kullanici.getId();

        beginingPresenter=new BeginingPresenterImpl(this, new BeginingInteractorImpl());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedIl=adapterView.getItemAtPosition(i).toString();
                selectedIlPlaka=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if (Build.VERSION.SDK_INT >= 27) {
            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        return view;
    }

    @SuppressLint({"NonConstantResourceId", "UseCompatLoadingForDrawables"})
    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.baslarkenSonrakiBtn:
                int etiketKod=0;
                if(etiketList.size() > 0)
                {
                    for(int i = 0; i < etiketList.size(); i++)
                    {
                        if("Adres".equals(etiketList.get(i)))
                        {
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,1);
                        }else if("Yemek".equals(etiketList.get(i))){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,2);
                        }else if("Spor".equals(etiketList.get(i))){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,3);
                        }else if("Alışveriş".equals(etiketList.get(i))){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,4);
                        }else if("Tatil".equals(etiketList.get(i))){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,5);
                        }else if("Sanat".equals(etiketList.get(i))){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,6);
                        }else if("Yazilim".equals(etiketList.get(i))){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,7);
                        }else if("Oto".equals(etiketList.get(i))){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,17);
                        }else if("Tarih".equals(etiketList.get(i))){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,15);
                        }else if("Egitim".equals(etiketList.get(i))){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,14);
                        }else if("Moda".equals(etiketList.get(i))){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,16);
                        }else if("Muzik".equals(etiketList.get(i))){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,13);
                        }else if("Teknoloji".equals(etiketList.get(i))){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,10);
                        }else if("Oyun".equals(etiketList.get(i))){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,11);
                        }else if("Saglik".equals(etiketList.get(i))){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,12);
                        }
                    }
                }else{
                    beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,0);
                }

                break;
            case R.id.yazilimEtiketButon:

                if(!checkYazilimEtiket)
                {

                    etiketList.add("Yazilim");
                    yazilimEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    yazilimBaslarkenTxt.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    yazilimImage.setImageResource(R.mipmap.onay_icon);
                    checkYazilimEtiket = true;

                }
                else
                {
                    etiketList.remove("Yazilim");
                    yazilimEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    yazilimBaslarkenTxt.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    yazilimImage.setImageResource(R.drawable.ic_engineer);
                    checkYazilimEtiket=false;

                }
                break;
            case R.id.otoEtiketButon:

                if(!checkOtoEtiket)
                {

                    etiketList.add("Oto");
                    otoEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    otoBaslarkenTxt.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    otoImage.setImageResource(R.mipmap.onay_icon);
                    checkOtoEtiket = true;

                }
                else
                {
                    etiketList.remove("Oto");
                    otoEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    otoBaslarkenTxt.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    otoImage.setImageResource(R.drawable.ic_auto_svg);
                    checkOtoEtiket=false;

                }
                break;
            case R.id.tarihEtiketButon:

                if(!checkTarihEtiket)
                {

                    etiketList.add("Tarih");
                    tarihEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    tarihBaslarkenTxt.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    tarihImage.setImageResource(R.mipmap.onay_icon);
                    checkTarihEtiket = true;

                }
                else
                {
                    etiketList.remove("Tarih");
                    tarihEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    tarihBaslarkenTxt.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    tarihImage.setImageResource(R.drawable.ic_tarih_svg);
                    checkTarihEtiket=false;

                }
                break;
            case R.id.egitimEtiketButon:

                if(!checkEgitimEtiket)
                {

                    etiketList.add("Egitim");
                    egitimEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    egitimBaslarkenTxt.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    egitimImage.setImageResource(R.mipmap.onay_icon);
                    checkEgitimEtiket = true;

                }
                else
                {
                    etiketList.remove("Egitim");
                    egitimEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    egitimBaslarkenTxt.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    egitimImage.setImageResource(R.drawable.ic_book);
                    checkEgitimEtiket=false;

                }
                break;
            case R.id.modaEtiketButon:

                if(!checkModaEtiket)
                {

                    etiketList.add("Moda");
                    modaEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    modaBaslarkenTxt.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    modaImage.setImageResource(R.mipmap.onay_icon);
                    checkModaEtiket = true;

                }
                else
                {
                    etiketList.remove("Moda");
                    modaEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    modaBaslarkenTxt.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    modaImage.setImageResource(R.drawable.ic_moda_svg);
                    checkModaEtiket=false;

                }
                break;
            case R.id.muzikEtiketButon:

                if(checkMuzikEtiket == false)
                {

                    etiketList.add("Muzik");
                    muzikEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    muzikBaslarkenTxt.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    muzikImage.setImageResource(R.mipmap.onay_icon);
                    checkMuzikEtiket = true;

                }
                else
                {
                    etiketList.remove("Muzik");
                    muzikEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    muzikBaslarkenTxt.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    muzikImage.setImageResource(R.drawable.ic_muzik_svg);
                    checkMuzikEtiket=false;

                }
                break;
            case R.id.teknoEtiketButon:

                if(checkTeknoEtiket == false)
                {

                    etiketList.add("Teknoloji");
                    teknoEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    teknolojiBaslarkenTxt.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    teknoImage.setImageResource(R.mipmap.onay_icon);
                    checkTeknoEtiket = true;

                }
                else
                {
                    etiketList.remove("Teknoloji");
                    teknoEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    teknolojiBaslarkenTxt.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    teknoImage.setImageResource(R.drawable.ic_teknoloji_svg);
                    checkTeknoEtiket=false;

                }
                break;
            case R.id.oyunEtiketButon:

                if(checkOyunEtiket == false)
                {

                    etiketList.add("Oyun");
                    oyunEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    oyunBaslarkenTxt.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    oyunImage.setImageResource(R.mipmap.onay_icon);
                    checkOyunEtiket = true;

                }
                else
                {
                    etiketList.remove("Oyun");
                    oyunEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    oyunBaslarkenTxt.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    oyunImage.setImageResource(R.drawable.ic_oyun_svg);
                    checkOyunEtiket=false;

                }
                break;
            case R.id.saglikEtiketButon:

                if(checkSaglikEtiket == false)
                {

                    etiketList.add("Saglik");
                    saglikEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    saglikBaslarkenTxt.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    saglikImage.setImageResource(R.mipmap.onay_icon);
                    checkSaglikEtiket = true;

                }
                else
                {
                    etiketList.remove("Saglik");
                    saglikEtiketButon.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    saglikBaslarkenTxt.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    saglikImage.setImageResource(R.drawable.ic_saglik_svg);
                    checkSaglikEtiket=false;

                }
                break;
            case R.id.adresEtiketButon:

                if(checkAdresEtiket == false)
                {

                    etiketList.add("Adres");
                    adres.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    adresBaslarkenTxt.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    adresImage.setImageResource(R.mipmap.onay_icon);
                    checkAdresEtiket = true;

                }
                else
                {
                    etiketList.remove("Adres");
                    adres.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    adresBaslarkenTxt.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    adresImage.setImageResource(R.drawable.ic_location);
                    checkAdresEtiket=false;

                }
                break;
            case R.id.yemekEtiketButon:

                if(checkYemekEtiket == false)
                {
                    etiketList.add("Yemek");
                    yemek.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    yemekBaslarkenTxt.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    yemekImage.setImageResource(R.mipmap.onay_icon);
                    checkYemekEtiket = true;
                }
                else
                {

                    etiketList.remove("Yemek");
                    yemek.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    yemekBaslarkenTxt.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    yemekImage.setImageResource(R.drawable.ic_yemek);
                    checkYemekEtiket=false;
                }

                break;
            case R.id.sporEtiketButon:
                if(checkSporEtiket == false)
                {
                    etiketList.add("Spor");
                    spor.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    sporBaslarkenTxt.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    sporImage.setImageResource(R.mipmap.onay_icon);
                    checkSporEtiket = true;
                }
                else
                {

                    etiketList.remove("Spor");
                    spor.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    sporBaslarkenTxt.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    sporImage.setImageResource(R.drawable.ic_trophy);
                    checkSporEtiket=false;
                }

                break;
            case R.id.alisverisEtiketButon:
                if(checkAlisverisEtiket == false)
                {
                    etiketList.add("Alışveriş");
                    alisveris.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    alisverisBaslarkenTxt.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    alisverisImage.setImageResource(R.mipmap.onay_icon);
                    checkAlisverisEtiket = true;
                }
                else
                {
                    etiketList.remove("Alışveriş");
                    alisveris.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    alisverisBaslarkenTxt.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    alisverisImage.setImageResource(R.drawable.ic_alisveris);
                    checkAlisverisEtiket=false;
                }
                break;
            case R.id.tatilEtiketButon:
                if(!checkTatilEtiket)
                {
                    etiketList.add("Tatil");
                    tatil.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    tatilBaslarkenTxt.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    tatilImage.setImageResource(R.mipmap.onay_icon);
                    checkTatilEtiket = true;
                }
                else
                {
                    etiketList.remove("Tatil");
                    tatil.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    tatilBaslarkenTxt.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    tatilImage.setImageResource(R.drawable.ic_tatil);
                    checkTatilEtiket=false;
                }
                break;
            case R.id.sanatEtiketButon:
                if(!checkSanatEtiket)
                {
                    etiketList.add("Sanat");
                    sanat.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    sanatBaslarkenTxt.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    sanatImage.setImageResource(R.mipmap.onay_icon);
                    checkSanatEtiket = true;
                }
                else
                {
                    etiketList.remove("Sanat");
                    sanat.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    sanatBaslarkenTxt.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    sanatImage.setImageResource(R.drawable.ic_tv_svg);
                    checkSanatEtiket=false;
                }
                break;
            case R.id.yirmiDortSaatBildirimButonIc:
                if(!checkYirmiDortBildirim)
                {
                    yrmDrtBildirim.setBackground(getActivity().getDrawable(R.drawable.baslarkenilonaybuton));
                    yirmiDortSaatImage.setImageResource(R.mipmap.onay_icon);
                    checkYirmiDortBildirim = true;
                }
                else
                {
                    yrmDrtBildirim.setBackground(getActivity().getDrawable(R.drawable.baslarkenilbuton));
                    yirmiDortSaatImage.setImageResource(R.mipmap.bildirim_icon);
                    checkYirmiDortBildirim=false;
                }
                break;
        }

    }
    @Override
    public void navigateToHome() {
        startActivity(new Intent(getActivity().getApplicationContext(), HomeActivity.class));
        textView31.setVisibility(View.INVISIBLE);
    }
    @Override
    public void setIlHatasi() {
        //Toast.makeText(getActivity(),"Lütfen İl Seçiniz",Toast.LENGTH_SHORT).show();
        textView31.setVisibility(View.VISIBLE);


    }
    @Override
    public void setEtiketHatasi() {
        Toast.makeText(getActivity(),"En Az Bir Tane Soru Alanı Seçiniz",Toast.LENGTH_SHORT).show();
    }

}
