package com.birinesor.mvpornek.Presenter.CevapKazancGuncelle;

import com.birinesor.mvpornek.Presenter.CevapKazancGuncelle.CevapKazancGuncellePresenter;
import com.birinesor.mvpornek.Response.CevapKazancResponse;
import com.birinesor.mvpornek.View.CevapKazancGuncelle;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CevapKazancGuncellePresenterImpl implements CevapKazancGuncellePresenter {
    CevapKazancGuncelle cevapKazancGuncelle;

    public CevapKazancGuncellePresenterImpl(CevapKazancGuncelle cevapKazancGuncelle) {
        this.cevapKazancGuncelle = cevapKazancGuncelle;
    }

    @Override
    public void cevapKazancGuncelle(ArrayList<Integer> il) {
        Call<CevapKazancResponse> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .cevapKazancGuncelleme(il);

        call.enqueue(new Callback<CevapKazancResponse>() {
            @Override
            public void onResponse(Call<CevapKazancResponse> call, Response<CevapKazancResponse> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    cevapKazancGuncelle.showCevapKazancGuncelleSuccesMessage();
                }
            }
            @Override
            public void onFailure(Call<CevapKazancResponse> call, Throwable t) {
                System.out.println(t.getMessage());
                cevapKazancGuncelle.showCevapKazancGuncelleFailedMessage();

            }
        });
    }
}
