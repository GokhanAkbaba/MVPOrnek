package com.example.mvpornek.View;

import com.example.mvpornek.Response.SearchListResponse;

import java.util.ArrayList;
import java.util.List;

public interface SearchUsersView {
    void onGetResult(List<SearchListResponse> data);
    void onErrorLoading(String message);
}
