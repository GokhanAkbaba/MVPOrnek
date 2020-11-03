package com.birinesor.mvpornek.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Fragment.NavBarFragment.ProfilFragment;
import com.birinesor.mvpornek.GlideApp;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.Response.UserSearchListResponse;

import java.util.List;

public class AdapterSearchUsers  extends RecyclerView.Adapter<AdapterSearchUsers.RecyclerViewAdapter> {
    private List<UserSearchListResponse> searchListResponses;
    private Context context;
    private AdapterSearchUsers.ItemClickListener itemClickListener;
    ProfilFragment profilFragment;

    public AdapterSearchUsers(List<UserSearchListResponse> searchListResponses, Context context, AdapterSearchUsers.ItemClickListener itemClickListener) {
        this.searchListResponses = searchListResponses;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public AdapterSearchUsers.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_users_icerik, parent, false);
        return new AdapterSearchUsers.RecyclerViewAdapter(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSearchUsers.RecyclerViewAdapter holder, int position) {
        UserSearchListResponse searchListResponse=searchListResponses.get(position);
        String kullaniciAdSoyad = searchListResponse.getAdSoyad();
        String kullaniciAdi=searchListResponse.getKullanici_adi();
        String kullaniciProfilResmi = searchListResponse.getProfil_foto();
        holder.adSoyad.setText(kullaniciAdSoyad);
        holder.kullaniciAdi.setText("@"+kullaniciAdi);
        GlideApp.with(context).load(kullaniciProfilResmi).apply(new RequestOptions().centerCrop()).into(holder.profilResmi);
    }

    @Override
    public int getItemCount() {
        return searchListResponses.size();
    }

    public class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        AdapterSearchUsers.ItemClickListener itemClickListener;
        TextView adSoyad,kullaniciAdi;
        ImageView profilResmi;
        RecyclerViewAdapter(View itemView, AdapterSearchUsers.ItemClickListener itemClickListener) {
            super(itemView);
            this.itemClickListener=itemClickListener;
            adSoyad=itemView.findViewById(R.id.textView29);
            kullaniciAdi=itemView.findViewById(R.id.textView30);
            profilResmi=itemView.findViewById(R.id.kullaniciResimAlani);
            kullaniciAdi.setOnClickListener(this::onClick);
            profilResmi.setOnClickListener(this::onClick);
            adSoyad.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.kullaniciResimAlani:
                    System.out.println("resime tıkla"+searchListResponses.get(getAdapterPosition()).getKullaniciId());
                    profilFragment = ProfilFragment.newInstance(searchListResponses.get(getAdapterPosition()).getKullaniciId());
                    ((HomeActivity)context).loadFragment(profilFragment,"Fragment");

                    break;
                case R.id.textView29:
                    System.out.println("ad soyad"+searchListResponses.get(getAdapterPosition()).getKullaniciId());
                    profilFragment = ProfilFragment.newInstance(searchListResponses.get(getAdapterPosition()).getKullaniciId());
                    ((HomeActivity)context).loadFragment(profilFragment,"Fragment");
                    break;
                case R.id.textView30:
                    System.out.println("kullaniciAdi"+searchListResponses.get(getAdapterPosition()).getKullaniciId());
                    profilFragment = ProfilFragment.newInstance(searchListResponses.get(getAdapterPosition()).getKullaniciId());
                    ((HomeActivity)context).loadFragment(profilFragment,"Fragment");
            }

            //itemClickListener.onItemClick(view,getAdapterPosition());
        }
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
