package com.example.mvpornek.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpornek.R;

public class QuestionAdapterActivity extends RecyclerView.Adapter<QuestionAdapterActivity.MyViewHolder>
{
    String kullanici_isimleri[], yorum_sayisi[],sorular[],etiket[];
    int kullaniciResimleri[];
    Context context;
    public QuestionAdapterActivity(Context ct, String kullanici_isimleri[], String yorum_sayisi[], String sorular[],String etiket[], int kullaniciImg[])
    {
        this.context=ct;
        this.kullanici_isimleri=kullanici_isimleri;
        this.yorum_sayisi=yorum_sayisi;
        this.etiket=etiket;
        this.sorular=sorular;
        this.kullaniciResimleri=kullaniciImg;

    }
    @NonNull
    @Override
    public QuestionAdapterActivity.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.sorular_icerik,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapterActivity.MyViewHolder holder, int position) {
        holder.adSoyad.setText(kullanici_isimleri[position]);
        holder.yorum_sayisi.setText(yorum_sayisi[position]);
        holder.etiket.setText(etiket[position]);
        holder.sorular.setText(sorular[position]);
        holder.profilResmi.setImageResource(kullaniciResimleri[position]);

    }

    @Override
    public int getItemCount() {
        return kullanici_isimleri.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView adSoyad,etiket,yorum_sayisi,sorular;
        ImageView profilResmi;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            adSoyad=itemView.findViewById(R.id.kullaniciAdSoyad);
            etiket=itemView.findViewById(R.id.textView11);
            yorum_sayisi=itemView.findViewById(R.id.yorumSayisiTxt);
            sorular=itemView.findViewById(R.id.textView12);
            profilResmi=itemView.findViewById(R.id.kullaniciResmi);
        }
    }
}
