package com.birinesor.mvpornek.Presenter;

import com.birinesor.mvpornek.Models.CevaplarOnayModels;
import com.birinesor.mvpornek.Models.SorularOnayModel;
import com.birinesor.mvpornek.View.CevapOnayView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class CevapOnayPresenterImpl implements CevapOnayPresenter{
    CevapOnayView cevapOnayView;

    public CevapOnayPresenterImpl(CevapOnayView cevapOnayView) {
        this.cevapOnayView = cevapOnayView;
    }

    @Override
    public void cevapOnayLoad() {
        cevapOnayView.onCevapOnayShow();
        Call<List<CevaplarOnayModels>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .cevapOnaylariGetir(5);

        call.enqueue(new Callback<List<CevaplarOnayModels>>() {
            @Override
            public void onResponse(Call<List<CevaplarOnayModels>> call, Response<List<CevaplarOnayModels>> response) {
                cevapOnayView.onCevapOnayHide();
                if (response.isSuccessful() && response.body() !=null) {
                    cevapOnayView.onCevapOnayResult(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<CevaplarOnayModels>> call, Throwable t) {
                cevapOnayView.onCevapOnayHide();
                cevapOnayView.onCevapOnayErrorLoading(t.getMessage());
            }
        });
    }
}
