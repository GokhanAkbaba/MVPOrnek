package com.example.mvpornek.Fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpornek.Activity.Adapter.CommentAdapter;
import com.example.mvpornek.Model.Kullanıcı.KullaniciKayit.CommentModel;
import com.example.mvpornek.Model.Kullanıcı.KullaniciKayit.Kullanici;
import com.example.mvpornek.Model.Response.KullaniciResponse;
import com.example.mvpornek.Model.Response.LikeModel;
import com.example.mvpornek.Presenter.CommentDeletePresenterImpl;
import com.example.mvpornek.Presenter.CommentPresenterImpl;
import com.example.mvpornek.Presenter.LikesPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.CommentDeleteView;
import com.example.mvpornek.View.CommentView;
import com.example.mvpornek.View.LikesView;
import com.example.mvpornek.WebService.RetrofitClientInstance;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentBottomDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener, LikesView, CommentView, CommentDeleteView {


    private static final String ARG_PARAM1 = "param1";
    public static final String TAG = "CommentBottomDialogFragment";


    private int mParam1;
    LikeModel likeModel;
    ImageButton bottomSheetCloseBtn;
    ConstraintLayout yorumAlani;
    Boolean checkYorumAlani = false;
    RecyclerView recyclerViewCevapAlani;
    List<CommentModel> commentModels;
    CommentAdapter commentAdapter;
    CommentPresenterImpl commentPresenter;
    CommentAdapter.ItemClickListener itemClickListener;
    CommentAdapter.ItemClickListener begeniClickListener;
    CommentDeletePresenterImpl commentDeletePresenter;


    CommentFieldFragment addPhotoBottomDialogFragment;

    LikesPresenterImpl likesPresenter;

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
        commentPresenter=new CommentPresenterImpl(this);
        dataLoad(mParam1);
        commentDeletePresenter=new CommentDeletePresenterImpl(this);
        likesPresenter=new LikesPresenterImpl(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.yorumlar_bottom_sheet, container, false);
        bottomSheetCloseBtn=view.findViewById(R.id.bottomSheetCloseBtn);
        bottomSheetCloseBtn.setOnClickListener(this);
        yorumAlani=view.findViewById(R.id.yorumAlani);
        yorumAlani.setOnClickListener(this);

        Kullanici kullanici= SharedPrefManager.getInstance(getActivity()).getKullanici();
        begeniClickListener=((vw,position)-> {
            ImageView begeniButonu=vw.findViewById(R.id.imageButton);
            Object durum=begeniButonu.getTag();
            View textViever = recyclerViewCevapAlani.getLayoutManager().findViewByPosition(position);
            TextView begeniSayisiTxt=textViever.findViewById(R.id.begeniSayisiTxt);
            if(durum == "secilmedi") {
                begeniButonu.setColorFilter(Color.argb(255, 255, 172, 51));
                likesPresenter.loadLikes(commentModels.get(position).getCevap_id(),kullanici.getId(),1,1);
               new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        begeniSayisiTxt.setText(String.valueOf(likeModel.getBegeniSayisi()));
                    }
                },300);
                begeniButonu.setTag("secildi");
            } else if(durum == "secildi"){
                begeniButonu.setColorFilter(Color.argb(255, 223, 225, 229));
                likesPresenter.loadLikes(commentModels.get(position).getCevap_id(),kullanici.getId(),1,0);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        begeniSayisiTxt.setText(String.valueOf(likeModel.getBegeniSayisi()));
                    }
                },500);
                begeniButonu.setTag("secilmedi");
            }

        });

        itemClickListener =((vw,position)-> {
            ConstraintLayout yorumlarIcerik=vw.findViewById(R.id.yorumlarIcerikLayout);
            if(checkYorumAlani == false) {
                yorumlarIcerik.setBackgroundColor(getResources().getColor(R.color.colorGrayPrimay));
                checkYorumAlani = true;
            }
            if(kullanici.getId()==commentModels.get(position).getKullaniciId()) {
                final CharSequence[] items = {"Kopyala", "Sil"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item] == "Kopyala") {
                            Toast.makeText(getActivity().getApplicationContext(), "Kopyalandı", Toast.LENGTH_SHORT).show();
                        } else {
                            commentDeletePresenter.deleteOptions(commentModels.get(position).getId());
                            dataLoad(mParam1);
                        }
                    }
                });

                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if(checkYorumAlani == true) {
                            yorumlarIcerik.setBackgroundColor(getResources().getColor(R.color.white));
                            checkYorumAlani=false;
                        }
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }else{
                final CharSequence[] items = {"Kopyala"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                            Toast.makeText(getActivity().getApplicationContext(), "Kopyalandı", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
            });

        recyclerViewCevapAlani=(RecyclerView) view.findViewById(R.id.yorumlarRecyclerView);
        recyclerViewCevapAlani.setAdapter(commentAdapter);
        recyclerViewCevapAlani.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        addPhotoBottomDialogFragment =
                CommentFieldFragment.newInstance(soruID);
        addPhotoBottomDialogFragment.show(getActivity().getSupportFragmentManager(),
                CommentFieldFragment.TAG);
        getActivity().getSupportFragmentManager().executePendingTransactions();
        addPhotoBottomDialogFragment.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dataLoad(soruID);
                addPhotoBottomDialogFragment.dismiss();
            }
        });

    }

    @Override
    public void onGetResult(List<CommentModel> data) {
        commentAdapter= new CommentAdapter(data,getActivity(),itemClickListener,begeniClickListener);
        commentAdapter.notifyDataSetChanged();
        recyclerViewCevapAlani.setAdapter(commentAdapter);
        commentModels=data;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getActivity().getApplicationContext(), "Yorumlar Görüntülenirken Hata Oluştu", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showSuccesMessage() {
        Toast.makeText(getActivity().getApplicationContext(), "Yorumunuz Silindi", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showFailedMessage() {
        Toast.makeText(getActivity().getApplicationContext(), "Yorumunuz Silinirken Hata Oluştu", Toast.LENGTH_SHORT).show();
    }

    public void dataLoad(int a){
        commentPresenter.loadData(a);
    }
   @Override
    public void onGetLike(LikeModel items) {
        this.likeModel=items;
        likeModel=items;
    }
}