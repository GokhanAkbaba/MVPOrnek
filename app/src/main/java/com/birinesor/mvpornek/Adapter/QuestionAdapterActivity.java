package com.birinesor.mvpornek.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.birinesor.mvpornek.Activity.Ayrıntılar.YorumAyrintiActivity;
import com.bumptech.glide.request.RequestOptions;
import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Fragment.NavBarFragment.ProfilFragment;
import com.birinesor.mvpornek.GlideApp;
import com.birinesor.mvpornek.Models.QuestionModel;
import com.birinesor.mvpornek.R;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class QuestionAdapterActivity extends RecyclerView.Adapter<QuestionAdapterActivity.RecyclerViewAdapter>{

    private List<QuestionModel> questionModels;
    private Context context;
    private ItemClickListener itemClickListener;
    private ItemClickListener itemLongClickListener;
    ProfilFragment profilFragment;

    public QuestionAdapterActivity(List<QuestionModel> questionModels, Context context, ItemClickListener itemClickListener, ItemClickListener itemLongClickListener) {
        this.questionModels = questionModels;
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.itemLongClickListener=itemLongClickListener;
    }

    @NonNull
    @Override
    public QuestionAdapterActivity.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.sorular_icerik, parent, false);
            return new RecyclerViewAdapter(view, itemClickListener, itemLongClickListener);


    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapterActivity.RecyclerViewAdapter holder, int position) {
            QuestionModel questionModel = questionModels.get(position);
            int soruId = questionModel.getId();
            String kullaniciAdSoyad = questionModel.getAdSoyad();
            String kullaniciAdi = questionModel.getKullaniciAdi();
            String soru = questionModel.getSoru();
            String zaman = zamanDonusumu(questionModel.getZaman());
            String date=questionModel.getZaman();
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
            int yorumSayisi = questionModel.getCevapSayisi();
            String kullaniciProfilResmi = questionModel.getProfilFoto();
            String etiket = questionModel.getEtiketAdi();
            holder.adSoyad.setText(kullaniciAdSoyad);
            holder.kullanicAdi.setText("@"+kullaniciAdi);
            holder.etiket.setText(etiket);
            holder.sorular.setText(soru);
            holder.zaman.setText(zaman);
            holder.soruId.setText(String.valueOf(soruId));
            GlideApp.with(context).load(kullaniciProfilResmi).apply(new RequestOptions().centerCrop()).into(holder.profilResmi);
            if (yorumSayisi == 0) {
                holder.yorum_sayisi.setText("");
            } else {
                holder.yorum_sayisi.setText(String.valueOf(yorumSayisi));
            }


    }

    @Override
    public int getItemCount() {
        return questionModels.size();
    }



    public class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        ItemClickListener itemClickListener;
        ItemClickListener itemLongClickListener;
        TextView adSoyad,kullanicAdi,etiket,yorum_sayisi,sorular,zaman,soruId;
        ImageView profilResmi,soruYorumIcon;
        ConstraintLayout sorularIcerik;
        RecyclerViewAdapter(View itemView,ItemClickListener itemClickListener,ItemClickListener itemLongClickListener) {
            super(itemView);
            this.itemClickListener=itemClickListener;
            this.itemLongClickListener=itemLongClickListener;
            adSoyad=itemView.findViewById(R.id.kullaniciAdSoyad);
            kullanicAdi=itemView.findViewById(R.id.kullaniciAdiTxt);
            etiket=itemView.findViewById(R.id.textView11);
            yorum_sayisi=itemView.findViewById(R.id.yorumSayisiTxt);
            sorular=itemView.findViewById(R.id.textView12);
            zaman=itemView.findViewById(R.id.soruPaylasmaZamani);
            profilResmi=itemView.findViewById(R.id.sorularIcerikKullaniciResmi);
            sorularIcerik=itemView.findViewById(R.id.sorularIcerikLayout);
            soruId=itemView.findViewById(R.id.soruIdTxt);
            soruYorumIcon=itemView.findViewById(R.id.soruYorumIcon);
            sorularIcerik.setOnLongClickListener(this::onLongClick);
            sorularIcerik.setOnClickListener(this);
            soruYorumIcon.setOnClickListener(this::onClick);
            adSoyad.setOnClickListener(this::onClick);
            profilResmi.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.kullaniciAdSoyad:
                    profilFragment = ProfilFragment.newInstance(questionModels.get(getAdapterPosition()).getKullaniciId());
                    ((HomeActivity)context).loadFragment(profilFragment,"Fragment");
                    ((HomeActivity)context).menuDurum(3);
                    break;
                case R.id.kullaniciAdiTxt:
                    profilFragment = ProfilFragment.newInstance(questionModels.get(getAdapterPosition()).getKullaniciId());
                    ((HomeActivity)context).loadFragment(profilFragment,"Fragment");
                    ((HomeActivity)context).menuDurum(3);
                    break;
                case R.id.sorularIcerikKullaniciResmi:
                    profilFragment = ProfilFragment.newInstance(questionModels.get(getAdapterPosition()).getKullaniciId());
                    ((HomeActivity)context).loadFragment(profilFragment,"Fragment");
                    ((HomeActivity)context).menuDurum(3);
                    break;
                case R.id.soruYorumIcon:
                    itemClickListener.onItemClick(view,getAdapterPosition());
                    break;
                case R.id.sorularIcerikLayout:
                    int soruID=questionModels.get(getAdapterPosition()).getId();
                    Intent intent = new Intent(context, YorumAyrintiActivity.class);
                    intent.putExtra("soruID", soruID);
                    context.startActivity(intent);
                    ((Activity) context).overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
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
            if(seconds < 0){
                seconds=seconds*-1;
            }
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