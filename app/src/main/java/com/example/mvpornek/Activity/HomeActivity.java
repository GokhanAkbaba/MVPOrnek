package com.example.mvpornek.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.mvpornek.Fragment.NavBarFragment.BildirimlerFragment;
import com.example.mvpornek.Fragment.NavBarFragment.HomeFragment;
import com.example.mvpornek.Fragment.NavBarFragment.SearchFragment;
import com.example.mvpornek.Fragment.NavBarFragment.SettingsFragment;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends FragmentActivity implements View.OnClickListener {



    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ana_sayfa);
        getHomeFragment();

        bottomNavigationView=findViewById(R.id.anasayfa_nav_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.anaSayfaItem:
                        getHomeFragment();
                        break;
                    case R.id.profilItem:
                        getProfilFragment();
                        break;
                    case R.id.bildirimItem:
                        getBildirimlerFragment();
                        break;
                    case R.id.aramaItem:
                        getSearchFragment();
                        break;
                    case R.id.ayarlarItem:
                        getSettingsFragment();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {


    }
    public void  getHomeFragment(){
        HomeFragment homeFragment=new HomeFragment();
        callFragment(homeFragment);

    }

    public void getSettingsFragment()
    {
        SettingsFragment settingsFragment=new SettingsFragment();
        callFragment(settingsFragment);
    }
    public void getSearchFragment()
    {
        SearchFragment searchFragment=new SearchFragment();
        callFragment(searchFragment);
    }
    public void getBildirimlerFragment()
    {
        BildirimlerFragment bildirimlerFragment = new BildirimlerFragment();
        callFragment(bildirimlerFragment);
    }
    public void getProfilFragment()
    {
        ProfilFragment profilFragment = new ProfilFragment();
        callFragment(profilFragment);
    }
    public void callFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.anaSayfaFrameLayout,fragment);
        fragmentTransaction.commit();
    }

}
