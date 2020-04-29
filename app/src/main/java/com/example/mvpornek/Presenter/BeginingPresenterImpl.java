package com.example.mvpornek.Presenter;

import com.example.mvpornek.Model.ModelGiris.BeginingInteractor;
import com.example.mvpornek.View.BeginingView;

public class BeginingPresenterImpl implements BeginingPresenter, BeginingInteractor.onBeginingFinishedListener {
    private BeginingView beginingView;
    private BeginingInteractor beginingInteractor;

    public BeginingPresenterImpl(BeginingView beginingView, BeginingInteractor beginingInteractor) {
        this.beginingView = beginingView;
        this.beginingInteractor = beginingInteractor;
    }

    @Override
    public void validateOptions(int id, int il, int secim) {
        beginingInteractor.optionsSave(id,il,secim,this);
    }

    @Override
    public void onIlHatasi() {
        if(beginingView != null)
        {
            beginingView.setIlHatasi();
        }
    }

    @Override
    public void onEtiketHatasi() {
        if(beginingView != null)
        {
            beginingView.setEtiketHatasi();
        }
    }

    @Override
    public void onSuccess() {
        if(beginingView != null)
        {
            beginingView.navigateToHome();
        }
    }
}
