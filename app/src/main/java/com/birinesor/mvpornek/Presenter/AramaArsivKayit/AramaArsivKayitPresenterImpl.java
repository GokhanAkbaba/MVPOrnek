package com.birinesor.mvpornek.Presenter.AramaArsivKayit;

import com.birinesor.mvpornek.Response.AramaArsivKayitResponse;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class AramaArsivKayitPresenterImpl implements AramaArsivKayitPresenter {
    AramaArsivKayitView aramaArsivKayitView;

    public AramaArsivKayitPresenterImpl(AramaArsivKayitView aramaArsivKayitView) {
        this.aramaArsivKayitView = aramaArsivKayitView;
    }

    @Override
    public void createAramaArsivKayit(int kullaniciID, String arananIcerik) {

        Call<AramaArsivKayitResponse> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .aramaArsivKayit(kullaniciID,arananIcerik);

        call.enqueue(new Callback<AramaArsivKayitResponse>() {
            @Override
            public void onResponse(Call<AramaArsivKayitResponse> call, Response<AramaArsivKayitResponse> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    aramaArsivKayitView.showAramaArsivKayitSuccesMessage();
                }
            }
            @Override
            public void onFailure(Call<AramaArsivKayitResponse> call, Throwable t) {
                aramaArsivKayitView.showAramaArsivKayitFailedMessage();

            }
        });

    }
}
