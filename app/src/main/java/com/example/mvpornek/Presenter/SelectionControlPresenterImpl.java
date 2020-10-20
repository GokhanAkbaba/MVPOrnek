package com.example.mvpornek.Presenter;

import com.example.mvpornek.Models.CommentModel;
import com.example.mvpornek.Models.SelectionControlModel;
import com.example.mvpornek.View.SelectionControl;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class SelectionControlPresenterImpl implements SelectionControlPresenter {
    SelectionControl selectionControl;

    public SelectionControlPresenterImpl(SelectionControl selectionControl) {
        this.selectionControl = selectionControl;
    }

    @Override
    public void loadData(int kullaniciId) {
        System.out.println("DEĞERIDDDDD");
        Call<SelectionControlModel> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .secimKontrol(kullaniciId);
        call.enqueue(new Callback<SelectionControlModel>() {
            @Override
            public void onResponse(Call<SelectionControlModel> call, Response<SelectionControlModel> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    selectionControl.showSuccesMessage(response.body());
                    System.out.println("DEĞER"+response.body().getSecim());
                }
            }
            @Override
            public void onFailure(Call<SelectionControlModel> call, Throwable t) {
               System.out.println("Selection Control Presenter"+t.getMessage());
            }
        });
    }
}
