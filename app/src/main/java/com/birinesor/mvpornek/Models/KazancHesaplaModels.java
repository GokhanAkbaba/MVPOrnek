package com.birinesor.mvpornek.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KazancHesaplaModels {
    @SerializedName("soruSayisi")
    @Expose
    private int soruSayisi;

    @SerializedName("cevapSayisi")
    @Expose
    private int cevapSayisi;


    public int getSoruSayisi() {
        return soruSayisi;
    }

    public void setSoruSayisi(int soruSayisi) {
        this.soruSayisi = soruSayisi;
    }

    public int getCevapSayisi() {
        return cevapSayisi;
    }

    public void setCevapSayisi(int cevapSayisi) {
        this.cevapSayisi = cevapSayisi;
    }
}

