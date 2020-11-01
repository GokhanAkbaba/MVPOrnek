package com.birinesor.mvpornek.Model.ProfilGuncelle;

public interface ProfilUpdateInteractor {
    interface onProfilUpdateFinishedListener{
        void onGuncelleEPostaHatasi();
        void onGuncelleKullaniciAdiHatasi();
        void onGuncelleKullaniciAdSoyad();
        void onGuncelleKullaniciProfilFoto(String message);
        void onSuccess();
    }
    void profilUpdate(int id,String adSoyad, String kullaniciAdi, String kullaniciEposta,String profilResmi,onProfilUpdateFinishedListener listener);
}
