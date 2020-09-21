package com.example.mvpornek.Presenter.SearchUser;

import com.example.mvpornek.Response.SearchListResponse;
import com.example.mvpornek.Response.UserSearchListResponse;
import com.example.mvpornek.View.SearchUser;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchUserPresenterImpl implements SearchUserPresenter {
    SearchUser searchUser;

    public SearchUserPresenterImpl(SearchUser searchUser) {
        this.searchUser = searchUser;
    }

    @Override
    public void loadData(String soruId) {
        Call<List<UserSearchListResponse>> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .aramaKullanicilariGetir(soruId);

        call.enqueue(new Callback<List<UserSearchListResponse>>() {
            @Override
            public void onResponse(Call<List<UserSearchListResponse>> call, Response<List<UserSearchListResponse>> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    searchUser.onGetResult(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<UserSearchListResponse>> call, Throwable t) {
                searchUser.onErrorLoading(t.getMessage());
            }
        });

    }
}
