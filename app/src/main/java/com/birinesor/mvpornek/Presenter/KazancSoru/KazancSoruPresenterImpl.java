package com.birinesor.mvpornek.Presenter.KazancSoru;

import com.birinesor.mvpornek.Models.KazancCevap;
import com.birinesor.mvpornek.Models.KazancSoru;
import com.birinesor.mvpornek.View.KazancSoruView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class KazancSoruPresenterImpl implements KazancSoruPresenter {
    KazancSoruView kazancSoruView;

    public KazancSoruPresenterImpl(KazancSoruView kazancSoruView) {
        this.kazancSoruView = kazancSoruView;
    }

    @Override
    public void loadData(int kullaniciId) {
        Call<List<KazancSoru>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .kazancSoruGetir(kullaniciId);

        call.enqueue(new Callback<List<KazancSoru>>() {
            @Override
            public void onResponse(Call<List<KazancSoru>> call, Response<List<KazancSoru>> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    kazancSoruView.onGetKazancSoruResult(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<KazancSoru>> call, Throwable t) {
                kazancSoruView.onErrorKazancSoruLoading(t.getMessage());
            }
        });
    }
}
