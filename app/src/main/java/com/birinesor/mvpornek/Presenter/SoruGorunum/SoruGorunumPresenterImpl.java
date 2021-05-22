package com.birinesor.mvpornek.Presenter.SoruGorunum;

import com.birinesor.mvpornek.Response.CevapKazancResponse;
import com.birinesor.mvpornek.Response.SoruGorunumKaydet;
import com.birinesor.mvpornek.View.SoruGorunumView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class SoruGorunumPresenterImpl implements SoruGorunumPresenter {
    SoruGorunumView soruGorunumView;

    public SoruGorunumPresenterImpl(SoruGorunumView soruGorunumView) {
        this.soruGorunumView = soruGorunumView;
    }

    @Override
    public void soruGorunumDurumGuncelle(int kullaniciId, int soruId, int secim) {
        Call<SoruGorunumKaydet> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .soruGorunumDurum(kullaniciId,soruId,secim);

        call.enqueue(new Callback<SoruGorunumKaydet>() {
            @Override
            public void onResponse(Call<SoruGorunumKaydet> call, Response<SoruGorunumKaydet> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    soruGorunumView.showSoruGorunumSuccesMessage();
                }
            }
            @Override
            public void onFailure(Call<SoruGorunumKaydet> call, Throwable t) {
                System.out.println(t.getMessage());
                soruGorunumView.showSoruGorunumFailedMessage();

            }
        });
    }
}
