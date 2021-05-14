package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Models.KazancCevap;

import java.util.List;

public
interface KazancCevapView {
    void onGetResult(List<KazancCevap> data);
    void onErrorLoading(String message);
    void onKazancCevapShowLoading();
    void onKazancCevapHideLoading();
}
