package com.example.mvpornek.Model;

public interface LoginInteractor {

    interface OnLoginFinishedListener{

        void validateCredentials(String username, String password);

        void onDestroyed();

        void onUsernameError();

        void onPasswordError();

        void onSuccess();
    }

    void login(String username, String password, OnLoginFinishedListener listener);
}
