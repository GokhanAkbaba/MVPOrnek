package com.example.mvpornek;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvpornek.BirineSorHelper.BirineSorUtil;
import com.example.mvpornek.Model.Kullanici;
import com.example.mvpornek.Model.ModelGiris.GirisUygulamasi;
import com.example.mvpornek.Presenter.GirisPrensenter;
import com.example.mvpornek.Presenter.GirisPresenterUygulamasi;
import com.example.mvpornek.View.GirisView;
import com.example.mvpornek.WebService.ServicesHelper;
import com.kaopiz.kprogresshud.KProgressHUD;

public class LoginActivity extends Activity implements GirisView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText adSoyad,kullaniciAdi,eMail,sifre,sifreTekrar;
    private GirisPrensenter prensenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_kullanici_kayit);

        adSoyad = findViewById(R.id.adSoyadText);
        kullaniciAdi = findViewById(R.id.kullaniciAdiText);
        eMail = findViewById(R.id.ePostaText);
        sifre = findViewById(R.id.sifreText);
        sifreTekrar = findViewById(R.id.sifreTekrariText);

        findViewById(R.id.kayitYapBtn).setOnClickListener(this);
        BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBaslat(this,null,null);
        ServicesHelper servicesHelper = new ServicesHelper();
        //servicesHelper.tumKullanicilariGetir();
        servicesHelper.kullaniciGetir("HasanKarasahin");

        prensenter = new GirisPresenterUygulamasi(this, new GirisUygulamasi());
    }

    @Override
    protected void onDestroy() {
        prensenter.uygulamayiYokEt();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        Kullanici kullanici = new Kullanici(adSoyad.getText().toString(),kullaniciAdi.getText().toString(),eMail.getText().toString(),sifre.getText().toString(),sifreTekrar.getText().toString());
        prensenter.girisKontrolIslemleri(kullanici);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setUsernameError() {

    }

    @Override
    public void setPasswordError() {

    }

    @Override
    public void navigateToHome() {
        Toast.makeText(this, "OOO Helal Adamımmmmm",Toast.LENGTH_SHORT).show();
    }
}
