package com.example.mvpornek.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.mvpornek.Fragment.NavBarFragment.BildirimlerFragment;
import com.example.mvpornek.Fragment.NavBarFragment.HomeFragment;
import com.example.mvpornek.Fragment.NavBarFragment.SearchFragment;
import com.example.mvpornek.Fragment.NavBarFragment.SettingsFragment;
import com.example.mvpornek.Fragment.Search.AramaIcerikFragment;
import com.example.mvpornek.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class HomeActivity extends FragmentActivity implements View.OnClickListener,FragmentManager.OnBackStackChangedListener {



    BottomNavigationView bottomNavigationView;
    int item ;
    int sayac=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ana_sayfa);
        loadFragment(new HomeFragment(),"AnaSayfaFragment");
        getSupportFragmentManager().addOnBackStackChangedListener(this);


        bottomNavigationView=findViewById(R.id.anasayfa_nav_view);


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


    }

    private boolean loadFragment(Fragment fragment,String fragmentTag)
    {
                /*int count = getSupportFragmentManager().getBackStackEntryCount();
                int last = getSupportFragmentManager().getBackStackEntryCount()-1;
                 FragmentManager.BackStackEntry entry = getSupportFragmentManager().getBackStackEntryAt(last);
                System.out.println("Count"+last+"NAME"+entry.getName());
                    if (TextUtils.equals(entry.getName(), fragmentTag)) {

                }*/
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
}
