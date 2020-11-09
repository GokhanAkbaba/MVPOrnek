package com.birinesor.mvpornek.Fragment.Search;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.birinesor.mvpornek.AramaAyarlari.AramaAyarlariGetir.AramaAyarlariGetirImpl;
import com.birinesor.mvpornek.AramaAyarlari.AramaAyarlariGetir.AramaAyarlariGetirModel;
import com.birinesor.mvpornek.AramaAyarlari.AramaAyarlariGetir.AramaAyarlariGetirView;
import com.birinesor.mvpornek.Fragment.NavBarFragment.SearchFragment;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.SharedPrefManager;

import com.birinesor.mvpornek.AramaAyarlari.AramaAyarlariPresenterImpl;
import com.birinesor.mvpornek.AramaAyarlari.AramaAyarlariView;

import java.util.List;

public class AramaAyarlariActivity extends FragmentActivity implements View.OnClickListener, AramaAyarlariView, AramaAyarlariGetirView {
    Switch kisilestirmeSwitch;
    Switch konumSwitch;
    AramaAyarlariPresenterImpl aramaAyarlariPresenter;
    AramaAyarlariGetirImpl aramaAyarlariGetirPresenter;
    Kullanici kullanici;
    Button geriButon;
    Boolean secim1=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arama_ayarlari);
        kullanici= SharedPrefManager.getInstance(this).getKullanici();
        kisilestirmeSwitch=findViewById(R.id.kisilestirmeSwitch);
        konumSwitch=findViewById(R.id.konumSwitch);
        aramaAyarlariPresenter=new AramaAyarlariPresenterImpl(this);
        aramaAyarlariGetirPresenter=new AramaAyarlariGetirImpl(this);
        aramaAyarlariGetirPresenter.aramaAyarlariGetirLoad(kullanici.getId());
        geriButon=findViewById(R.id.aramaAyarlariGeriBtn);

        geriButon.setOnClickListener(this);

        kisilestirmeSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                aramaAyarlariPresenter.aramaAyarlariKayit(kullanici.getId(),2,1);
            } else {
                aramaAyarlariPresenter.aramaAyarlariKayit(kullanici.getId(),2,0);
            }
        });
        konumSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                    aramaAyarlariPresenter.aramaAyarlariKayit(kullanici.getId(),3,1);
            } else {
                aramaAyarlariPresenter.aramaAyarlariKayit(kullanici.getId(),3,0);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);

    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }


    @Override
    public void succesAramaAyarlari() {
        Log.d("Succes","Arama Ayar Kaydı Başarılı Bir Şekilde Yapıldı.");
    }

    @Override
    public void failedAramaAyarlari() {
        Log.d("Failed","Arama Ayar Kaydı Yapılırken Hata Oluştu.");
    }

    @Override
    public void onGetResultAramaAyarlariGetir(List<AramaAyarlariGetirModel> data) {

        for (int i=0;i<data.size();i++){
            if(data.get(i).getKod_id()==3 && data.get(i).getSecim()==1){
                konumSwitch.setChecked(true);

            }else if(data.get(i).getKod_id()==2 && data.get(i).getSecim()==1){
                kisilestirmeSwitch.setChecked(true);
            }
        }

    }

    @Override
    public void onErrorLoadingAramaAyarlariGetir(String message) {
        Log.d("Failed","Arama Ayarlarını Getirirken Hata Oluştu");

    }
}