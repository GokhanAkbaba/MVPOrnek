package com.example.mvpornek.Presenter;

import android.util.Log;

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
        Log.e("onKullaniciAdiHatasi","onKullaniciAdiHatasi");
    }

    @Override
    public void onAdSoyadHatasi() {
        if(loginView != null)
        {
            loginView.setAdSoyadHatasi();
            loginView.hideProgress();
        }
        Log.e("onAdSoyadHatasi","onAdSoyadHatasi");
    }

    @Override
    public void onSifreHatasi() {
        if(loginView != null)
        {
            loginView.setSifeHatasi();
            loginView.hideProgress();
        }
        Log.e("onSifreHatasi","onSifreHatasi");
    }

    @Override
    public void onSifreTekrarHatasi() {
        if(loginView != null)
        {
            loginView.setSifreTekrarHatasi();
            loginView.hideProgress();
        }
        Log.e("onSifreTekrarHatasi","onSifreTekrarHatasi");
    }

    @Override
    public void onEpostaHatasi() {
        if(loginView != null)
        {
            loginView.setEpostaHatasi();
            loginView.hideProgress();
        }
        Log.e("onEpostaHatasi","onEpostaHatasi");
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
