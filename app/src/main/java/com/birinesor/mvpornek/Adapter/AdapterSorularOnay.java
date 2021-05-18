package com.birinesor.mvpornek.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Fragment.NavBarFragment.ProfilFragment;
import com.birinesor.mvpornek.GlideApp;
import com.birinesor.mvpornek.Models.SorularOnayModel;
import com.birinesor.mvpornek.R;
import com.bumptech.glide.request.RequestOptions;

import org.jetbrains.annotations.NotNull;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterSorularOnay extends RecyclerView.Adapter<AdapterSorularOnay.RecyclerViewAdapter> {

    private List<SorularOnayModel> sorularOnayModels;
    private Context context;
    private AdapterSorularOnay.ItemClickListener itemOnayClickListener;
    private AdapterSorularOnay.ItemClickListener itemRedClickListener;

    public AdapterSorularOnay(List<SorularOnayModel> sorularOnayModels, Context context, AdapterSorularOnay.ItemClickListener itemClickListener , AdapterSorularOnay.ItemClickListener itemRedClickListener) {
        this.sorularOnayModels = sorularOnayModels;
        this.context = context;
        this.itemOnayClickListener = itemClickListener;
        this.itemRedClickListener =itemRedClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public AdapterSorularOnay.RecyclerViewAdapter onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_sayfasi_sorular_icerik, parent, false);
        return new AdapterSorularOnay.RecyclerViewAdapter(view, itemOnayClickListener,itemRedClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterSorularOnay.RecyclerViewAdapter holder, int position) {
    SorularOnayModel sorularOnayModel = sorularOnayModels.get(position);
        String soruOnayAdSoyad=sorularOnayModel.getAdSoyad();
        String soruOnayKullaniciAdi =sorularOnayModel.getKullaniciAdi();
        String soruOnaySoru=sorularOnayModel.getSoru();
        String soruOnayZaman=zamanDonusumu(sorularOnayModel.getZaman());
        String soruOnayKullaniciResim=sorularOnayModel.getProfilFoto();
        holder.soruOnayAdSoyad.setText(soruOnayAdSoyad);
        holder.soruOnayKullaniciAdi.setText(soruOnayKullaniciAdi);
        holder.soruOnaySoru.setText(soruOnaySoru);
        holder.soruOnayZaman.setText(soruOnayZaman);
        holder.soruIdTextView.setText(sorularOnayModel.getSoruId());
        GlideApp.with(context).load(soruOnayKullaniciResim).apply(new RequestOptions().centerCrop()).into(holder.soruOnayKullaniciResim);
    }

    @Override
    public int getItemCount() {
        return sorularOnayModels.size();
    }
    public class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        AdapterSorularOnay.ItemClickListener itemOnayClickListener;
        AdapterSorularOnay.ItemClickListener itemRedClickListener;
        TextView soruOnayKullaniciAdi,soruOnayAdSoyad,soruOnaySoru,soruOnayZaman,soruIdTextView,cevapIdTextView;
        ImageView soruOnayKullaniciResim;
        Button onayButon,redButon;
        public RecyclerViewAdapter(View itemView, AdapterSorularOnay.ItemClickListener itemClickListener,AdapterSorularOnay.ItemClickListener itemRedClickListener) {
            super(itemView);
            this.itemOnayClickListener=itemClickListener;
            this.itemRedClickListener=itemRedClickListener;
            onayButon=itemView.findViewById(R.id.onayBtn);
            redButon=itemView.findViewById(R.id.reddetBtn);
            soruOnayAdSoyad=itemView.findViewById(R.id.adminSorularKullaniciAdSoyad);
            soruOnayKullaniciAdi=itemView.findViewById(R.id.adminSorularKullaniciAdiTxt);
            soruOnaySoru=itemView.findViewById(R.id.textView12);
            soruOnayZaman=itemView.findViewById(R.id.adminSorularPaylasmaZamani);
            soruIdTextView=itemView.findViewById(R.id.soruOnayId);
            cevapIdTextView=itemView.findViewById(R.id.reddetCevapId);
            soruOnayKullaniciResim=itemView.findViewById(R.id.adminSorularRoundedKullaniciResmi);
            onayButon.setOnClickListener(this::onClick);
            redButon.setOnClickListener(this::onClick);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.onayBtn:
                    itemOnayClickListener.onItemClick(view,getPosition());
                    break;
                case R.id.reddetBtn:
                    itemRedClickListener.onItemClick(view,getPosition());
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
