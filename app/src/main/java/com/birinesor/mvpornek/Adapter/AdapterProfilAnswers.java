package com.birinesor.mvpornek.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Fragment.NavBarFragment.ProfilFragment;
import com.birinesor.mvpornek.GlideApp;
import com.birinesor.mvpornek.Models.AnswersModel;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.SharedPrefManager;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterProfilAnswers extends RecyclerView.Adapter<AdapterProfilAnswers.RecyclerViewAdapter>{
    private List<AnswersModel>  answersModels;
    private Context context;
    private ItemClickListener itemClickListener;
    private ItemClickListener itemLongClickListener;

    public AdapterProfilAnswers(List<AnswersModel> answersModels, Context context,ItemClickListener itemClickListener,ItemClickListener itemLongClickListener) {
        this.answersModels = answersModels;
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.itemLongClickListener =itemLongClickListener;
    }

    @NonNull
    @Override
    public AdapterProfilAnswers.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cevaplar_icerik, parent, false);
        return new AdapterProfilAnswers.RecyclerViewAdapter(view, itemClickListener,itemLongClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProfilAnswers.RecyclerViewAdapter holder, int position) {
        AnswersModel answersModel=answersModels.get(position);
        String kullaniciAdSoyad=answersModel.getAdSoyad();
        String kullaniciAdi =answersModel.getKullaniciAdi();
        String cevap=answersModel.getCevap();
        String cevaplananKisi=answersModel.getSoru_soran();
        String zaman=zamanDonusumu(answersModel.getZaman());
        String profilFoto=answersModel.getProfilFoto();
        int begeniSayisi=answersModel.getBegeni_sayisi();
        holder.profilCevaplananKisiTxt.setText(cevaplananKisi);
        holder.profilCevapKullaniciAdSoyad.setText(kullaniciAdSoyad);
        holder.profilCevapKullaniciAdiTxt.setText("@"+kullaniciAdi);
        holder.cevap.setText(cevap);
        holder.profilCevapPaylasmaZamani.setText(zaman);
        holder.yorumCevapBegeniSayisiTxt.setText(String.valueOf(begeniSayisi));
        GlideApp.with(context).load(profilFoto).apply(new RequestOptions().centerCrop()).into(holder.profilCevapRoundedKullaniciResmi);
        Kullanici kullanici= SharedPrefManager.getInstance(context).getKullanici();
        if(answersModel.getKim_begendi()==kullanici.getId()){
            holder.profilCevapImageButton.setColorFilter(Color.argb(255, 255, 172, 51));
            holder.profilCevapImageButton.setTag("secildi");
        }else {
            holder.profilCevapImageButton.setColorFilter(Color.argb(255, 223, 225, 229));
            holder.profilCevapImageButton.setTag("secilmedi");
        }
    }

    @Override
    public int getItemCount() {
        return answersModels.size();
    }
    public class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        ItemClickListener itemClickListener;
        ItemClickListener itemLongClickListener;
        TextView profilCevapKullaniciAdSoyad,profilCevapKullaniciAdiTxt,profilCevaplananKisiTxt,yorumCevapBegeniSayisiTxt,cevap,profilCevapPaylasmaZamani,soruId;
        ImageView profilCevapRoundedKullaniciResmi,profilCevapImageButton;
        ConstraintLayout profilCevaplarIcerikLayout;
        RecyclerViewAdapter(View itemView, ItemClickListener itemClickListener,ItemClickListener itemLongClickListener) {
            super(itemView);
            this.itemClickListener=itemClickListener;
            this.itemLongClickListener=itemLongClickListener;
            profilCevapImageButton=itemView.findViewById(R.id.profilCevapImageButton);
            profilCevapKullaniciAdSoyad=itemView.findViewById(R.id.profilCevapKullaniciAdSoyad);
            profilCevapKullaniciAdiTxt=itemView.findViewById(R.id.profilCevapKullaniciAdiTxt);
            profilCevaplananKisiTxt=itemView.findViewById(R.id.profilCevaplananKisiTxt);
            yorumCevapBegeniSayisiTxt=itemView.findViewById(R.id.yorumCevapBegeniSayisiTxt);
            cevap=itemView.findViewById(R.id.textView12);
            profilCevapPaylasmaZamani=itemView.findViewById(R.id.profilCevapPaylasmaZamani);
            profilCevapRoundedKullaniciResmi=itemView.findViewById(R.id.profilCevapRoundedKullaniciResmi);
            profilCevaplarIcerikLayout=itemView.findViewById(R.id.profilCevaplarIcerikLayout);
            profilCevaplarIcerikLayout.setOnLongClickListener(this);
            soruId=itemView.findViewById(R.id.soruIdTxt);
            profilCevapKullaniciAdSoyad.setOnClickListener(this::onClick);
            profilCevapImageButton.setOnClickListener(this::onClick);
            profilCevaplananKisiTxt.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.profilCevapImageButton:
                        itemClickListener.onItemClick(view,getAdapterPosition());
                    break;
                case R.id.profilCevaplananKisiTxt:
                    ProfilFragment profilFragment = ProfilFragment.newInstance(answersModels.get(getAdapterPosition()).getSoru_soran_id());
                    ((HomeActivity)context).loadFragment(profilFragment,"Fragment");
                    ((HomeActivity)context).menuDurum(3);
                    break;
            }

        }

        @Override
        public boolean onLongClick(View view) {
            itemLongClickListener.onItemClick(view,getAdapterPosition());
            return false;
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
