package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Models.KullaniciGetir;
import com.birinesor.mvpornek.Models.YorumAyrintiSorusuModel;
import com.birinesor.mvpornek.Response.NotificationCommentAndLikeModel;

import java.util.List;

public
interface YorumAyrintiSorusuView {
    void onGetResult(List<YorumAyrintiSorusuModel> data);
    void onErrorLoading(String message);
    void onGetYorumAyrintiSorusuKontrol();
}
