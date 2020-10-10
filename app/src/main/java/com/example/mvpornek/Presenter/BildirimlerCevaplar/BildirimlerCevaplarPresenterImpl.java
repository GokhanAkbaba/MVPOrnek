package com.example.mvpornek.Presenter.BildirimlerCevaplar;

import com.example.mvpornek.Presenter.BildirimlerCevaplar.BildirimlerCevaplarPresenter;
import com.example.mvpornek.Response.BildirimlerCevaplarModel;
import com.example.mvpornek.View.BildirimlerCevaplarView;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BildirimlerCevaplarPresenterImpl implements BildirimlerCevaplarPresenter {
    BildirimlerCevaplarView bildirimlerCevaplarView;

    public BildirimlerCevaplarPresenterImpl(BildirimlerCevaplarView bildirimlerCevaplarView) {
        this.bildirimlerCevaplarView = bildirimlerCevaplarView;
    }

    @Override
    public void loadData(int kullaniciID) {
        bildirimlerCevaplarView.bildirimCevaplarShowLoading();
        Call<List<BildirimlerCevaplarModel>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .bildirimCevaplarGetir(kullaniciID);

        call.enqueue(new Callback<List<BildirimlerCevaplarModel>>() {
            @Override
            public void onResponse(Call<List<BildirimlerCevaplarModel>> call, Response<List<BildirimlerCevaplarModel>> response) {
                bildirimlerCevaplarView.bildirimCevaplarHideLoading();
                if (response.isSuccessful() && response.body() !=null) {
                    bildirimlerCevaplarView.onGetResult(response.body());
                }
                List<BildirimlerCevaplarModel> data=response.body();
                if(data == null || data.isEmpty() ){
                    bildirimlerCevaplarView.onGetBildirimCevaplarKontrol();
                }
            }
            @Override
            public void onFailure(Call<List<BildirimlerCevaplarModel>> call, Throwable t) {
                bildirimlerCevaplarView.bildirimCevaplarHideLoading();
                bildirimlerCevaplarView.onErrorLoading(t.getMessage());
            }
        });
    }
}
