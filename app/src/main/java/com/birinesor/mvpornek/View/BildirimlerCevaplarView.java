package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Response.BildirimlerCevaplarModel;

import java.util.List;

public
interface BildirimlerCevaplarView {
    void onGetResult(List<BildirimlerCevaplarModel> data);
    void onErrorLoading(String message);
    void onGetBildirimCevaplarKontrol();
    void bildirimCevaplarShowLoading();
    void bildirimCevaplarHideLoading();
}
