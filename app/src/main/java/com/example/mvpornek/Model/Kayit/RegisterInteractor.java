package com.example.mvpornek.Model.Kayit;

public interface RegisterInteractor {
    interface onRegisterFinishedListener{
        void onKullaniciAdiHatasi(String error);
        void onAdSoyadHatasi();
        void onSifreHatasi();
        void onSifreTekrarHatasi();
        void onEpostaHatasi(String error);
        void onSifreKontrol();
        void onSuccess();
    }

    void Register(String kullaniciAdi,String adSoyad, String sifre, String sifreTekrar, String ePosta, onRegisterFinishedListener listener);
}
