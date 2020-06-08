package com.example.mvpornek.Fragment;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.example.mvpornek.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommentFieldFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommentFieldFragment extends BottomSheetDialogFragment implements View.OnClickListener{

    public static final String TAG = "ActionBottomDialog";
    ScrollView klavyeAlani;
    EditText editText;
    ImageButton yorumGonderBtn;

    public CommentFieldFragment() {
        // Required empty public constructor
    }


    public static CommentFieldFragment newInstance() {


        return new CommentFieldFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_comment_field, container, false);
        editText=view.findViewById(R.id.edt);
        editText.setOnClickListener(this);

        yorumGonderBtn=view.findViewById(R.id.yorumGonderBtn);
        yorumGonderBtn.setOnClickListener(this);

        klavyeAlani=view.findViewById(R.id.klavyeAlani);
        ViewGroup.LayoutParams params=klavyeAlani.getLayoutParams();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().length()==0){
                    yorumGonderBtn.setEnabled(false);
                    yorumGonderBtn.setBackground(getActivity().getDrawable(R.drawable.yorum_gonder_buton));
                    yorumGonderBtn.setColorFilter(getResources().getColor(R.color.ayarlarGrisi));
                }else{
                    yorumGonderBtn.setEnabled(true);
                    yorumGonderBtn.setColorFilter(getResources().getColor(R.color.uygulamaMavisi));
                    yorumGonderBtn.setBackground(getActivity().getDrawable(R.drawable.baslarkenilbuton));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return view;
    }
    @Override
    public void onResume()
    {
        super.onResume();
        editText.post(new Runnable()
        {
            @Override
            public void run()
            {
                editText.requestFocus();
                InputMethodManager imm =
                        (InputMethodManager)editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }


    @Override
    public void onClick(View view) {

    }


}