package com.example.mvpornek.View;
import com.example.mvpornek.Models.LikesModel;

import java.util.List;

public interface ProfilLikeView {
    void onGetResult(List<LikesModel> data);
    void onErrorLoading();
    void onGetResultControl();
}
