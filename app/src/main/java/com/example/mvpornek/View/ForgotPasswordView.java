package com.example.mvpornek.View;

public interface ForgotPasswordView {
    void showProgress();
    void hideProgress();
    void setEpostaHatasi(String error);
    void setEpostaKontrolu();
    void navigateToHome();
}
