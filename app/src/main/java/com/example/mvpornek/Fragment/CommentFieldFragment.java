package com.example.mvpornek.Fragment;


import android.content.Context;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.mvpornek.BirineSorHelper.BirineSorUtil;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Model.YorumKayit.CommentRegistrationInteractorImpl;
import com.example.mvpornek.Presenter.NotificaitonPostPresenterImpl;
import com.example.mvpornek.Presenter.YorumKayit.CommentRegistrationPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.CommentRegistrationView;
import com.example.mvpornek.View.NotificaitonPostView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CommentFieldFragment extends BottomSheetDialogFragment implements View.OnClickListener, CommentRegistrationView, NotificaitonPostView {

    public static final String TAG = "CommentFieldFragment";
    private static final String ARG_PARAM1 = "param1";
    private int mParam1;
    ScrollView klavyeAlani;
    EditText editText;
    ImageButton yorumGonderBtn;

    CommentRegistrationPresenterImpl commentRegistrationPresenter;
    NotificaitonPostPresenterImpl notificaitonPostPresenter;

    public CommentFieldFragment() {
        // Required empty public constructor
    }


    public static CommentFieldFragment newInstance(int param1) {
        CommentFieldFragment fragment = new CommentFieldFragment();
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
        setStyle(DialogFragment.STYLE_NORMAL,R.style.DialogStyle);
        notificaitonPostPresenter=new NotificaitonPostPresenterImpl(this);
        commentRegistrationPresenter=new CommentRegistrationPresenterImpl(this,new CommentRegistrationInteractorImpl(getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_comment_field, container, false);
        editText=view.findViewById(R.id.edt);
        editText.setOnClickListener(this);

        yorumGonderBtn=view.findViewById(R.id.yorumGonderBtn);
        yorumGonderBtn.setOnClickListener(this);
        yorumGonderBtn.setEnabled(false);
        yorumGonderBtn.setBackground(getActivity().getDrawable(R.drawable.yorum_gonder_buton));
        yorumGonderBtn.setColorFilter(getResources().getColor(R.color.ayarlarGrisi));
        klavyeAlani=view.findViewById(R.id.klavyeAlani);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
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
        String soru = editText.getText().toString();
        Kullanici kullanici= SharedPrefManager.getInstance(getActivity()).getKullanici();
        switch(view.getId()){
            case R.id.yorumGonderBtn:
                commentRegistrationPresenter.commentRegistrationValideCredentals(mParam1,kullanici.getId(),soru);
                notificaitonPostPresenter.postNotification(kullanici.getKullaniciAdi()+ " adlı kullanıcı sorunuza cevap verdi",11,soru);

                break;
        }


    }

    @Override
    public void showProgress() {
        BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBaslat(getActivity(),null,"Yorum Yapılıyor");
    }

    @Override
    public void hideProgress() {
        BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBitir();
    }

    @Override
    public void navigateToCommentRegistration() {
        Toast.makeText(getActivity(),"Yorumunuz Gönderildi",Toast.LENGTH_SHORT).show();
        hideProgress();
        getDialog().cancel();

    }

    @Override
    public void showNotificaitonSuccesMessage() {

    }

    @Override
    public void showNotificaitonFailedMessage() {

    }
}