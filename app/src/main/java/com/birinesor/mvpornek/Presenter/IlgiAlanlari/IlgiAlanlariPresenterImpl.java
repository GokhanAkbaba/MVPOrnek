package com.birinesor.mvpornek.Presenter.IlgiAlanlari;

import com.birinesor.mvpornek.Models.IlgiAlanlariGetirModel;
import com.birinesor.mvpornek.View.IlgiAlanlariGetirView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class IlgiAlanlariPresenterImpl implements IlgiAlanlariPresenter{
    IlgiAlanlariGetirView ilgiAlanlariGetirView;

    public IlgiAlanlariPresenterImpl(IlgiAlanlariGetirView ilgiAlanlariGetirView) {
        this.ilgiAlanlariGetirView = ilgiAlanlariGetirView;
    }

    @Override
    public void ilgiAlanlariGetir(int kullaniciID) {
        Call<List<IlgiAlanlariGetirModel>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .ilgiAlanlariGetir(kullaniciID);

        call.enqueue(new Callback<List<IlgiAlanlariGetirModel>>() {
            @Override
            public void onResponse(Call<List<IlgiAlanlariGetirModel>> call, Response<List<IlgiAlanlariGetirModel>> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    ilgiAlanlariGetirView.onGetResultIlgiAlanlari(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<IlgiAlanlariGetirModel>> call, Throwable t) {
                ilgiAlanlariGetirView.onErrorLoadingIlgiAlanlari(t.getMessage());
            }
        });
    }
}
