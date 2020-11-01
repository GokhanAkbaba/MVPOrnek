package com.birinesor.mvpornek.View;


import com.birinesor.mvpornek.Models.KullaniciGetir;

public interface UsersGetView {
    void onGetResult(KullaniciGetir kullaniciGetir);
    void onErrorLoading(String message);
}
