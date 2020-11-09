package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Models.EtiketlerModel;
import com.birinesor.mvpornek.Models.QuestionModel;

import java.util.List;

public
interface EtiketlerView {
    void onGetEtiketlerResult(List<EtiketlerModel> etiketlerModels);
    void onEtiketlerErrorLoading(String message);
    void showEtiketlerLoading();
    void hideEtiketlerLoading();
    void etiketlerKontrol();
}
