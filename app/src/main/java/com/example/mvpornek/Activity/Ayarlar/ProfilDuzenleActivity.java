package com.example.mvpornek.Activity.Ayarlar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvpornek.Model.Kullanıcı.KullaniciKayit.Kullanici;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilDuzenleActivity extends Activity implements View.OnClickListener {
    CircleImageView circleImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profil_duzenle);
        TextInputLayout adSoyadInput=(TextInputLayout) findViewById(R.id.adSoyadTextField);
        Kullanici kullanici= SharedPrefManager.getInstance(this).getKullanici();
        EditText adSoyadTxt=findViewById(R.id.profilDuzenleAdSoyadTxt);
        EditText kullaniciAdiTxt=findViewById(R.id.profilDuzenleKullaniciAdiTxt);
        EditText kullaniciEposta=findViewById(R.id.profilDuzenleEpostaTxt);
        Button profilDuzenleGeriBtn=findViewById(R.id.profilDuzenleGeriBtn);
        profilDuzenleGeriBtn.setOnClickListener(this);
        circleImageView=findViewById(R.id.profilDuzenleProfilResmi);
        Picasso.get().load(kullanici.getProfilFoto()).into(circleImageView);
        adSoyadTxt.setText(kullanici.getAdSoyad());
        kullaniciAdiTxt.setText(kullanici.getKullaniciAdi());
        kullaniciEposta.setText(kullanici.getKullaniciEposta());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.profilDuzenleGeriBtn:
                onBackPressed();
                break;
        }
    }
}
