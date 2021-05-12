package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Models.KazancCevap;
import com.birinesor.mvpornek.Models.KazancSoru;

import java.util.List;

public
interface KazancSoruView {
    void onGetKazancSoruResult(List<KazancSoru> data);
    void onErrorKazancSoruLoading(String message);
}
