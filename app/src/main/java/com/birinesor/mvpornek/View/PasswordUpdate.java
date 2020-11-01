package com.birinesor.mvpornek.View;

public interface PasswordUpdate {
    void showProgress();
    void hideProgress();
    void setGuncelleMevcutSifreHatasi(String hata);
    void setGuncelleYeniSifreHatasi();
    void setGuncelleYeniTekrarSifreHatasi();
    void setGuncelleYeniSifreKontrolHatasi();
    void navigateToPasswordUpdate();
}
