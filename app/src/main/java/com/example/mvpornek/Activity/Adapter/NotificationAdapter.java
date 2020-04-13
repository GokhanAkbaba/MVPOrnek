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

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.BildirimViewHolder> {
    String kullanici_isimleri[], bildirim_aciklama[],bildirim_neden[],bildirim_zaman[];
    int kullaniciResimleri[];
    Context context;

    public NotificationAdapter(String[] kullanici_isimleri, String[] bildirim_aciklama, String[] bildirim_neden, String[] bildirim_zaman, int[] kullaniciResimleri, Context context) {
        this.kullanici_isimleri = kullanici_isimleri;
        this.bildirim_aciklama = bildirim_aciklama;
        this.bildirim_neden = bildirim_neden;
        this.bildirim_zaman = bildirim_zaman;
        this.kullaniciResimleri = kullaniciResimleri;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationAdapter.BildirimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.bildirimler_icerik,parent,false);
        return new BildirimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.BildirimViewHolder holder, int position) {
        holder.bildirimKisi.setText(kullanici_isimleri[position]);
        holder.bildirimAciklama.setText(bildirim_aciklama[position]);
        holder.bildirimNedenTxt.setText(bildirim_neden[position]);
        holder.bildirimZamani.setText(bildirim_zaman[position]);
        holder.bildirimKisiResim.setImageResource(kullaniciResimleri[position]);
    }

    @Override
    public int getItemCount() {
        return bildirim_neden.length;
    }

    public class BildirimViewHolder extends RecyclerView.ViewHolder{
        TextView bildirimKisi,bildirimNedenTxt,bildirimAciklama,bildirimZamani;
        ImageView bildirimKisiResim;
        public BildirimViewHolder(@NonNull View itemView) {
            super(itemView);
            bildirimKisi=itemView.findViewById(R.id.bildirimKisi);
            bildirimNedenTxt=itemView.findViewById(R.id.bildirimNedenTxt);
            bildirimAciklama=itemView.findViewById(R.id.bildirimAciklama);
            bildirimZamani=itemView.findViewById(R.id.bildirimZamani);
            bildirimKisiResim=itemView.findViewById(R.id.bildirimKisiResim);

        }
    }
}
