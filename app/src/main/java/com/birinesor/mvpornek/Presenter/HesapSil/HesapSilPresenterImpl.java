package com.birinesor.mvpornek.Presenter.HesapSil;

import com.birinesor.mvpornek.Response.CevapSilResponse;
import com.birinesor.mvpornek.View.HesapSilView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class HesapSilPresenterImpl implements HesapSilPresenter {
    HesapSilView hesapSilView;

    public HesapSilPresenterImpl(HesapSilView hesapSilView) {
        this.hesapSilView = hesapSilView;
    }

    @Override
    public void kullaniciSil(int kullaniciId) {
        Call<CevapSilResponse> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .hesapSil(kullaniciId);

        call.enqueue(new Callback<CevapSilResponse>() {
            @Override
            public void onResponse(Call<CevapSilResponse> call, Response<CevapSilResponse> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    hesapSilView.showSuccesMessage();
                }
            }
            @Override
            public void onFailure(Call<CevapSilResponse> call, Throwable t) {
                System.out.println(t.getMessage());
                hesapSilView.showFailedMessage();

            }
        });
    }
}
