package com.example.mvpornek.Presenter.Login;
import com.example.mvpornek.Model.Giris.LoginInteractor;
import com.example.mvpornek.Presenter.Login.LoginPresenter;
import com.example.mvpornek.View.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.onLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor)
    {
        this.loginView=loginView;
        this.loginInteractor=loginInteractor;
    }
    @Override
    public void onGirisEPostaHatasi() {
        if(loginView != null)
        {
            loginView.setGirisEPostaAdiHatasi();
            loginView.hideProgress();
        }
    }
    @Override
    public void onGirisSifreHatasi() {
        if(loginView != null)
        {
            loginView.setGirisSifreHatasi();
            loginView.hideProgress();
        }
    }

    @Override
    public void onGirisKontrol(String message) {
        if(loginView != null)
        {
            loginView.setGirisKontrol(message);
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if(loginView != null)
        {
            loginView.navigateToGiris();
            loginView.hideProgress();
        }
    }
    @Override
    public void loginValideCredentals(String ePosta, String sifre) {
        if(loginView != null)
        {
            loginView.showProgress();
        }
        loginInteractor.Login(ePosta,sifre,this);

    }
    @Override
    public void onDestroy() {
        loginView = null;
    }
}
