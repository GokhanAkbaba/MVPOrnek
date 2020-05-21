package com.example.mvpornek.View;

import android.graphics.Movie;

import com.example.mvpornek.Model.Kullanıcı.QuestionModel;
import com.example.mvpornek.Model.Response.SorularResponse;

import java.util.List;

public interface QuestionView {
    void onGetResult(List<QuestionModel> data);
    void onErrorLoading(String message);
    void showLoading();
    void hideLoading();
}
