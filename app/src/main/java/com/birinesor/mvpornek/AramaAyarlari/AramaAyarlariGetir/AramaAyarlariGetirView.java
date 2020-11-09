package com.birinesor.mvpornek.AramaAyarlari.AramaAyarlariGetir;

import com.birinesor.mvpornek.Models.IlgiAlanlariGetirModel;

import java.util.List;

public
interface AramaAyarlariGetirView {
    void onGetResultAramaAyarlariGetir(List<AramaAyarlariGetirModel> data);
    void onErrorLoadingAramaAyarlariGetir(String message);
}
