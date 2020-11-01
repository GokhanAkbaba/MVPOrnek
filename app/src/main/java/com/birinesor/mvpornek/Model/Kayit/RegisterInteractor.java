package com.birinesor.mvpornek.Model.Kayit;

public interface RegisterInteractor {
    interface onRegisterFinishedListener{
        void onKullaniciAdiHatasi(String error);
        void onKullaniciAdiOnay();
        void onAdSoyadHatasi();
        void onAdSoyadOnay();
        void onSifreHatasi();
        void onSifreHatasiOnay();
        void onSifreTekrarHatasi();
        void onSifreTekrarHatasiOnay();
        void onEpostaHatasi(String error);
        void onEpostaOnay();
        void onSifreKontrol();
        void onSuccess();
    }

    void Register(String kullaniciAdi,String adSoyad, String sifre, String sifreTekrar, String ePosta, onRegisterFinishedListener listener);
}
