package com.birinesor.mvpornek.Presenter.KazancSoruGuncelle;

import com.birinesor.mvpornek.Presenter.KazancSoruGuncelle.KazancSoruGuncellePresenter;
import com.birinesor.mvpornek.Response.CevapKazancResponse;
import com.birinesor.mvpornek.View.KazancSoruGuncelleView;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KazancSoruGuncellePresenterImpl implements KazancSoruGuncellePresenter {
    KazancSoruGuncelleView kazancSoruGuncelleView;

    public KazancSoruGuncellePresenterImpl(KazancSoruGuncelleView kazancSoruGuncelleView) {
        this.kazancSoruGuncelleView = kazancSoruGuncelleView;
    }

    @Override
    public void soruKazancGuncelle(ArrayList<Integer> soruId) {
        Call<CevapKazancResponse> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .soruKazancGuncelleme(soruId);

        call.enqueue(new Callback<CevapKazancResponse>() {
            @Override
            public void onResponse(Call<CevapKazancResponse> call, Response<CevapKazancResponse> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    kazancSoruGuncelleView.showKazancSoruGuncelleSuccesMessage();
                }
            }
            @Override
            public void onFailure(Call<CevapKazancResponse> call, Throwable t) {
                System.out.println(t.getMessage());
                kazancSoruGuncelleView.showKazancSoruGuncelleFailedMessage();

            }
        });

    }
}
