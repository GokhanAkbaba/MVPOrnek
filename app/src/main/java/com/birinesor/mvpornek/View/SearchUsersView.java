package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Response.SearchListResponse;

import java.util.List;

public interface SearchUsersView {
    void onGetResult(List<SearchListResponse> data);
    void onErrorLoading(String message);
}
