package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Models.YorumAyrintiSorusuYorumlarModel;
import com.birinesor.mvpornek.Response.NotificationCommentAndLikeModel;

import java.util.List;

public
interface YorumAyrintiYorumlarView {
    void onorumAyrintiYorumlarGetResult(List<YorumAyrintiSorusuYorumlarModel> data);
    void onorumAyrintiYorumlarErrorLoading(String message);
    void onorumAyrintiYorumlarKontrol();
    void onYorumAyrintiYorumlarShow();
    void onYorumAyrintiYorumlarHide();
}
