package com.example.mvpornek.Model.ModelGiris;

import android.os.Handler;
import android.text.TextUtils;

public class LoginInteractorImpl implements LoginInteractor{

    @Override
    public void Login(final String ePosta, final String sifre, final onLoginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(ePosta)){
                    listener.onGirisEPostaHatasi();
                    return;
                }
                if(TextUtils.isEmpty(sifre)){
                    listener.onGirisSifreHatasi();
                    return;
                }
                listener.onSuccess();
            }
        },1000);

    }
}
