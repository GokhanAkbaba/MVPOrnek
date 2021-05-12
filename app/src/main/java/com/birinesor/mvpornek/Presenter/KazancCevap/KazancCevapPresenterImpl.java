package com.birinesor.mvpornek.Presenter.KazancCevap;

import com.birinesor.mvpornek.Models.CommentModel;
import com.birinesor.mvpornek.Models.KazancCevap;
import com.birinesor.mvpornek.View.KazancCevapView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class KazancCevapPresenterImpl implements KazancCevapPresenter{
    KazancCevapView kazancCevapView;

    public KazancCevapPresenterImpl(KazancCevapView kazancCevapView) {
        this.kazancCevapView = kazancCevapView;
    }

    @Override
    public void loadData(int kullaniciId) {
        Call<List<KazancCevap>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .kazancCevapGetir(kullaniciId);

        call.enqueue(new Callback<List<KazancCevap>>() {
            @Override
            public void onResponse(Call<List<KazancCevap>> call, Response<List<KazancCevap>> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    kazancCevapView.onGetResult(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<KazancCevap>> call, Throwable t) {
                kazancCevapView.onErrorLoading(t.getMessage());
            }
        });
    }
}
