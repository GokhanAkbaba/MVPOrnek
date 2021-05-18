package com.birinesor.mvpornek.Activity.Ayarlar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.birinesor.mvpornek.Adapter.AdapterCevaplarOnay;
import com.birinesor.mvpornek.Adapter.AdapterSorularOnay;
import com.birinesor.mvpornek.Models.CevaplarOnayModels;
import com.birinesor.mvpornek.Models.SorularOnayModel;
import com.birinesor.mvpornek.Presenter.CevapOnay.CevapOnayDurumGuncellePresenterImpl;
import com.birinesor.mvpornek.Presenter.CevapOnayPresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.View.CevapOnayDurumGuncelle;
import com.birinesor.mvpornek.View.CevapOnayView;

import java.util.List;

public class AdminCevaplarActivity extends AppCompatActivity implements CevapOnayDurumGuncelle, CevapOnayView {
    private RecyclerView adminCevaplarSayfasiRecyclerView;
    CevapOnayDurumGuncellePresenterImpl cevapOnayDurumGuncellePresenter;
    CevapOnayPresenterImpl cevapOnayPresenter;
    AdapterCevaplarOnay adapterCevaplarOnay;
    List<CevaplarOnayModels> cevaplarOnayModels;
    SwipeRefreshLayout swipeRefreshLayout;
    AdapterCevaplarOnay.ItemClickListener itemOnayClickListener;
    AdapterCevaplarOnay.ItemClickListener itemRedClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cevaplar);
        cevapOnayPresenter=new CevapOnayPresenterImpl(this);
        cevapOnayPresenter.cevapOnayLoad();
        adminCevaplarSayfasiRecyclerView=findViewById(R.id.adminCevaplarSayfasiRecyclerView);
        adminCevaplarSayfasiRecyclerView.setAdapter(adapterCevaplarOnay);
        adminCevaplarSayfasiRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cevapOnayDurumGuncellePresenter=new CevapOnayDurumGuncellePresenterImpl(this);
        swipeRefreshLayout=findViewById(R.id.adminCevaplarSwipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.uygulamaMavisi);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            cevapOnayPresenter.cevapOnayLoad();
        });


        itemOnayClickListener =((vw,position)-> {
            View onayViewButon = adminCevaplarSayfasiRecyclerView.getLayoutManager().findViewByPosition(position);
            TextView onayViewButonTxt=onayViewButon.findViewById(R.id.cevapOnayId);
            System.out.println("HEYY"+onayViewButonTxt);
            cevapOnayDurumGuncellePresenter.cevapOnayDurumGuncelle(Integer.parseInt((String) onayViewButonTxt.getText()),1);
        });
        itemRedClickListener =((vw,position)-> {
            View redViewButon = adminCevaplarSayfasiRecyclerView.getLayoutManager().findViewByPosition(position);
            TextView redViewButonTxt=redViewButon.findViewById(R.id.cevapOnayId);
            cevapOnayDurumGuncellePresenter.cevapOnayDurumGuncelle(Integer.parseInt((String) redViewButonTxt.getText()),-1);
        });

    }

    @Override
    public void showCevapOnayDurumGuncelleSuccesMessage() {
        System.out.println("Cevaplar Onay Gücelleme Başarılı");
        cevapOnayPresenter.cevapOnayLoad();
    }

    @Override
    public void showCevapOnayDurumGuncelleFailedMessage() {
        System.out.println("Cevaplar Onay Gücelleme Başarısız");
        cevapOnayPresenter.cevapOnayLoad();
    }

    @Override
    public void onCevapOnayResult(List<CevaplarOnayModels> data) {
        adapterCevaplarOnay=new AdapterCevaplarOnay(data,this,itemOnayClickListener,itemRedClickListener);
        adapterCevaplarOnay.notifyDataSetChanged();
        adminCevaplarSayfasiRecyclerView.setAdapter(adapterCevaplarOnay);
        this.cevaplarOnayModels=data;
    }

    @Override
    public void onCevapOnayErrorLoading(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCevapOnayShow() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void onCevapOnayHide() {
        swipeRefreshLayout.setRefreshing(false);
    }
}