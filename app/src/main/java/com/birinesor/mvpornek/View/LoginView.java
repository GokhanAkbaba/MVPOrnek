package com.birinesor.mvpornek.View;

public interface LoginView {
    void showProgress();
    void hideProgress();
    void setGirisEPostaAdiHatasi();
    void setGirisSifreHatasi();
    void setGirisKontrol(String message);
    void navigateToGiris();
}
