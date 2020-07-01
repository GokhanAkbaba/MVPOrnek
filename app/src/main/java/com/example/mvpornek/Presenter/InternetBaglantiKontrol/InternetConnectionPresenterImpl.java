package com.example.mvpornek.Presenter.InternetBaglantiKontrol;

import com.example.mvpornek.Model.InternetBaglantiKontrol.InternetConnectionInteractor;
import com.example.mvpornek.Presenter.InternetBaglantiKontrol.InternetConnectionPresenter;
import com.example.mvpornek.View.InternetConnectionView;

public class InternetConnectionPresenterImpl implements InternetConnectionPresenter, InternetConnectionInteractor.onIntBaglantiFinishedListener {
    private InternetConnectionView internetConnectionView;
    private InternetConnectionInteractor internetConnectionInteractor;

    public InternetConnectionPresenterImpl(InternetConnectionView internetConnectionView, InternetConnectionInteractor internetConnectionInteractor) {
        this.internetConnectionView = internetConnectionView;
        this.internetConnectionInteractor = internetConnectionInteractor;
    }

    @Override
    public void internetBaglantiKontrolu() {
        internetConnectionInteractor.internetBaglan(this);
    }

    @Override
    public void onBaglantiHatasi() {
        if(internetConnectionView != null)
        {
            internetConnectionView.internetBaglantiHatasi();
        }

    }

    @Override
    public void onBaglanti() {
        if(internetConnectionView != null)
        {
            internetConnectionView.internetBaglantisi();
        }

    }
}
