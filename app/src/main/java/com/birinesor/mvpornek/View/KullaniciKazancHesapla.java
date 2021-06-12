package com.birinesor.mvpornek.View;


import com.birinesor.mvpornek.Models.KazancHesaplaModels;

import java.util.List;

public interface KullaniciKazancHesapla {
    void onKullaniciKazancHesaplaGetResult(List<KazancHesaplaModels> data);
    void onKullaniciKazancHesaplaErrorLoading(String message);
    void onKullaniciKazancHesaplaShowLoading();
    void onKullaniciKazancHesaplaHideLoading();
}
