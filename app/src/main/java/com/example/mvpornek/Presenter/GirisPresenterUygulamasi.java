package com.example.mvpornek.Presenter;
import android.util.Log;

import com.example.mvpornek.Model.Kullanici;
import com.example.mvpornek.Model.ModelGiris.GirisEtkilesimi;
import com.example.mvpornek.View.GirisView;

public class GirisPresenterUygulamasi implements GirisEtkilesimi, GirisEtkilesimi.GirisDinleyicisi, GirisPrensenter{

    GirisView girisView;
    GirisEtkilesimi girisEtkilesimi;

    public GirisPresenterUygulamasi(GirisView girisView, GirisEtkilesimi girisEtkilesimi) {
        this.girisView = girisView;
        this.girisEtkilesimi = girisEtkilesimi;
    }


    @Override
    public void girisKontrolIslemleri(Kullanici kullanici) {

        if(girisView != null)
        {
            girisView.showProgress();
        }
        girisEtkilesimi.giris(kullanici, this);
    }

    @Override
    public void uygulamayiYokEt() {
        Log.v("MESAJ HATASI","---------------------------------------------------------Uygulamayı Yok Et -----------------------------------------------------------");
    }

    @Override
    public void sifreTekrarHatasi() {
        Log.v("MESAJ HATASI","---------------------------------------------------------Şifre Tekrar Boş -----------------------------------------------------------");
    }

    @Override
    public void sifrelerFarkliHatasi() {
        Log.v("MESAJ HATASI","---------------------------------------------------------Şifreler Farklı -----------------------------------------------------------");
    }

    @Override
    public void adSoyadHatasi() {
        Log.v("MESAJ HATASI","---------------------------------------------------------Ad Soyad BOŞ -----------------------------------------------------------");
    }

    @Override
    public void eMailHatasi() {
        Log.v("MESAJ HATASI","---------------------------------------------------------E-mail BOŞ -----------------------------------------------------------");
    }

    @Override
    public void sifreHatasi() {
        Log.v("MESAJ HATASI","---------------------------------------------------------Şifre BOŞ -----------------------------------------------------------");
    }

    @Override
    public void kullaniciAdiHatasi() {
        Log.v("MESAJ HATASI","---------------------------------------------------------kullanıcı Adı BOŞ -----------------------------------------------------------");
    }

    @Override
    public void girisBasarili() {
        Log.v("MESAJ HATASI","---------------------------------------------------------Giriş Başarılı -----------------------------------------------------------");
        girisView.navigateToHome();
    }

    @Override
    public void giris(Kullanici kullanici, GirisDinleyicisi dinleyici) {

    }
}
