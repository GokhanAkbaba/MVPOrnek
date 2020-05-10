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

import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class HomeActivity extends FragmentActivity implements View.OnClickListener {



    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ana_sayfa);
        loadFragment(new HomeFragment(),"AnaSayfa");


        bottomNavigationView=findViewById(R.id.anasayfa_nav_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment=null;
                switch (menuItem.getItemId())
                {
                    case R.id.anaSayfaItem:
                        fragment=new HomeFragment();
                        loadFragment(fragment,"AnaSayfaFragment");
                        break;
                    case R.id.profilItem:
                        fragment=new ProfilFragment();
                        loadFragment(fragment,"ProfilFragment");
                        break;
                    case R.id.bildirimItem:
                        fragment=new BildirimlerFragment();
                        loadFragment(fragment,"BildirimFragment");
                        break;
                    case R.id.aramaItem:
                        fragment=new SearchFragment();
                        loadFragment(fragment,"AramaFragment");
                        break;
                    case R.id.ayarlarItem:
                        fragment=new SettingsFragment();
                        loadFragment(fragment,"AyarlarFragment");
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {


    }

    private boolean loadFragment(Fragment fragment,String fragmentTag)
    {
        System.out.println("-----"+fragmentTag);
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(fragmentTag)
                    .replace(R.id.anaSayfaFrameLayout,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStack("AramaFragment", POP_BACK_STACK_INCLUSIVE);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        //getHomeFragment();
    }
}
