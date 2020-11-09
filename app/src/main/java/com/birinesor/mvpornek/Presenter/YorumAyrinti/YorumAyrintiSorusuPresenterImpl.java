package com.birinesor.mvpornek.Presenter.YorumAyrinti;

import com.birinesor.mvpornek.Models.YorumAyrintiSorusuModel;
import com.birinesor.mvpornek.Response.NotificationCommentAndLikeModel;
import com.birinesor.mvpornek.View.YorumAyrintiSorusuView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class YorumAyrintiSorusuPresenterImpl implements YorumAyrintiSorusuPresenter{
    YorumAyrintiSorusuView yorumAyrintiSorusuView;

    public YorumAyrintiSorusuPresenterImpl(YorumAyrintiSorusuView yorumAyrintiSorusuView) {
        this.yorumAyrintiSorusuView = yorumAyrintiSorusuView;
    }

    @Override
    public void loadYorumAyrintiSorusu(int soruID) {
        Call<List<YorumAyrintiSorusuModel>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .yorumAyrintiSorusuGetir(soruID);

        call.enqueue(new Callback<List<YorumAyrintiSorusuModel>>() {
            @Override
            public void onResponse(Call<List<YorumAyrintiSorusuModel>> call, Response<List<YorumAyrintiSorusuModel>> response) {
                if(response.body().get(0).getAdSoyad() != null){
                    yorumAyrintiSorusuView.onGetResult(response.body());
                }else{

                    yorumAyrintiSorusuView.onGetYorumAyrintiSorusuKontrol();
               }
            }
            @Override
            public void onFailure(Call<List<YorumAyrintiSorusuModel>> call, Throwable t) {
                yorumAyrintiSorusuView.onErrorLoading("yorumAyrintiSorusu "+t.getMessage());

            }
        });
    }
}
