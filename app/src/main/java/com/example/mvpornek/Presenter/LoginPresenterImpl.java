package com.example.mvpornek.Presenter;

import com.example.mvpornek.Model.ModelGiris.LoginInteractor;
import com.example.mvpornek.View.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.onLoginFinishedListener {
   private LoginView loginView;
   private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void onKullaniciAdiHatasi() {
        if(loginView != null)
        {
            loginView.setKullaniciAdiHatasi();
            loginView.hideProgress();
        }
    }

    @Override
    public void onAdSoyadHatasi() {
        if(loginView != null)
        {
            loginView.setAdSoyadHatasi();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSifreHatasi() {
        if(loginView != null)
        {
            loginView.setSifeHatasi();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSifreTekrarHatasi() {
        if(loginView != null)
        {
            loginView.setSifreTekrarHatasi();
            loginView.hideProgress();
        }
    }

    @Override
    public void onEpostaHatasi() {
        if(loginView != null)
        {
            loginView.setEpostaHatasi();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if(loginView != null)
        {
            loginView.navigateToHome();
            loginView.hideProgress();
        }
    }

    @Override
    public void validateCredentials(String kullaniciAdi, String adSoyad, String sifre, String sifreTekrar, String ePosta) {
        if(loginView != null)
        {
            loginView.showProgress();

        }
        loginInteractor.Login(kullaniciAdi,adSoyad,sifre,sifreTekrar,ePosta,this);
    }

    @Override
    public void onDestroy() {
       loginView = null;
    }
}
