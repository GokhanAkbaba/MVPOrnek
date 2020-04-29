package com.example.mvpornek.Model.ModelGiris;

import android.text.TextUtils;

public class BeginingInteractorImpl implements BeginingInteractor {
    @Override
    public void optionsSave(int id, int il,int secim,onBeginingFinishedListener listener) {

        if(il==0){
            listener.onIlHatasi();
            return;
        }

        if(secim ==0){
            listener.onEtiketHatasi();
            return;
        }
        listener.onSuccess();

    }
}
