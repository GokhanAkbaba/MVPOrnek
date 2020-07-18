package com.example.mvpornek.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpornek.Activity.HomeActivity;
import com.example.mvpornek.Fragment.NavBarFragment.ProfilFragment;
import com.example.mvpornek.Models.QuestionModel;
import com.example.mvpornek.R;
import com.example.mvpornek.Response.SearchListResponse;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.RecyclerViewAdapter> {
    private List<SearchListResponse> searchListResponses;
    private Context context;

    public SearchAdapter(List<SearchListResponse> searchListResponses, Context context) {
        this.searchListResponses = searchListResponses;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kullanici_arama_icerik, parent, false);
        return new SearchAdapter.RecyclerViewAdapter(view);
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

    public class RecyclerViewAdapter extends RecyclerView.ViewHolder{
        TextView adSoyad;
        RecyclerViewAdapter(View itemView) {
            super(itemView);
            adSoyad=itemView.findViewById(R.id.textView28);
        }

    }
}
