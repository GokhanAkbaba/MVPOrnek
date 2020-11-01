package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Response.UserSearchListResponse;

import java.util.List;

public interface SearchUser {
    void onGetResult(List<UserSearchListResponse> data);
    void onErrorLoading(String message);
    void onGetResultControl();
}
