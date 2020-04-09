package com.example.mvpornek.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mvpornek.R;

public class BaslarkenActivity extends Activity implements View.OnClickListener {
    Button adanaBtn,adres,yemek,spor,tatil,alisveris,sanat,yrmDrtBildirim;
    Boolean checkIl = false;
    Boolean checkYemekEtiket =false,checkAdresEtiket = false,checkSporEtiket = false,
            checkTatilEtiket = false,checkAlisverisEtiket = false,checkSanatEtiket = false,checkYirmiDortBildirim=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baslarken);

        findViewById(R.id.baslarkenSonrakiBtn).setOnClickListener(this);
        adanaBtn=findViewById(R.id.il_01);
        adanaBtn.setOnClickListener(this);
        findViewById(R.id.il_02).setOnClickListener(this);
        findViewById(R.id.il_03).setOnClickListener(this);
        findViewById(R.id.il_04).setOnClickListener(this);
        findViewById(R.id.il_05).setOnClickListener(this);
        findViewById(R.id.il_06).setOnClickListener(this);
        adres=findViewById(R.id.adresEtiketButon);
        adres.setOnClickListener(this);
        yemek=findViewById(R.id.yemekEtiketButon);
        yemek.setOnClickListener(this);
        spor=findViewById(R.id.sporEtiketButon);
        spor.setOnClickListener(this);
        tatil=findViewById(R.id.tatilEtiketButon);
        tatil.setOnClickListener(this);
        alisveris=findViewById(R.id.alisverisEtiketButon);
        alisveris.setOnClickListener(this);
        sanat=findViewById(R.id.sanatEtiketButon);
        sanat.setOnClickListener(this);
        yrmDrtBildirim=findViewById(R.id.yirmiDortSaatBildirimButon);
        yrmDrtBildirim.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.baslarkenSonrakiBtn:
                Intent intent= new Intent(this,AnaSayfaActivity.class);
                startActivity(intent);
                break;
            case R.id.il_01:
                if(checkIl==false)
                {
                    adanaBtn.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                    adanaBtn.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    checkIl=true;
                }
                else
                {
                    adanaBtn.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                    adanaBtn.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    checkIl=false;
                }

                break;
            case R.id.adresEtiketButon:

                if(checkAdresEtiket == false)
                {
                    adres.setBackground(getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    adres.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgTatil =adres.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                    adres.setCompoundDrawablesWithIntrinsicBounds(imgTatil,null,null,null);
                    checkAdresEtiket = true;
                }
                else
                {
                    adres.setBackground(getDrawable(R.drawable.baslarkenetiketbuton));
                    adres.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgTatil =adres.getContext().getResources().getDrawable(R.mipmap.ic_launcher_adres);
                    adres.setCompoundDrawablesWithIntrinsicBounds(imgTatil,null,null,null);
                    checkAdresEtiket=false;
                }


                break;
            case R.id.yemekEtiketButon:

                if(checkYemekEtiket == false)
                {
                    yemek.setBackground(getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    yemek.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgYemek =yemek.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                    yemek.setCompoundDrawablesWithIntrinsicBounds(imgYemek,null,null,null);
                    checkYemekEtiket = true;
                }
                else
                {
                    yemek.setBackground(getDrawable(R.drawable.baslarkenetiketbuton));
                    yemek.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgYemek =yemek.getContext().getResources().getDrawable(R.mipmap.yemek_icon);
                    yemek.setCompoundDrawablesWithIntrinsicBounds(imgYemek,null,null,null);
                    checkYemekEtiket=false;
                }

                break;
            case R.id.sporEtiketButon:
                if(checkSporEtiket == false)
                {
                    spor.setBackground(getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    spor.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgSpor =spor.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                    spor.setCompoundDrawablesWithIntrinsicBounds(imgSpor,null,null,null);
                    checkSporEtiket = true;
                }
                else
                {
                    spor.setBackground(getDrawable(R.drawable.baslarkenetiketbuton));
                    spor.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgSpor =spor.getContext().getResources().getDrawable(R.mipmap.spor_icon);
                    spor.setCompoundDrawablesWithIntrinsicBounds(imgSpor,null,null,null);
                    checkSporEtiket=false;
                }

                break;
            case R.id.alisverisEtiketButon:
                if(checkAlisverisEtiket == false)
                {
                    alisveris.setBackground(getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    alisveris.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgAlisveris =alisveris.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                    alisveris.setCompoundDrawablesWithIntrinsicBounds(imgAlisveris,null,null,null);
                    checkAlisverisEtiket = true;
                }
                else
                {
                    alisveris.setBackground(getDrawable(R.drawable.baslarkenetiketbuton));
                    alisveris.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgAlisveris =alisveris.getContext().getResources().getDrawable(R.mipmap.alisveris_icon);
                    alisveris.setCompoundDrawablesWithIntrinsicBounds(imgAlisveris,null,null,null);
                    checkAlisverisEtiket=false;
                }
                break;
            case R.id.tatilEtiketButon:
                if(checkTatilEtiket == false)
                {
                    tatil.setBackground(getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    tatil.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgTatil =tatil.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                    tatil.setCompoundDrawablesWithIntrinsicBounds(imgTatil,null,null,null);
                    checkTatilEtiket = true;
                }
                else
                {
                    tatil.setBackground(getDrawable(R.drawable.baslarkenetiketbuton));
                    tatil.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgTatil =tatil.getContext().getResources().getDrawable(R.mipmap.tatil_icon);
                    tatil.setCompoundDrawablesWithIntrinsicBounds(imgTatil,null,null,null);
                    checkTatilEtiket=false;
                }
                break;
            case R.id.sanatEtiketButon:
                if(checkSanatEtiket == false)
                {
                    sanat.setBackground(getDrawable(R.drawable.baslarkenetiketonaylibuton));
                    sanat.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                    Drawable imgSanat =sanat.getContext().getResources().getDrawable(R.mipmap.onay_icon);
                    sanat.setCompoundDrawablesWithIntrinsicBounds(imgSanat,null,null,null);
                    checkSanatEtiket = true;
                }
                else
                {
                    sanat.setBackground(getDrawable(R.drawable.baslarkenetiketbuton));
                    sanat.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                    Drawable imgSanat =sanat.getContext().getResources().getDrawable(R.mipmap.film_icon);
                    sanat.setCompoundDrawablesWithIntrinsicBounds(imgSanat,null,null,null);
                    checkSanatEtiket=false;
                }
                break;
            case R.id.yirmiDortSaatBildirimButon:
                if(checkYirmiDortBildirim == false)
                {
                    yrmDrtBildirim.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                    Drawable imgyrmDrtBildirim =yrmDrtBildirim.getContext().getResources().getDrawable(R.mipmap.onay_beyaz_icon);
                    yrmDrtBildirim.setCompoundDrawablesWithIntrinsicBounds(imgyrmDrtBildirim,null,null,null);
                    checkYirmiDortBildirim = true;
                }
                else
                {
                    yrmDrtBildirim.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                    Drawable imgyrmDrtBildirim =yrmDrtBildirim.getContext().getResources().getDrawable(R.mipmap.bildirim_icon);
                    yrmDrtBildirim.setCompoundDrawablesWithIntrinsicBounds(imgyrmDrtBildirim,null,null,null);
                    checkYirmiDortBildirim=false;
                }
                break;
        }
    }

}
