package com.example.mvpornek.Model.ModelGiris;

import android.content.Context;

public class PasswordUpdateInteractorImpl implements PasswordUpdateInteractor {
    Context context;

    public PasswordUpdateInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void passwordUpdate(int id, String mevcutSifre, String yeniSifre, String yeniTekrarSifre, onPasswordUpdateFinishedListener listener) {

    }
}
