package com.example.mvpornek.View;


import com.example.mvpornek.Models.KullaniciGetir;

public interface UsersGetView {
    void onGetResult(KullaniciGetir kullaniciGetir);
    void onErrorLoading(String message);
}
