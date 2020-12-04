package com.birinesor.mvpornek.Activity.Notification;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.birinesor.mvpornek.Adapter.NotificationCommentAndQuestionAdapter;
import com.birinesor.mvpornek.Fragment.Comment.CommentBottomDialogFragment;
import com.birinesor.mvpornek.InitApplication;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Models.NotificationCommetAndQuestionModel;
import com.birinesor.mvpornek.Presenter.Like.LikesPresenterImpl;
import com.birinesor.mvpornek.Presenter.NotificationCommetAndQuestionPresenter;
import com.birinesor.mvpornek.Presenter.NotificationCommetAndQuestionPresenterImpl;
import com.birinesor.mvpornek.Presenter.NotificationPost.NotificaitonPostPresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.Response.LikeModel;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.View.LikesView;
import com.birinesor.mvpornek.View.NotificaitonPostView;
import com.birinesor.mvpornek.View.NotificationCommetAndQuestionView;

import java.util.List;

public class NotificationCommentActivity extends AppCompatActivity implements NotificationCommetAndQuestionView, LikesView, NotificaitonPostView {
    TextView txtView;
    RecyclerView notificationCommentRecyclerView;
    LikeModel likeModel;
    @SuppressLint("StaticFieldLeak")
    public static TextView begeniSayisiTxt;
    public static Boolean bildirimAcilis;
    public int soruID;
    public Object durum;
    NotificationCommetAndQuestionPresenter notificationCommetAndQuestionPresenter;
    LikesPresenterImpl likesPresenter;
    NotificaitonPostPresenterImpl notificaitonPostPresenter;
    List<NotificationCommetAndQuestionModel>  notificationCommetAndQuestionModel;
    RelativeLayout relativeLayout;
    NotificationCommentAndQuestionAdapter.ItemClickListener begeniClickListener;
    NotificationCommentAndQuestionAdapter notificationCommentAndQuestionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_comment);
        if (InitApplication.getInstance(this).isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
            if (Build.VERSION.SDK_INT >= 27) {
                getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
            }
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            getWindow().setStatusBarColor(getResources().getColor(R.color.whiteStatus));
            if (Build.VERSION.SDK_INT >= 27) {
                getWindow().setNavigationBarColor(getResources().getColor(R.color.whiteStatus));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        Bundle extras = getIntent().getExtras();
        if(CommentBottomDialogFragment.kutuDurum){
            CommentBottomDialogFragment.instance.dialogCancel();
        }
        txtView = (TextView) findViewById(R.id.notificationCommentQuestionText);
        notificationCommentRecyclerView=(RecyclerView) findViewById(R.id.notificationCommentRecyclerView);
        relativeLayout=findViewById(R.id.relativeLayout);
        notificationCommetAndQuestionPresenter=new NotificationCommetAndQuestionPresenterImpl(this);
        likesPresenter=new LikesPresenterImpl(this);
        notificaitonPostPresenter=new NotificaitonPostPresenterImpl(this);

        notificationCommentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Kullanici kullanici= SharedPrefManager.getInstance(this).getKullanici();
        if(extras != null){
            if(extras.containsKey("bildirimYapılanSoruID"))
            {
                soruID = Integer.parseInt(extras.getString("bildirimYapılanSoruID"));
                notificationCommetAndQuestionPresenter.loadDataNotificationComment(soruID);
                setBildirimAcilis(true);
            }
            else if (extras.containsKey("soruID")) {
                soruID=extras.getInt("soruID");
                notificationCommetAndQuestionPresenter.loadDataNotificationComment(soruID);
                setBildirimAcilis(false);
            }
        }

        begeniClickListener=((vw,position)-> {
            ImageView begeniButonu=vw.findViewById(R.id.notificationYorumBegeniButon);
             durum=begeniButonu.getTag();
            View textViever = notificationCommentRecyclerView.getLayoutManager().findViewByPosition(position);
            begeniSayisiTxt=textViever.findViewById(R.id.notificationYorumBegeniSayisiTxt);
            if(durum == "secilmedi") {
                begeniButonu.setColorFilter(Color.argb(255, 255, 172, 51));
                int cevapID=notificationCommetAndQuestionModel.get(position).getCevapID();
                String cevapTxt=notificationCommetAndQuestionModel.get(position).getCevap();
                String durumTxt=kullanici.getAdSoyad()+" adlı kullanıcı yorumunuzu beğendi";
                int cevapSahibiKullaniciID=notificationCommetAndQuestionModel.get(position).getKullaniciID();

                likesPresenter.loadLikes(cevapID,kullanici.getId(),1,1);

                if(kullanici.getId() != cevapSahibiKullaniciID)
                {
                    notificaitonPostPresenter.postNotification(kullanici.getId(),cevapSahibiKullaniciID,cevapID,soruID,cevapTxt,durumTxt,1);
                }
                begeniButonu.setTag("secildi");
            } else if(durum == "secildi"){
                begeniButonu.setColorFilter(Color.argb(255, 223, 225, 229));
                likesPresenter.loadLikes(notificationCommetAndQuestionModel.get(position).getCevapID(),kullanici.getId(),1,0);
                begeniButonu.setTag("secilmedi");
            }

        });

    }
    public static Boolean getBildirimAcilis() {
        return bildirimAcilis;
    }
    public static void setBildirimAcilis(Boolean bildirimAcilis) {
        NotificationCommentActivity.bildirimAcilis = bildirimAcilis;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onGetResult(List<NotificationCommetAndQuestionModel> data) {
        notificationCommentAndQuestionAdapter=new NotificationCommentAndQuestionAdapter(data,this,begeniClickListener);
        notificationCommentAndQuestionAdapter.notifyDataSetChanged();
        notificationCommentRecyclerView.setAdapter(notificationCommentAndQuestionAdapter);
        this.notificationCommetAndQuestionModel=data;
        txtView.setText("\""+data.get(0).getSoru()+"\""+" sorusu hakkındaki cevaplar");
    }

    @Override
    public void onErrorLoading(String message) {
        System.out.println("Bir Hata Oluştu"+message);
    }

    @Override
    public void onGetNotificationCommetAndQuestionKontrol() {
        relativeLayout.setVisibility(View.INVISIBLE);
        Toast.makeText(this,"Soru Silinmiş",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },2000);
    }

    @Override
    public void onGetLike(LikeModel items) {
        this.likeModel=items;
        if(durum == "secilmedi") {
            begeniSayisiTxt.setText(String.valueOf(likeModel.getBegeniSayisi()));
        } else if(durum == "secildi"){
            begeniSayisiTxt.setText(String.valueOf(likeModel.getBegeniSayisi()));
        }


    }

    @Override
    public void showNotificaitonSuccesMessage() {
        Log.d("Bildirim Göderimi","Bildirim Başarı İle Gönderildi");
    }

    @Override
    public void showNotificaitonFailedMessage() {
        Log.d("Bildirim Göderimi","Bildirim Gönderimi Başarısız");
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}