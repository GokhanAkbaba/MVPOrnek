package com.birinesor.mvpornek.Activity.Ayrıntılar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Adapter.YorumAyrintiYorumlarAdapter;
import com.birinesor.mvpornek.Fragment.Comment.CommentFieldFragment;
import com.birinesor.mvpornek.Fragment.NavBarFragment.ProfilFragment;
import com.birinesor.mvpornek.GlideApp;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Models.YorumAyrintiSorusuModel;
import com.birinesor.mvpornek.Models.YorumAyrintiSorusuYorumlarModel;
import com.birinesor.mvpornek.Presenter.Like.LikesPresenterImpl;
import com.birinesor.mvpornek.Presenter.NotificationPost.NotificaitonPostPresenterImpl;
import com.birinesor.mvpornek.Presenter.QuestionsDeletePresenterImpl;
import com.birinesor.mvpornek.Presenter.YorumAyrinti.YorumAyrintiSorusuPresenterImpl;
import com.birinesor.mvpornek.Presenter.YorumAyrintiYorumlarPresenterImpl;
import com.birinesor.mvpornek.Presenter.YorumSil.CommentDeletePresenter;
import com.birinesor.mvpornek.Presenter.YorumSil.CommentDeletePresenterImpl;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.Response.LikeModel;
import com.birinesor.mvpornek.SharedPrefManager;
import com.birinesor.mvpornek.View.CommentDeleteView;
import com.birinesor.mvpornek.View.LikesView;
import com.birinesor.mvpornek.View.NotificaitonPostView;
import com.birinesor.mvpornek.View.QuestionsDeleteView;
import com.birinesor.mvpornek.View.YorumAyrintiSorusuView;
import com.birinesor.mvpornek.View.YorumAyrintiYorumlarView;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class YorumAyrintiActivity extends FragmentActivity implements YorumAyrintiSorusuView, CommentDeleteView, View.OnClickListener,NotificaitonPostView, YorumAyrintiYorumlarView, LikesView {
    YorumAyrintiSorusuPresenterImpl yorumAyrintiSorusuPresenter;
    ImageView yorumAyrintiSorularIcerikKullaniciResmi;
    TextView yorumAyrintiKullaniciAdSoyad;
    TextView yorumAyrintiKullaniciAdiTxt;
    TextView yorumAyrintiSoruPaylasmaZamani;
    TextView yorumAyrintiSoruEtiketleri;
    TextView yorumAyrintiSoruTxt;
    TextView yorumAyrintiYorumSayiTxt;
    SwipeRefreshLayout swipeRefreshLayout;
    LikeModel likeModel;
    ProfilFragment profilFragment;
    CommentFieldFragment addPhotoBottomDialogFragment;
    public static TextView begeniSayisiTxt;
    public Object durum;
    RelativeLayout yorumAyrintiRelativeLayout;
    List<YorumAyrintiSorusuYorumlarModel> yorumAyrintiSorusuYorumlarModels;
    List<YorumAyrintiSorusuModel> yorumAyrintiSorusuModels;
    YorumAyrintiYorumlarAdapter yorumAyrintiYorumlarAdapter;
    YorumAyrintiYorumlarPresenterImpl yorumAyrintiYorumlarPresenter;
    CommentDeletePresenterImpl commentDeletePresenter;
    YorumAyrintiYorumlarAdapter.ItemClickListener begeniItemClickListener;
    YorumAyrintiYorumlarAdapter.ItemClickListener yorumSilItemClickListener;
    Boolean checkSoruAlani=false;
    RecyclerView yorumAyrintiRecyclerView;
    LikesPresenterImpl likesPresenter;
    RelativeLayout yorumAyrintiAlani;
    Bundle extras;
    NotificaitonPostPresenterImpl notificaitonPostPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yorum_ayrinti);
       extras = getIntent().getExtras();
        yorumAyrintiSorusuPresenter=new YorumAyrintiSorusuPresenterImpl(this);
        yorumAyrintiSorusuPresenter.loadYorumAyrintiSorusu(extras.getInt("soruID"));
        yorumAyrintiYorumlarPresenter=new YorumAyrintiYorumlarPresenterImpl(this);
        yorumAyrintiYorumlarPresenter.YorumAyrintiYorumlarLoad(extras.getInt("soruID"));
        yorumAyrintiSorularIcerikKullaniciResmi=findViewById(R.id.yorumAyrintiSorularIcerikKullaniciResmi);
        yorumAyrintiKullaniciAdSoyad=findViewById(R.id.yorumAyrintiKullaniciAdSoyad);
        yorumAyrintiKullaniciAdiTxt=findViewById(R.id.yorumAyrintiKullaniciAdiTxt);
        yorumAyrintiSoruPaylasmaZamani=findViewById(R.id.yorumAyrintiSoruPaylasmaZamani);
        yorumAyrintiSoruEtiketleri=findViewById(R.id.yorumAyrintiSoruEtiketleri);
        yorumAyrintiSoruTxt=findViewById(R.id.yorumAyrintiSoruTxt);
        yorumAyrintiYorumSayiTxt=findViewById(R.id.yorumAyrintiYorumSayiTxt);
        yorumAyrintiRelativeLayout=findViewById(R.id.yorumAyrintiRelativeLayout);
        yorumAyrintiRecyclerView=findViewById(R.id.yorumAyrintiRecyclerView);
        yorumAyrintiKullaniciAdSoyad.setOnClickListener(this);
        yorumAyrintiAlani=findViewById(R.id.yorumAyrintiAlani);
        yorumAyrintiAlani.setOnClickListener(this);
        swipeRefreshLayout=findViewById(R.id.swiperefreshYorumAyrinti);
        swipeRefreshLayout.setColorSchemeResources(R.color.uygulamaMavisi);
        yorumAyrintiSorularIcerikKullaniciResmi.setOnClickListener(this);
        yorumAyrintiRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        likesPresenter=new LikesPresenterImpl(this);
        commentDeletePresenter=new CommentDeletePresenterImpl(this);
        notificaitonPostPresenter=new NotificaitonPostPresenterImpl(this);
        Kullanici kullanici= SharedPrefManager.getInstance(this).getKullanici();
        begeniItemClickListener=((vw,position)-> {
            ImageView begeniButonu=vw.findViewById(R.id.yorumlarAyrintiYorumBegeniButon);
            durum=begeniButonu.getTag();
            View textViever = yorumAyrintiRecyclerView.getLayoutManager().findViewByPosition(position);
            begeniSayisiTxt=textViever.findViewById(R.id.yorumlarAyrintiYorumBegeniSayisiTxt);
            if(durum == "secilmedi") {
                begeniButonu.setColorFilter(Color.argb(255, 255, 172, 51));
                int cevapID=yorumAyrintiSorusuYorumlarModels.get(position).getCevapId();
                String cevapTxt=yorumAyrintiSorusuYorumlarModels.get(position).getCevap();
                String durumTxt=kullanici.getAdSoyad()+" adlı kullanıcı yorumunuzu beğendi";
                int cevapSahibiKullaniciID=yorumAyrintiSorusuYorumlarModels.get(position).getKullaniciId();

                likesPresenter.loadLikes(cevapID,kullanici.getId(),1,1);

                if(kullanici.getId() != cevapSahibiKullaniciID)
                {
                    notificaitonPostPresenter.postNotification(kullanici.getId(),cevapSahibiKullaniciID,cevapID,extras.getInt("soruID"),cevapTxt,durumTxt,1);
                }
                begeniButonu.setTag("secildi");
            } else if(durum == "secildi"){
                begeniButonu.setColorFilter(Color.argb(255, 223, 225, 229));
                likesPresenter.loadLikes(yorumAyrintiSorusuYorumlarModels.get(position).getCevapId(),kullanici.getId(),1,0);
                begeniButonu.setTag("secilmedi");
            }

        });
        swipeRefreshLayout.setOnRefreshListener(() -> {
            yorumAyrintiYorumlarPresenter.YorumAyrintiYorumlarLoad(extras.getInt("soruID"));
        });
        yorumSilItemClickListener =((vw,position)-> {
            ConstraintLayout sorularIcerik=vw.findViewById(R.id.yorumlarAyrintiLayout);
            if(kullanici.getId() == yorumAyrintiSorusuYorumlarModels.get(position).getKullaniciId()) {
                if(!checkSoruAlani) {
                    sorularIcerik.setBackgroundColor(getResources().getColor(R.color.colorGrayPrimay));
                    checkSoruAlani = true;
                }
                final CharSequence[] items = {"Sil"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setItems(items, (dialog, item) -> commentDeletePresenter.deleteOptions(yorumAyrintiSorusuYorumlarModels.get(position).getCevapId()));
                builder.setOnDismissListener(dialogInterface -> {
                    if(checkSoruAlani) {
                        sorularIcerik.setBackgroundColor(getResources().getColor(R.color.white));
                        checkSoruAlani=false;
                    }
                    yorumAyrintiYorumlarPresenter.YorumAyrintiYorumlarLoad(extras.getInt("soruID"));
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    @Override
    public void onGetResult(List<YorumAyrintiSorusuModel> data) {
        this.yorumAyrintiSorusuModels=data;
        String zaman=data.get(0).getSoruZaman();
        SimpleDateFormat spf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate= null;
        try {
            newDate = spf.parse(zaman);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf= new SimpleDateFormat("dd MMM yyyy HH:mm");
        zaman = spf.format(newDate);
        yorumAyrintiSoruPaylasmaZamani.setText(zaman);
        yorumAyrintiKullaniciAdSoyad.setText(data.get(0).getAdSoyad());
        yorumAyrintiKullaniciAdiTxt.setText("@"+data.get(0).getKullaniciAdi());
        yorumAyrintiSoruEtiketleri.setText(data.get(0).getEtiket());
        yorumAyrintiYorumSayiTxt.setText(String.valueOf(data.get(0).getYorumSayisi()));
        yorumAyrintiSoruTxt.setText(data.get(0).getSoru());
        GlideApp.with(this).load(data.get(0).getProfilFoto()).apply(new RequestOptions().centerCrop()).into(yorumAyrintiSorularIcerikKullaniciResmi);
    }

    @Override
    public void onErrorLoading(String message) {
        Log.d("Bir Hata Oluştu",message);
    }

    @Override
    public void onGetYorumAyrintiSorusuKontrol() {
        yorumAyrintiRelativeLayout.setVisibility(View.INVISIBLE);
        Toast.makeText(this,"Soru Silinmiş",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },2000);
    }

    @Override
    public void onorumAyrintiYorumlarGetResult(List<YorumAyrintiSorusuYorumlarModel> data) {
        this.yorumAyrintiSorusuYorumlarModels=data;
        yorumAyrintiYorumlarAdapter=new YorumAyrintiYorumlarAdapter(data,this,begeniItemClickListener,yorumSilItemClickListener);
        yorumAyrintiYorumlarAdapter.notifyDataSetChanged();
        yorumAyrintiRecyclerView.setAdapter(yorumAyrintiYorumlarAdapter);

    }

    @Override
    public void onorumAyrintiYorumlarErrorLoading(String message) {
        System.out.println("Bir Hata Oluştu"+message);
    }

    @Override
    public void onorumAyrintiYorumlarKontrol() {
        yorumAyrintiRelativeLayout.setVisibility(View.INVISIBLE);
        Toast.makeText(this,"Soru Silinmiş",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },2000);
    }

    @Override
    public void onYorumAyrintiYorumlarShow() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void onYorumAyrintiYorumlarHide() {
        swipeRefreshLayout.setRefreshing(false);
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.yorumAyrintiSorularIcerikKullaniciResmi:
                profilFragment = ProfilFragment.newInstance(yorumAyrintiSorusuModels.get(0).getKullanici_id());
                HomeActivity.getInstance().loadFragment(profilFragment,"Fragment");
                HomeActivity.getInstance().menuDurum(3);
                finish();
                break;
            case R.id.yorumAyrintiKullaniciAdSoyad:
                profilFragment = ProfilFragment.newInstance(yorumAyrintiSorusuModels.get(0).getKullanici_id());
                HomeActivity.getInstance().loadFragment(profilFragment,"Fragment");
                HomeActivity.getInstance().menuDurum(3);
                finish();
                break;
            case R.id.yorumAyrintiAlani:
                showBottomSheet(extras.getInt("soruID"));
                break;
        }
    }
    public void showBottomSheet(int soruID) {
        addPhotoBottomDialogFragment =
                CommentFieldFragment.newInstance(soruID,yorumAyrintiSorusuModels.get(0).getKullanici_id());
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                CommentFieldFragment.TAG);
        getSupportFragmentManager().executePendingTransactions();
       addPhotoBottomDialogFragment.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                yorumAyrintiYorumlarPresenter.YorumAyrintiYorumlarLoad(soruID);
                addPhotoBottomDialogFragment.dismiss();
            }
        });


    }

    @Override
    public void showSuccesMessage() {
        Toast.makeText(this,"Cevabınız Başarı ile Silindi",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedMessage() {
        Toast.makeText(this,"Cevabınız Silinirken Hata Oluştu",Toast.LENGTH_SHORT).show();
    }
}