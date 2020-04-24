package com.example.mvpornek.Model.ModelGiris;

import android.os.Handler;
import android.text.TextUtils;


public class RegisterInteractorImpl implements RegisterInteractor {
    @Override
    public void Register(final String kullaniciAdi, final String adSoyad, final String sifre, final String sifreTekrar, final String ePosta, final onRegisterFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(adSoyad)) {
                    listener.onAdSoyadHatasi();
                    return;
                }

                if (TextUtils.isEmpty(kullaniciAdi)) {
                    listener.onKullaniciAdiHatasi();
                    return;
                }

                if (TextUtils.isEmpty(sifre)) {
                    listener.onSifreHatasi();
                    return;
                }

                if (TextUtils.isEmpty(sifreTekrar)) {
                    listener.onSifreTekrarHatasi();
                    return;
                }

                if (TextUtils.isEmpty(ePosta)) {
                    listener.onEpostaHatasi();
                    return;
                }

                if (!TextUtils.equals(sifre, sifreTekrar)) {
                    listener.onSifreKontrol();
                    return;
                }
                listener.onSuccess();
            }
        }, 1000);
    }

}
