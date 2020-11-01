package com.birinesor.mvpornek.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.birinesor.mvpornek.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionPostFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public QuestionPostFragment() {
        // Required empty public constructor
    }

    public static QuestionPostFragment newInstance(String param1, String param2) {
        QuestionPostFragment fragment = new QuestionPostFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.soru_paylas_ekrani, container, false);

        return view;
    }


    @Override
    public void onClick(View view) {
    }
}
