package com.birinesor.mvpornek.Model.SifremiUnuttum;

public
interface ForgotPasswordInteractor {
    interface onForgotFinishedListener{
        void onEpostaHatasi(String error);
        void onEpostaKontrolu();
        void onSuccess();
    }
    void EmailControl(String eposta, ForgotPasswordInteractor.onForgotFinishedListener listener);
}
