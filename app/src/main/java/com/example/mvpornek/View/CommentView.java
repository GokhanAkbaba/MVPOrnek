package com.example.mvpornek.View;

import com.example.mvpornek.Models.CommentModel;

import java.util.List;

public interface CommentView {
    void onGetResult(List<CommentModel> data);
    void onErrorLoading(String message);
}
