package com.birinesor.mvpornek.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public
class IlgiAlanlariGetirModel {
    @SerializedName("kullanici_id")
    @Expose
    private int kullanici_id;
    @SerializedName("etiket_id")
    @Expose
    private int etiketID;
    @SerializedName("il")
    @Expose
    private int il;

    public int getKullanici_id() {
        return kullanici_id;
    }

    public int getEtiketID() {
        return etiketID;
    }

    public int getIl() {
        return il;
    }
}
