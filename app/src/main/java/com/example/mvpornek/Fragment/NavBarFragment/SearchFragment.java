package com.example.mvpornek.Fragment.NavBarFragment;


import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;

import android.support.v4.media.RatingCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;

import com.example.mvpornek.Activity.Adapter.SearchAdapter;
import com.example.mvpornek.Activity.DenemeActivity;
import com.example.mvpornek.Activity.QuestionDetailActivity;
import com.example.mvpornek.Fragment.AramaIcerikFragment;
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
        setHasOptionsMenu(true);
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
        //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.arama_sayfasi, container, false);
        aramaSayfasiRecyclerView=(RecyclerView) view.findViewById(R.id.arama_sayfasi_recyclerView);

        aramaSayfasiRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        aramaSayfasiRecyclerView.setAdapter(searchAdapter);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_items,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        searchView= (SearchView) menuItem.getActionView();
        searchView.setBackgroundResource(R.drawable.input_field);
        searchView.setMinimumHeight(Integer.MAX_VALUE);
        searchView.onActionViewExpanded();
        searchView.setQueryHint(getResources().getString(R.string.aramaCubuguTxt));
        searchView.clearFocus();
        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("Gd","Gökhan");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("Gd","Gökhan");
                return false;
            }
        });*/
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.e("Gd","Gökhan");
                searchView.clearFocus();
                searchView.setVisibility(View.INVISIBLE);
                AramaIcerikFragment bildirimlerFragment = new AramaIcerikFragment();
                FragmentTransaction fragmentTransactionBildirim=getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransactionBildirim.replace(R.id.anaSayfaFrameLayout,bildirimlerFragment);
                fragmentTransactionBildirim.commit();
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id == R.id.action_search)
        {

        }
        return super.onOptionsItemSelected(item);
    }
}
