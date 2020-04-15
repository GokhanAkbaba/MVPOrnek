package com.example.mvpornek.Fragment.NavBarFragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import com.example.mvpornek.Activity.Adapter.SearchAdapter;
import com.example.mvpornek.Activity.DenemeActivity;
import com.example.mvpornek.Activity.QuestionDetailActivity;
import com.example.mvpornek.Model.Kullanıcı.SearchModel;
import com.example.mvpornek.R;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements View.OnClickListener,SearchAdapter.SearchQuestionSelected {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView aramaSayfasiRecyclerView;
     List<SearchModel>searchModelList=new ArrayList<>();
     SearchAdapter searchAdapter;
    private SearchView.OnQueryTextListener queryTextListener;
    private SearchView searchView = null;
     Toolbar toolbar;

    private String mParam1;
    private String mParam2;

    public SearchFragment() {

    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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

        searchModelList.add(new SearchModel("Gökhan Akbaba","3 haftalık izine çıktım. Akdeniz tarafında fiyat performans bakımından güzel oteller hangi illerde.","153","#Tatil#Adres",R.drawable.man));
        searchModelList.add(new SearchModel("Aykut Erdal","Gaziantepde güzel baklava yiyebileceğim yerler neresi?","263","#Yemek",R.drawable.man1));
        searchModelList.add(new SearchModel("Mustafa Akbel","Trabzondaki en iyi öğrenci yurdu nerde?","300","#Adres",R.drawable.ceo));
        searchModelList.add(new SearchModel("Aykut Erdal","Gaziantepde güzel baklava yiyebileceğim yerler neresi?","263","#Yemek",R.drawable.man1));
        searchModelList.add(new SearchModel("Mustafa Akbel","Trabzondaki en iyi öğrenci yurdu nerde?","300","#Adres",R.drawable.ceo));
        searchAdapter=new SearchAdapter(searchModelList,this);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.arama_sayfasi, container, false);
        aramaSayfasiRecyclerView=(RecyclerView) view.findViewById(R.id.arama_sayfasi_recyclerView);
        toolbar=(Toolbar) view.findViewById(R.id.searchTabBar);
        aramaSayfasiRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        aramaSayfasiRecyclerView.setAdapter(searchAdapter);
        toolbar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity(), DenemeActivity.class));
    }

    @Override
    public void searchQuestionSelected(SearchModel searchModel) {
        startActivity(new Intent(getActivity(), QuestionDetailActivity.class).putExtra("data",searchModel));
    }


}
