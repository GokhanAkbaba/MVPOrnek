package com.example.mvpornek.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.mvpornek.Activity.HomeActivity;
import com.example.mvpornek.Activity.NotificationCommentActivity;
import com.example.mvpornek.Fragment.NavBarFragment.ProfilFragment;
import com.example.mvpornek.GlideApp;
import com.example.mvpornek.Models.Kullanici;
import com.example.mvpornek.Models.NotificationCommetAndQuestionModel;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public
class NotificationCommentAndQuestionAdapter extends RecyclerView.Adapter<NotificationCommentAndQuestionAdapter.RecyclerViewAdapter> {
    private List<NotificationCommetAndQuestionModel> notificationCommetAndQuestionModels;
    private Context context;
    private ItemClickListener itemClickListener;
    Fragment profilFragment=null;
    public NotificationCommentAndQuestionAdapter(List<NotificationCommetAndQuestionModel> notificationCommetAndQuestionModels, Context context,ItemClickListener itemClickListener) {
        this.notificationCommetAndQuestionModels = notificationCommetAndQuestionModels;
        this.context = context;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public NotificationCommentAndQuestionAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notificaiton_comment_and_question_icerik, parent, false);
        return new NotificationCommentAndQuestionAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationCommentAndQuestionAdapter.RecyclerViewAdapter holder, int position) {
    NotificationCommetAndQuestionModel notificationCommetAndQuestionModel=notificationCommetAndQuestionModels.get(position);
    String yorumKullaniniciAdi=notificationCommetAndQuestionModel.getKullaniciAdi();
    String yorumTxt=notificationCommetAndQuestionModel.getCevap();
    int begeniSayisi=notificationCommetAndQuestionModel.getBegeniSayisi();
    String yorumZaman=notificationCommetAndQuestionModel.getCevapZaman();
    String yorumKullaniciResmi=notificationCommetAndQuestionModel.getProfilFoto();
    SimpleDateFormat spf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date newDate= null;
        try {
            newDate = spf.parse(yorumZaman);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf= new SimpleDateFormat("dd MMM yyyy HH:mm");
        yorumZaman = spf.format(newDate);
        holder.notificatioYorumKullaniciAdi.setText(yorumKullaniniciAdi);
    holder.notificationYorumTxt.setText(yorumTxt);
    holder.notificationYorumZaman.setText(yorumZaman);
    holder.notificationYorumBegeniSayisiTxt.setText(String.valueOf(begeniSayisi));
    GlideApp.with(context).load(yorumKullaniciResmi).apply(new RequestOptions().centerCrop()).into(holder.notificationYorumRoundedKullaniciResmi);
    Kullanici kullanici= SharedPrefManager.getInstance(context).getKullanici();
    if(notificationCommetAndQuestionModel.getKimBegendi()==kullanici.getId()){
        holder.notificationYorumBegeniButon.setColorFilter(Color.argb(255, 255, 172, 51));
        holder.notificationYorumBegeniButon.setTag("secildi");
    }else {
        holder.notificationYorumBegeniButon.setColorFilter(Color.argb(255, 223, 225, 229));
        holder.notificationYorumBegeniButon.setTag("secilmedi");
    }
    }

    @Override
    public int getItemCount() {
        return notificationCommetAndQuestionModels.size();
    }



    public class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        ItemClickListener itemClickListener;
        TextView notificatioYorumKullaniciAdi,notificationYorumTxt,notificationYorumBegeniSayisiTxt,notificationYorumZaman;
        ImageView notificationYorumBegeniButon,notificationYorumRoundedKullaniciResmi;
        RecyclerViewAdapter(View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            this.itemClickListener=itemClickListener;
            notificatioYorumKullaniciAdi=itemView.findViewById(R.id.notificatioYorumKullaniciAdi);
            notificationYorumTxt=itemView.findViewById(R.id.notificationYorumTxt);
            notificationYorumBegeniSayisiTxt=itemView.findViewById(R.id.notificationYorumBegeniSayisiTxt);
            notificationYorumZaman=itemView.findViewById(R.id.notificationYorumZaman);
            notificationYorumBegeniButon=itemView.findViewById(R.id.notificationYorumBegeniButon);
            notificationYorumRoundedKullaniciResmi=itemView.findViewById(R.id.notificationYorumRoundedKullaniciResmi);
            notificationYorumBegeniButon.setOnClickListener(this::onClick);
            notificationYorumRoundedKullaniciResmi.setOnClickListener(this::onClick);
            notificatioYorumKullaniciAdi.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.notificatioYorumKullaniciAdi:
                    profilFragment = ProfilFragment.newInstance(notificationCommetAndQuestionModels.get(getAdapterPosition()).getKullaniciID());
                    HomeActivity.getInstance().loadFragment(profilFragment,"Fragment");
                    HomeActivity.getInstance().menuDurum(3);
                    ((NotificationCommentActivity)context).finish();
                    break;
                case R.id.notificationYorumRoundedKullaniciResmi:
                    profilFragment = ProfilFragment.newInstance(notificationCommetAndQuestionModels.get(getAdapterPosition()).getKullaniciID());
                    HomeActivity.getInstance().loadFragment(profilFragment,"Fragment");
                    HomeActivity.getInstance().menuDurum(3);
                    ((NotificationCommentActivity)context).finish();
                    break;
                case R.id.notificationYorumBegeniButon:
                    itemClickListener.onItemClick(view,getAdapterPosition());
                    break;
            }
        }
    }
    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
