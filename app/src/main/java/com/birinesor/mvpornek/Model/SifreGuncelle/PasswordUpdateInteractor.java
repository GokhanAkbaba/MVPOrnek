package com.birinesor.mvpornek.Model.SifreGuncelle;

public interface PasswordUpdateInteractor {
    interface onPasswordUpdateFinishedListener{
        void onGuncelleMevcutSifreHatasi(String hata);
        void onGuncelleYeniSifreHatasi();
        void onGuncelleYeniTekrarSifreHatasi();
        void GuncelleYeniSifreKontrolHatasi();
        void onSuccess();
    }
    void passwordUpdate(int id, String mevcutSifre, String yeniSifre, String yeniTekrarSifre, PasswordUpdateInteractor.onPasswordUpdateFinishedListener listener);
}
