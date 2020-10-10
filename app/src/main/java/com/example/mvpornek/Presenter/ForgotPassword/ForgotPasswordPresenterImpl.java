package com.example.mvpornek.Presenter.ForgotPassword;

import com.example.mvpornek.Model.SifremiUnuttum.ForgotPasswordInteractor;
import com.example.mvpornek.Presenter.ForgotPassword.ForgotPasswordPresenter;
import com.example.mvpornek.View.ForgotPasswordView;

public
class ForgotPasswordPresenterImpl implements ForgotPasswordPresenter, ForgotPasswordInteractor.onForgotFinishedListener {
    ForgotPasswordView forgotPasswordView;
    ForgotPasswordInteractor forgotPasswordInteractor;

    public ForgotPasswordPresenterImpl(ForgotPasswordView forgotPasswordView, ForgotPasswordInteractor forgotPasswordInteractor) {
        this.forgotPasswordView = forgotPasswordView;
        this.forgotPasswordInteractor = forgotPasswordInteractor;
    }

    @Override
    public void validateCredentials(String ePosta) {
        if(forgotPasswordView != null)
        {
            forgotPasswordView.showProgress();
        }
        forgotPasswordInteractor.EmailControl(ePosta,this);
    }

    @Override
    public void onDestroy() {
        forgotPasswordView=null;
    }

    @Override
    public void onEpostaHatasi(String error) {
        if(forgotPasswordView != null)
        {
            forgotPasswordView.setEpostaHatasi(error);
            forgotPasswordView.hideProgress();
        }
    }

    @Override
    public void onEpostaKontrolu() {
        if(forgotPasswordView != null)
        {
            forgotPasswordView.setEpostaKontrolu();
            forgotPasswordView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if(forgotPasswordView != null)
        {
            forgotPasswordView.navigateToHome();
            forgotPasswordView.hideProgress();
        }
    }
}
