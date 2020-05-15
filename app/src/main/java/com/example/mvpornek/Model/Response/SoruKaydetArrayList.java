package com.example.mvpornek.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SoruKaydetArrayList {

    private ArrayList<Integer> il;

    private ArrayList<Integer> etiket;


    public SoruKaydetArrayList(ArrayList<Integer> il, ArrayList<Integer> etiket) {
        this.il = il;
        this.etiket = etiket;
    }

    public ArrayList<Integer> getIl() {
        return il;
    }

    public ArrayList<Integer> getEtiket() {
        return etiket;
    }
}
