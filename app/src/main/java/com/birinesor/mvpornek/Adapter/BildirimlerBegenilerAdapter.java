package com.birinesor.mvpornek.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Activity.Notification.NotificationLikeActivity;
import com.birinesor.mvpornek.Fragment.NavBarFragment.ProfilFragment;
import com.birinesor.mvpornek.GlideApp;
import com.birinesor.mvpornek.Models.BildirimlerBegenilerModel;
import com.birinesor.mvpornek.R;

import java.util.List;

public
class BildirimlerBegenilerAdapter extends RecyclerView.Adapter<BildirimlerBegenilerAdapter.BegenilerViewHolder> {
    private List<BildirimlerBegenilerModel> bildirimlerCevaplarModelList;
    private Context context;
    private ItemClickListener itemClickListener;
    ProfilFragment profilFragment;

    public BildirimlerBegenilerAdapter(List<BildirimlerBegenilerModel> bildirimlerCevaplarModelList, Context context, ItemClickListener itemClickListener) {
        this.bildirimlerCevaplarModelList = bildirimlerCevaplarModelList;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public BildirimlerBegenilerAdapter.BegenilerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.bildirimler_cevaplar_icerik,parent,false);
        return new BegenilerViewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BildirimlerBegenilerAdapter.BegenilerViewHolder holder, int position) {
        BildirimlerBegenilerModel bildirimlerBegenilerModel=bildirimlerCevaplarModelList.get(position);
        String profilResmi=bildirimlerBegenilerModel.getProfilFoto();
        String cevapTxt=bildirimlerBegenilerModel.getCevap();
        String iletiTxt=bildirimlerBegenilerModel.getAdSoyad();
        holder.textView32.setText("adlı kullanıcı cevabınızı beğendi");
        holder.bildirimlerCevap.setText(cevapTxt);
        holder.bildirimlerCevapİleti.setText(iletiTxt);
        GlideApp.with(context).load(profilResmi).apply( new RequestOptions().centerCrop()).into(holder.bildirimlerCevaplarKullaniciResimAlani);

    }

    @Override
    public int getItemCount() {
        return bildirimlerCevaplarModelList.size();
    }

    public class BegenilerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView bildirimlerCevapİleti,bildirimlerCevap,textView32;
        ImageView bildirimlerCevaplarKullaniciResimAlani;
        ItemClickListener itemClickListener;
        ConstraintLayout bildirimCevaplarLayout;
        public BegenilerViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            this.itemClickListener=itemClickListener;
            bildirimlerCevapİleti=itemView.findViewById(R.id.bildirimlerCevapİleti);
            bildirimlerCevap=itemView.findViewById(R.id.bildirimlerCevapTxt);
            bildirimCevaplarLayout=itemView.findViewById(R.id.bildirimCevaplarLayout);
            textView32=itemView.findViewById(R.id.textView32);
            bildirimlerCevaplarKullaniciResimAlani=itemView.findViewById(R.id.bildirimlerCevaplarKullaniciResimAlani);
            bildirimlerCevaplarKullaniciResimAlani.setOnClickListener(this::onClick);
            bildirimCevaplarLayout.setOnClickListener(this::onClick);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.bildirimlerCevaplarKullaniciResimAlani:
                    profilFragment = ProfilFragment.newInstance(bildirimlerCevaplarModelList.get(getAdapterPosition()).getKullaniciID());
                    ((HomeActivity)context).loadFragment(profilFragment,"Fragment");
                    ((HomeActivity)context).menuDurum(3);
                    break;
                case R.id.bildirimCevaplarLayout:
                    int cevapID=bildirimlerCevaplarModelList.get(getAdapterPosition()).getCevapID();
                    int soruID=bildirimlerCevaplarModelList.get(getAdapterPosition()).getSoruID();
                    Intent intent = new Intent(context, NotificationLikeActivity.class);
                    intent.putExtra("cevapID", cevapID);
                    intent.putExtra("soruID", soruID);
                    context.startActivity(intent);
                    ((Activity) context).overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
                    break;
            }
        }
    }
    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
