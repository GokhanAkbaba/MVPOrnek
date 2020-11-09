package com.birinesor.mvpornek.AramaAyarlari;

import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class AramaAyarlariPresenterImpl implements AramaAyarlariPresenter {
    AramaAyarlariView aramaAyarlariView;

    public AramaAyarlariPresenterImpl(AramaAyarlariView aramaAyarlariView) {
        this.aramaAyarlariView = aramaAyarlariView;
    }

    @Override
    public void aramaAyarlariKayit(int kullaniciID, int kodID,int secenek) {
        Call<AramaAyarlariModel> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .aramaAyarlariKayit(kullaniciID,kodID,secenek);

        call.enqueue(new Callback<AramaAyarlariModel>() {
            @Override
            public void onResponse(Call<AramaAyarlariModel> call, Response<AramaAyarlariModel> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    aramaAyarlariView.succesAramaAyarlari();
                }
            }
            @Override
            public void onFailure(Call<AramaAyarlariModel> call, Throwable t) {
                aramaAyarlariView.failedAramaAyarlari();

            }
        });
    }
}
