package com.example.mvpornek.View;

import com.example.mvpornek.Response.UserSearchListResponse;

import java.util.List;

public interface SearchUser {
    void onGetResult(List<UserSearchListResponse> data);
    void onErrorLoading(String message);
}
