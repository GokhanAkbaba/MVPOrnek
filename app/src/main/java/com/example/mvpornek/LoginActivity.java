package com.example.mvpornek;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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
    private EditText editTextadSoyad,editTextkullaniciAdi,editTextMail,editTextsifre,editTextsifreTekrar;
    private GirisPrensenter prensenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_kullanici_kayit);

        editTextadSoyad = findViewById(R.id.adSoyadText);
        editTextkullaniciAdi = findViewById(R.id.kullaniciAdiText);
        editTextMail = findViewById(R.id.ePostaText);
        editTextsifre = findViewById(R.id.sifreText);
        editTextsifreTekrar = findViewById(R.id.sifreTekrariText);

        findViewById(R.id.kayitYapBtn).setOnClickListener(this);
        BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBaslat(this,null,null);
        ServicesHelper servicesHelper = new ServicesHelper();
        //servicesHelper.tumKullanicilariGetir();
        servicesHelper.kullaniciGetir("HasanKarasahin");

        prensenter = new GirisPresenterUygulamasi(this, new GirisUygulamasi());
    }
    private void userSignUp()
    {
        String ePosta = editTextMail.getText().toString().trim();
        String sifre=editTextsifre.getText().toString().trim();
        String adSoyad=editTextadSoyad.getText().toString().trim();
        String kullaniciAdi=editTextadSoyad.getText().toString().trim();

        if(ePosta.isEmpty())
        {
            editTextMail.setError("E-posta adresiniz gerekli");
            editTextMail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(ePosta).matches())
        {
            editTextMail.setError("Enter a valid ePosta");
            editTextMail.requestFocus();
            return;
        }

        if(sifre.isEmpty())
        {
            editTextsifre.setError("Şifreniz Gerekli");
            editTextsifre.requestFocus();
            return;
        }

        if(sifre.length() < 6)
        {
            editTextsifre.setError("Şifreniz en az 6 karkater olmalıdır");
            editTextsifre.requestFocus();
            return;
        }

        if(adSoyad.isEmpty())
        {
            editTextsifre.setError("Ad ve Soyad Gerekli");
            editTextsifre.requestFocus();
            return;
        }

        if(kullaniciAdi.isEmpty())
        {
            editTextsifre.setError("Kullanici Adınız Gerekli");
            editTextsifre.requestFocus();
            return;
        }

    }
    @Override
    protected void onDestroy() {
        prensenter.uygulamayiYokEt();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        Kullanici kullanici = new Kullanici(editTextadSoyad.getText().toString(),editTextkullaniciAdi.getText().toString(),editTextMail.getText().toString(),editTextsifre.getText().toString(),editTextsifreTekrar.getText().toString());
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
