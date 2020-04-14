package com.example.mvpornek.Fragment.NavBarFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.mvpornek.Activity.Adapter.QuestionAdapterActivity;
import com.example.mvpornek.Model.Kullanıcı.QuestionModel;
import com.example.mvpornek.R;

import java.util.ArrayList;
import java.util.List;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ImageButton alisverisButon,tatilButon,adresButon,sporButon,yemekButon,sanatButon;
    Boolean checkYemekEtiket =false,checkAdresEtiket = false,checkSporEtiket = false,
            checkTatilEtiket = false,checkAlisverisEtiket = false,checkSanatEtiket = false;
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerViewSoruAlani;
    List<QuestionModel> questionModels=new ArrayList<>();
    QuestionAdapterActivity questionAdapterActivity;

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
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        questionModels.add(new QuestionModel("Gökhan Akbaba","3 haftalık izine çıktım. Akdeniz tarafında fiyat performans bakımından güzel oteller hangi illerde.","153","#Tatil#Adres",R.drawable.man));
        questionModels.add(new QuestionModel("Aykut Erdal","Gaziantepde güzel baklava yiyebileceğim yerler neresi?","263","#Yemek",R.drawable.man1));
        questionModels.add(new QuestionModel("Mustafa Akbel","Trabzondaki en iyi öğrenci yurdu nerde?","300","#Adres",R.drawable.ceo));
        questionAdapterActivity=new QuestionAdapterActivity(questionModels);

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
       recyclerViewSoruAlani=(RecyclerView) view.findViewById(R.id.recyclerViewSoruAlani);
        recyclerViewSoruAlani.setAdapter(questionAdapterActivity);
        recyclerViewSoruAlani.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
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
            case R.id.anasayfa_soru_gonder:
                break;
        }
    }
}
