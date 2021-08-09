package com.birinesor.mvpornek.Presenter.IlgiAlaniKayit;

import com.birinesor.mvpornek.Presenter.IlgiAlaniKayit.IlgiAlaniKayitPresenter;
import com.birinesor.mvpornek.Response.IlgiAlaniKayitModel;
import com.birinesor.mvpornek.View.IlgiAlanıKayitView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class IlgiAlaniKayitPresenterImpl implements IlgiAlaniKayitPresenter {
    IlgiAlanıKayitView ilgiAlanıKayitView;

    public IlgiAlaniKayitPresenterImpl(IlgiAlanıKayitView ilgiAlanıKayitView) {
        this.ilgiAlanıKayitView = ilgiAlanıKayitView;
    }

    @Override
    public void validateIlgiAlaniKayit(int kullaniciId, int etiketId, int il, int durum) {
        Call<IlgiAlaniKayitModel> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .IlgiAlaniKayit(kullaniciId,etiketId,il,durum);
        call.enqueue(new Callback<IlgiAlaniKayitModel>() {
            @Override
            public void onResponse(Call<IlgiAlaniKayitModel> call, Response<IlgiAlaniKayitModel> response) {

                if (response.isSuccessful() && response.body() !=null) {
                        ilgiAlanıKayitView.succesIlgiAlanıKayit();
                }else{
                    ilgiAlanıKayitView.failedIlgiAlanıKayit();
                }
            }
            @Override
            public void onFailure(Call<IlgiAlaniKayitModel> call, Throwable t) {
                System.out.println("Selection Control Presenter"+t.getMessage());
            }
        });
    }
}
