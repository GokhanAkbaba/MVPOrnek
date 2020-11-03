package com.birinesor.mvpornek.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.birinesor.mvpornek.Activity.Notification.NotificationCommentActivity;
import com.birinesor.mvpornek.AnalyticsApplication;
import com.birinesor.mvpornek.BildirimFonksiyonları;
import com.birinesor.mvpornek.Fragment.NavBarFragment.BildirimlerFragment;
import com.birinesor.mvpornek.Fragment.NavBarFragment.HomeFragment;
import com.birinesor.mvpornek.Fragment.NavBarFragment.ProfilFragment;
import com.birinesor.mvpornek.Fragment.NavBarFragment.SearchFragment;
import com.birinesor.mvpornek.Fragment.NavBarFragment.SettingsFragment;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Model.InternetBaglantiKontrol.InternetConnectionInteractorImpl;
import com.birinesor.mvpornek.Model.SoruKayit.QuestionRegistrationInteractorImpl;
import com.birinesor.mvpornek.Presenter.InternetBaglantiKontrol.InternetConnectionPresenter;
import com.birinesor.mvpornek.Presenter.InternetBaglantiKontrol.InternetConnectionPresenterImpl;
import com.birinesor.mvpornek.Presenter.QuestionRegistrationPresenter;
import com.birinesor.mvpornek.Presenter.QuestionRegistrationPresenterImpl;
import com.birinesor.mvpornek.Presenter.TokenCreate.TokenCreatePresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.View.InternetConnectionView;
import com.birinesor.mvpornek.View.QuestionRegistrationView;

import com.birinesor.mvpornek.View.TokenCreateView;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


