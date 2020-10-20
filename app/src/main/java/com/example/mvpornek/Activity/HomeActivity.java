package com.example.mvpornek.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpornek.Activity.Notification.NotificationCommentActivity;
import com.example.mvpornek.BildirimFonksiyonları;
import com.example.mvpornek.Fragment.NavBarFragment.BildirimlerFragment;
import com.example.mvpornek.Fragment.NavBarFragment.HomeFragment;
import com.example.mvpornek.Fragment.NavBarFragment.ProfilFragment;
import com.example.mvpornek.Fragment.NavBarFragment.SearchFragment;
import com.example.mvpornek.Fragment.NavBarFragment.SettingsFragment;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Model.InternetBaglantiKontrol.InternetConnectionInteractorImpl;
import com.example.mvpornek.Model.SoruKayit.QuestionRegistrationInteractorImpl;
import com.example.mvpornek.Presenter.InternetBaglantiKontrol.InternetConnectionPresenter;
import com.example.mvpornek.Presenter.InternetBaglantiKontrol.InternetConnectionPresenterImpl;
import com.example.mvpornek.Presenter.QuestionRegistrationPresenter;
import com.example.mvpornek.Presenter.QuestionRegistrationPresenterImpl;
import com.example.mvpornek.Presenter.TokenCreate.TokenCreatePresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.InternetConnectionView;
import com.example.mvpornek.View.QuestionRegistrationView;

