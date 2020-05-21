package com.example.mvpornek.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvpornek.GlideApp;
import com.example.mvpornek.Model.Kullanıcı.QuestionModel;
import com.example.mvpornek.Model.Response.SoruKaydetResponse;
import com.example.mvpornek.Model.Response.SorularResponse;
import com.example.mvpornek.R;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

public class QuestionAdapterActivity extends RecyclerView.Adapter<QuestionAdapterActivity.RecyclerViewAdapter>{
    private List<QuestionModel> questionModels;
    private Context context;
    private ItemClickListener itemClickListener;

    public QuestionAdapterActivity(List<QuestionModel> questionModels, Context context, ItemClickListener itemClickListener) {
        this.questionModels = questionModels;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public QuestionAdapterActivity.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.sorular_icerik,parent,false);
        return new RecyclerViewAdapter(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapterActivity.RecyclerViewAdapter holder, int position) {
        QuestionModel questionModel=questionModels.get(position);
        String kullaniciAdSoyad=questionModel.getAdSoyad();
        String kullaniciAdi=questionModel.getKullaniciAdi();
        String soru=questionModel.getSoru();
        String zaman=zamanDonusumu(questionModel.getZaman());
        //String yorumSayisi=questionModel.getYorumSayisi();
        String kullaniciProfilResmi=questionModel.getProfilFoto();
        String etiket=questionModel.getEtiketAdi();
        holder.adSoyad.setText(kullaniciAdSoyad);
        //holder.yorum_sayisi.setText(yorumSayisi);
        holder.kullanicAdi.setText(kullaniciAdi);
        holder.etiket.setText(etiket);
        holder.sorular.setText(soru);
        holder.zaman.setText(zaman);

        GlideApp.with(context).load(kullaniciProfilResmi).apply( new RequestOptions().centerCrop()).into(holder.profilResmi);
    }

    @Override
    public int getItemCount() {
        return questionModels.size();
    }

    public class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        ItemClickListener itemClickListener;
        TextView adSoyad,kullanicAdi,etiket,yorum_sayisi,sorular,zaman;
        ImageView profilResmi;
        ConstraintLayout sorularIcerik;
        RecyclerViewAdapter(View itemView,ItemClickListener itemClickListener) {
            super(itemView);
            this.itemClickListener=itemClickListener;
            adSoyad=itemView.findViewById(R.id.kullaniciAdSoyad);
            kullanicAdi=itemView.findViewById(R.id.kullaniciAdiTxt);
            etiket=itemView.findViewById(R.id.textView11);
            yorum_sayisi=itemView.findViewById(R.id.yorumSayisiTxt);
            sorular=itemView.findViewById(R.id.textView12);
            zaman=itemView.findViewById(R.id.soruPaylasmaZamani);
            profilResmi=itemView.findViewById(R.id.roundedKullaniciResmi);
            sorularIcerik=itemView.findViewById(R.id.sorularIcerikLayout);

            sorularIcerik.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view,getAdapterPosition());
        }
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }

    public String zamanDonusumu(String zaman){
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

    }
}