import java.util.ArrayList;
import java.util.Objects;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class HomeActivity extends FragmentActivity implements View.OnClickListener, TokenCreateView, InternetConnectionView,FragmentManager.OnBackStackChangedListener, QuestionRegistrationView {


    private static final String CHANNEL_ID ="birine_sor";
    private static final String CHANNEL_NAME ="Birine_Sor";
    private static final String CHANNEL_DESC ="Birine_Sor_Notifications";

    BottomNavigationView bottomNavigationView;

    Button soruPaylasButon;
    View layoutView;
    private QuestionRegistrationPresenter questionRegistrationPresenter;
    private InternetConnectionPresenter internetConnectionPresenter;
    TokenCreatePresenterImpl tokenCreatePresenter;
    public static HomeActivity instance;
    int item;
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

    Boolean checkIl01Etiket=false,checkIl02Etiket=false,checkIl03Etiket=false,checkIl04Etiket=false,checkIl05Etiket=false,checkIl06Etiket=false,checkIl07Etiket=false;
    Boolean checkIl08Etiket=false,checkIl09Etiket=false,checkIl10Etiket=false,checkIl11Etiket=false,checkIl12Etiket=false,checkIl13Etiket=false,checkIl14Etiket=false;
    Boolean checkIl15Etiket=false,checkIl16Etiket=false,checkIl17Etiket=false,checkIl18Etiket=false,checkIl19Etiket=false,checkIl20Etiket=false,checkIl21Etiket=false;
    Boolean checkIl22Etiket=false,checkIl23Etiket=false,checkIl24Etiket=false,checkIl25Etiket=false,checkIl26Etiket=false,checkIl27Etiket=false,checkIl28Etiket=false;
    Boolean checkIl29Etiket=false,checkIl30Etiket=false,checkIl31Etiket=false,checkIl32Etiket=false,checkIl33Etiket=false,checkIl34Etiket=false,checkIl35Etiket=false;
    Boolean checkIl36Etiket=false,checkIl37Etiket=false,checkIl38Etiket=false,checkIl39Etiket=false,checkIl40Etiket=false,checkIl41Etiket=false,checkIl42Etiket=false;
    Boolean checkIl43Etiket=false,checkIl44Etiket=false,checkIl45Etiket=false,checkIl46Etiket=false,checkIl47Etiket=false,checkIl48Etiket=false,checkIl49Etiket=false;
    Boolean checkIl50Etiket=false,checkIl51Etiket=false,checkIl52Etiket=false,checkIl53Etiket=false,checkIl54Etiket=false,checkIl55Etiket=false,checkIl56Etiket=false;
    Boolean checkIl57Etiket=false,checkIl58Etiket=false,checkIl59Etiket=false,checkIl60Etiket=false,checkIl61Etiket=false,checkIl62Etiket=false,checkIl63Etiket=false;
    Boolean checkIl64Etiket=false,checkIl65Etiket=false,checkIl66Etiket=false,checkIl67Etiket=false,checkIl68Etiket=false,checkIl69Etiket=false,checkIl70Etiket=false;
    Boolean checkIl71Etiket=false,checkIl72Etiket=false,checkIl73Etiket=false,checkIl74Etiket=false,checkIl75Etiket=false,checkIl76Etiket=false,checkIl77Etiket=false;
    Boolean checkIl78Etiket=false,checkIl79Etiket=false,checkIl80Etiket=false,checkIl81Etiket=false;
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
        /*AnalyticsApplication application = (AnalyticsApplication) getApplication();
       Tracker mTracker = application.getDefaultTracker();*/
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
                layoutView = getLayoutInflater().inflate(R.layout.soru_paylas_ekrani, null);
                internetConnectionPresenter.internetBaglantiKontrolu();
                dialogBuilder = new AlertDialog.Builder(HomeActivity.this);
                dialogBuilder.setView(layoutView);
                alertDialog = dialogBuilder.create();
                Objects.requireNonNull(alertDialog.getWindow()).setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                EditText soruAlaniTxt=layoutView.findViewById(R.id.soruAlaniText);
                Button yemekButon=layoutView.findViewById(R.id.yemekEtiketBtn);
                Button tatilButon=layoutView.findViewById(R.id.tatilEtiketBtn);
                Button filmDiziButon=layoutView.findViewById(R.id.filmDiziEtiketBtn);
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
                Button alisverisButon=layoutView.findViewById(R.id.alisverisEtiketBtn);
                Button adresButon=layoutView.findViewById(R.id.adresEtiketBtn);
                Button il01=layoutView.findViewById(R.id.il_01);
                il01.setOnClickListener(this);
                Button il02=layoutView.findViewById(R.id.il_02);
                il02.setOnClickListener(this);
                Button il03=layoutView.findViewById(R.id.il_03);
                il03.setOnClickListener(this);
                Button il04=layoutView.findViewById(R.id.il_04);
                il04.setOnClickListener(this);
                Button il05=layoutView.findViewById(R.id.il_05);
                il05.setOnClickListener(this);
                Button il06=layoutView.findViewById(R.id.il_06);
                il06.setOnClickListener(this);
                Button il07=layoutView.findViewById(R.id.il_07);
                il07.setOnClickListener(this);
                Button il08=layoutView.findViewById(R.id.il_08);
                il08.setOnClickListener(this);
                Button il09=layoutView.findViewById(R.id.il_09);
                il09.setOnClickListener(this);
                Button il10=layoutView.findViewById(R.id.il_10);
                il10.setOnClickListener(this);
                Button il11=layoutView.findViewById(R.id.il_11);
                il11.setOnClickListener(this);
                Button il12=layoutView.findViewById(R.id.il_12);
                il12.setOnClickListener(this);
                Button il13=layoutView.findViewById(R.id.il_13);
                il13.setOnClickListener(this);
                Button il14=layoutView.findViewById(R.id.il_14);
                il14.setOnClickListener(this);
                Button il15=layoutView.findViewById(R.id.il_15);
                il15.setOnClickListener(this);
                Button il16=layoutView.findViewById(R.id.il_16);
                il16.setOnClickListener(this);
                Button il17=layoutView.findViewById(R.id.il_17);
                il17.setOnClickListener(this);
                Button il18=layoutView.findViewById(R.id.il_18);
                il18.setOnClickListener(this);
                Button il19=layoutView.findViewById(R.id.il_19);
                il19.setOnClickListener(this);
                Button il20=layoutView.findViewById(R.id.il_20);
                il20.setOnClickListener(this);
                Button il21=layoutView.findViewById(R.id.il_21);
                il21.setOnClickListener(this);
                Button il22=layoutView.findViewById(R.id.il_22);
                il22.setOnClickListener(this);
                Button il23=layoutView.findViewById(R.id.il_23);
                il23.setOnClickListener(this);
                Button il24=layoutView.findViewById(R.id.il_24);
                il24.setOnClickListener(this);
                Button il25=layoutView.findViewById(R.id.il_25);
                il25.setOnClickListener(this);
                Button il26=layoutView.findViewById(R.id.il_26);
                il26.setOnClickListener(this);
                Button il27=layoutView.findViewById(R.id.il_27);
                il27.setOnClickListener(this);
                Button il28=layoutView.findViewById(R.id.il_28);
                il28.setOnClickListener(this);
                Button il29=layoutView.findViewById(R.id.il_29);
                il29.setOnClickListener(this);
                Button il30=layoutView.findViewById(R.id.il_30);
                il30.setOnClickListener(this);
                Button il31=layoutView.findViewById(R.id.il_31);
                il31.setOnClickListener(this);
                Button il32=layoutView.findViewById(R.id.il_32);
                il32.setOnClickListener(this);
                Button il33=layoutView.findViewById(R.id.il_33);
                il33.setOnClickListener(this);
                Button il34=layoutView.findViewById(R.id.il_34);
                il34.setOnClickListener(this);
                Button il35=layoutView.findViewById(R.id.il_35);
                il35.setOnClickListener(this);
                Button il36=layoutView.findViewById(R.id.il_36);
                il36.setOnClickListener(this);
                Button il37=layoutView.findViewById(R.id.il_37);
                il37.setOnClickListener(this);
                Button il38=layoutView.findViewById(R.id.il_38);
                il38.setOnClickListener(this);
                Button il39=layoutView.findViewById(R.id.il_39);
                il39.setOnClickListener(this);
                Button il40=layoutView.findViewById(R.id.il_40);
                il40.setOnClickListener(this);
                Button il41=layoutView.findViewById(R.id.il_41);
                il41.setOnClickListener(this);
                Button il42=layoutView.findViewById(R.id.il_42);
                il42.setOnClickListener(this);
                Button il43=layoutView.findViewById(R.id.il_43);
                il43.setOnClickListener(this);
                Button il44=layoutView.findViewById(R.id.il_44);
                il44.setOnClickListener(this);
                Button il45=layoutView.findViewById(R.id.il_45);
                il45.setOnClickListener(this);
                Button il46=layoutView.findViewById(R.id.il_46);
                il46.setOnClickListener(this);
                Button il47=layoutView.findViewById(R.id.il_47);
                il47.setOnClickListener(this);
                Button il48=layoutView.findViewById(R.id.il_48);
                il48.setOnClickListener(this);
                Button il49=layoutView.findViewById(R.id.il_49);
                il49.setOnClickListener(this);
                Button il50=layoutView.findViewById(R.id.il_50);
                il50.setOnClickListener(this);
                Button il51=layoutView.findViewById(R.id.il_51);
                il51.setOnClickListener(this);
                Button il52=layoutView.findViewById(R.id.il_52);
                il52.setOnClickListener(this);
                Button il53=layoutView.findViewById(R.id.il_53);
                il53.setOnClickListener(this);
                Button il54=layoutView.findViewById(R.id.il_54);
                il54.setOnClickListener(this);
                Button il55=layoutView.findViewById(R.id.il_55);
                il55.setOnClickListener(this);
                Button il56=layoutView.findViewById(R.id.il_56);
                il56.setOnClickListener(this);
                Button il57=layoutView.findViewById(R.id.il_57);
                il57.setOnClickListener(this);
                Button il58=layoutView.findViewById(R.id.il_58);
                il58.setOnClickListener(this);
                Button il59=layoutView.findViewById(R.id.il_59);
                il59.setOnClickListener(this);
                Button il60=layoutView.findViewById(R.id.il_60);
                il60.setOnClickListener(this);
                Button il61=layoutView.findViewById(R.id.il_61);
                il61.setOnClickListener(this);
                Button il62=layoutView.findViewById(R.id.il_62);
                il62.setOnClickListener(this);
                Button il63=layoutView.findViewById(R.id.il_63);
                il63.setOnClickListener(this);
                Button il64=layoutView.findViewById(R.id.il_64);
                il64.setOnClickListener(this);
                Button il65=layoutView.findViewById(R.id.il_65);
                il65.setOnClickListener(this);
                Button il66=layoutView.findViewById(R.id.il_66);
                il66.setOnClickListener(this);
                Button il67=layoutView.findViewById(R.id.il_67);
                il67.setOnClickListener(this);
                Button il68=layoutView.findViewById(R.id.il_68);
                il68.setOnClickListener(this);
                Button il69=layoutView.findViewById(R.id.il_69);
                il69.setOnClickListener(this);
                Button il70=layoutView.findViewById(R.id.il_70);
                il70.setOnClickListener(this);
                Button il71=layoutView.findViewById(R.id.il_71);
                il71.setOnClickListener(this);
                Button il72=layoutView.findViewById(R.id.il_72);
                il72.setOnClickListener(this);
                Button il73=layoutView.findViewById(R.id.il_73);
                il73.setOnClickListener(this);
                Button il74=layoutView.findViewById(R.id.il_74);
                il74.setOnClickListener(this);
                Button il75=layoutView.findViewById(R.id.il_75);
                il75.setOnClickListener(this);
                Button il76=layoutView.findViewById(R.id.il_76);
                il76.setOnClickListener(this);
                Button il77=layoutView.findViewById(R.id.il_77);
                il77.setOnClickListener(this);
                Button il78=layoutView.findViewById(R.id.il_78);
                il78.setOnClickListener(this);
                Button il79=layoutView.findViewById(R.id.il_79);
                il79.setOnClickListener(this);
                Button il80=layoutView.findViewById(R.id.il_80);
                il80.setOnClickListener(this);
                Button il81=layoutView.findViewById(R.id.il_81);
                il81.setOnClickListener(this);

                CheckBox ilCheckbox=layoutView.findViewById(R.id.checkBox);
                ilCheckbox.setOnCheckedChangeListener((compoundButton, b) -> {
                    if(b){
                        illerList.add(81);
                    }else{
                        illerList.remove(Integer.valueOf(81));
                    }

                });

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

                soruPaylasButon.setOnClickListener((View v) -> {
                        internetConnectionPresenter.internetBaglantiKontrolu();
                        String soruAlaniText=soruAlaniTxt.getText().toString();
                        questionRegistrationPresenter.questionRegistrationValideCredentals(kullanici.getId(),soruAlaniText,etiketList,illerList);

                });

                il01.setOnClickListener((View v) -> {
                        if(!checkIl01Etiket)
                        {
                            illerList.add(1);
                            il01.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il01.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl01Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(1));
                            il01.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il01.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl01Etiket=false;
                        }
                });

                il02.setOnClickListener((View v) -> {
                        if(!checkIl02Etiket)
                        {
                            illerList.add(2);
                            il02.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il02.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl02Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(2));
                            il02.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il02.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl02Etiket=false;
                        }

                });

                il03.setOnClickListener((View v) -> {
                        if(!checkIl03Etiket)
                        {
                            illerList.add(3);
                            il03.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il03.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl03Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(3));
                            il03.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il03.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl03Etiket=false;
                        }
                });

                il04.setOnClickListener((View v) -> {
                        if(!checkIl04Etiket)
                        {
                            illerList.add(4);
                            il04.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il04.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl04Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(4));
                            il04.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il04.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl04Etiket=false;
                        }
                });

                il05.setOnClickListener((View v) -> {
                        if(!checkIl05Etiket)
                        {
                            illerList.add(5);
                            il05.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il05.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl05Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(5));
                            il05.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il05.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl05Etiket=false;
                        }
                });

                il06.setOnClickListener((View v) -> {
                        if(!checkIl06Etiket)
                        {
                            illerList.add(6);
                            il06.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il06.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl06Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(6));
                            il06.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il06.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl06Etiket=false;
                        }
                });

                il07.setOnClickListener((View v) -> {
                        if(!checkIl07Etiket)
                        {
                            illerList.add(7);
                            il07.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il07.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl07Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(7));
                            il07.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il07.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl07Etiket=false;
                        }
                });

                il08.setOnClickListener((View v) -> {
                        if(!checkIl08Etiket)
                        {
                            illerList.add(8);
                            il08.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il08.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl08Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(8));
                            il08.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il08.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl08Etiket=false;
                        }

                });
                il09.setOnClickListener((View v) -> {
                        if(!checkIl09Etiket)
                        {
                            illerList.add(9);
                            il09.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il09.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl09Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(9));
                            il09.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il09.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl09Etiket=false;
                        }

                });

                il10.setOnClickListener((View v) -> {
                        if(!checkIl10Etiket)
                        {
                            illerList.add(10);
                            il10.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il10.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl10Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(10));
                            il10.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il10.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl10Etiket=false;
                        }

                });

                il11.setOnClickListener((View v) -> {
                        if(!checkIl11Etiket)
                        {
                            illerList.add(11);
                            il11.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il11.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl11Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(11));
                            il11.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il11.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl11Etiket=false;
                        }

                });

                il12.setOnClickListener((View v) -> {
                        if(!checkIl12Etiket)
                        {
                            illerList.add(12);
                            il12.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il12.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl12Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(12));
                            il12.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il12.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl12Etiket=false;
                        }

                });
                il13.setOnClickListener((View v) -> {
                        if(!checkIl13Etiket)
                        {
                            illerList.add(13);
                            il13.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il13.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl13Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(13));
                            il13.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il13.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl13Etiket=false;
                        }

                });

                il14.setOnClickListener((View v) -> {
                        if(!checkIl14Etiket)
                        {
                            illerList.add(14);
                            il14.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il14.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl14Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(14));
                            il14.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il14.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl14Etiket=false;
                        }

                });

                il15.setOnClickListener((View v) -> {
                        if(!checkIl15Etiket)
                        {
                            illerList.add(15);
                            il15.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il15.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl15Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(15));
                            il15.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il15.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl15Etiket=false;
                        }

                });

                il16.setOnClickListener((View v) -> {
                        if(!checkIl16Etiket)
                        {
                            illerList.add(16);
                            il16.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il16.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl16Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(16));
                            il16.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il16.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl16Etiket=false;
                        }

                });
                il17.setOnClickListener((View v) -> {
                        if(!checkIl17Etiket)
                        {
                            illerList.add(17);
                            il17.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il17.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl17Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(17));
                            il17.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il17.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl17Etiket=false;
                        }

                });

                il18.setOnClickListener((View v) -> {
                        if(!checkIl18Etiket)
                        {
                            illerList.add(18);
                            il18.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il18.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl18Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(18));
                            il18.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il18.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl18Etiket=false;
                        }

                });

                il19.setOnClickListener((View v) -> {
                        if(!checkIl19Etiket)
                        {
                            illerList.add(19);
                            il19.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il19.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl19Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(19));
                            il19.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il19.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl19Etiket=false;
                        }

                });

                il20.setOnClickListener((View v) -> {
                        if(!checkIl20Etiket)
                        {
                            illerList.add(20);
                            il20.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il20.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl20Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(20));
                            il20.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il20.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl20Etiket=false;
                        }

                });
                il21.setOnClickListener((View v) -> {
                        if(!checkIl21Etiket)
                        {
                            illerList.add(21);
                            il21.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il21.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl21Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(21));
                            il21.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il21.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl21Etiket=false;
                        }

                });

                il22.setOnClickListener((View v) -> {
                        if(!checkIl22Etiket)
                        {
                            illerList.add(22);
                            il22.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il22.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl22Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(22));
                            il22.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il22.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl22Etiket=false;
                        }

                });

                il23.setOnClickListener((View v) -> {
                        if(!checkIl23Etiket)
                        {
                            illerList.add(23);
                            il23.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il23.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl23Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(23));
                            il23.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il23.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl23Etiket=false;
                        }
                });

                il24.setOnClickListener((View v) -> {
                        if(!checkIl24Etiket)
                        {
                            illerList.add(24);
                            il24.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il24.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl24Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(24));
                            il24.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il24.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl24Etiket=false;
                        }

                });
                il25.setOnClickListener((View v) -> {
                        if(!checkIl25Etiket)
                        {
                            illerList.add(25);
                            il25.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il25.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl25Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(25));
                            il25.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il25.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl25Etiket=false;
                        }

                });

                il26.setOnClickListener((View v) -> {
                        if(!checkIl26Etiket)
                        {
                            illerList.add(26);
                            il26.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il26.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl26Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(26));
                            il26.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il26.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl26Etiket=false;
                        }

                });

                il27.setOnClickListener((View v) -> {
                        if(!checkIl27Etiket)
                        {
                            illerList.add(27);
                            il27.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il27.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl27Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(27));
                            il27.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il27.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl27Etiket=false;
                        }
                });

                il28.setOnClickListener((View v) -> {
                        if(!checkIl28Etiket)
                        {
                            illerList.add(28);
                            il28.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il28.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl28Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(28));
                            il28.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il28.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl28Etiket=false;
                        }

                });
                il29.setOnClickListener((View v) -> {
                        if(!checkIl29Etiket)
                        {
                            illerList.add(29);
                            il29.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il29.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl29Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(29));
                            il29.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il29.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl29Etiket=false;
                        }

                });

                il30.setOnClickListener((View v) -> {
                        if(!checkIl30Etiket)
                        {
                            illerList.add(30);
                            il30.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il30.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl30Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(30));
                            il30.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il30.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl30Etiket=false;
                        }

                });

                il31.setOnClickListener((View v) -> {
                        if(!checkIl31Etiket)
                        {
                            illerList.add(31);
                            il31.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il31.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl31Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(31));
                            il31.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il31.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl31Etiket=false;
                        }

                });

                il32.setOnClickListener((View v) -> {
                        if(!checkIl32Etiket)
                        {
                            illerList.add(32);
                            il32.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il32.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl32Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(32));
                            il32.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il32.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl32Etiket=false;
                        }

                });

                il33.setOnClickListener((View v) -> {
                        if(!checkIl33Etiket)
                        {
                            illerList.add(33);
                            il33.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il33.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl33Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(33));
                            il33.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il33.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl33Etiket=false;
                        }

                });

                il34.setOnClickListener((View v) -> {
                        if(!checkIl34Etiket)
                        {
                            illerList.add(34);
                            il34.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il34.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl34Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(34));
                            il34.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il34.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl34Etiket=false;
                        }

                });
                 il35.setOnClickListener((View v) -> {
                        if(!checkIl35Etiket)
                        {
                            illerList.add(35);
                            il35.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il35.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl35Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(35));
                            il35.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il35.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl35Etiket=false;
                        }

                });

                il36.setOnClickListener((View v) -> {
                        if(!checkIl36Etiket)
                        {
                            illerList.add(36);
                            il36.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il36.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl36Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(36));
                            il36.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il36.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl36Etiket=false;
                        }
                });

              il37.setOnClickListener((View v) -> {
                        if(!checkIl37Etiket)
                        {
                            illerList.add(37);
                            il37.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il37.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl37Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(37));
                            il37.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il37.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl37Etiket=false;
                        }
                });

                il38.setOnClickListener((View v) -> {
                        if(!checkIl38Etiket)
                        {
                            illerList.add(38);
                            il38.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il38.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl38Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(38));
                            il38.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il38.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl38Etiket=false;
                        }
                });
                il39.setOnClickListener((View v) -> {
                        if(!checkIl39Etiket)
                        {
                            illerList.add(39);
                            il39.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il39.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl39Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(39));
                            il39.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il39.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl39Etiket=false;
                        }
                });

                il40.setOnClickListener((View v) -> {
                        if(!checkIl40Etiket)
                        {
                            illerList.add(40);
                            il40.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il40.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl40Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(40));
                            il40.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il40.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl40Etiket=false;
                        }

                });

                il41.setOnClickListener((View v) -> {
                        if(!checkIl41Etiket)
                        {
                            illerList.add(41);
                            il41.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il41.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl41Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(41));
                            il41.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il41.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl41Etiket=false;
                        }

                });
                il42.setOnClickListener((View v) -> {
                        if(!checkIl42Etiket)
                        {
                            illerList.add(42);
                            il42.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il42.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl42Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(42));
                            il42.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il42.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl42Etiket=false;
                        }

                });

                il43.setOnClickListener((View v) -> {
                        if(!checkIl43Etiket)
                        {
                            illerList.add(43);
                            il43.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il43.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl43Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(43));
                            il43.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il43.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl43Etiket=false;
                        }

                });

                il44.setOnClickListener((View v) -> {
                        if(!checkIl44Etiket)
                        {
                            illerList.add(44);
                            il44.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il44.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl44Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(44));
                            il44.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il44.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl44Etiket=false;
                        }

                });

                il45.setOnClickListener((View v) -> {
                        if(!checkIl45Etiket)
                        {
                            illerList.add(45);
                            il45.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il45.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl45Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(45));
                            il45.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il45.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl45Etiket=false;
                        }

                });
                il46.setOnClickListener((View v) -> {
                        if(!checkIl46Etiket)
                        {
                            illerList.add(46);
                            il46.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il46.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl46Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(46));
                            il46.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il46.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl46Etiket=false;
                        }

                });

                il47.setOnClickListener((View v) -> {
                        if(!checkIl47Etiket)
                        {
                            illerList.add(47);
                            il47.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il47.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl47Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(47));
                            il47.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il47.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl47Etiket=false;
                        }

                });

                il48.setOnClickListener((View v) -> {
                        if(!checkIl48Etiket)
                        {
                            illerList.add(48);
                            il48.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il48.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl48Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(48));
                            il48.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il48.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl48Etiket=false;
                        }

                });

                il49.setOnClickListener((View v) -> {
                        if(!checkIl49Etiket)
                        {
                            illerList.add(49);
                            il49.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il49.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl49Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(49));
                            il49.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il49.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl49Etiket=false;
                        }

                });
                il50.setOnClickListener((View v) -> {
                        if(!checkIl50Etiket)
                        {
                            illerList.add(50);
                            il50.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il50.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl50Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(50));
                            il50.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il50.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl50Etiket=false;
                        }

                });

                il51.setOnClickListener((View v) -> {
                        if(!checkIl51Etiket)
                        {
                            illerList.add(51);
                            il51.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il51.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl51Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(51));
                            il51.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il51.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl51Etiket=false;
                        }

                });

                il52.setOnClickListener((View v) -> {
                        if(!checkIl52Etiket)
                        {
                            illerList.add(52);
                            il52.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il52.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl52Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(52));
                            il52.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il52.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl52Etiket=false;
                        }
                });
                il53.setOnClickListener((View v) -> {
                        if(!checkIl53Etiket)
                        {
                            illerList.add(53);
                            il53.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il53.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl53Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(53));
                            il53.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il53.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl53Etiket=false;
                        }
                });

                il54.setOnClickListener((View v) -> {
                        if(!checkIl54Etiket)
                        {
                            illerList.add(54);
                            il54.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il54.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl54Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(54));
                            il54.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il54.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl54Etiket=false;
                        }

                });

                il55.setOnClickListener((View v) -> {
                        if(!checkIl55Etiket)
                        {
                            illerList.add(55);
                            il55.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il55.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl55Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(55));
                            il55.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il55.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl55Etiket=false;
                        }
                });

                il56.setOnClickListener((View v) -> {
                        if(!checkIl56Etiket)
                        {
                            illerList.add(56);
                            il56.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il56.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl56Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(56));
                            il56.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il56.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl56Etiket=false;
                        }
                });
                il57.setOnClickListener((View v) -> {
                        if(!checkIl57Etiket)
                        {
                            illerList.add(57);
                            il57.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il57.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl57Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(57));
                            il57.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il57.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl57Etiket=false;
                        }
                });

                il58.setOnClickListener((View v) -> {
                        if(!checkIl58Etiket)
                        {
                            illerList.add(58);
                            il58.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il58.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl58Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(58));
                            il58.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il58.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl58Etiket=false;
                        }
                });

                il59.setOnClickListener((View v) -> {
                        if(!checkIl59Etiket)
                        {
                            illerList.add(59);
                            il59.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il59.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl59Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(59));
                            il59.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il59.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl59Etiket=false;
                        }
                });

                il60.setOnClickListener((View v) -> {
                        if(!checkIl60Etiket)
                        {
                            illerList.add(60);
                            il60.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il60.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl60Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(60));
                            il60.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il60.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl60Etiket=false;
                        }
                });
                il61.setOnClickListener((View v) -> {
                        if(!checkIl61Etiket)
                        {
                            illerList.add(61);
                            il61.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il61.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl61Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(61));
                            il61.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il61.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl61Etiket=false;
                        }
                });

                il62.setOnClickListener((View v) -> {
                        if(!checkIl62Etiket)
                        {
                            illerList.add(62);
                            il62.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il62.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl62Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(62));
                            il62.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il62.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl62Etiket=false;
                        }
                });

                il63.setOnClickListener((View v) -> {
                        if(!checkIl63Etiket)
                        {
                            illerList.add(63);
                            il63.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il63.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl63Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(63));
                            il63.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il63.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl63Etiket=false;
                        }
                });

                il64.setOnClickListener((View v) -> {
                        if(!checkIl64Etiket)
                        {
                            illerList.add(64);
                            il64.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il64.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl64Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(64));
                            il64.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il64.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl64Etiket=false;
                        }
                });

                il65.setOnClickListener((View v) -> {
                        if(!checkIl65Etiket)
                        {
                            illerList.add(65);
                            il65.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il65.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl65Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(65));
                            il65.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il65.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl65Etiket=false;
                        }
                });
                il66.setOnClickListener((View v) -> {
                        if(!checkIl66Etiket)
                        {
                            illerList.add(66);
                            il66.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il66.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl66Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(66));
                            il66.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il66.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl66Etiket=false;
                        }
                });

                il67.setOnClickListener((View v) -> {
                        if(!checkIl67Etiket)
                        {
                            illerList.add(67);
                            il67.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il67.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl67Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(67));
                            il67.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il67.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl67Etiket=false;
                        }
                });

                il68.setOnClickListener((View v) -> {
                        if(!checkIl68Etiket)
                        {
                            illerList.add(68);
                            il68.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il68.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl68Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(68));
                            il68.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il68.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl68Etiket=false;
                        }
                });
                il69.setOnClickListener((View v) -> {
                        if(!checkIl69Etiket)
                        {
                            illerList.add(69);
                            il69.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il69.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl69Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(69));
                            il69.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il69.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl69Etiket=false;
                        }
                });

                il70.setOnClickListener((View v) -> {
                        if(!checkIl70Etiket)
                        {
                            illerList.add(70);
                            il70.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il70.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl70Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(70));
                            il70.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il70.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl70Etiket=false;
                        }
                });

                il71.setOnClickListener((View v) -> {
                        if(!checkIl71Etiket)
                        {
                            illerList.add(71);
                            il71.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il71.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl71Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(71));
                            il71.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il71.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl71Etiket=false;
                        }
                });

                il72.setOnClickListener((View v) -> {
                        if(!checkIl72Etiket)
                        {
                            illerList.add(72);
                            il72.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il72.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl72Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(72));
                            il72.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il72.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl72Etiket=false;
                        }
                });
                il73.setOnClickListener((View v) -> {
                        if(!checkIl73Etiket)
                        {
                            illerList.add(73);
                            il73.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il73.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl73Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(73));
                            il73.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il73.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl73Etiket=false;
                        }
                });
                 il74.setOnClickListener((View v) -> {
                        if(!checkIl74Etiket)
                        {
                            illerList.add(74);
                            il74.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il74.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl74Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(74));
                            il74.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il74.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl74Etiket=false;
                        }
                });

                il75.setOnClickListener((View v) -> {
                        if(!checkIl75Etiket)
                        {
                            illerList.add(75);
                            il75.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il75.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl75Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(75));
                            il75.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il75.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl75Etiket=false;
                        }
                });

                il76.setOnClickListener((View v) -> {
                        if(!checkIl76Etiket)
                        {
                            illerList.add(76);
                            il76.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il76.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl76Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(76));
                            il76.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il76.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl76Etiket=false;
                        }
                });

                il77.setOnClickListener((View v) -> {
                        if(!checkIl77Etiket)
                        {
                            illerList.add(77);
                            il77.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il77.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl77Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(77));
                            il77.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il77.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl77Etiket=false;
                        }
                });
                il78.setOnClickListener((View v) -> {
                        if(!checkIl78Etiket)
                        {
                            illerList.add(78);
                            il78.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il78.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl78Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(78));
                            il78.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il78.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl78Etiket=false;
                        }
                });

                il79.setOnClickListener((View v) -> {
                        if(!checkIl79Etiket)
                        {
                            illerList.add(79);
                            il79.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il79.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl79Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(79));
                            il79.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il79.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl79Etiket=false;
                        }
                });

                il80.setOnClickListener((View v) -> {
                        if(!checkIl80Etiket)
                        {
                            illerList.add(80);
                            il80.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il80.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl80Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(80));
                            il80.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il80.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl80Etiket=false;
                        }
                });

                il81.setOnClickListener((View v) -> {{
                        if(!checkIl81Etiket)
                        {
                            illerList.add(81);
                            il81.setBackground(getDrawable(R.drawable.baslarkenilonaybuton));
                            il81.setTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkIl81Etiket = true;
                        }
                        else
                        {
                            illerList.remove(Integer.valueOf(81));
                            il81.setBackground(getDrawable(R.drawable.baslarkenilbuton));
                            il81.setTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkIl81Etiket=false;
                        }
                    }
                });


                sporButon.setOnClickListener((View v) -> {
                        if(!checkSporEtiket) {
                            etiketList.add(3);
                            sporButon.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            sporButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkSporEtiket = true;
                        }else{
                            etiketList.remove(Integer.valueOf(3));
                            sporButon.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            sporButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkSporEtiket=false;
                        }

                });
                yazilimButon.setOnClickListener((View v) -> {
                        if(!checkYazilimEtiket) {
                            etiketList.add(7);
                            yazilimButon.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            yazilimButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkYazilimEtiket = true;
                        }else{
                            etiketList.remove(Integer.valueOf(7));
                            yazilimButon.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            yazilimButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkYazilimEtiket=false;
                        }

                });


                teknoEtiketBtn.setOnClickListener((View v) -> {
                        if(!checkTeknoEtiket) {
                            etiketList.add(10);
                            teknoEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            teknoEtiketBtn.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkTeknoEtiket = true;
                        }else{
                            etiketList.remove(Integer.valueOf(10));
                            teknoEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            teknoEtiketBtn.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkTeknoEtiket=false;
                        }

                });

                saglikEtiketBtn.setOnClickListener((View v) -> {
                        if(!checkSaglikEtiket) {
                            etiketList.add(12);
                            saglikEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            saglikEtiketBtn.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkSaglikEtiket = true;

                        }else{
                            etiketList.remove(Integer.valueOf(12));
                            saglikEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            saglikEtiketBtn.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkSaglikEtiket=false;
                        }

                });

                geziButon.setOnClickListener((View v) -> {
                        if(!checkGeziEtiket) {
                            etiketList.add(8);
                            geziButon.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            geziButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkGeziEtiket = true;
                        }else{
                            etiketList.remove(Integer.valueOf(8));
                            geziButon.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            geziButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkGeziEtiket=false;
                        }

                });

                alisverisButon.setOnClickListener((View v) -> {
                        if(!checkAlisverisEtiket) {
                            etiketList.add(5);
                            alisverisButon.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            alisverisButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkAlisverisEtiket = true;
                        }else{
                            etiketList.remove(Integer.valueOf(5));
                            alisverisButon.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            alisverisButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkAlisverisEtiket=false;
                        }

                });

                filmDiziButon.setOnClickListener((View v) -> {
                        if(!checkSanatEtiket) {
                            etiketList.add(9);
                            filmDiziButon.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            filmDiziButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkSanatEtiket = true;
                        }else{
                            etiketList.remove(Integer.valueOf(9));
                            filmDiziButon.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            filmDiziButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkSanatEtiket=false;
                        }

                });

                yemekButon.setOnClickListener((View v) -> {
                        if(!checkYemekEtiket) {
                            etiketList.add(2);
                            yemekButon.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            yemekButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkYemekEtiket = true;
                        }else{
                            etiketList.remove(Integer.valueOf(2));
                            yemekButon.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            yemekButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkYemekEtiket=false;
                        }

                });

                adresButon.setOnClickListener((View v) -> {
                        if(!checkAdresEtiket) {
                            etiketList.add(1);
                            adresButon.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            adresButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkAdresEtiket = true;
                        }else{
                            etiketList.remove(Integer.valueOf(1));
                            adresButon.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            adresButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkAdresEtiket=false;
                        }

                });

                tatilButon.setOnClickListener((View v) -> {
                        if(!checkTatilEtiket) {
                            etiketList.add(4);
                            tatilButon.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            tatilButon.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkTatilEtiket = true;
                        }else{
                            etiketList.remove(Integer.valueOf(4));
                            tatilButon.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            tatilButon.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkTatilEtiket=false;
                        }

                });
                oyunEtiketBtn.setOnClickListener((View v) -> {
                        if(!checkOyunEtiket) {
                            etiketList.add(11);
                            oyunEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            oyunEtiketBtn.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkOyunEtiket = true;
                        }else{
                            etiketList.remove(Integer.valueOf(11));
                            oyunEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            oyunEtiketBtn.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkOyunEtiket=false;

                        }
                });
                modaEtiketBtn.setOnClickListener((View v) -> {
                        if(!checkModaEtiket) {
                            etiketList.add(16);
                            modaEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            modaEtiketBtn.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkModaEtiket = true;
                        }else{
                            etiketList.remove(Integer.valueOf(16));
                            modaEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            modaEtiketBtn.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkModaEtiket=false;

                        }
                });
                otoEtiketBtn.setOnClickListener((View v) -> {
                        if(!checkOtoEtiket) {
                            etiketList.add(16);
                            otoEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            otoEtiketBtn.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkOtoEtiket = true;
                        }else{
                            etiketList.remove(Integer.valueOf(16));
                            otoEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            otoEtiketBtn.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkOtoEtiket=false;


                        }
                });
                tarihEtiketBtn.setOnClickListener((View v) -> {
                        if(!checkTarihEtiket) {
                            etiketList.add(15);
                            tarihEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                            tarihEtiketBtn.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                            checkTarihEtiket = true;
                        }else{
                            etiketList.remove(Integer.valueOf(15));
                            tarihEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                            tarihEtiketBtn.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                            checkTarihEtiket=false;

                        }
                });

                egitimEtiketBtn.setOnClickListener((View v) -> {
                    if(!checkEgitimEtiket) {
                        etiketList.add(14);
                        egitimEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                        egitimEtiketBtn.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                        checkEgitimEtiket = true;
                    }else{
                        etiketList.remove(Integer.valueOf(14));
                        egitimEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                        egitimEtiketBtn.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                        checkEgitimEtiket=false;
                    }
                });
                muzikEtiketBtn.setOnClickListener((View v) -> {
                    if(!checkMuzikEtiket) {
                        etiketList.add(13);
                        muzikEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_arkaplan));
                        muzikEtiketBtn.setHintTextColor(getResources().getColor(R.color.profilSekmeBeyaz));
                        checkMuzikEtiket = true;
                    }else{
                        etiketList.remove(Integer.valueOf(13));
                        muzikEtiketBtn.setBackground(getDrawable(R.drawable.soru_etiket_buton_beyaz));
                        muzikEtiketBtn.setHintTextColor(getResources().getColor(R.color.uygulamaMavisi));
                        checkMuzikEtiket=false;
                    }
                });
                alertDialog.show();


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
            if(Objects.equals(entry.getName(), "AramaAsama-4") || Objects.equals(entry.getName(), "AramaAsamaTık-2")){
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
        if(Objects.equals(entry.getName(), "Fragment")){
           getSupportFragmentManager().popBackStack("Fragment", POP_BACK_STACK_INCLUSIVE);
           bottomNavigationView.getMenu().getItem(0).setChecked(true);
        }else if(Objects.equals(entry.getName(), "AnaSayfaFragment")){
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
