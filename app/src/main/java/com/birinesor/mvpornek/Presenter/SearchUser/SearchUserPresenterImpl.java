package com.birinesor.mvpornek.Presenter.SearchUser;

import com.birinesor.mvpornek.Response.UserSearchListResponse;
import com.birinesor.mvpornek.View.SearchUser;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

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
                List<UserSearchListResponse> data=response.body();
                if(data == null || data.isEmpty() ){
                    searchUser.onGetResultControl();
                }
            }
            @Override
            public void onFailure(Call<List<UserSearchListResponse>> call, Throwable t) {
                System.out.println("Hata");
                searchUser.onErrorLoading(t.getMessage());
            }
        });

    }
}
