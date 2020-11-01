package com.birinesor.mvpornek.View;
import com.birinesor.mvpornek.Models.LikesModel;

import java.util.List;

public interface ProfilLikeView {
    void onGetResult(List<LikesModel> data);
    void onErrorLoading();
    void onGetResultControl();
}
