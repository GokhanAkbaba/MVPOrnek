package com.birinesor.mvpornek.Presenter.ProfilUpdate;

import com.birinesor.mvpornek.Model.ProfilGuncelle.ProfilUpdateInteractor;
import com.birinesor.mvpornek.Presenter.ProfilUpdate.ProfilUpdatePresenter;
import com.birinesor.mvpornek.View.ProfilUpdateView;

public class ProfilUpdatePresenterImpl implements ProfilUpdatePresenter,ProfilUpdateInteractor.onProfilUpdateFinishedListener {
    private ProfilUpdateView profilUpdateView;
    private ProfilUpdateInteractor profilUpdateInteractor;

    public ProfilUpdatePresenterImpl(ProfilUpdateView profilUpdateView, ProfilUpdateInteractor profilUpdateInteractor) {
        this.profilUpdateView = profilUpdateView;
        this.profilUpdateInteractor = profilUpdateInteractor;
    }

    @Override
    public void updateValideCredentals(int id, String adSoyad, String kullaniciAdi, String kullaniciEposta, String profilResmi) {

        if(profilUpdateView != null)
        {
            profilUpdateView.showProgress();

        }
        profilUpdateInteractor.profilUpdate(id,adSoyad,kullaniciAdi,kullaniciEposta,profilResmi,this);

    }

    @Override
    public void onGuncelleEPostaHatasi(String message) {
        if(profilUpdateView != null)
        {
            profilUpdateView.setGuncelleEPostaAdiHatasi(message);
            profilUpdateView.hideProgress();
        }
    }

    @Override
    public void onGuncelleKullaniciAdiHatasi(String message) {
        if(profilUpdateView != null)
        {
            profilUpdateView.setGuncelleKullaniciAdiHatasi(message);
            profilUpdateView.hideProgress();
        }

    }

    @Override
    public void onGuncelleKullaniciAdSoyad() {
        if(profilUpdateView != null)
        {
            profilUpdateView.setGuncelleKullaniciAdiSoyad();
            profilUpdateView.hideProgress();
        }
    }

    @Override
    public void onGuncelleKullaniciProfilFoto(String message) {
        if(profilUpdateView != null)
        {
            profilUpdateView.setGuncelleKullaniciProfilFoto(message);
            profilUpdateView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if(profilUpdateView != null)
        {
            profilUpdateView.navigateToProfilUpdate();
            profilUpdateView.hideProgress();

        }
    }
}
