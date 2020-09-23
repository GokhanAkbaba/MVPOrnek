package com.example.mvpornek.Model.Giris;

public interface LoginInteractor {
    interface onLoginFinishedListener{
        void onGirisEPostaHatasi();
        void onGirisSifreHatasi();
        void onGirisKontrol(String message);
        void onSuccess();
    }
    void Login(String ePosta, String sifre, onLoginFinishedListener listener);
}
