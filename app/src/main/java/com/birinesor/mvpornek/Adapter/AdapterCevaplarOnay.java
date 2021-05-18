package com.birinesor.mvpornek.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.birinesor.mvpornek.GlideApp;
import com.birinesor.mvpornek.Models.CevaplarOnayModels;
import com.birinesor.mvpornek.Models.SorularOnayModel;
import com.birinesor.mvpornek.R;
import com.bumptech.glide.request.RequestOptions;

import org.jetbrains.annotations.NotNull;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public
class AdapterCevaplarOnay  extends RecyclerView.Adapter<AdapterCevaplarOnay.RecyclerViewAdapter> {

    private List<CevaplarOnayModels> cevaplarOnayModels;
    private Context context;
    private AdapterCevaplarOnay.ItemClickListener itemOnayClickListener;
    private AdapterCevaplarOnay.ItemClickListener itemRedClickListener;

    public AdapterCevaplarOnay(List<CevaplarOnayModels> cevaplarOnayModels, Context context, AdapterCevaplarOnay.ItemClickListener itemClickListener , AdapterCevaplarOnay.ItemClickListener itemRedClickListener) {
        this.cevaplarOnayModels = cevaplarOnayModels;
        this.context = context;
        this.itemOnayClickListener = itemClickListener;
        this.itemRedClickListener =itemRedClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public AdapterCevaplarOnay.RecyclerViewAdapter onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_sayfasi_cevaplar_icerik, parent, false);
        return new AdapterCevaplarOnay.RecyclerViewAdapter(view, itemOnayClickListener,itemRedClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterCevaplarOnay.RecyclerViewAdapter holder, int position) {
        CevaplarOnayModels cevaplarOnayModel = cevaplarOnayModels.get(position);
        String cevapOnayAdSoyad=cevaplarOnayModel.getAdSoyad();
        String cevapOnayKullaniciAdi =cevaplarOnayModel.getKullaniciAdi();
        String cevapOnaySoru=cevaplarOnayModel.getCevap();
        String cevapOnayZaman=zamanDonusumu(cevaplarOnayModel.getZaman());
        String cevapOnayKullaniciResim=cevaplarOnayModel.getProfilFoto();
        holder.cevapOnayAdSoyad.setText(cevapOnayAdSoyad);
        holder.cevapOnayKullaniciAdi.setText(cevapOnayKullaniciAdi);
        holder.cevapOnayCevap.setText(cevapOnaySoru);
        holder.cevapOnayZaman.setText(cevapOnayZaman);
        holder.cevapIdTextView.setText(cevaplarOnayModel.getCevapId());
        GlideApp.with(context).load(cevapOnayKullaniciResim).apply(new RequestOptions().centerCrop()).into(holder.cevapOnayKullaniciResim);
    }

    @Override
    public int getItemCount() {
        return cevaplarOnayModels.size();
    }
    public class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        AdapterCevaplarOnay.ItemClickListener itemOnayClickListener;
        AdapterCevaplarOnay.ItemClickListener itemRedClickListener;
        TextView cevapOnayKullaniciAdi,cevapOnayAdSoyad,cevapOnayCevap,cevapOnayZaman,cevapIdTextView;
        ImageView cevapOnayKullaniciResim;
        Button onayButon,redButon;
        public RecyclerViewAdapter(View itemView, AdapterCevaplarOnay.ItemClickListener itemClickListener,AdapterCevaplarOnay.ItemClickListener itemRedClickListener) {
            super(itemView);
            this.itemOnayClickListener=itemClickListener;
            this.itemRedClickListener=itemRedClickListener;
            onayButon=itemView.findViewById(R.id.onayBtn);
            redButon=itemView.findViewById(R.id.reddetBtn);
            cevapOnayAdSoyad=itemView.findViewById(R.id.adminCevaplarKullaniciAdSoyad);
            cevapOnayKullaniciAdi=itemView.findViewById(R.id.adminCevaplarKullaniciAdiTxt);
            cevapOnayCevap=itemView.findViewById(R.id.textView12);
            cevapOnayZaman=itemView.findViewById(R.id.adminCevaplarPaylasmaZamani);
            cevapIdTextView=itemView.findViewById(R.id.cevapOnayId);
            cevapOnayKullaniciResim=itemView.findViewById(R.id.adminCevaplarRoundedKullaniciResmi);
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
