package com.birinesor.mvpornek.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.birinesor.mvpornek.Activity.Ayrıntılar.YorumAyrintiActivity;
import com.birinesor.mvpornek.Activity.Notification.NotificationLikeActivity;
import com.bumptech.glide.request.RequestOptions;
import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Fragment.NavBarFragment.ProfilFragment;
import com.birinesor.mvpornek.GlideApp;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Models.LikesModel;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.SharedPrefManager;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterProfilLikes extends RecyclerView.Adapter<AdapterProfilLikes.RecyclerViewAdapter> {
    private List<LikesModel> likesModels;
    private Context context;
    private AdapterProfilLikes.ItemClickListener itemClickListener;

    public AdapterProfilLikes(List<LikesModel> likesModels, Context context, AdapterProfilLikes.ItemClickListener itemClickListener) {
        this.likesModels = likesModels;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public AdapterProfilLikes.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.begeni_icerik, parent, false);
        return new AdapterProfilLikes.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProfilLikes.RecyclerViewAdapter holder, int position) {
        LikesModel likesModel=likesModels.get(position);
        String kullaniciAdSoyad=likesModel.getAdSoyad();
        String kullaniciAdi =likesModel.getKullaniciAdi();
        String cevap=likesModel.getCevap();
        String zaman=zamanDonusumu(likesModel.getZaman());
        String profilFoto=likesModel.getProfilFoto();
        int begeniSayisi=likesModel.getBegeni_sayisi();
        holder.begeniKullaniciAdSoyad.setText(kullaniciAdSoyad);
        holder.begeniKullaniciAdiTxt.setText("@"+kullaniciAdi);
        holder.cevap.setText(cevap);
        holder.begeniPaylasmaZamani.setText(zaman);
        holder.yorumCevapBegeniSayisiTxt.setText(String.valueOf(begeniSayisi));
        GlideApp.with(context).load(profilFoto).apply(new RequestOptions().centerCrop()).into(holder.begeniRoundedKullaniciResmi);
        Kullanici kullanici= SharedPrefManager.getInstance(context).getKullanici();
        if(likesModel.getKim_begendi()==kullanici.getId()){
            holder.profilBegeniImageButton.setColorFilter(Color.argb(255, 255, 172, 51));
            holder.profilBegeniImageButton.setTag("secildi");
        }else {
            holder.profilBegeniImageButton.setColorFilter(Color.argb(255, 223, 225, 229));
            holder.profilBegeniImageButton.setTag("secilmedi");
        }
    }

    @Override
    public int getItemCount() {
        return likesModels.size();
    }
    public class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        AdapterProfilLikes.ItemClickListener itemClickListener;
        TextView begeniKullaniciAdSoyad,begeniKullaniciAdiTxt,yorumCevapBegeniSayisiTxt,cevap,begeniPaylasmaZamani,soruId;
        ImageView begeniRoundedKullaniciResmi,profilBegeniImageButton;
        ConstraintLayout sorularIcerikLayout;
        RecyclerViewAdapter(View itemView, AdapterProfilLikes.ItemClickListener itemClickListener) {
            super(itemView);
            this.itemClickListener=itemClickListener;
            profilBegeniImageButton=itemView.findViewById(R.id.profilBegeniImageButton);
            begeniKullaniciAdSoyad=itemView.findViewById(R.id.begeniKullaniciAdSoyad);
            begeniKullaniciAdiTxt=itemView.findViewById(R.id.begeniKullaniciAdiTxt);
            sorularIcerikLayout=itemView.findViewById(R.id.sorularIcerikLayout);
            yorumCevapBegeniSayisiTxt=itemView.findViewById(R.id.yorumCevapBegeniSayisiTxt);
            cevap=itemView.findViewById(R.id.textView12);
            begeniPaylasmaZamani=itemView.findViewById(R.id.begeniPaylasmaZamani);
            begeniRoundedKullaniciResmi=itemView.findViewById(R.id.begeniRoundedKullaniciResmi);;
            soruId=itemView.findViewById(R.id.soruIdTxt);
            begeniKullaniciAdSoyad.setOnClickListener(this::onClick);
            profilBegeniImageButton.setOnClickListener(this::onClick);
            sorularIcerikLayout.setOnClickListener(this::onClick);
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.begeniKullaniciAdSoyad:
                    ProfilFragment profilFragment = ProfilFragment.newInstance(likesModels.get(getAdapterPosition()).getKullaniciId());
                    ((HomeActivity)context).loadFragment(profilFragment,"Fragment");
                    ((HomeActivity)context).menuDurum(3);
                    break;
                case R.id.profilBegeniImageButton:
                    itemClickListener.onItemClick(view,getAdapterPosition());
                    break;
                case R.id.sorularIcerikLayout:
                    int soruID=likesModels.get(getAdapterPosition()).getSoru_id();
                    Intent intent = new Intent(context, YorumAyrintiActivity.class);
                    intent.putExtra("soruID", soruID);
                    context.startActivity(intent);
                    ((Activity) context).overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
                    break;
            }
        }
    }
    public interface ItemClickListener{
        void onItemClick(View view, int position);
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
                friendly = days + " gn";
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
}
