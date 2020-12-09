package com.birinesor.mvpornek.Presenter.IpKayit;

import com.birinesor.mvpornek.Response.TokenOlusturResponse;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class IpKayitPresenterImpl implements IpKayitPresenter {
    IpKayit ipKayit;

    public IpKayitPresenterImpl(IpKayit ipKayit) {
        this.ipKayit = ipKayit;
    }

    @Override
    public void createIP(int kullaniciID, String iPv4, String ipV6, String macAdress,String macAdress2) {
        Call<TokenOlusturResponse> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .ipKayitOlustur(kullaniciID, iPv4, ipV6, macAdress,macAdress2);

        call.enqueue(new Callback<TokenOlusturResponse>() {
            @Override
            public void onResponse(Call<TokenOlusturResponse> call, Response<TokenOlusturResponse> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    ipKayit.showIpKayitSuccesMessage();
                }
            }
            @Override
            public void onFailure(Call<TokenOlusturResponse> call, Throwable t) {
                ipKayit.showIpKayitFailedMessage();
            }
        });
    }
}
