package com.example.mvpornek.Presenter;

import com.example.mvpornek.Response.AramaGecmisSilResponse;
import com.example.mvpornek.Response.SearchListResponse;
import com.example.mvpornek.View.AramaGecmisSilView;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class AramaGecmisSilPresenterImpl implements AramaGecmisSilPresenter {
    AramaGecmisSilView aramaGecmisSilView;

    public AramaGecmisSilPresenterImpl(AramaGecmisSilView aramaGecmisSilView) {
        this.aramaGecmisSilView = aramaGecmisSilView;
    }

    @Override
    public void deleteAramaGEcmis(int kayitId) {
        Call<AramaGecmisSilResponse> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .aramaGecmisSil(kayitId);

        call.enqueue(new Callback<AramaGecmisSilResponse>() {
            @Override
            public void onResponse(Call<AramaGecmisSilResponse> call, Response<AramaGecmisSilResponse> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    aramaGecmisSilView.showAramaGecmisSuccesMessage();
                }
            }
            @Override
            public void onFailure(Call<AramaGecmisSilResponse> call, Throwable t) {
                aramaGecmisSilView.showAramaGecmisFailedMessage();
            }
        });
    }
}
