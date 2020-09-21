package com.example.mvpornek.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpornek.Activity.HomeActivity;
import com.example.mvpornek.Fragment.KullaniciIcerikFragment;
import com.example.mvpornek.Fragment.NavBarFragment.HomeFragment;
import com.example.mvpornek.Fragment.NavBarFragment.ProfilFragment;
import com.example.mvpornek.Fragment.Search.AramaIcerikFragment;
import com.example.mvpornek.Models.QuestionModel;
import com.example.mvpornek.R;
import com.example.mvpornek.Response.SearchListResponse;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.RecyclerViewAdapter> {
    private List<SearchListResponse> searchListResponses;
    private Context context;
    private ItemClickListener itemClickListener;

    public SearchAdapter(List<SearchListResponse> searchListResponses, Context context,ItemClickListener itemClickListener) {
        this.searchListResponses = searchListResponses;
        this.context = context;
        this.itemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public SearchAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kullanici_arama_icerik, parent, false);
        return new SearchAdapter.RecyclerViewAdapter(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.RecyclerViewAdapter holder, int position) {
        SearchListResponse searchListResponse=searchListResponses.get(position);
        String kullaniciAdSoyad = searchListResponse.getAdSoyad();
        holder.adSoyad.setText(kullaniciAdSoyad);
    }

    @Override
    public int getItemCount() {
        return searchListResponses.size();
    }

    public class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        ItemClickListener itemClickListener;
        TextView adSoyad;
        RecyclerViewAdapter(View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            this.itemClickListener=itemClickListener;
            adSoyad=itemView.findViewById(R.id.textView28);
            adSoyad.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            KullaniciIcerikFragment kullaniciIcerikFragment=KullaniciIcerikFragment.newInstance(searchListResponses.get(getAdapterPosition()).getKullanici_adi());
            ((HomeActivity)context).loadFragment(kullaniciIcerikFragment,"AramaAyrintiIcerik");
            itemClickListener.onItemClick(view,getAdapterPosition());
        }
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
