package com.birinesor.mvpornek.Presenter.SoruOnay;

import com.birinesor.mvpornek.Models.SorularOnayModel;
import com.birinesor.mvpornek.Presenter.SoruOnay.SoruOnayPresenter;
import com.birinesor.mvpornek.View.SoruOnayView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SoruOnayPresenterImpl implements SoruOnayPresenter {
    SoruOnayView soruOnayView;

    public SoruOnayPresenterImpl(SoruOnayView soruOnayView) {
        this.soruOnayView = soruOnayView;
    }

    @Override
    public void loadSoruOnay() {
        soruOnayView.onSoruOnayShow();
        Call<List<SorularOnayModel>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .soruOnaylariGetir(5);

        call.enqueue(new Callback<List<SorularOnayModel>>() {
            @Override
            public void onResponse(Call<List<SorularOnayModel>> call, Response<List<SorularOnayModel>> response) {
                soruOnayView.onSoruOnayHide();
                if (response.isSuccessful() && response.body() !=null) {
                    soruOnayView.onSoruOnayResult(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<SorularOnayModel>> call, Throwable t) {
                soruOnayView.onSoruOnayHide();
                soruOnayView.onSoruOnayErrorLoading(t.getMessage());
            }
        });
    }
}
