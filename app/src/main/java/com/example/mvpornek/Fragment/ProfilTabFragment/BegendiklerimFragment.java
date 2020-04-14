package com.example.mvpornek.Fragment.ProfilTabFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvpornek.Activity.Adapter.QuestionAdapterActivity;
import com.example.mvpornek.Model.Kullanıcı.QuestionModel;
import com.example.mvpornek.R;

import java.util.ArrayList;
import java.util.List;

public class BegendiklerimFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView begendiklerimRecyclerView;

    List<QuestionModel> questionModels=new ArrayList<>();
    QuestionAdapterActivity questionAdapterActivity;
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
        questionModels.add(new QuestionModel("Gökhan Akbaba","3 haftalık izine çıktım. Akdeniz tarafında fiyat performans bakımından güzel oteller hangi illerde.","153","#Tatil#Adres",R.drawable.man));
        questionModels.add(new QuestionModel("Aykut Erdal","Gaziantepde güzel baklava yiyebileceğim yerler neresi?","263","#Yemek",R.drawable.man1));
        questionModels.add(new QuestionModel("Mustafa Akbel","Trabzondaki en iyi öğrenci yurdu nerde?","300","#Adres",R.drawable.ceo));
        questionAdapterActivity=new QuestionAdapterActivity(questionModels);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_begendiklerim, container, false);
        begendiklerimRecyclerView=(RecyclerView) view.findViewById(R.id.profilBegendiklerimRecyclerView);
        begendiklerimRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        begendiklerimRecyclerView.setAdapter(questionAdapterActivity);
        return view;
    }
}
