package com.birinesor.mvpornek.Presenter.PasswordUpdate;

import com.birinesor.mvpornek.Model.SifreGuncelle.PasswordUpdateInteractor;
import com.birinesor.mvpornek.Presenter.PasswordUpdate.PasswordUpdatePresenter;
import com.birinesor.mvpornek.View.PasswordUpdate;

public class PasswordUpdatePresenterImpl implements PasswordUpdatePresenter, PasswordUpdateInteractor.onPasswordUpdateFinishedListener {
    private PasswordUpdate passwordUpdateView;
    private PasswordUpdateInteractor passwordUpdateInteractor;

    public PasswordUpdatePresenterImpl(PasswordUpdate passwordUpdateView, PasswordUpdateInteractor passwordUpdateInteractor) {
        this.passwordUpdateView = passwordUpdateView;
        this.passwordUpdateInteractor = passwordUpdateInteractor;
    }

    @Override
    public void updatePasswordValideCredentals(int id, String mevcutSifre, String yeniSifre, String yeniTekrarSifre) {
        if(passwordUpdateView != null)
        {
            passwordUpdateView.showProgress();

        }
        passwordUpdateInteractor.passwordUpdate(id,mevcutSifre,yeniSifre,yeniTekrarSifre,this);
    }

    @Override
    public void onGuncelleMevcutSifreHatasi(String hata) {

        if(passwordUpdateView != null)
        {
            passwordUpdateView.setGuncelleMevcutSifreHatasi(hata);
            passwordUpdateView.hideProgress();
        }

    }

    @Override
    public void onGuncelleYeniSifreHatasi() {
        if(passwordUpdateView != null)
        {
            passwordUpdateView.setGuncelleYeniSifreHatasi();
            passwordUpdateView.hideProgress();
        }

    }

    @Override
    public void onGuncelleYeniTekrarSifreHatasi() {
        if(passwordUpdateView != null)
        {
            passwordUpdateView.setGuncelleYeniTekrarSifreHatasi();
            passwordUpdateView.hideProgress();
        }

    }
    @Override
    public void GuncelleYeniSifreKontrolHatasi() {
        if(passwordUpdateView != null)
        {
            passwordUpdateView.setGuncelleYeniSifreKontrolHatasi();
            passwordUpdateView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if(passwordUpdateView != null)
        {
            passwordUpdateView.navigateToPasswordUpdate();
            passwordUpdateView.hideProgress();
        }
    }
}
