package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Models.CevaplarOnayModels;
import com.birinesor.mvpornek.Models.SorularOnayModel;

import java.util.List;

public
interface CevapOnayView {
    void onCevapOnayResult(List<CevaplarOnayModels> data);
    void onCevapOnayErrorLoading(String message);
    void onCevapOnayShow();
    void onCevapOnayHide();
}
