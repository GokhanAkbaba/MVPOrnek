package com.birinesor.mvpornek.AramaAyarlari.AramaAyarlariGetir;

import com.birinesor.mvpornek.Models.IlgiAlanlariGetirModel;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class AramaAyarlariGetirImpl implements AramaAyarlariGetirPresenter {
    AramaAyarlariGetirView aramaAyarlariGetirView;

    public AramaAyarlariGetirImpl(AramaAyarlariGetirView aramaAyarlariGetirView) {
        this.aramaAyarlariGetirView = aramaAyarlariGetirView;
    }

    @Override
    public void aramaAyarlariGetirLoad(int kullaniciId) {
        Call<List<AramaAyarlariGetirModel>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .aramaAyarlariGetir(kullaniciId);

        call.enqueue(new Callback<List<AramaAyarlariGetirModel>>() {
            @Override
            public void onResponse(Call<List<AramaAyarlariGetirModel>> call, Response<List<AramaAyarlariGetirModel>> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    aramaAyarlariGetirView.onGetResultAramaAyarlariGetir(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<AramaAyarlariGetirModel>> call, Throwable t) {
                aramaAyarlariGetirView.onErrorLoadingAramaAyarlariGetir(t.getMessage());
            }
        });
    }
}
