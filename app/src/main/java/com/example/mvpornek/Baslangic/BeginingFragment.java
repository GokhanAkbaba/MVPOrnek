package com.example.mvpornek.Baslangic;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mvpornek.Activity.HomeActivity;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Model.Baslangic.BeginingInteractorImpl;
import com.example.mvpornek.Presenter.Baslangic.BeginingPresenter;
import com.example.mvpornek.Presenter.Baslangic.BeginingPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.BeginingView;

import java.util.ArrayList;


public class BeginingFragment extends Fragment implements BeginingView, View.OnClickListener {

    RelativeLayout adres,yemek,spor, tatil,alisveris,sanat,yrmDrtBildirim,secimOnay;
    Spinner spinner;
    Boolean checkYemekEtiket =false,checkAdresEtiket = false,checkSporEtiket = false,
            checkTatilEtiket = false,checkAlisverisEtiket = false,checkSanatEtiket = false,checkYirmiDortBildirim=false;
    String selectedIl;
    int selectedIlPlaka;


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.baslarken, container, false);
        view.findViewById(R.id.baslarkenSonrakiBtn).setOnClickListener(this);
        spinner=view.findViewById(R.id.ilAutoCompleteTextView);
        ArrayAdapter arrayAdapter=ArrayAdapter.createFromResource(getContext(),R.array.il,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

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
        yrmDrtBildirim=view.findViewById(R.id.yirmiDortSaatBildirimButon);
        yrmDrtBildirim.setOnClickListener(this);
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
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        return view;
    }

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
                        if("Adres"==etiketList.get(i))
                        {
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,1);
                        }else if("Yemek"==etiketList.get(i)){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,2);
                        }else if("Spor"==etiketList.get(i)){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,3);
                        }else if("Alışveriş"==etiketList.get(i)){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,4);
                        }else if("Tatil"==etiketList.get(i)){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,5);
                        }else if("Sanat"==etiketList.get(i)){
                            beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,6);
                        }
                    }
                }else{
                    beginingPresenter.validateOptions(kullaniciId,selectedIlPlaka,0);
                }

                break;
            case R.id.adresEtiketButon:

                if(checkAdresEtiket == false)
                {

                    etiketList.add("Adres");
                    adres.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    //adres.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgTatil =adres.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                    //adres.setCompoundDrawablesWithIntrinsicBounds(imgTatil,null,null,null);
                    checkAdresEtiket = true;

                }
                else
                {
                    etiketList.remove("Adres");
                    adres.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    //adres.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgTatil =adres.getContext().getResources().getDrawable(R.mipmap.ic_launcher_adres);
                    ///adres.setCompoundDrawablesWithIntrinsicBounds(imgTatil,null,null,null);
                    checkAdresEtiket=false;

                }


                break;
            case R.id.yemekEtiketButon:

                if(checkYemekEtiket == false)
                {
                    etiketList.add("Yemek");
                    yemek.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    //yemek.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgYemek =yemek.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                    //yemek.setCompoundDrawablesWithIntrinsicBounds(imgYemek,null,null,null);
                    checkYemekEtiket = true;
                }
                else
                {

                    etiketList.remove("Yemek");
                    yemek.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    //yemek.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgYemek =yemek.getContext().getResources().getDrawable(R.mipmap.yemek_icon);
                    //yemek.setCompoundDrawablesWithIntrinsicBounds(imgYemek,null,null,null);
                    checkYemekEtiket=false;
                }

                break;
            case R.id.sporEtiketButon:
                if(checkSporEtiket == false)
                {
                    etiketList.add("Spor");
                    spor.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    //spor.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgSpor =spor.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                    //spor.setCompoundDrawablesWithIntrinsicBounds(imgSpor,null,null,null);
                    checkSporEtiket = true;
                }
                else
                {

                    etiketList.remove("Spor");
                    spor.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    //spor.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgSpor =spor.getContext().getResources().getDrawable(R.mipmap.spor_icon);
                    //spor.setCompoundDrawablesWithIntrinsicBounds(imgSpor,null,null,null);
                    checkSporEtiket=false;
                }

                break;
            case R.id.alisverisEtiketButon:
                if(checkAlisverisEtiket == false)
                {
                    etiketList.add("Alışveriş");
                    alisveris.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    //alisveris.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgAlisveris =alisveris.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                   // alisveris.setCompoundDrawablesWithIntrinsicBounds(imgAlisveris,null,null,null);
                    checkAlisverisEtiket = true;
                }
                else
                {
                    etiketList.remove("Alışveriş");
                    alisveris.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    //alisveris.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgAlisveris =alisveris.getContext().getResources().getDrawable(R.mipmap.alisveris_icon);
                    //alisveris.setCompoundDrawablesWithIntrinsicBounds(imgAlisveris,null,null,null);
                    checkAlisverisEtiket=false;
                }
                break;
            case R.id.tatilEtiketButon:
                if(checkTatilEtiket == false)
                {
                    etiketList.add("Tatil");
                    tatil.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    //tatil.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgTatil =tatil.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                   // tatil.setCompoundDrawablesWithIntrinsicBounds(imgTatil,null,null,null);
                    checkTatilEtiket = true;
                }
                else
                {
                    etiketList.remove("Tatil");
                    tatil.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    //tatil.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgTatil =tatil.getContext().getResources().getDrawable(R.mipmap.tatil_icon);
                    //tatil.setCompoundDrawablesWithIntrinsicBounds(imgTatil,null,null,null);
                    checkTatilEtiket=false;
                }
                break;
            case R.id.sanatEtiketButon:
                if(checkSanatEtiket == false)
                {
                    etiketList.add("Sanat");
                    sanat.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    //sanat.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgSanat =sanat.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                    //sanat.setCompoundDrawablesWithIntrinsicBounds(imgSanat,null,null,null);
                    checkSanatEtiket = true;
                }
                else
                {
                    etiketList.remove("Sanat");
                    sanat.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    //sanat.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgSanat =sanat.getContext().getResources().getDrawable(R.mipmap.film_icon);
                    //sanat.setCompoundDrawablesWithIntrinsicBounds(imgSanat,null,null,null);
                    checkSanatEtiket=false;
                }
                break;
            case R.id.yirmiDortSaatBildirimButon:
                if(checkYirmiDortBildirim == false)
                {
                    yrmDrtBildirim.setBackground(getActivity().getDrawable(R.drawable.baslarkenilonaybuton));
                    Drawable imgyrmDrtBildirim =yrmDrtBildirim.getContext().getResources().getDrawable(R.mipmap.onay_beyaz_icon);
                   // yrmDrtBildirim.setCompoundDrawablesWithIntrinsicBounds(imgyrmDrtBildirim,null,null,null);
                    checkYirmiDortBildirim = true;
                }
                else
                {
                    yrmDrtBildirim.setBackground(getActivity().getDrawable(R.drawable.baslarkenilbuton));
                    Drawable imgyrmDrtBildirim =yrmDrtBildirim.getContext().getResources().getDrawable(R.mipmap.bildirim_icon);
                    //yrmDrtBildirim.setCompoundDrawablesWithIntrinsicBounds(imgyrmDrtBildirim,null,null,null);
                    checkYirmiDortBildirim=false;
                }
                break;
        }

    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(getActivity().getApplicationContext(), HomeActivity.class));
        Toast.makeText(getActivity(),"Seçim İşleminiz Başarılı",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setIlHatasi() {
        Toast.makeText(getActivity(),"Lütfen İl Seçiniz",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setEtiketHatasi() {
        Toast.makeText(getActivity(),"En Az Bir Tane Soru Alanı Seçiniz",Toast.LENGTH_SHORT).show();
    }
}
