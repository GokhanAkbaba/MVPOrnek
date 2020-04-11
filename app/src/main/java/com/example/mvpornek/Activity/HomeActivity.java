package com.example.mvpornek.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.mvpornek.R;

public class HomeActivity extends Activity implements View.OnClickListener {
    ImageButton alisverisButon,tatilButon,adresButon,sporButon,yemekButon,sanatButon;
    Button anaSayfaSoruButon;
    Boolean checkYemekEtiket =false,checkAdresEtiket = false,checkSporEtiket = false,
            checkTatilEtiket = false,checkAlisverisEtiket = false,checkSanatEtiket = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ana_sayfa);

        alisverisButon=findViewById(R.id.anasayfa_alisveris_btn);
        alisverisButon.setOnClickListener(this);
        yemekButon=findViewById(R.id.anasayfa_yemek_btn);
        yemekButon.setOnClickListener(this);
        tatilButon=findViewById(R.id.anasayfa_tatil_btn);
        tatilButon.setOnClickListener(this);
        adresButon=findViewById(R.id.anasayfa_adres_btn);
        adresButon.setOnClickListener(this);
        sporButon=findViewById(R.id.anasayfa_spor_btn);
        sporButon.setOnClickListener(this);
        sanatButon=findViewById(R.id.anasayfa_sanat_btn);
        sanatButon.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.anasayfa_alisveris_btn:
                if(checkAlisverisEtiket == false)
                {
                    alisverisButon.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                    alisverisButon.setImageResource(R.mipmap.alisveris_icon_beyaz);
                    checkAlisverisEtiket = true;
                }
                else
                {
                    alisverisButon.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                    alisverisButon.setImageResource(R.mipmap.alisveris_icon);
                    checkAlisverisEtiket=false;
                }
                break;
            case R.id.anasayfa_yemek_btn:
                if(checkYemekEtiket == false)
                {
                    yemekButon.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                    yemekButon.setImageResource(R.mipmap.yemek_icon_beyaz);
                    checkYemekEtiket = true;
                }
                else
                {
                    yemekButon.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                    yemekButon.setImageResource(R.mipmap.yemek_icon);
                    checkYemekEtiket=false;
                }
                break;
            case R.id.anasayfa_tatil_btn:
                if(checkTatilEtiket == false)
                {
                    tatilButon.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                    tatilButon.setImageResource(R.mipmap.tatil_icon_beyaz);
                    checkTatilEtiket = true;
                }
                else
                {
                    tatilButon.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                    tatilButon.setImageResource(R.mipmap.tatil_icon);
                    checkTatilEtiket=false;
                }
                break;
            case R.id.anasayfa_adres_btn:
                if(checkAdresEtiket == false)
                {
                    adresButon.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                    adresButon.setImageResource(R.mipmap.adres_icon_beyaz);
                    checkAdresEtiket = true;
                }
                else
                {
                    adresButon.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                    adresButon.setImageResource(R.mipmap.ic_launcher_adres);
                    checkAdresEtiket=false;
                }
                break;
            case R.id.anasayfa_spor_btn:
                if(checkSporEtiket == false)
                {
                    sporButon.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                    sporButon.setImageResource(R.mipmap.spor_icon_beyaz);
                    checkSporEtiket = true;
                }
                else
                {
                    sporButon.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                    sporButon.setImageResource(R.mipmap.spor_icon);
                    checkSporEtiket=false;
                }
                break;
            case R.id.anasayfa_sanat_btn:
                if(checkSanatEtiket == false)
                {
                    sanatButon.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                    sanatButon.setImageResource(R.mipmap.sanat_icon_beyaz);
                    checkSanatEtiket = true;
                }
                else
                {
                    sanatButon.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                    sanatButon.setImageResource(R.mipmap.film_icon);
                    checkSanatEtiket=false;
                }
                break;
            case R.id.anasayfa_soru_gonder:
                break;
        }

    }
}
