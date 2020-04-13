package com.example.mvpornek.Fragment.ProfilTabFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvpornek.Activity.Adapter.QuestionAdapterActivity;
import com.example.mvpornek.R;

public class BegendiklerimFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView begendiklerimRecyclerView;

    String kullanici_isimleri[], yorum_sayisi[], sorular[], etiket[];
    int kullaniciResmi[] ={R.drawable.man,R.drawable.man1,R.drawable.ceo};


    private String mParam1;
    private String mParam2;

    public BegendiklerimFragment() {

    }


    public static BegendiklerimFragment newInstance(String param1, String param2) {
        BegendiklerimFragment fragment = new BegendiklerimFragment();
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
        View view = inflater.inflate(R.layout.fragment_begendiklerim, container, false);
        begendiklerimRecyclerView=(RecyclerView) view.findViewById(R.id.profilBegendiklerimRecyclerView);
        QuestionAdapterActivity questionAdapterActivity=new QuestionAdapterActivity(getActivity(),kullanici_isimleri,yorum_sayisi,sorular,etiket,kullaniciResmi);
        begendiklerimRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        begendiklerimRecyclerView.setAdapter(questionAdapterActivity);
        return view;
    }
}
