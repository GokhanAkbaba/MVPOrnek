package com.birinesor.mvpornek.Adapter;

import android.annotation.SuppressLint;
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

import com.birinesor.mvpornek.Activity.Ayrıntılar.YorumAyrintiActivity;
import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Activity.Notification.NotificationCommentActivity;
import com.birinesor.mvpornek.Fragment.NavBarFragment.ProfilFragment;
import com.birinesor.mvpornek.GlideApp;
import com.birinesor.mvpornek.Models.Kullanici;
import com.birinesor.mvpornek.Models.YorumAyrintiSorusuModel;
import com.birinesor.mvpornek.Models.YorumAyrintiSorusuYorumlarModel;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.SharedPrefManager;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public
class YorumAyrintiYorumlarAdapter extends RecyclerView.Adapter<YorumAyrintiYorumlarAdapter.RecyclerViewAdapter> {
    private List<YorumAyrintiSorusuYorumlarModel> yorumAyrintiSorusuYorumlarModels;
    private Context context;
    private ItemClickListener itemClickListener;
    private ItemClickListener itemLongClickListener;
    ProfilFragment profilFragment;

    public YorumAyrintiYorumlarAdapter(List<YorumAyrintiSorusuYorumlarModel> yorumAyrintiSorusuYorumlarModels, Context context, YorumAyrintiYorumlarAdapter.ItemClickListener itemClickListener, ItemClickListener itemLongClickListener) {
        this.yorumAyrintiSorusuYorumlarModels = yorumAyrintiSorusuYorumlarModels;
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.itemLongClickListener=itemLongClickListener;
    }

    @NonNull
    @Override
    public YorumAyrintiYorumlarAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.yorum_ayrinti_yorumlar_icerik, parent, false);
        return new YorumAyrintiYorumlarAdapter.RecyclerViewAdapter(view, itemClickListener,itemLongClickListener);
    }

    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    @Override
    public void onBindViewHolder(@NonNull YorumAyrintiYorumlarAdapter.RecyclerViewAdapter holder, int position) {
        YorumAyrintiSorusuYorumlarModel yorumAyrintiSorusuYorumlarModel=yorumAyrintiSorusuYorumlarModels.get(position);
        String adSoyad=yorumAyrintiSorusuYorumlarModel.getAdSoyad();
        String kullaniciAdi=yorumAyrintiSorusuYorumlarModel.getKullaniciAdi();
        String cevapZamani=yorumAyrintiSorusuYorumlarModel.getZaman();
        String cevap=yorumAyrintiSorusuYorumlarModel.getCevap();
        int begeniSayisi=yorumAyrintiSorusuYorumlarModel.getBegeni_sayisi();
        String profilFoto=yorumAyrintiSorusuYorumlarModel.getProfil_foto();

        SimpleDateFormat spf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate= null;
        try {
            newDate = spf.parse(cevapZamani);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf= new SimpleDateFormat("dd MMM yyyy HH:mm");
        cevapZamani = spf.format(newDate);
        holder.yorumlarAyrintiKullaniciAdi.setText("@"+kullaniciAdi);
        holder.yorumlarAyrintiKullaniciAdSoyad.setText(adSoyad);
        holder.yorumlarAyrintiYorumBegeniSayisiTxt.setText(String.valueOf(begeniSayisi));
        holder.yorumlarAyrintiYorumTxt.setText(cevap);
        holder.yorumlarAyrintiYorumZaman.setText(cevapZamani);
        GlideApp.with(context).load(profilFoto).apply(new RequestOptions().centerCrop()).into(holder.yorumlarAyrintiRoundedKullaniciResmi);
        Kullanici kullanici= SharedPrefManager.getInstance(context).getKullanici();
        if(yorumAyrintiSorusuYorumlarModel.getKim_begendi()==kullanici.getId()){
            holder.yorumlarAyrintiYorumBegeniButon.setColorFilter(Color.argb(255, 255, 172, 51));
            holder.yorumlarAyrintiYorumBegeniButon.setTag("secildi");
        }else {
            holder.yorumlarAyrintiYorumBegeniButon.setColorFilter(Color.argb(255, 223, 225, 229));
            holder.yorumlarAyrintiYorumBegeniButon.setTag("secilmedi");
        }

    }

    @Override
    public int getItemCount() {
        return yorumAyrintiSorusuYorumlarModels.size();
    }

    public class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        ItemClickListener itemClickListener;
        ItemClickListener itemLongClickListener;
        ConstraintLayout yorumlarAyrintiLayout;
        TextView yorumlarAyrintiKullaniciAdSoyad;
        TextView yorumlarAyrintiKullaniciAdi;
        TextView yorumlarAyrintiYorumTxt;
        TextView yorumlarAyrintiYorumBegeniSayisiTxt;
        TextView yorumlarAyrintiYorumZaman;
        ImageView yorumlarAyrintiYorumBegeniButon;
        ImageView yorumlarAyrintiRoundedKullaniciResmi;
        public RecyclerViewAdapter(@NonNull View itemView, ItemClickListener itemClickListener,ItemClickListener itemLongClickListener) {
            super(itemView);
            this.itemClickListener=itemClickListener;
            this.itemLongClickListener=itemLongClickListener;
            yorumlarAyrintiRoundedKullaniciResmi=itemView.findViewById(R.id.yorumlarAyrintiRoundedKullaniciResmi);
            yorumlarAyrintiKullaniciAdSoyad=itemView.findViewById(R.id.yorumlarAyrintiKullaniciAdSoyad);
            yorumlarAyrintiKullaniciAdi=itemView.findViewById(R.id.yorumlarAyrintiKullaniciAdi);
            yorumlarAyrintiYorumTxt=itemView.findViewById(R.id.yorumlarAyrintiYorumTxt);
            yorumlarAyrintiYorumBegeniSayisiTxt=itemView.findViewById(R.id.yorumlarAyrintiYorumBegeniSayisiTxt);
            yorumlarAyrintiYorumZaman=itemView.findViewById(R.id.yorumlarAyrintiYorumZaman);
            yorumlarAyrintiYorumBegeniButon=itemView.findViewById(R.id.yorumlarAyrintiYorumBegeniButon);
            yorumlarAyrintiLayout=itemView.findViewById(R.id.yorumlarAyrintiLayout);
            yorumlarAyrintiLayout.setOnLongClickListener(this);
            yorumlarAyrintiYorumBegeniButon.setOnClickListener(this);
            yorumlarAyrintiRoundedKullaniciResmi.setOnClickListener(this);
            yorumlarAyrintiKullaniciAdSoyad.setOnClickListener(this);
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.yorumlarAyrintiYorumBegeniButon:
                    itemClickListener.onItemClick(view,getAdapterPosition());
                    break;
                case R.id.yorumlarAyrintiRoundedKullaniciResmi:
                    profilFragment = ProfilFragment.newInstance(yorumAyrintiSorusuYorumlarModels.get(getAdapterPosition()).getKullaniciId());
                    HomeActivity.getInstance().loadFragment(profilFragment,"Fragment");
                    HomeActivity.getInstance().menuDurum(3);
                    ((YorumAyrintiActivity)context).finish();
                    break;
                case R.id.yorumlarAyrintiKullaniciAdSoyad:
                    profilFragment = ProfilFragment.newInstance(yorumAyrintiSorusuYorumlarModels.get(getAdapterPosition()).getKullaniciId());
                    HomeActivity.getInstance().loadFragment(profilFragment,"Fragment");
                    HomeActivity.getInstance().menuDurum(3);
                    ((YorumAyrintiActivity)context).finish();
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

}
