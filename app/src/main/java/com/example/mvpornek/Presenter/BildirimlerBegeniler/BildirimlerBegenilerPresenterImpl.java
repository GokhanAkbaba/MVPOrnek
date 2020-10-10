package com.example.mvpornek.Presenter.BildirimlerBegeniler;

import com.example.mvpornek.Models.BildirimlerBegenilerModel;
import com.example.mvpornek.Response.BildirimlerCevaplarModel;
import com.example.mvpornek.View.BildirimlerBegenilerView;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class BildirimlerBegenilerPresenterImpl implements BildirimlerBegenilerPresenter {
    BildirimlerBegenilerView bildirimlerBegenilerView;

    public BildirimlerBegenilerPresenterImpl(BildirimlerBegenilerView bildirimlerBegenilerView) {
        this.bildirimlerBegenilerView = bildirimlerBegenilerView;
    }

    @Override
    public void loadData(int kullaniciID) {
        bildirimlerBegenilerView.bildirimBegenilerShowLoading();
        Call<List<BildirimlerBegenilerModel>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .bildirimBegenileriGetir(kullaniciID);

        call.enqueue(new Callback<List<BildirimlerBegenilerModel>>() {
            @Override
            public void onResponse(Call<List<BildirimlerBegenilerModel>> call, Response<List<BildirimlerBegenilerModel>> response) {
                bildirimlerBegenilerView.bildirimBegenilerHideLoading();
                if (response.isSuccessful() && response.body() !=null) {
                    bildirimlerBegenilerView.onBildirimBegenilerGetResult(response.body());
                }
                List<BildirimlerBegenilerModel> data=response.body();
                if(data == null || data.isEmpty() ){
                    bildirimlerBegenilerView.onGetBildirimBegenilerKontrol();
                }
            }
            @Override
            public void onFailure(Call<List<BildirimlerBegenilerModel>> call, Throwable t) {
                bildirimlerBegenilerView.bildirimBegenilerHideLoading();
                bildirimlerBegenilerView.onBildirimBegenilerErrorLoading(t.getMessage());
            }
        });
    }
}
