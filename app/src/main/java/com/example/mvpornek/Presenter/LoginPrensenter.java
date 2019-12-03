package com.example.mvpornek.Presenter;

public interface LoginPrensenter {

    void validateCredentials(String username, String password);

    void onDestroyed();

    public interface OnLoginFinishedListener {
    }
}
