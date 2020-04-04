package com.example.mvpornek.Model.ModelGiris;

public interface LoginInteractor {
    interface onLoginFinishedListener{
        void onKullaniciAdiHatasi();
        void onAdSoyadHatasi();
        void onSifreHatasi();
        void onSifreTekrarHatasi();
        void onEpostaHatasi();
        void onSuccess();
    }

    void Login(String kullaniciAdi,String adSoyad, String sifre, String sifreTekrar, String ePosta, onLoginFinishedListener listener);
}
