package com.birinesor.mvpornek.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public
class KazancCevap {
    @SerializedName("cevapId")
    @Expose
    private int cevapId;

    public int getCevapId() {
        return cevapId;
    }

    public void setCevapId(int cevapId) {
        this.cevapId = cevapId;
    }
}
