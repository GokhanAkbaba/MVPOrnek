package com.example.mvpornek.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectionControlModel {
    @SerializedName("secim")
    @Expose
    Boolean secim;

    public SelectionControlModel(Boolean secim) {
        this.secim = secim;
    }

    public Boolean getSecim() {
        return secim;
    }
}
