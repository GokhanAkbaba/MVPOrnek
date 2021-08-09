package com.birinesor.mvpornek.Presenter.YorumAyrintiYorumlar;

import com.birinesor.mvpornek.Models.YorumAyrintiSorusuYorumlarModel;
import com.birinesor.mvpornek.Presenter.YorumAyrintiYorumlar.YorumAyrintiYorumlarPresenter;
import com.birinesor.mvpornek.View.YorumAyrintiYorumlarView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class YorumAyrintiYorumlarPresenterImpl implements YorumAyrintiYorumlarPresenter {
    YorumAyrintiYorumlarView yorumAyrintiYorumlarView;

    public YorumAyrintiYorumlarPresenterImpl(YorumAyrintiYorumlarView yorumAyrintiYorumlarView) {
        this.yorumAyrintiYorumlarView = yorumAyrintiYorumlarView;
    }

    @Override
    public void YorumAyrintiYorumlarLoad(int soruId) {
        yorumAyrintiYorumlarView.onYorumAyrintiYorumlarShow();
        Call<List<YorumAyrintiSorusuYorumlarModel>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .yorumAyrintiSorusuYorumlarGetir(soruId);

        call.enqueue(new Callback<List<YorumAyrintiSorusuYorumlarModel>>() {
            @Override
            public void onResponse(Call<List<YorumAyrintiSorusuYorumlarModel>> call, Response<List<YorumAyrintiSorusuYorumlarModel>> response) {
                yorumAyrintiYorumlarView.onYorumAyrintiYorumlarHide();
                if(response.body().size() >0 ){
                    if (response.isSuccessful() && response.body() !=null) {
                        yorumAyrintiYorumlarView.onorumAyrintiYorumlarGetResult(response.body());
                    }
                }/*else{
                    yorumAyrintiYorumlarView.onorumAyrintiYorumlarKontrol();
                }*/
            }
            @Override
            public void onFailure(Call<List<YorumAyrintiSorusuYorumlarModel>> call, Throwable t) {
                yorumAyrintiYorumlarView.onYorumAyrintiYorumlarHide();
                yorumAyrintiYorumlarView.onorumAyrintiYorumlarErrorLoading("NotificationLikeAndQuestion "+t.getMessage());

            }
        });
    }
}
