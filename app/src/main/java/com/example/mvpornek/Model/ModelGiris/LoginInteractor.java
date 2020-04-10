package com.example.mvpornek.Model.ModelGiris;

public interface LoginInteractor {
    interface onLoginFinishedListener{
        void onGirisEPostaHatasi();
        void onGirisSifreHatasi();
        void onSuccess();
    }
    void Login(String ePosta);
}
