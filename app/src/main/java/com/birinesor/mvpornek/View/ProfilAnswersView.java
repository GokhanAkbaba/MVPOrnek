package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Models.AnswersModel;

import java.util.List;

public interface ProfilAnswersView {
    void onGetResult(List<AnswersModel> data);
    void onErrorLoading();
}
