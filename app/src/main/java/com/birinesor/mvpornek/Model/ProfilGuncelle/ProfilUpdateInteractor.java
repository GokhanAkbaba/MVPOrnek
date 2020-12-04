package com.birinesor.mvpornek.Model.ProfilGuncelle;

public interface ProfilUpdateInteractor {
    interface onProfilUpdateFinishedListener{
        void onGuncelleEPostaHatasi(String message);
        void onGuncelleKullaniciAdiHatasi(String message);
        void onGuncelleKullaniciAdSoyad();
        void onGuncelleKullaniciProfilFoto(String message);
        void onSuccess();
    }
    void profilUpdate(int id,String adSoyad, String kullaniciAdi, String kullaniciEposta,String profilResmi,onProfilUpdateFinishedListener listener);
}
