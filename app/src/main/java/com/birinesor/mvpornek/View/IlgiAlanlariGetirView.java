package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Models.IlgiAlanlariGetirModel;

import java.util.List;

public
interface IlgiAlanlariGetirView {
    void onGetResultIlgiAlanlari(List<IlgiAlanlariGetirModel> data);
    void onErrorLoadingIlgiAlanlari(String message);
}
