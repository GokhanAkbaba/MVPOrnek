package com.birinesor.mvpornek.Activity.Ayarlar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.birinesor.mvpornek.Adapter.AdapterSorularOnay;
import com.birinesor.mvpornek.Models.SorularOnayModel;
import com.birinesor.mvpornek.Presenter.SoruOnayDurumGuncelle.SoruOnayDurumGuncellePresenterImpl;
import com.birinesor.mvpornek.Presenter.SoruOnay.SoruOnayPresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.View.SoruOnayDurumGuncelle;
import com.birinesor.mvpornek.View.SoruOnayView;

import java.util.List;

public class AdminActivity extends AppCompatActivity implements SoruOnayView, SoruOnayDurumGuncelle {
    private RecyclerView soruOnayRecyclerView;
    SoruOnayDurumGuncellePresenterImpl soruOnayDurumGuncellePresenter;
    AdapterSorularOnay adapterSorularOnay;
    List<SorularOnayModel> sorularOnayData;
    SoruOnayPresenterImpl soruOnayPresenter;
    SwipeRefreshLayout swipeRefreshLayout;
    AdapterSorularOnay.ItemClickListener itemOnayClickListener;
    AdapterSorularOnay.ItemClickListener itemRedClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        soruOnayDurumGuncellePresenter=new SoruOnayDurumGuncellePresenterImpl(this);

        soruOnayRecyclerView=findViewById(R.id.adminSayfasiRecyclerView);
        soruOnayPresenter=new SoruOnayPresenterImpl(this);
        soruOnayPresenter.loadSoruOnay();
        soruOnayRecyclerView.setAdapter(adapterSorularOnay);
        soruOnayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout=findViewById(R.id.adminSwipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.uygulamaMavisi);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            soruOnayPresenter.loadSoruOnay();
        });
        itemOnayClickListener =((vw,position)-> {
            View onayViewButon = soruOnayRecyclerView.getLayoutManager().findViewByPosition(position);
            TextView onayViewButonTxt=onayViewButon.findViewById(R.id.soruOnayId);
            soruOnayDurumGuncellePresenter.soruOnayDurumGuncelle(Integer.parseInt((String) onayViewButonTxt.getText()),1);
        });
        itemRedClickListener =((vw,position)-> {
            View redViewButon = soruOnayRecyclerView.getLayoutManager().findViewByPosition(position);
            TextView redViewButonTxt=redViewButon.findViewById(R.id.soruOnayId);
            soruOnayDurumGuncellePresenter.soruOnayDurumGuncelle(Integer.parseInt((String) redViewButonTxt.getText()),-1);
        });

    }

    @Override
    public void onSoruOnayResult(List<SorularOnayModel> data) {
        adapterSorularOnay=new AdapterSorularOnay(data,this,itemOnayClickListener,itemRedClickListener);
        adapterSorularOnay.notifyDataSetChanged();
        soruOnayRecyclerView.setAdapter(adapterSorularOnay);
        this.sorularOnayData=data;
    }

    @Override
    public void onSoruOnayErrorLoading(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSoruOnayShow() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void onSoruOnayHide() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showSoruOnayDurumGuncelleSuccesMessage() {
        System.out.println("Soru Onay Güncellme Başarılı");
        soruOnayPresenter.loadSoruOnay();
    }

    @Override
    public void showSoruOnayDurumGuncelleFailedMessage() {
        System.out.println("Soru Onay Güncellme Başarısız");
    }
}