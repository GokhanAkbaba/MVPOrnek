package com.example.mvpornek.Fragment.NavBarFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvpornek.Activity.QuestionAdapterActivity;
import com.example.mvpornek.R;

public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String kullanici_isimleri[], yorum_sayisi[], sorular[], etiket[];
    int kullaniciResmi[] ={R.drawable.man,R.drawable.man1,R.drawable.ceo};
    RecyclerView recyclerViewSoruAlani;

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
        kullanici_isimleri=getResources().getStringArray(R.array.kullanici_isimleri);
        yorum_sayisi=getResources().getStringArray(R.array.yorum_sayisi);
        sorular=getResources().getStringArray(R.array.sorular);
        etiket=getResources().getStringArray(R.array.etiket);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_anasayfa, container, false);
        recyclerViewSoruAlani=(RecyclerView) view.findViewById(R.id.recyclerViewSoruAlani);
        QuestionAdapterActivity questionAdapterActivity=new QuestionAdapterActivity(getActivity(),kullanici_isimleri,yorum_sayisi,sorular,etiket,kullaniciResmi);
        recyclerViewSoruAlani.setAdapter(questionAdapterActivity);
        recyclerViewSoruAlani.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}
