package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Models.BildirimlerBegenilerModel;

import java.util.List;

public interface BildirimlerBegenilerView {
    void onBildirimBegenilerGetResult(List<BildirimlerBegenilerModel> data);
    void onBildirimBegenilerErrorLoading(String message);
    void onGetBildirimBegenilerKontrol();
    void bildirimBegenilerShowLoading();
    void bildirimBegenilerHideLoading();
}
