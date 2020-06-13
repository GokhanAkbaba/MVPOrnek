package com.example.mvpornek.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.mvpornek.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommentBottomDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommentBottomDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    public static final String TAG = "ActionBottomDialog";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;
    ImageButton bottomSheetCloseBtn;
    ConstraintLayout yorumAlani;
    public CommentBottomDialogFragment() {
        // Required empty public constructor
    }


    public static CommentBottomDialogFragment newInstance(int param1) {
        CommentBottomDialogFragment commentBottomDialogFragment = new CommentBottomDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        commentBottomDialogFragment.setArguments(args);
        return commentBottomDialogFragment;
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
        View view=inflater.inflate(R.layout.yorumlar_bottom_sheet, container, false);

        bottomSheetCloseBtn=view.findViewById(R.id.bottomSheetCloseBtn);
        bottomSheetCloseBtn.setOnClickListener(this);

        yorumAlani=view.findViewById(R.id.yorumAlani);
        yorumAlani.setOnClickListener(this);


        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottomSheetCloseBtn:
                getDialog().cancel();
                break;
            case R.id.yorumAlani:
                showBottomSheet(mParam1);
                break;
        }
    }
    public void showBottomSheet(int soruID) {
        CommentFieldFragment addPhotoBottomDialogFragment =
                CommentFieldFragment.newInstance(soruID);
        addPhotoBottomDialogFragment.show(getActivity().getSupportFragmentManager(),
                CommentFieldFragment.TAG);
    }


}