package com.example.mvpornek.Fragment.NavBarFragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mvpornek.Activity.Adapter.QuestionAdapterActivity;
import com.example.mvpornek.Activity.HomeActivity;
import com.example.mvpornek.Fragment.CommentBottomDialogFragment;
import com.example.mvpornek.Model.Kullanıcı.KullaniciKayit.Kullanici;
import com.example.mvpornek.Model.Kullanıcı.QuestionModel;
import com.example.mvpornek.Model.ModelGiris.InternetConnectionInteractorImpl;
import com.example.mvpornek.Presenter.InternetConnectionPresenterImpl;
import com.example.mvpornek.Presenter.QuestionPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.InternetConnectionView;
import com.example.mvpornek.View.QuestionView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

public class HomeFragment extends BottomSheetDialogFragment implements View.OnClickListener, QuestionView, InternetConnectionView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ImageButton alisverisButon,tatilButon,adresButon,sporButon,yemekButon,sanatButon;
    Boolean checkYemekEtiket =false,checkAdresEtiket = false,checkSporEtiket = false,
            checkTatilEtiket = false,checkAlisverisEtiket = false,checkSanatEtiket = false;

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerViewSoruAlani;
    List<QuestionModel> questionModels;
    QuestionAdapterActivity questionAdapterActivity;
    QuestionPresenterImpl questionPresenter;
    QuestionAdapterActivity.ItemClickListener itemClickListener;

    Kullanici kullanici;

    InternetConnectionPresenterImpl internetConnectionPresenter;
    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        kullanici= SharedPrefManager.getInstance(getActivity()).getKullanici();
        questionPresenter = new QuestionPresenterImpl(this);
        questionPresenter.loadData(kullanici.getId());
        internetConnectionPresenter=new InternetConnectionPresenterImpl(this,new InternetConnectionInteractorImpl(getActivity()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_anasayfa, container, false);
        alisverisButon=view.findViewById(R.id.anasayfa_alisveris_btn);
        alisverisButon.setOnClickListener(this);
        yemekButon=view.findViewById(R.id.anasayfa_yemek_btn);
        yemekButon.setOnClickListener(this);
        tatilButon=view.findViewById(R.id.anasayfa_tatil_btn);
        tatilButon.setOnClickListener(this);
        adresButon=view.findViewById(R.id.anasayfa_adres_btn);
        adresButon.setOnClickListener(this);
        sporButon=view.findViewById(R.id.anasayfa_spor_btn);
        sporButon.setOnClickListener(this);
        sanatButon=view.findViewById(R.id.anasayfa_sanat_btn);
        sanatButon.setOnClickListener(this);
        swipeRefreshLayout=view.findViewById(R.id.swiperefreshAnasayfa);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                internetConnectionPresenter.internetBaglantiKontrolu();
                questionPresenter.loadData(kullanici.getId());

            }
        });

        itemClickListener =((vw,position)-> {
            int soruId=questionModels.get(position).getId();
            showBottomSheet(soruId);
        });

        recyclerViewSoruAlani=(RecyclerView) view.findViewById(R.id.recyclerViewSoruAlani);
        recyclerViewSoruAlani.setAdapter(questionAdapterActivity);
        recyclerViewSoruAlani.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }
    public void showBottomSheet(int soruId) {
        CommentBottomDialogFragment addPhotoBottomDialogFragment =
                CommentBottomDialogFragment.newInstance(soruId);
        addPhotoBottomDialogFragment.show(getActivity().getSupportFragmentManager(),
                CommentBottomDialogFragment.TAG);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

            case R.id.bottomSheetCloseBtn:

                break;
            case R.id.anasayfa_alisveris_btn:
                if(checkAlisverisEtiket == false)
                {
                    alisverisButon.setBackground(getDrawable(getActivity(),R.drawable.baslarkenilonaybuton));
                    alisverisButon.setImageResource(R.mipmap.alisveris_icon_beyaz);
                    checkAlisverisEtiket = true;
                }
                else
                {
                    alisverisButon.setBackground(getDrawable(getActivity(),R.drawable.baslarkenilbuton));
                    alisverisButon.setImageResource(R.mipmap.alisveris_icon);
                    checkAlisverisEtiket=false;
                }
                break;
            case R.id.anasayfa_yemek_btn:
                if(checkYemekEtiket == false)
                {
                    yemekButon.setBackground(getDrawable(getActivity(),R.drawable.baslarkenilonaybuton));
                    yemekButon.setImageResource(R.mipmap.yemek_icon_beyaz);
                    checkYemekEtiket = true;
                }
                else
                {
                    yemekButon.setBackground(getDrawable(getActivity(),R.drawable.baslarkenilbuton));
                    yemekButon.setImageResource(R.mipmap.yemek_icon);
                    checkYemekEtiket=false;
                }
                break;
            case R.id.anasayfa_tatil_btn:
                if(checkTatilEtiket == false)
                {
                    tatilButon.setBackground(getDrawable(getActivity(),R.drawable.baslarkenilonaybuton));
                    tatilButon.setImageResource(R.mipmap.tatil_icon_beyaz);
                    checkTatilEtiket = true;
                }
                else
                {
                    tatilButon.setBackground(getDrawable(getActivity(),R.drawable.baslarkenilbuton));
                    tatilButon.setImageResource(R.mipmap.tatil_icon);
                    checkTatilEtiket=false;
                }
                break;
            case R.id.anasayfa_adres_btn:
                if(checkAdresEtiket == false)
                {
                    adresButon.setBackground(getDrawable(getActivity(),R.drawable.baslarkenilonaybuton));
                    adresButon.setImageResource(R.mipmap.adres_icon_beyaz);
                    checkAdresEtiket = true;
                }
                else
                {
                    adresButon.setBackground(getDrawable(getActivity(),R.drawable.baslarkenilbuton));
                    adresButon.setImageResource(R.mipmap.ic_launcher_adres);
                    checkAdresEtiket=false;
                }
                break;
            case R.id.anasayfa_spor_btn:
                if(checkSporEtiket == false)
                {
                    sporButon.setBackground(getDrawable(getActivity(),R.drawable.baslarkenilonaybuton));
                    sporButon.setImageResource(R.mipmap.spor_icon_beyaz);
                    checkSporEtiket = true;
                }
                else
                {
                    sporButon.setBackground(getDrawable(getActivity(),R.drawable.baslarkenilbuton));
                    sporButon.setImageResource(R.mipmap.spor_icon);
                    checkSporEtiket=false;
                }
                break;
            case R.id.anasayfa_sanat_btn:
                if(checkSanatEtiket == false)
                {
                    sanatButon.setBackground(getDrawable(getActivity(),R.drawable.baslarkenilonaybuton));
                    sanatButon.setImageResource(R.mipmap.sanat_icon_beyaz);
                    checkSanatEtiket = true;
                }
                else
                {
                    sanatButon.setBackground(getDrawable(getActivity(),R.drawable.baslarkenilbuton));
                    sanatButon.setImageResource(R.mipmap.film_icon);
                    checkSanatEtiket=false;
                }
                break;

        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_items,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onGetResult(List<QuestionModel> data) {
        questionAdapterActivity= new QuestionAdapterActivity(data,getActivity(),itemClickListener);
        questionAdapterActivity.notifyDataSetChanged();
        recyclerViewSoruAlani.setAdapter(questionAdapterActivity);
        questionModels=data;

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void showLoading() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void internetBaglantiHatasi() {
        Toast.makeText(getActivity(),"İnternet Bağlantınızı Kontrol Ediniz",Toast.LENGTH_LONG).show();
    }

    @Override
    public void internetBaglantisi() {

    }

    private boolean loadFragment(Fragment fragment,String fragmentTag) {

        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(fragmentTag)
                    .add(R.id.anaSayfaFrameLayout, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
