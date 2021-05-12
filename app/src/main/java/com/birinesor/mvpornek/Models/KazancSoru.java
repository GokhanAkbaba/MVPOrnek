package com.birinesor.mvpornek.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public
class KazancSoru {
    @SerializedName("soruId")
    @Expose
    private int soruId;

    public int getSorId() {
        return soruId;
    }

    public void setSoruId(int soruId) {
        this.soruId = soruId;
    }
}
