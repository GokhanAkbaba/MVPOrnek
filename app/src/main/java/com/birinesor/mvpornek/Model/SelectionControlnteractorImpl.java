package com.birinesor.mvpornek.Model;

import android.content.Context;

import com.birinesor.mvpornek.Models.SelectionControlModel;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class SelectionControlnteractorImpl implements SelectionControlnteractor{
    Context context;

    public SelectionControlnteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void controlSelection(int kullaniciId, onSelectionControlnteractorListener listener) {
        Call<SelectionControlModel> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .secimKontrol(kullaniciId);
        call.enqueue(new Callback<SelectionControlModel>() {
            @Override
            public void onResponse(Call<SelectionControlModel> call, Response<SelectionControlModel> response) {

                if (response.isSuccessful() && response.body() !=null) {
                    if(response.body().getSecim()){
                        listener.onSuccess();
                    }
                    else{
                        listener.onFailed();
                    }
                }
            }
            @Override
            public void onFailure(Call<SelectionControlModel> call, Throwable t) {
                System.out.println("Selection Control Presenter "+t.getMessage());
            }
        });
    }
}
