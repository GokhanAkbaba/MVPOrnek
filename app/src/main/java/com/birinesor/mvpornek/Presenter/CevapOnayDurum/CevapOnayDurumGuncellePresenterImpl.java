package com.birinesor.mvpornek.Presenter.CevapOnayDurum;

import com.birinesor.mvpornek.Response.CevapKazancResponse;
import com.birinesor.mvpornek.View.CevapOnayDurumGuncelle;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class CevapOnayDurumGuncellePresenterImpl implements CevapOnayDurumGuncellePresenter{
    CevapOnayDurumGuncelle cevapOnayDurumGuncelle;

    public CevapOnayDurumGuncellePresenterImpl(CevapOnayDurumGuncelle cevapOnayDurumGuncelle) {
        this.cevapOnayDurumGuncelle = cevapOnayDurumGuncelle;
    }

    @Override
    public void cevapOnayDurumGuncelle(int cevapId, int durum) {
        Call<CevapKazancResponse> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .cevapDurumuGuncelleme(cevapId,durum);

        call.enqueue(new Callback<CevapKazancResponse>() {
            @Override
            public void onResponse(Call<CevapKazancResponse> call, Response<CevapKazancResponse> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    cevapOnayDurumGuncelle.showCevapOnayDurumGuncelleSuccesMessage();
                }
            }
            @Override
            public void onFailure(Call<CevapKazancResponse> call, Throwable t) {
                System.out.println(t.getMessage());
                cevapOnayDurumGuncelle.showCevapOnayDurumGuncelleFailedMessage();

            }
        });
    }
}
