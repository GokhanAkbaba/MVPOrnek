package com.example.mvpornek.Response;

import com.example.mvpornek.Models.Kullanici;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikeModel {
    @SerializedName("begeniSayisi")
    @Expose
    public int begeniSayisi;

    public LikeModel(int begeniSayisi) {
        this.begeniSayisi = begeniSayisi;
    }

    public int getBegeniSayisi() {
        return begeniSayisi;
    }


}
