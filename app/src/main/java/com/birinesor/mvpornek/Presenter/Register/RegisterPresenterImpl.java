package com.birinesor.mvpornek.Presenter.Register;

import com.birinesor.mvpornek.Model.Kayit.RegisterInteractor;
import com.birinesor.mvpornek.Presenter.Register.RegisterPresenter;
import com.birinesor.mvpornek.View.RegisterView;

public class RegisterPresenterImpl implements RegisterPresenter, RegisterInteractor.onRegisterFinishedListener {
   private RegisterView registerView;
   private RegisterInteractor registerInteractor;

    public RegisterPresenterImpl(RegisterView registerView, RegisterInteractor registerInteractor) {
        this.registerView = registerView;
        this.registerInteractor = registerInteractor;
    }

    @Override
    public void onKullaniciAdiHatasi(String error) {
        if(registerView != null)
        {
            registerView.setKullaniciAdiHatasi(error);
            registerView.hideProgress();
        }
    }

    @Override
    public void onKullaniciAdiOnay() {
        if(registerView != null)
        {
            registerView.setKullaniciAdiOnay();
            registerView.hideProgress();
        }
    }

    @Override
    public void onAdSoyadHatasi() {
        if(registerView != null)
        {
            registerView.setAdSoyadHatasi();
            registerView.hideProgress();
        }
    }

    @Override
    public void onAdSoyadOnay() {
        if(registerView != null)
        {
            registerView.setAdSoyadOnay();
            registerView.hideProgress();
        }
    }

    @Override
    public void onSifreHatasi() {
        if(registerView != null)
        {
            registerView.setSifeHatasi();
            registerView.hideProgress();
        }
    }

    @Override
    public void onSifreHatasiOnay() {
        if(registerView != null)
        {
            registerView.setSifreHatasiOnay();
            registerView.hideProgress();
        }
    }

    @Override
    public void onSifreTekrarHatasi() {
        if(registerView != null)
        {
            registerView.setSifreTekrarHatasi();
            registerView.hideProgress();
        }
    }

    @Override
    public void onSifreTekrarHatasiOnay() {
        if(registerView != null)
        {
            registerView.setSifreTekrarOnay();
            registerView.hideProgress();
        }
    }

    @Override
    public void onEpostaHatasi(String error) {
        if(registerView != null)
        {
            registerView.setEpostaHatasi(error);
            registerView.hideProgress();
        }
    }

    @Override
    public void onEpostaOnay() {
        if(registerView != null)
        {
            registerView.setEpostaOnay();
            registerView.hideProgress();
        }
    }

    @Override
    public void onSifreKontrol() {
        if(registerView != null)
        {
            registerView.setSifreKontrol();
            registerView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if(registerView != null)
        {
            registerView.navigateToHome();
        }
    }
    @Override
    public void validateCredentials(String kullaniciAdi, String adSoyad, String sifre, String sifreTekrar, String ePosta) {
        if(registerView != null)
        {
            registerView.showProgress();
        }
        registerInteractor.Register(kullaniciAdi,adSoyad,sifre,sifreTekrar,ePosta,this);
    }

    @Override
    public void onDestroy() {
        registerView = null;

    }
}