import com.example.mvpornek.View.TokenCreateView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class HomeActivity extends FragmentActivity implements View.OnClickListener, TokenCreateView, InternetConnectionView,FragmentManager.OnBackStackChangedListener, QuestionRegistrationView {


    private static final String CHANNEL_ID ="birine_sor";
    private static final String CHANNEL_NAME ="Birine_Sor";
    private static final String CHANNEL_DESC ="Birine_Sor_Notifications";

    BottomNavigationView bottomNavigationView;

    Button soruPaylasButon;
    private QuestionRegistrationPresenter questionRegistrationPresenter;
    private InternetConnectionPresenter internetConnectionPresenter;
    TokenCreatePresenterImpl tokenCreatePresenter;
    public static HomeActivity instance;
    int item ;
    Kullanici kullanici;
    FloatingActionButton birineSorBtn;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    ProgressBar progressBar;

    Button tv;
    View notificationBadge;
    BottomNavigationMenuView menuView;
    BottomNavigationItemView itemView;
    BildirimFonksiyonları bildirimFonksiyonları;
    ArrayList<Integer> illerList=new ArrayList<Integer>();
    ArrayList<Integer> etiketList=new ArrayList<Integer>();
    Boolean checkAdanaEtiket=false,checkArtvinEtiket=false,checkTeknoEtiket=false,checkSaglikEtiket=false;
    Boolean checkMuzikEtiket=false,checkEgitimEtiket=false,checkTarihEtiket=false,checkOtoEtiket=false,checkModaEtiket=false,checkOyunEtiket=false;
    Boolean checkYemekEtiket =false,checkAdresEtiket = false,checkSporEtiket = false,checkGeziEtiket = false,
            checkTatilEtiket = false,checkAlisverisEtiket = false,checkSanatEtiket = false,checkYirmiDortBildirim=false,checkYazilimEtiket = false;

    public static HomeActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ana_sayfa);
        instance=this;
        bildirimFonksiyonları=new BildirimFonksiyonları(this);
        tokenCreatePresenter=new TokenCreatePresenterImpl(this);
        loadFragment(new HomeFragment(),"AnaSayfaFragment");
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        birineSorBtn=findViewById(R.id.soruPaylasimButon);
        birineSorBtn.setOnClickListener(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        progressBar=findViewById(R.id.anaSayfaProgressBar);
        bottomNavigationView=findViewById(R.id.anasayfa_nav_view);
        internetConnectionPresenter=new InternetConnectionPresenterImpl(this,new InternetConnectionInteractorImpl(this));
        internetConnectionPresenter.internetBaglantiKontrolu();
        questionRegistrationPresenter=new QuestionRegistrationPresenterImpl(this,new QuestionRegistrationInteractorImpl(this));

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment=null;
                item=menuItem.getItemId();
                switch (menuItem.getItemId())
                {
                    case R.id.anaSayfaItem:
                        fragment=new HomeFragment();
                        loadFragment(fragment,"AnaSayfaFragment");

                        break;
                    case R.id.profilItem:
                        fragment=new ProfilFragment();
                        loadFragment(fragment,"Fragment");
                        break;
                    case R.id.bildirimItem:
                        fragment=new BildirimlerFragment();
                        loadFragment(fragment,"Fragment");
                        refreshBadgeView(bildirimFonksiyonları.getCount());
                        bildirimFonksiyonları.setCount(0);
                        break;
                    case R.id.aramaItem:
                        fragment=new SearchFragment();
                        loadFragment(fragment,"Fragment");
                        break;
                    case R.id.ayarlarItem:
                        fragment=new SettingsFragment();
                        loadFragment(fragment,"Fragment");
                        break;
                }
                return true;
            }
        });

        addBadgeView();

        notificationBadge.setVisibility(View.INVISIBLE);
        if(NotificationCommentActivity.getBildirimAcilis() != null){
            if(NotificationCommentActivity.getBildirimAcilis() == true){
                notificationView(bildirimFonksiyonları.getCount());
            }
        }

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(task.isSuccessful()){
                            kullanici= SharedPrefManager.getInstance(getApplicationContext()).getKullanici();
                            tokenCreatePresenter.createToken(kullanici.getId(),task.getResult().getToken());
                        }
                        else{
                            System.out.println("İşlem Başarısız");
                        }
                    }
                });
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            int soruID=extras.getInt("soruID");
             ProfilFragment profilFragment = ProfilFragment.newInstance(soruID);
             loadFragment(profilFragment,"Fragment");

        }


    }
    protected void refreshBadgeView(int count){
        if(count != 0){
            boolean badgeIsVisible = notificationBadge.getVisibility() != VISIBLE;
            notificationBadge.setVisibility(badgeIsVisible ? VISIBLE : GONE);
        }
    }
    public void addBadgeView(){

        menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        itemView = (BottomNavigationItemView) menuView.getChildAt(2);
        notificationBadge = LayoutInflater.from(this).inflate(R.layout.component_tabbar_badge, menuView, false);
        tv = notificationBadge.findViewById(R.id.notification_badge);
        itemView.addView(notificationBadge);
    }

    public void notificationView(int count){
        tv.setText(String.valueOf(count));
        notificationBadge.setVisibility(VISIBLE);

    }

   public void init(int value){
    progressBar.setVisibility(value);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.soruPaylasimButon:
                internetConnectionPresenter.internetBaglantiKontrolu();
                dialogBuilder = new AlertDialog.Builder(HomeActivity.this);
                View layoutView = getLayoutInflater().inflate(R.layout.soru_paylas_ekrani, null);
                dialogBuilder.setView(layoutView);
                alertDialog = dialogBuilder.create();
                alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                EditText soruAlaniTxt=layoutView.findViewById(R.id.soruAlaniText);
                Button yemekButon=layoutView.findViewById(R.id.yemekEtiketBtn);
                Button adresButon=layoutView.findViewById(R.id.adresEtiketBtn);
                Button tatilButon=layoutView.findViewById(R.id.tatilEtiketBtn);
                Button filmDiziButon=layoutView.findViewById(R.id.filmDiziEtiketBtn);
                Button alisverisButon=layoutView.findViewById(R.id.alisverisEtiketBtn);
                Button geziButon=layoutView.findViewById(R.id.geziEtiketBtn);
                Button yazilimButon=layoutView.findViewById(R.id.yazilimEtiketBtn);
                Button sporButon=layoutView.findViewById(R.id.sporEtiketBtn);
                Button oyunEtiketBtn=layoutView.findViewById(R.id.oyunEtiketBtn);
                Button otoEtiketBtn=layoutView.findViewById(R.id.otoEtiketBtn);
                Button teknoEtiketBtn=layoutView.findViewById(R.id.teknoEtiketBtn);
                Button saglikEtiketBtn=layoutView.findViewById(R.id.saglikEtiketBtn);
                Button modaEtiketBtn=layoutView.findViewById(R.id.modaEtiketBtn);
                Button tarihEtiketBtn=layoutView.findViewById(R.id.tarihEtiketBtn);
                Button egitimEtiketBtn=layoutView.findViewById(R.id.egitimEtiketBtn);
                Button muzikEtiketBtn=layoutView.findViewById(R.id.muzikEtiketBtn);
                Button adanaButon=layoutView.findViewById(R.id.il_01);
                Button artvinButon=layoutView.findViewById(R.id.il_02);
                soruPaylasButon=layoutView.findViewById(R.id.soruPaylasBtn);
                Kullanici kullanici= SharedPrefManager.getInstance(this).getKullanici();
                soruPaylasButon.setEnabled(false);
                soruPaylasButon.setBackgroundColor(getResources().getColor(R.color.ayarlarGrisi));
                soruAlaniTxt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if(charSequence.toString().trim().length()==0){
                            soruPaylasButon.setEnabled(false);
                            soruPaylasButon.setBackgroundColor(getResources().getColor(R.color.ayarlarGrisi));
                        }else{
                            soruPaylasButon.setEnabled(true);
                            soruPaylasButon.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                });

                soruPaylasButon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        internetConnectionPresenter.internetBaglantiKontrolu();
                        String soruAlaniText=soruAlaniTxt.getText().toString();
                        questionRegistrationPresenter.questionRegistrationValideCredentals(kullanici.getId(),soruAlaniText,etiketList,illerList);
                    }
                });

                adanaButon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkAdanaEtiket == false)
                        {
                            illerList.add(1);
                            adanaButon.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            adanaButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkAdanaEtiket = true;
                        }
                        else
                        {
                            illerList.remove(new Integer(1));
                            adanaButon.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            adanaButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkAdanaEtiket=false;
                        }
                    }
                });

                artvinButon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkArtvinEtiket == false)
                        {
                            illerList.add(2);
                            artvinButon.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            artvinButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkArtvinEtiket = true;
                        }
                        else
                        {
                            illerList.remove(new Integer(2));
                            artvinButon.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            artvinButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkArtvinEtiket=false;
                        }
                    }
                });

                sporButon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkSporEtiket == false) {
                            etiketList.add(3);
                            sporButon.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            sporButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkSporEtiket = true;
                        }else{
                            etiketList.remove(new Integer(3));
                            sporButon.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            sporButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkSporEtiket=false;
                        }
                    }
                });
                yazilimButon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkYazilimEtiket == false) {
                            etiketList.add(7);
                            yazilimButon.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            yazilimButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkYazilimEtiket = true;
                        }else{
                            etiketList.remove(new Integer(7));
                            yazilimButon.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            yazilimButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkYazilimEtiket=false;
                        }
                    }
                });


                teknoEtiketBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkTeknoEtiket == false) {
                            etiketList.add(10);
                            teknoEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            teknoEtiketBtn.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkTeknoEtiket = true;
                        }else{
                            etiketList.remove(new Integer(10));
                            teknoEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            teknoEtiketBtn.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkTeknoEtiket=false;
                        }
                    }
                });

                saglikEtiketBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkSaglikEtiket == false) {
                            etiketList.add(12);
                            saglikEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            saglikEtiketBtn.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkSaglikEtiket = true;

                        }else{
                            etiketList.remove(new Integer(12));
                            saglikEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            saglikEtiketBtn.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkSaglikEtiket=false;
                        }
                    }
                });

                geziButon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkGeziEtiket == false) {
                            etiketList.add(8);
                            geziButon.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            geziButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkGeziEtiket = true;
                        }else{
                            etiketList.remove(new Integer(8));
                            geziButon.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            geziButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkGeziEtiket=false;
                        }
                    }
                });

                alisverisButon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkAlisverisEtiket == false) {
                            etiketList.add(5);
                            alisverisButon.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            alisverisButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkAlisverisEtiket = true;
                        }else{
                            etiketList.remove(new Integer(5));
                            alisverisButon.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            alisverisButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkAlisverisEtiket=false;
                        }
                    }
                });

                filmDiziButon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkSanatEtiket == false) {
                            etiketList.add(9);
                            filmDiziButon.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            filmDiziButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkSanatEtiket = true;
                        }else{
                            etiketList.remove(new Integer(9));
                            filmDiziButon.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            filmDiziButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkSanatEtiket=false;
                        }
                    }
                });

                yemekButon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkYemekEtiket == false) {
                            etiketList.add(2);
                            yemekButon.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            yemekButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkYemekEtiket = true;
                        }else{
                            etiketList.remove(new Integer(2));
                            yemekButon.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            yemekButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkYemekEtiket=false;
                        }
                    }
                });

                adresButon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkAdresEtiket == false) {
                            etiketList.add(1);
                            adresButon.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            adresButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkAdresEtiket = true;
                        }else{
                            etiketList.remove(new Integer(1));
                            adresButon.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            adresButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkAdresEtiket=false;
                        }
                    }
                });

                tatilButon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkTatilEtiket == false) {
                            etiketList.add(4);
                            tatilButon.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            tatilButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkTatilEtiket = true;
                        }else{
                            etiketList.remove(new Integer(4));
                            tatilButon.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            tatilButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkTatilEtiket=false;
                        }
                    }
                });
                oyunEtiketBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkOyunEtiket == false) {
                            etiketList.add(11);
                            oyunEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            oyunEtiketBtn.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkOyunEtiket = true;
                        }else{
                            etiketList.remove(new Integer(11));
                            oyunEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            oyunEtiketBtn.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkOyunEtiket=false;

                        }
                    }
                });
                modaEtiketBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkModaEtiket == false) {
                            etiketList.add(16);
                            modaEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            modaEtiketBtn.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkModaEtiket = true;
                        }else{
                            etiketList.remove(new Integer(16));
                            modaEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            modaEtiketBtn.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkModaEtiket=false;

                        }
                    }
                });
                otoEtiketBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkOtoEtiket == false) {
                            etiketList.add(16);
                            otoEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            otoEtiketBtn.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkOtoEtiket = true;
                        }else{
                            etiketList.remove(new Integer(16));
                            otoEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            otoEtiketBtn.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkOtoEtiket=false;


                        }
                    }
                });
                tarihEtiketBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkTarihEtiket == false) {
                            etiketList.add(15);
                            tarihEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            tarihEtiketBtn.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkTarihEtiket = true;
                        }else{
                            etiketList.remove(new Integer(15));
                            tarihEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            tarihEtiketBtn.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkTarihEtiket=false;

                        }
                    }
                });
                egitimEtiketBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkEgitimEtiket == false) {
                            etiketList.add(14);
                            egitimEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            egitimEtiketBtn.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkEgitimEtiket = true;
                        }else{
                            etiketList.remove(new Integer(14));
                            egitimEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            egitimEtiketBtn.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkEgitimEtiket=false;
                        }
                    }
                });
                muzikEtiketBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkMuzikEtiket == false) {
                            etiketList.add(13);
                            muzikEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            muzikEtiketBtn.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkMuzikEtiket = true;
                        }else{
                            etiketList.remove(new Integer(13));
                            muzikEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            muzikEtiketBtn.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkMuzikEtiket=false;
                        }
                    }
                });
                alertDialog.show();
                break;
        }

    }

    public boolean loadFragment(Fragment fragment,String fragmentTag) {

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(fragmentTag)
                    .replace(R.id.anaSayfaFrameLayout, fragment)
                    .commitAllowingStateLoss();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {

        int count=getSupportFragmentManager().getBackStackEntryCount();
        FragmentManager.BackStackEntry entry=getSupportFragmentManager().getBackStackEntryAt(count -1);
        if(count == 0){
            super.onBackPressed();
        } else if(count >1){
            if(entry.getName() == "AramaAsama-4" || entry.getName() == "AramaAsamaTık-2"){
                getSupportFragmentManager().popBackStack("Fragment", 0);
            }else{
                getSupportFragmentManager().popBackStack();
            }


       }else if (count == 1){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        if(entry.getName() == "Fragment"){
           getSupportFragmentManager().popBackStack("Fragment", POP_BACK_STACK_INCLUSIVE);
           bottomNavigationView.getMenu().getItem(0).setChecked(true);
        }else if(entry.getName() == "AnaSayfaFragment"){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            getSupportFragmentManager().popBackStack("AnaSayfaFragment", 0);
        }
    }

    @Override
    public void onBackStackChanged() {
        System.out.println("Stack Durumu");
        int count=getSupportFragmentManager().getBackStackEntryCount();
        if(count <= 2)
        {
            bottomNavigationView.setVisibility(VISIBLE);
        }
        for (int i=count - 1;i>=0;i--){
            FragmentManager.BackStackEntry entry=getSupportFragmentManager().getBackStackEntryAt(i);
            System.out.println(i+"--"+entry.getName());
        }
        System.out.println("\n");
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setIlSecimHatasi() {
        Toast.makeText(this,"En Az Bir Adet İl Seçiniz",Toast.LENGTH_LONG).show();
    }

    @Override
    public void setEtiketSecimHatasi() {
        Toast.makeText(this,"En Az Bir Adet Etiket Seçiniz",Toast.LENGTH_LONG).show();
    }

    @Override
    public void setSoruAlaniHatasi() {
        Toast.makeText(this,"Hata Oluştu",Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToQuestionRegistration() {
        alertDialog.dismiss();
        Toast.makeText(this,"Birine Soruldu",Toast.LENGTH_LONG).show();

    }

    @Override
    public void internetBaglantiHatasi() {

        Toast.makeText(this,"İnternet Bağlantınızı Kontrol Ediniz",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void internetBaglantisi() {

    }

    public void menuDurum(int durum){
        bottomNavigationView.getMenu().getItem(durum).setChecked(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

   @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    @Override
    public void showTokenSuccesMessage() {
        System.out.println("Token Başarılı Bir Şekilde Oluştu");
    }

    @Override
    public void showTokenFailedMessage() {
        System.out.println("Token HATA");
    }
}
