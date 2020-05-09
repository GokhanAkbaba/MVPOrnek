package com.example.mvpornek.Model.ModelGiris;

public interface ProfilUpdateInteractor {
    interface onProfilUpdateFinishedListener{
        void onGuncelleEPostaHatasi();
        void onGuncelleKullaniciAdiHatasi();
        void onGuncelleKullaniciAdSoyad();
        void onSuccess();
    }
    void profilUpdate(int id,String adSoyad, String kullaniciAdi, String kullaniciEposta,String profilResmi,onProfilUpdateFinishedListener listener);
}
