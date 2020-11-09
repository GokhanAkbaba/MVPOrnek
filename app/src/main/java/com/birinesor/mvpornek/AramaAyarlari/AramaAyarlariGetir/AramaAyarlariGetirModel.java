package com.birinesor.mvpornek.AramaAyarlari.AramaAyarlariGetir;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public
class AramaAyarlariGetirModel {
    @SerializedName("kullanici_id")
    @Expose
    private int kullanici_id;
    @SerializedName("kod_id")
    @Expose
    private int kod_id;
    @SerializedName("secim")
    @Expose
    private int secim;

    public int getKullanici_id() {
        return kullanici_id;
    }

    public int getKod_id() {
        return kod_id;
    }

    public int getSecim() {
        return secim;
    }
}

