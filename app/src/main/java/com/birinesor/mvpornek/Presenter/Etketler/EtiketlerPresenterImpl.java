package com.birinesor.mvpornek.Presenter.Etketler;

import com.birinesor.mvpornek.Models.EtiketlerModel;
import com.birinesor.mvpornek.Response.NotificationCommentAndLikeModel;
import com.birinesor.mvpornek.View.EtiketlerView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class EtiketlerPresenterImpl implements EtiketlerPresenter {
    EtiketlerView etiketlerView;

    public EtiketlerPresenterImpl(EtiketlerView etiketlerView) {
        this.etiketlerView = etiketlerView;
    }

    @Override
    public void etiketLoad(int kullaniciID) {
        etiketlerView.showEtiketlerLoading();
        Call<List<EtiketlerModel>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .etiketleriGetir(kullaniciID);

        call.enqueue(new Callback<List<EtiketlerModel>>() {
            @Override
            public void onResponse(Call<List<EtiketlerModel>> call, Response<List<EtiketlerModel>> response) {
                etiketlerView.hideEtiketlerLoading();
                if(response.body().size() >0 ){
                    if (response.isSuccessful() && response.body() !=null) {
                        etiketlerView.onGetEtiketlerResult(response.body());
                    }

                }else{
                    etiketlerView.etiketlerKontrol();

                }

            }
            @Override
            public void onFailure(Call<List<EtiketlerModel>> call, Throwable t) {
                etiketlerView.hideEtiketlerLoading();
                etiketlerView.onEtiketlerErrorLoading("EtiketlerView "+t.getMessage());

            }
        });
    }
}
