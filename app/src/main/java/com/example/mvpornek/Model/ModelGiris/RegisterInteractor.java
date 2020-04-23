package com.example.mvpornek.Model.ModelGiris;

public interface RegisterInteractor {
    interface onRegisterFinishedListener{
        void onKullaniciAdiHatasi();
        void onOffKullaniciAdiHatasi();
        void onAdSoyadHatasi();
        void onOffAdSoyadHatasi();
        void onSifreHatasi();
        void onOffSifreHatasi();
        void onSifreTekrarHatasi();
        void onOffSifreTekrarHatasi();
        void onEpostaHatasi();
        void onOffEpostaHatasi();
        void onSifreKontrol();
        void onOffSifreKontrol();
        void onSuccess();
    }

    void Register(String kullaniciAdi,String adSoyad, String sifre, String sifreTekrar, String ePosta, onRegisterFinishedListener listener);
}
