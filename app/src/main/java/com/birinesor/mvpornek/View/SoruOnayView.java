package com.birinesor.mvpornek.View;

import com.birinesor.mvpornek.Models.SorularOnayModel;

import java.util.List;

public interface SoruOnayView {
    void onSoruOnayResult(List<SorularOnayModel> data);
    void onSoruOnayErrorLoading(String message);
    void onSoruOnayShow();
    void onSoruOnayHide();
}
