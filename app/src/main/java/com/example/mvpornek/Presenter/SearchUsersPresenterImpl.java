package com.example.mvpornek.Presenter;


import com.example.mvpornek.Response.SearchListResponse;
import com.example.mvpornek.View.SearchUsersView;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchUsersPresenterImpl implements SearchUsersPresenter {
    SearchUsersView searchUsersView;

    public SearchUsersPresenterImpl(SearchUsersView searchUsersView) {
        this.searchUsersView = searchUsersView;
    }

    @Override
    public void loadData(String kullaniciAdi) {
        Call<List<SearchListResponse>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .aramaKullaniciGetir(kullaniciAdi);

        call.enqueue(new Callback<List<SearchListResponse>>() {
            @Override
            public void onResponse(Call<List<SearchListResponse>> call, Response<List<SearchListResponse>> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    searchUsersView.onGetResult(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<SearchListResponse>> call, Throwable t) {
                searchUsersView.onErrorLoading(t.getMessage());
            }
        });
    }
}