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
import com.birinesor.mvpornek.GlideApp;
import com.birinesor.mvpornek.Models.CommentModel;
import com.birinesor.mvpornek.Models.Kullanici;

import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.SharedPrefManager;


import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.RecyclerViewAdapter> {
    private List<CommentModel> commentModels;
    private Context context;
    private ItemClickListener itemClickListener;
    private ItemClickListener begeniClickListener;


    public CommentAdapter(List<CommentModel> commentModels, Context context, ItemClickListener itemClickListener,ItemClickListener begeniClickListener) {
        this.commentModels = commentModels;
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.begeniClickListener=begeniClickListener;



    }

    @NonNull
    @Override
    public CommentAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.soru_yorum_ekrani,parent,false);
        return new RecyclerViewAdapter(view,itemClickListener,begeniClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.RecyclerViewAdapter holder, int position) {
        CommentModel commentModel=commentModels.get(position);
        String yorum=commentModel.getCevap();
        String kullaniciAdi=commentModel.getKullaniciAdi();
        String kullaniciProfilResmi=commentModel.getProfilFoto();
        String begeniSayisi=commentModel.getBegeniSayisi();
        holder.yorum.setText(yorum);
        holder.kullaniciAdi.setText("@"+kullaniciAdi);
        holder.begeniSayisi.setText(begeniSayisi);
        GlideApp.with(context).load(kullaniciProfilResmi).apply( new RequestOptions().centerCrop()).into(holder.profilResmi);
        Kullanici kullanici= SharedPrefManager.getInstance(context).getKullanici();
        if(commentModel.getKimBegendi()==kullanici.getId()){
            holder.begeniButonu.setColorFilter(Color.argb(255, 255, 172, 51));
            holder.begeniButonu.setTag("secildi");
        }else {
            holder.begeniButonu.setColorFilter(Color.argb(255, 223, 225, 229));
            holder.begeniButonu.setTag("secilmedi");
        }

    }

    @Override
    public int getItemCount() {
        return commentModels.size();
    }

    public class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnLongClickListener,View.OnClickListener{
        ItemClickListener itemClickListener;
        ItemClickListener begeniClickListener;
        ImageView profilResmi;
        ImageView begeniButonu;
        TextView kullaniciAdi,yorum,begeniSayisi;
        ConstraintLayout yorumlarIcerik;
        RecyclerViewAdapter(View itemView, ItemClickListener itemClickListener,ItemClickListener begeniClickListener){
            super(itemView);
            this.itemClickListener=itemClickListener;
            this.begeniClickListener=begeniClickListener;
            kullaniciAdi=itemView.findViewById(R.id.yorumKullaniciAdi);
            yorum=itemView.findViewById(R.id.yorumTxt);
            begeniSayisi=itemView.findViewById(R.id.begeniSayisiTxt);
            profilResmi=itemView.findViewById(R.id.roundedKullaniciResmi);
            yorumlarIcerik=itemView.findViewById(R.id.yorumlarIcerikLayout);
            begeniButonu=itemView.findViewById(R.id.imageButton);
            begeniButonu.setOnClickListener(this::onClick);
            yorumlarIcerik.setOnLongClickListener(this::onLongClick);


        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onItemClick(view,getAdapterPosition());
            return false;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.imageButton:
                    begeniClickListener.onItemClick(view,getAdapterPosition());
                break;
            }

        }
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }




}
