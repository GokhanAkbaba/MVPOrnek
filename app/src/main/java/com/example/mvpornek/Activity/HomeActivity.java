package com.example.mvpornek.Activity;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvpornek.Fragment.NavBarFragment.BildirimlerFragment;
import com.example.mvpornek.Fragment.NavBarFragment.HomeFragment;
import com.example.mvpornek.Fragment.NavBarFragment.ProfilFragment;
import com.example.mvpornek.Fragment.NavBarFragment.SearchFragment;
import com.example.mvpornek.Fragment.NavBarFragment.SettingsFragment;
import com.example.mvpornek.Model.Kullanıcı.KullaniciKayit.Kullanici;
import com.example.mvpornek.Model.ModelGiris.InternetConnectionInteractorImpl;
import com.example.mvpornek.Model.ModelGiris.QuestionRegistrationInteractorImpl;
import com.example.mvpornek.Presenter.InternetConnectionPresenter;
import com.example.mvpornek.Presenter.InternetConnectionPresenterImpl;
import com.example.mvpornek.Presenter.QuestionRegistrationPresenter;
import com.example.mvpornek.Presenter.QuestionRegistrationPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.InternetConnectionView;
import com.example.mvpornek.View.QuestionRegistrationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class HomeActivity extends FragmentActivity implements View.OnClickListener, InternetConnectionView,FragmentManager.OnBackStackChangedListener, QuestionRegistrationView {



    BottomNavigationView bottomNavigationView;
    Button soruPaylasButon;
    private QuestionRegistrationPresenter questionRegistrationPresenter;
    private InternetConnectionPresenter internetConnectionPresenter;
    int item ;
    FloatingActionButton birineSorBtn;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    ProgressBar progressBar;
    ArrayList<Integer> illerList=new ArrayList<Integer>();
    ArrayList<Integer> etiketList=new ArrayList<Integer>();
    Boolean checkAdanaEtiket=false,checkArtvinEtiket=false;
    Boolean checkYemekEtiket =false,checkAdresEtiket = false,checkSporEtiket = false,checkGeziEtiket = false,
            checkTatilEtiket = false,checkAlisverisEtiket = false,checkSanatEtiket = false,checkYirmiDortBildirim=false,checkYazilimEtiket = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ana_sayfa);
        loadFragment(new HomeFragment(),"AnaSayfaFragment");
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        birineSorBtn=findViewById(R.id.soruPaylasimButon);
        birineSorBtn.setOnClickListener(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

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
                alertDialog.show();
                break;
        }

    }

    private boolean loadFragment(Fragment fragment,String fragmentTag) {

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(fragmentTag)
                    .replace(R.id.anaSayfaFrameLayout, fragment)
                    .commit();
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
           getSupportFragmentManager().popBackStack();
       }else if (count == 1){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        if(entry.getName() == "Fragment"){
           getSupportFragmentManager().popBackStack("Fragment", POP_BACK_STACK_INCLUSIVE);
           bottomNavigationView.getMenu().getItem(0).setChecked(true);
        }
    }

    @Override
    public void onBackStackChanged() {
        System.out.println("Stack Durumu");
        int count=getSupportFragmentManager().getBackStackEntryCount();
        if(count <= 2)
        {
            bottomNavigationView.setVisibility(View.VISIBLE);
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
    public void BottomNavigationViewHidden(){
        birineSorBtn.hide();
        bottomNavigationView.setVisibility(View.INVISIBLE);
    }
    public void BottomNavigationView(){
        birineSorBtn.show();
        bottomNavigationView.setVisibility(View.VISIBLE);
    }
}
