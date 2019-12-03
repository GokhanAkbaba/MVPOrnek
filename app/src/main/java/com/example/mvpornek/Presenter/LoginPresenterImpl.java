package com.example.mvpornek.Presenter;

import com.example.mvpornek.Model.LoginInteractor;
import com.example.mvpornek.View.LoginView;

public class LoginPresenterImpl implements LoginInteractor, LoginInteractor.OnLoginFinishedListener {

    LoginView loginView;
    LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void validateCredentials(String username, String password) {

        if(loginView != null)
        {
            loginView.showProgress();
        }
        loginInteractor.login(username,password, this);
    }

    @Override
    public void onDestroyed() {

        loginView = null;

    }

    @Override
    public void onUsernameError() {

        if(loginView!= null )
        {
            loginView.setUsernameError();
            loginView.hideProgress();
        }

    }

    @Override
    public void onPasswordError() {

        if(loginView != null)
        {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {

        if(loginView != null)
        {
            loginView.navigateToHome();
            loginView.hideProgress();
        }

    }

    @Override
    public void login(String username, String password, OnLoginFinishedListener listener) {

    }


}
