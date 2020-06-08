package com.example.mvpornek.Fragment.Search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mvpornek.Activity.HomeActivity;
import com.example.mvpornek.R;

public class AramaIcerikFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    SearchView searchView;
    ImageView imageView;
    ListView listView;
    Button geriButon;
    ArrayAdapter<String> arrayAdapter;

    HomeActivity homeActivity= new HomeActivity();

    String [] aranan_terim={"Gökhan","Akbaba","Araba","Ev","Bina","CNN Türk"};
    private String mParam1;
    private String mParam2;

    public AramaIcerikFragment() {

    }

    public static AramaIcerikFragment newInstance(String param1, String param2) {
        AramaIcerikFragment fragment = new AramaIcerikFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getActivity().findViewById(R.id.anasayfa_nav_view).setVisibility(View.GONE);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.arama_sayfasi_icerik, container, false);
        searchView=(SearchView) view.findViewById(R.id.searchView_page);
        listView=(ListView) view.findViewById(R.id.searchPageListView);

        geriButon=(Button) view.findViewById(R.id.aramaSayfasiIcerikGeriBtn);
        geriButon.setOnClickListener(this);
        arrayAdapter=new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1,aranan_terim);
        listView.setAdapter(arrayAdapter);
        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.aramaSayfasiIcerikGeriBtn:
                getActivity().getSupportFragmentManager().popBackStack();
            break;
        }
    }

    private boolean loadFragment(Fragment fragment,String fragmentTag)
    {

        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(fragmentTag)
                    .replace(R.id.anaSayfaFrameLayout, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}
