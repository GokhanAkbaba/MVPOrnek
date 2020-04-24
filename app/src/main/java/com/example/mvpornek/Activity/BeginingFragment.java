package com.example.mvpornek.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mvpornek.R;


public class BeginingFragment extends Fragment implements View.OnClickListener {

    Button adanaBtn,adres,yemek,spor,tatil,alisveris,sanat,yrmDrtBildirim;
    Boolean checkIl = false;
    Boolean checkYemekEtiket =false,checkAdresEtiket = false,checkSporEtiket = false,
            checkTatilEtiket = false,checkAlisverisEtiket = false,checkSanatEtiket = false,checkYirmiDortBildirim=false;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public BeginingFragment() {
        // Required empty public constructor
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
        adanaBtn=view.findViewById(R.id.il_01);
        adanaBtn.setOnClickListener(this);
        view.findViewById(R.id.il_02).setOnClickListener(this);
        view.findViewById(R.id.il_03).setOnClickListener(this);
        view.findViewById(R.id.il_04).setOnClickListener(this);
        view.findViewById(R.id.il_05).setOnClickListener(this);
        view.findViewById(R.id.il_06).setOnClickListener(this);
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
        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.baslarkenSonrakiBtn:
                startActivity(new Intent(getActivity().getApplicationContext(),HomeActivity.class));
                break;
            case R.id.il_01:
                if(checkIl==false)
                {
                    adanaBtn.setBackground(getActivity().getDrawable(R.drawable.baslarkenilonaybuton));
                    adanaBtn.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    checkIl=true;
                }
                else
                {
                    adanaBtn.setBackground(getActivity().getDrawable(R.drawable.baslarkenilbuton));
                    adanaBtn.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    checkIl=false;
                }

                break;
            case R.id.adresEtiketButon:

                if(checkAdresEtiket == false)
                {
                    adres.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    adres.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgTatil =adres.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                    adres.setCompoundDrawablesWithIntrinsicBounds(imgTatil,null,null,null);
                    checkAdresEtiket = true;
                }
                else
                {
                    adres.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    adres.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgTatil =adres.getContext().getResources().getDrawable(R.mipmap.ic_launcher_adres);
                    adres.setCompoundDrawablesWithIntrinsicBounds(imgTatil,null,null,null);
                    checkAdresEtiket=false;
                }


                break;
            case R.id.yemekEtiketButon:

                if(checkYemekEtiket == false)
                {
                    yemek.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    yemek.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgYemek =yemek.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                    yemek.setCompoundDrawablesWithIntrinsicBounds(imgYemek,null,null,null);
                    checkYemekEtiket = true;
                }
                else
                {
                    yemek.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    yemek.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgYemek =yemek.getContext().getResources().getDrawable(R.mipmap.yemek_icon);
                    yemek.setCompoundDrawablesWithIntrinsicBounds(imgYemek,null,null,null);
                    checkYemekEtiket=false;
                }

                break;
            case R.id.sporEtiketButon:
                if(checkSporEtiket == false)
                {
                    spor.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    spor.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgSpor =spor.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                    spor.setCompoundDrawablesWithIntrinsicBounds(imgSpor,null,null,null);
                    checkSporEtiket = true;
                }
                else
                {
                    spor.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    spor.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgSpor =spor.getContext().getResources().getDrawable(R.mipmap.spor_icon);
                    spor.setCompoundDrawablesWithIntrinsicBounds(imgSpor,null,null,null);
                    checkSporEtiket=false;
                }

                break;
            case R.id.alisverisEtiketButon:
                if(checkAlisverisEtiket == false)
                {
                    alisveris.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    alisveris.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgAlisveris =alisveris.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                    alisveris.setCompoundDrawablesWithIntrinsicBounds(imgAlisveris,null,null,null);
                    checkAlisverisEtiket = true;
                }
                else
                {
                    alisveris.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    alisveris.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgAlisveris =alisveris.getContext().getResources().getDrawable(R.mipmap.alisveris_icon);
                    alisveris.setCompoundDrawablesWithIntrinsicBounds(imgAlisveris,null,null,null);
                    checkAlisverisEtiket=false;
                }
                break;
            case R.id.tatilEtiketButon:
                if(checkTatilEtiket == false)
                {
                    tatil.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    tatil.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgTatil =tatil.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                    tatil.setCompoundDrawablesWithIntrinsicBounds(imgTatil,null,null,null);
                    checkTatilEtiket = true;
                }
                else
                {
                    tatil.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    tatil.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgTatil =tatil.getContext().getResources().getDrawable(R.mipmap.tatil_icon);
                    tatil.setCompoundDrawablesWithIntrinsicBounds(imgTatil,null,null,null);
                    checkTatilEtiket=false;
                }
                break;
            case R.id.sanatEtiketButon:
                if(checkSanatEtiket == false)
                {
                    sanat.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    sanat.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgSanat =sanat.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                    sanat.setCompoundDrawablesWithIntrinsicBounds(imgSanat,null,null,null);
                    checkSanatEtiket = true;
                }
                else
                {
                    sanat.setBackground(getActivity().getDrawable(R.drawable.baslarkenetiketbuton));
                    sanat.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgSanat =sanat.getContext().getResources().getDrawable(R.mipmap.film_icon);
                    sanat.setCompoundDrawablesWithIntrinsicBounds(imgSanat,null,null,null);
                    checkSanatEtiket=false;
                }
                break;
            case R.id.yirmiDortSaatBildirimButon:
                if(checkYirmiDortBildirim == false)
                {
                    yrmDrtBildirim.setBackground(getActivity().getDrawable(R.drawable.baslarkenilonaybuton));
                    Drawable imgyrmDrtBildirim =yrmDrtBildirim.getContext().getResources().getDrawable(R.mipmap.onay_beyaz_icon);
                    yrmDrtBildirim.setCompoundDrawablesWithIntrinsicBounds(imgyrmDrtBildirim,null,null,null);
                    checkYirmiDortBildirim = true;
                }
                else
                {
                    yrmDrtBildirim.setBackground(getActivity().getDrawable(R.drawable.baslarkenilbuton));
                    Drawable imgyrmDrtBildirim =yrmDrtBildirim.getContext().getResources().getDrawable(R.mipmap.bildirim_icon);
                    yrmDrtBildirim.setCompoundDrawablesWithIntrinsicBounds(imgyrmDrtBildirim,null,null,null);
                    checkYirmiDortBildirim=false;
                }
                break;
        }

    }
}
