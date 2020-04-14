package com.example.mvpornek.Fragment.NavBarFragment;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.mvpornek.Activity.Adapter.SearchAdapter;
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

        return view;
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void searchQuestionSelected(SearchModel searchModel) {

        startActivity(new Intent(getActivity(), QuestionDetailActivity.class).putExtra("data",searchModel));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_items, menu);
        MenuItem searchItem = menu.findItem(R.id.searchBar);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);

                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);

                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.searchBar:
                // Not implemented here
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }
}
