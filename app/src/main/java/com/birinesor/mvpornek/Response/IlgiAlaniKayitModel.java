package com.birinesor.mvpornek.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public
class IlgiAlaniKayitModel {
    @SerializedName("error")
    @Expose
    Boolean error;

    public IlgiAlaniKayitModel(Boolean error) {
        this.error = error;
    }

    public Boolean getSecim() {
        return error;
    }
}
