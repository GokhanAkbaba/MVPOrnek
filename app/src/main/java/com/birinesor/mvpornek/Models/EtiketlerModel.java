package com.birinesor.mvpornek.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public
class EtiketlerModel {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("etiket_sayisi")
    @Expose
    private int etiketSayisi;
    @SerializedName("etiket_adi")
    @Expose
    private String etiketAdi;

    public EtiketlerModel(int id, int etiketSayisi, String etiketAdi) {
        this.id = id;
        this.etiketSayisi = etiketSayisi;
        this.etiketAdi = etiketAdi;
    }

    public int getId() {
        return id;
    }

    public int getEtiketSayisi() {
        return etiketSayisi;
    }

    public String getEtiketAdi() {
        return etiketAdi;
    }
}
