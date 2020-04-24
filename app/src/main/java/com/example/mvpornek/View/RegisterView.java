package com.example.mvpornek.View;

public interface RegisterView {
    void showProgress();
    void hideProgress();
    void setKullaniciAdiHatasi(String error);
    void setAdSoyadHatasi();
    void setSifeHatasi();
    void setEpostaHatasi(String error);
    void setSifreTekrarHatasi();
    void setSifreKontrol();
    void navigateToHome();
}
