package com.birinesor.mvpornek.Presenter.KullaniciKazancLog;

import com.birinesor.mvpornek.Model.KullaniciKazanc.KullaniciKazancInteractor;
import com.birinesor.mvpornek.View.KullaniciKazancLogView;

public class KullaniciKazancPresenterImpl implements KullaniciKazancPresenter, KullaniciKazancInteractor.onKullaniciKazancLogKayitListener {

    private KullaniciKazancLogView kullaniciKazancLogView;
    private KullaniciKazancInteractor kullaniciKazancInteractor;

    public KullaniciKazancPresenterImpl(KullaniciKazancLogView kullaniciKazancLogView, KullaniciKazancInteractor kullaniciKazancInteractor) {
        this.kullaniciKazancLogView = kullaniciKazancLogView;
        this.kullaniciKazancInteractor = kullaniciKazancInteractor;
    }

    @Override
    public void onIbanHata(String error) {
        if(kullaniciKazancLogView !=null){
            kullaniciKazancLogView.setIbanHatasi("Lütfen IBAN Boş Bırakmayınız");
            kullaniciKazancLogView.hideKullaniciKazancProgress();
        }
    }

    @Override
    public void onIbanOnay() {
        if(kullaniciKazancLogView != null)
        {
            kullaniciKazancLogView.setIbanOnay();
            kullaniciKazancLogView.hideKullaniciKazancProgress();
        }
    }

    @Override
    public void onAdSoyadHatasi(String error) {
        if(kullaniciKazancLogView !=null){
            kullaniciKazancLogView.setAdSoyadHatasi("Lütfen Ad Soyad Bırakmayınız");
            kullaniciKazancLogView.hideKullaniciKazancProgress();
        }
    }

    @Override
    public void onAdSoyadOnay() {
        if(kullaniciKazancLogView != null)
        {
            kullaniciKazancLogView.setAdSoyadOnay();
            kullaniciKazancLogView.hideKullaniciKazancProgress();
        }
    }

    @Override
    public void onKullaniciKazancSuccess() {
        if(kullaniciKazancLogView != null)
        {
            kullaniciKazancLogView.kullaniciKazancToHome();
        }
    }

    @Override
    public void validateKullaniciKazanc(int id, String tutar, String iban, String adSoyad) {
        if(kullaniciKazancLogView != null)
        {
            kullaniciKazancLogView.showKullaniciKazancProgress();
        }
        kullaniciKazancInteractor.KullaniciKazancLogKayit(id,tutar,iban,adSoyad,this);
    }

    @Override
    public void onKullaniciKazancDestroy() {
        kullaniciKazancLogView = null;
    }
}
