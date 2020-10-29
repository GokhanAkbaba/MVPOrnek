package com.example.mvpornek.Activity.Notification;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpornek.Adapter.NotificationCommentAndQuestionAdapter;
import com.example.mvpornek.Fragment.Comment.CommentBottomDialogFragment;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Models.NotificationCommetAndQuestionModel;
import com.example.mvpornek.Presenter.Like.LikesPresenterImpl;
import com.example.mvpornek.Presenter.NotificationCommetAndQuestionPresenter;
import com.example.mvpornek.Presenter.NotificationCommetAndQuestionPresenterImpl;
import com.example.mvpornek.Presenter.NotificationPost.NotificaitonPostPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.Response.LikeModel;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.LikesView;
import com.example.mvpornek.View.NotificaitonPostView;
import com.example.mvpornek.View.NotificationCommetAndQuestionView;

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

    NotificationCommentAndQuestionAdapter.ItemClickListener begeniClickListener;
    NotificationCommentAndQuestionAdapter notificationCommentAndQuestionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_comment);
        Bundle extras = getIntent().getExtras();
        if(CommentBottomDialogFragment.kutuDurum){
            CommentBottomDialogFragment.instance.dialogCancel();
        }
        txtView = (TextView) findViewById(R.id.notificationCommentQuestionText);
        notificationCommentRecyclerView=(RecyclerView) findViewById(R.id.notificationCommentRecyclerView);

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
        /////
    }

    @Override
    public void showNotificaitonFailedMessage() {
        /////
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