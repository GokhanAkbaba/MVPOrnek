package com.birinesor.mvpornek.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.request.RequestOptions;
import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Fragment.NavBarFragment.ProfilFragment;
import com.birinesor.mvpornek.GlideApp;
import com.birinesor.mvpornek.Models.SearchQuestionModel;
import com.birinesor.mvpornek.R;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SearchAdapterQuestion extends RecyclerView.Adapter<SearchAdapterQuestion.RecyclerViewAdapter> {
    private List<SearchQuestionModel> searchQuestionModels;
    private Context context;
    private SearchAdapterQuestion.ItemClickListener itemClickListener;
    ProfilFragment profilFragment;


    public SearchAdapterQuestion(List<SearchQuestionModel> searchQuestionModels, Context context, SearchAdapterQuestion.ItemClickListener itemClickListener) {
        this.searchQuestionModels = searchQuestionModels;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public SearchAdapterQuestion.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.kullanici_arama_soru_icerik, parent, false);
        return new SearchAdapterQuestion.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapterQuestion.RecyclerViewAdapter holder, int position) {

        SearchQuestionModel searchQuestionModel=searchQuestionModels.get(position);

        String kullaniciAdSoyad = searchQuestionModel.getAd_soyad();
        String kullaniciAdi = searchQuestionModel.getKullanici_adi();
        String soru = searchQuestionModel.getSoru();
        String zaman = zamanDonusumu(searchQuestionModel.getZaman());
        int yorumSayisi = searchQuestionModel.getYorum_sayisi();
        String kullaniciProfilResmi = searchQuestionModel.getProfil_foto();
        String etiket = searchQuestionModel.getEtiket();
        String date=searchQuestionModel.getZaman();
        SimpleDateFormat spf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate= null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf= new SimpleDateFormat("dd MMM yyyy");
        if (zaman == "s"){
            zaman= spf.format(newDate);
        }
        holder.adSoyad.setText(kullaniciAdSoyad);
        holder.kullanicAdi.setText("@"+kullaniciAdi);
        holder.etiket.setText(etiket);
        holder.sorular.setText(soru);
        holder.zaman.setText(zaman);
        GlideApp.with(context).load(kullaniciProfilResmi).apply(new RequestOptions().centerCrop()).into(holder.profilResmi);
        if (yorumSayisi == 0) {
            holder.yorum_sayisi.setText("");
        } else {
            holder.yorum_sayisi.setText(String.valueOf(yorumSayisi));
        }
    }

    @Override
    public int getItemCount() {
       return searchQuestionModels.size();
    }

    public class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        SearchAdapterQuestion.ItemClickListener itemClickListener;

        TextView adSoyad;
        TextView kullanicAdi;
        TextView etiket;
        TextView yorum_sayisi;
        TextView sorular;
        TextView zaman;

        ImageView profilResmi;
        ImageView soruYorumIcon;

        RecyclerViewAdapter(View itemView, SearchAdapterQuestion.ItemClickListener itemClickListener) {
            super(itemView);

            this.itemClickListener=itemClickListener;
            adSoyad=itemView.findViewById(R.id.aramaKullaniciAdSoyad);
            kullanicAdi=itemView.findViewById(R.id.aramaKullaniciAdiTxt);
            etiket=itemView.findViewById(R.id.aramaSoruEtiket);
            yorum_sayisi=itemView.findViewById(R.id.aramaYorumSayisiTxt);
            sorular=itemView.findViewById(R.id.aramaSoruAlanı);
            zaman=itemView.findViewById(R.id.aramaSoruPaylasmaZamani);
            profilResmi=itemView.findViewById(R.id.aramaRoundedKullaniciResmi);
            soruYorumIcon=itemView.findViewById(R.id.aramaSoruYorumIcon);
            soruYorumIcon.setOnClickListener(this::onClick);
            adSoyad.setOnClickListener(this::onClick);
            kullanicAdi.setOnClickListener(this::onClick);
            profilResmi.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.aramaKullaniciAdSoyad:
                    profilFragment = ProfilFragment.newInstance(searchQuestionModels.get(getAdapterPosition()).getKullanici_id());
                    ((HomeActivity)context).loadFragment(profilFragment,"Fragment");
                    break;
                case R.id.aramaKullaniciAdiTxt:
                    profilFragment = ProfilFragment.newInstance(searchQuestionModels.get(getAdapterPosition()).getKullanici_id());
                    ((HomeActivity)context).loadFragment(profilFragment,"Fragment");
                    break;
                case R.id.aramaRoundedKullaniciResmi:
                    profilFragment = ProfilFragment.newInstance(searchQuestionModels.get(getAdapterPosition()).getKullanici_id());
                    ((HomeActivity)context).loadFragment(profilFragment,"Fragment");
                    break;
                case R.id.aramaSoruYorumIcon:
                    itemClickListener.onItemClick(view,getAdapterPosition());
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
}
