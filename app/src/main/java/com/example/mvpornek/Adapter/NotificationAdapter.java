package com.example.mvpornek.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpornek.Models.NotificationModel;
import com.example.mvpornek.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.BildirimViewHolder> {
    private List<NotificationModel> notificationModelList;
    Context context;

    public NotificationAdapter(List<NotificationModel> notificationModelList) {

        this.notificationModelList=notificationModelList;
    }

    @NonNull
    @Override
    public NotificationAdapter.BildirimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new NotificationAdapter.BildirimViewHolder(LayoutInflater.from(context).inflate(R.layout.bildirimler_icerik,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.BildirimViewHolder holder, int position) {

        NotificationModel notificationModel=notificationModelList.get(position);
        String kullaniciAdi=notificationModel.getKullaniciAdi();
        String bildirimAciklama=notificationModel.getBildirimAciklama();
        String bildirimNeden=notificationModel.getBildirimNeden();
        String bildirimZaman=notificationModel.getBildirimZaman();
        int kullaniciResimi=notificationModel.getKullaniciResimleri();
        holder.bildirimKisi.setText("@"+kullaniciAdi);
        holder.bildirimAciklama.setText(bildirimAciklama);
        holder.bildirimNedenTxt.setText(bildirimNeden);
        holder.bildirimZamani.setText(bildirimZaman);
        holder.bildirimKisiResim.setImageResource(kullaniciResimi);
    }

    @Override
    public int getItemCount() {
        return notificationModelList.size();
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
