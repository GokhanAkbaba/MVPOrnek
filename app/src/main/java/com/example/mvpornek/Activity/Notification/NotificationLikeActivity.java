package com.example.mvpornek.Activity.Notification;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.example.mvpornek.Activity.HomeActivity;
import com.example.mvpornek.Fragment.NavBarFragment.ProfilFragment;
import com.example.mvpornek.GlideApp;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Presenter.Like.LikesPresenterImpl;
import com.example.mvpornek.Presenter.NotificationLikeAndQuestion.NotificationLikeAndQuestionPresenter;
import com.example.mvpornek.Presenter.NotificationLikeAndQuestion.NotificationLikeAndQuestionPresenterImpl;
import com.example.mvpornek.Presenter.NotificationPost.NotificaitonPostPresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.Response.LikeModel;
import com.example.mvpornek.Response.NotificationCommentAndLikeModel;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.LikesView;
import com.example.mvpornek.View.NotificaitonPostView;
import com.example.mvpornek.View.NotificationLikeAndQuestionView;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NotificationLikeActivity extends AppCompatActivity implements NotificationLikeAndQuestionView,View.OnClickListener, LikesView, NotificaitonPostView {
    private TextView notificationLikeYorumZamanTxt;
    private TextView notificationLikeYorumBegeniSayisiTxt;
    private TextView notificationLikeYorumKullaniciAdiTxt;
    private TextView notificationLikeYorumTxt;
    TextView notificationLikeYorumSayisiTxt;
    private TextView notificationLikeSoruTxt;
    private TextView notificationLikeKullaniciAdiTxt;
    private TextView notificationLikeSoruEtiketleri;
    private TextView notificationLikeSoruPaylasmaZamani;
    private TextView notificationLikeKullaniciAdSoyad;
    private ImageView notificationLikeYorumBegeniButon;
    private ImageView notificationYorumRoundedKullaniciResmi;
    private ImageView notificationLikeSoruYorumIcon;
    private ImageView notificationLikeSorularIcerikKullaniciResmi;
    private RelativeLayout notificationRelativeLayout;

    private List<NotificationCommentAndLikeModel> notificationCommentAndLikeModel;
    private NotificationLikeAndQuestionPresenter notificationLikeAndQuestionPresenter;
    NotificaitonPostPresenterImpl notificaitonPostPresenter;
    LikesPresenterImpl likesPresenter;

    private Kullanici kullanici;

    private String cevapID;

    ProfilFragment profilFragment;
    Intent intent;

    Context context;
    Object durum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_like);
        notificationLikeYorumZamanTxt=(TextView) findViewById(R.id.notificationLikeYorumZaman);
        notificationLikeYorumBegeniSayisiTxt=(TextView) findViewById(R.id.notificationLikeYorumBegeniSayisiTxt);
        notificationLikeYorumKullaniciAdiTxt=(TextView) findViewById(R.id.notificationLikeYorumKullaniciAdi);
        notificationLikeYorumTxt=(TextView) findViewById(R.id.notificationLikeYorumTxt);
        notificationLikeYorumSayisiTxt=(TextView) findViewById(R.id.notificationLikeYorumSayiTxt);
        notificationLikeSoruTxt=(TextView) findViewById(R.id.notificationLikeSoruTxt);
        notificationLikeKullaniciAdiTxt=(TextView) findViewById(R.id.notificationLikeKullaniciAdiTxt);
        notificationLikeSoruEtiketleri=(TextView) findViewById(R.id.notificationLikeSoruEtiketleri);
        notificationLikeSoruPaylasmaZamani=(TextView) findViewById(R.id.notificationLikeSoruPaylasmaZamani);
        notificationLikeKullaniciAdSoyad=(TextView) findViewById(R.id.notificationLikeKullaniciAdSoyad);
        notificationLikeYorumBegeniButon=(ImageView) findViewById(R.id.notificationLikeYorumBegeniButon);
        notificationYorumRoundedKullaniciResmi=(ImageView) findViewById(R.id.notificationYorumRoundedKullaniciResmi);
        notificationLikeSoruYorumIcon=findViewById(R.id.notificationLikeSoruYorumIcon);
        notificationLikeSorularIcerikKullaniciResmi=(ImageView) findViewById(R.id.notificationLikeSorularIcerikKullaniciResmi);
        notificationRelativeLayout=(RelativeLayout) findViewById(R.id.notificationLikeRelativeLayout);
        notificationLikeAndQuestionPresenter=new NotificationLikeAndQuestionPresenterImpl(this);
        notificaitonPostPresenter=new NotificaitonPostPresenterImpl(this);
        likesPresenter=new LikesPresenterImpl(this);
        notificationLikeYorumBegeniButon.setTag("secilmedi");
        int gelenCevapID = getIntent().getExtras().getInt("cevapID");
        int gelenSoruID = getIntent().getExtras().getInt("soruID");
        System.out.println("gelenCevapID "+gelenCevapID+" gelenSoruID "+gelenSoruID);
        kullanici= SharedPrefManager.getInstance(getApplicationContext()).getKullanici();
        notificationLikeAndQuestionPresenter.loadDataNotificationLike(gelenSoruID,gelenCevapID);
        notificationLikeSorularIcerikKullaniciResmi.setOnClickListener(this);
        notificationYorumRoundedKullaniciResmi.setOnClickListener(this);
        notificationLikeYorumBegeniButon.setOnClickListener(this);
    }

    @Override
    public void onNotificationCommetAndLikeGetResult(List<NotificationCommentAndLikeModel> data) {
        this.notificationCommentAndLikeModel=data;
        notificationLikeKullaniciAdiTxt.setText(data.get(0).getSoruSoranKullaniciAdi());
        notificationLikeKullaniciAdSoyad.setText(data.get(0).getSoruSoranAdSoyad());
        notificationLikeSoruTxt.setText(data.get(0).getSoru());
        notificationLikeYorumSayisiTxt.setText(String.valueOf(data.get(0).getYorumSayisi()));
        notificationLikeSoruEtiketleri.setText(data.get(0).getSoruEtiket());
        GlideApp.with(getApplicationContext()).load(data.get(0).getSoruSoranProfilFoto()).apply(new RequestOptions().centerCrop()).into(notificationLikeSorularIcerikKullaniciResmi);
        GlideApp.with(getApplicationContext()).load(data.get(0).getCevapVerenKullaniciProfilFoto()).apply(new RequestOptions().centerCrop()).into(notificationYorumRoundedKullaniciResmi);
        notificationLikeYorumBegeniSayisiTxt.setText(String.valueOf(data.get(0).getCevapBegeniSayisi()));
        notificationLikeYorumTxt.setText(data.get(0).getCevap());
        notificationLikeYorumKullaniciAdiTxt.setText(data.get(0).getCevapVerenKullaniciAdi());
        String soruZaman = zamanDonusumu(data.get(0).getSoruZaman());
        String cevapZaman= zamanDonusumu(data.get(0).getCevapZaman());
        SimpleDateFormat spf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate= null;
        try {
            newDate = spf.parse(data.get(0).getSoruZaman());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf= new SimpleDateFormat("dd MMM yyyy HH:mm");
        soruZaman= spf.format(newDate);
        cevapZaman= spf.format(newDate);
        notificationLikeSoruPaylasmaZamani.setText(soruZaman);
        notificationLikeYorumZamanTxt.setText(cevapZaman);

    }
    public String zamanDonusumu(String zaman){

        if(zaman  != null){
            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pos = new ParsePosition(0);
            long then = simpleDateFormat.parse(zaman, pos).getTime();
            long now = new Date().getTime();

            long seconds = (now - then) / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;
            String friendly = null;
            long num = 0;
            if (days > 0) {
                num = days;
                if(days > 7 ){
                    friendly="s";
                }else{
                    friendly = days + " gn";
                }

            }
            else if (hours > 0) {
                num = hours;
                friendly = hours + " sa";
            }
            else if (minutes > 0) {
                num = minutes;
                friendly = minutes + " dk";
            }
            else {
                num = seconds;
                friendly = seconds + " sn";
            }
            return friendly;
        }else {
            return null;
        }

    }

    @Override
    public void onNotificationCommetAndLikeErrorLoading(String message) {
        Log.d("Bir Hata Oluştu",message);
    }

    @Override
    public void onGetNotificationCommetAndLikeKontrol() {
        notificationRelativeLayout.setVisibility(View.INVISIBLE);
        Toast.makeText(this,"Soru Silinmiş",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },2000);



    }

    @Override
    public void NotificationCommetAndLikeShowLoading() {

    }

    @Override
    public void NotificationCommetAndLikeHideLoading() {

    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.notificationLikeSorularIcerikKullaniciResmi:
                intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("soruID",notificationCommentAndLikeModel.get(0).getSoruSoranID());
                startActivity(intent);
                break;
            case R.id.notificationYorumRoundedKullaniciResmi:
                intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("soruID",notificationCommentAndLikeModel.get(0).getCevapVerenKullaniciID());
                startActivity(intent);
                break;
            case R.id.notificationLikeYorumBegeniButon:
                durum=notificationLikeYorumBegeniButon.getTag();

                if(durum == "secilmedi") {
                    notificationLikeYorumBegeniButon.setColorFilter(Color.argb(255, 255, 172, 51));
                    int soruID=notificationCommentAndLikeModel.get(0).getSoruSoranID();
                    int cevapID=notificationCommentAndLikeModel.get(0).getCevapID();
                    String cevapTxt=notificationCommentAndLikeModel.get(0).getCevap();
                    String durumTxt=kullanici.getAdSoyad()+" adlı kullanıcı yorumunuzu beğendi";
                    int cevapSahibiKullaniciID=notificationCommentAndLikeModel.get(0).getCevapVerenKullaniciID();

                    likesPresenter.loadLikes(cevapID,kullanici.getId(),1,1);

                    if(kullanici.getId() != cevapSahibiKullaniciID)
                    {
                        notificaitonPostPresenter.postNotification(kullanici.getId(),cevapSahibiKullaniciID,cevapID,soruID,cevapTxt,durumTxt,1);
                    }
                    notificationLikeYorumBegeniButon.setTag("secildi");
                } else if(durum == "secildi"){
                    notificationLikeYorumBegeniButon.setColorFilter(Color.argb(255, 223, 225, 229));
                    likesPresenter.loadLikes(notificationCommentAndLikeModel.get(0).getCevapID(),kullanici.getId(),1,0);
                    notificationLikeYorumBegeniButon.setTag("secilmedi");
                }
                break;

        }
    }

    @Override
    public void onGetLike(LikeModel items) {
        if(durum == "secilmedi") {
            notificationLikeYorumBegeniSayisiTxt.setText(String.valueOf(items.getBegeniSayisi()));
        } else if(durum == "secildi"){
            notificationLikeYorumBegeniSayisiTxt.setText(String.valueOf(items.getBegeniSayisi()));
        }
    }

    @Override
    public void showNotificaitonSuccesMessage() {
        ////
    }

    @Override
    public void showNotificaitonFailedMessage() {
      /////
    }
}