package com.birinesor.mvpornek.Presenter.KullaniciKazanHesapla;

import com.birinesor.mvpornek.Models.KazancCevap;
import com.birinesor.mvpornek.Models.KazancHesaplaModels;
import com.birinesor.mvpornek.View.KullaniciKazancHesapla;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KullaniciKazancHesaplaPresenterImpl implements KullaniciKazanHesaplaPresenter{
    KullaniciKazancHesapla kullaniciKazancHesapla;

    public KullaniciKazancHesaplaPresenterImpl(KullaniciKazancHesapla kullaniciKazancHesapla) {
        this.kullaniciKazancHesapla = kullaniciKazancHesapla;
    }

    @Override
    public void loadKazancData(int kullaniciId) {
        kullaniciKazancHesapla.onKullaniciKazancHesaplaShowLoading();
        Call<List<KazancHesaplaModels>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .kullaniciKazancHesapla(kullaniciId);

        call.enqueue(new Callback<List<KazancHesaplaModels>>() {
            @Override
            public void onResponse(Call<List<KazancHesaplaModels>> call, Response<List<KazancHesaplaModels>> response) {
                kullaniciKazancHesapla.onKullaniciKazancHesaplaHideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    kullaniciKazancHesapla.onKullaniciKazancHesaplaGetResult(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<KazancHesaplaModels>> call, Throwable t) {
                kullaniciKazancHesapla.onKullaniciKazancHesaplaErrorLoading(t.getMessage());
                kullaniciKazancHesapla.onKullaniciKazancHesaplaHideLoading();
            }
        });
    }
}
