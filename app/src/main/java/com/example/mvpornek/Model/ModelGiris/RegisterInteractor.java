package com.example.mvpornek.Model.ModelGiris;

public interface RegisterInteractor {
    interface onRegisterFinishedListener{
        void onKullaniciAdiHatasi();
        void onAdSoyadHatasi();
        void onSifreHatasi();
        void onSifreTekrarHatasi();
        void onEpostaHatasi();
        void onSuccess();
    }

    void Register(String kullaniciAdi,String adSoyad, String sifre, String sifreTekrar, String ePosta, onRegisterFinishedListener listener);
}
