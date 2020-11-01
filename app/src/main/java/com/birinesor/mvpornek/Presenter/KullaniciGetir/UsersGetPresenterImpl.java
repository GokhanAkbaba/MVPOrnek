package com.birinesor.mvpornek.Presenter.KullaniciGetir;


import com.birinesor.mvpornek.Response.KullaniciGetirResponse;
import com.birinesor.mvpornek.View.UsersGetView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersGetPresenterImpl implements UsersGetPresenter {
    UsersGetView usersGetView;

    public UsersGetPresenterImpl(UsersGetView usersGetView) {
        this.usersGetView = usersGetView;
    }

    @Override
    public void loadUsersData(int kullaniciId) {
        Call<KullaniciGetirResponse> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .kullaniciGetir(kullaniciId);

        call.enqueue(new Callback<KullaniciGetirResponse>() {
            @Override
            public void onResponse(Call<KullaniciGetirResponse> call, Response<KullaniciGetirResponse> response) {
                KullaniciGetirResponse kullaniciGetirResponse=response.body();
                Boolean hata=response.body().getError();
                if (response.isSuccessful() && response.body() !=null)
                {
                    if(hata==false)
                    {
                    usersGetView.onGetResult(kullaniciGetirResponse.getKullaniciGetir());
                    }else{
                    }
                }
            }
            @Override
            public void onFailure(Call<KullaniciGetirResponse> call, Throwable t) {
                System.out.println("Bağlantı Hatası(Kullanıcı Getir) "+t.getMessage());
            }
        });
    }
}
