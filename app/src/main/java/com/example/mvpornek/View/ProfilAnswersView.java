package com.example.mvpornek.View;

import com.example.mvpornek.Models.AnswersModel;

import java.util.List;

public interface ProfilAnswersView {
    void onGetResult(List<AnswersModel> data);
    void onErrorLoading();
}
