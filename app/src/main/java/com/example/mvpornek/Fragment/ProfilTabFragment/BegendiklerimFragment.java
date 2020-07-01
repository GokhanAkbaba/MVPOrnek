package com.example.mvpornek.Fragment.ProfilTabFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mvpornek.Adapter.QuestionAdapterActivity;
import com.example.mvpornek.AdapterProfilQuestion.AdapterProfilQuestion;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Models.QuestionModel;
import com.example.mvpornek.Presenter.ProfilQuestionPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.ProfilQuestionView;
import java.util.List;

public class BegendiklerimFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    RecyclerView profilBegendiklerimRecyclerView;


    private int mParam1;


    public BegendiklerimFragment() {

    }


    public static BegendiklerimFragment newInstance(int param1) {
        BegendiklerimFragment fragment = new BegendiklerimFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_begendiklerim, container, false);

        return view;
    }

}
