package com.birinesor.mvpornek.Fragment.NavBarFragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.birinesor.mvpornek.Activity.Ayarlar.AdminActivity;
import com.birinesor.mvpornek.Activity.Ayarlar.AdminCevaplarActivity;
import com.birinesor.mvpornek.Activity.Ayarlar.BildirimlerDuzenleActivity;
import com.birinesor.mvpornek.Activity.Ayarlar.KazancActivity;
import com.birinesor.mvpornek.Activity.Ayarlar.ProfilDuzenleActivity;
import com.birinesor.mvpornek.Activity.Ayarlar.SifreDuzenleActivity;
import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Baslangic.IntroActivity;
import com.birinesor.mvpornek.BirineSorHelper.BirineSorUtil;
import com.birinesor.mvpornek.Degiskenler;
import com.birinesor.mvpornek.InitApplication;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Presenter.HesapSil.HesapSilPresenterImpl;
import com.birinesor.mvpornek.Presenter.TokenSil.TokenDeletePresenter;
import com.birinesor.mvpornek.Presenter.TokenSil.TokenDeletePresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.View.HesapSilView;
import com.birinesor.mvpornek.View.TokenDeleteView;


import org.jetbrains.annotations.NotNull;


public class SettingsFragment extends Fragment implements View.OnClickListener, TokenDeleteView, HesapSilView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    RelativeLayout hesabiSilLayout;
    RelativeLayout bildirimlerLayout;
    RelativeLayout sifreRelativeDegistirLayout;
    RelativeLayout cikisRelativeLayout;
    RelativeLayout profilRelativeLayout;
    RelativeLayout kazancRelativeLayout;
    RelativeLayout adminRelativeLayout,adminCevaplarLayout;
    TokenDeletePresenter tokenDeletePresenter;
    HesapSilPresenterImpl hesapSilPresenter;

    String TAG="Birine Sor Reklam";

    Toolbar toolbar;

    private Kullanici kullanici;

    public SettingsFragment() {

    }

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        View view=inflater.inflate(R.layout.ayarlar, container, false);
        kullanici=SharedPrefManager.getInstance(getActivity()).getKullanici();
        toolbar=(Toolbar) view.findViewById(R.id.ayarlarToolbar);
        profilRelativeLayout=(RelativeLayout) view.findViewById(R.id.profilDuzenleLayout);
        profilRelativeLayout.setOnClickListener(this);
        cikisRelativeLayout=(RelativeLayout) view.findViewById(R.id.cikisYapLayout);
        cikisRelativeLayout.setOnClickListener(this);
        sifreRelativeDegistirLayout=(RelativeLayout) view.findViewById(R.id.sifreDegistirLayout);
        sifreRelativeDegistirLayout.setOnClickListener(this);
        bildirimlerLayout=(RelativeLayout) view.findViewById(R.id.bildirimlerLayout);
        bildirimlerLayout.setOnClickListener(this);
        hesabiSilLayout=view.findViewById(R.id.hesabiSilLayout);
        hesabiSilLayout.setOnClickListener(this);
        kazancRelativeLayout=view.findViewById(R.id.kazancLayout);
        kazancRelativeLayout.setOnClickListener(this);
        adminRelativeLayout=view.findViewById(R.id.adminLayout);
        adminRelativeLayout.setOnClickListener(this);
        adminCevaplarLayout=view.findViewById(R.id.adminCevaplarLayout);
        adminCevaplarLayout.setOnClickListener(this);
        Switch geceSwitch=view.findViewById(R.id.geceModuSwitch);
        tokenDeletePresenter=new TokenDeletePresenterImpl(this);
        hesapSilPresenter=new HesapSilPresenterImpl(this);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            geceSwitch.setChecked(true);
        geceSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                InitApplication.getInstance(getActivity()).setIsNightModeEnabled(true);
                Intent i=new Intent(getActivity(), HomeActivity.class);
                startActivity(i);

            } else {
                InitApplication.getInstance(getActivity()).setIsNightModeEnabled(false);
                Intent i=new Intent(getActivity(), HomeActivity.class);
                startActivity(i);

            }
        });
        HomeActivity.getInstance().startAds();
        return view;

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.profilDuzenleLayout:
                startActivity(new Intent(getActivity().getApplicationContext(), ProfilDuzenleActivity.class));
                getActivity().overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
                break;
            case R.id.cikisYapLayout:
                SharedPrefManager.getInstance(getActivity()).clear();
                startActivity(new Intent(getActivity().getApplicationContext(), IntroActivity.class));
                tokenDeletePresenter.deleteToken(kullanici.getId());
                BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBaslat(getActivity(),null,"Çıkış İşlemi Gerçekleştiriliyor");
                break;
            case R.id.sifreDegistirLayout:
                startActivity(new Intent(getActivity().getApplicationContext(), SifreDuzenleActivity.class));
                getActivity().overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
                break;
            case R.id.bildirimlerLayout:
                startActivity(new Intent(getActivity().getApplicationContext(), BildirimlerDuzenleActivity.class));
                getActivity().overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
                break;
            case R.id.kazancLayout:
                //showInterstitial();
                HomeActivity.getInstance().showInterstitial();
                startActivity(new Intent(getActivity().getApplicationContext(), KazancActivity.class));
                getActivity().overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
                break;
            case R.id.adminLayout:
                startActivity(new Intent(getActivity().getApplicationContext(), AdminActivity.class));
                getActivity().overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
                break;
            case R.id.adminCevaplarLayout:
                startActivity(new Intent(getActivity().getApplicationContext(), AdminCevaplarActivity.class));
                getActivity().overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
                break;
            case R.id.hesabiSilLayout:
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Birine Sor");
                    builder.setMessage("Hesabınızı silmek istediğinizden emin misiniz?");
                    builder.setNegativeButton("Hayır", null);
                    getActivity().getWindow().setBackgroundDrawableResource(R.color.colorWhite);
                    builder.setOnDismissListener(dialogInterface -> {
                    });
                    builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SharedPrefManager.getInstance(getActivity()).clear();
                            startActivity(new Intent(getActivity().getApplicationContext(), IntroActivity.class));
                            hesapSilPresenter.kullaniciSil(kullanici.getId());
                            BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBaslat(getActivity(),null,"Çıkış İşlemi Gerçekleştiriliyor");
                        }
                    });
                    builder.show();


                break;
        }
    }

    @Override
    public void showTokenDeleteSuccesMessage() {
        Log.d("Token Delete Success","Token Başarı ile Silindi");
    }

    @Override
    public void showTokenDeleteFailedMessage(String failedMessage) {
        Log.d("Token Delete Failed",failedMessage);
    }

    @Override
    public void showSuccesMessage() {

    }

    @Override
    public void showFailedMessage() {

    }
}
