package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Models.CommentModel;

import java.util.List;

public interface CommentView {
    void onGetResult(List<CommentModel> data);
    void onErrorLoading(String message);
}
