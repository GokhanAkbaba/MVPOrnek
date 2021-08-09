package com.birinesor.mvpornek.Presenter.SoruOnayDurumGuncelle;

import com.birinesor.mvpornek.Response.CevapKazancResponse;
import com.birinesor.mvpornek.View.SoruOnayDurumGuncelle;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class SoruOnayDurumGuncellePresenterImpl implements SoruOnayDurumGuncellePresenter {
    SoruOnayDurumGuncelle soruOnayDurumGuncelle;

    public SoruOnayDurumGuncellePresenterImpl(SoruOnayDurumGuncelle soruOnayDurumGuncelle) {
        this.soruOnayDurumGuncelle = soruOnayDurumGuncelle;
    }

    @Override
    public void soruOnayDurumGuncelle(int soruId,int durum) {
        Call<CevapKazancResponse> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .soruOnayDurumuGuncelleme(soruId,durum);

        call.enqueue(new Callback<CevapKazancResponse>() {
            @Override
            public void onResponse(Call<CevapKazancResponse> call, Response<CevapKazancResponse> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    soruOnayDurumGuncelle.showSoruOnayDurumGuncelleSuccesMessage();
                }
            }
            @Override
            public void onFailure(Call<CevapKazancResponse> call, Throwable t) {
                System.out.println(t.getMessage());
                soruOnayDurumGuncelle.showSoruOnayDurumGuncelleFailedMessage();

            }
        });
    }
}
