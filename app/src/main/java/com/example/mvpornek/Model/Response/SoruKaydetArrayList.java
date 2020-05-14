package com.example.mvpornek.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SoruKaydetArrayList {
    @SerializedName("il")
    @Expose
    private ArrayList<Integer> il;
    @SerializedName("etiket")
    @Expose
    private ArrayList<Integer> etiket;


    public SoruKaydetArrayList(ArrayList<Integer> il, ArrayList<Integer> etiket) {
        this.il = il;
        this.etiket = etiket;
    }

}
