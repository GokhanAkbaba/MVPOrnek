package com.birinesor.mvpornek.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.birinesor.mvpornek.Activity.HomeActivity;
import com.birinesor.mvpornek.Fragment.KullaniciIcerikFragment;
import com.birinesor.mvpornek.Models.EtiketlerModel;
import com.birinesor.mvpornek.R;
import com.birinesor.mvpornek.Response.SearchListResponse;

import java.util.List;

public
class EtiketlerAdapter extends RecyclerView.Adapter<EtiketlerAdapter.RecyclerViewAdapter>{
    private List<EtiketlerModel> etiketlerModels;
    private Context context;
    private ItemClickListener itemClickListener;
    KullaniciIcerikFragment kullaniciIcerikFragment;

    public EtiketlerAdapter(List<EtiketlerModel> searchListResponses, Context context, ItemClickListener itemClickListener) {
        this.etiketlerModels = searchListResponses;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public EtiketlerAdapter.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.etiketleri_getir_icerik, parent, false);
        return new EtiketlerAdapter.RecyclerViewAdapter(view,itemClickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull EtiketlerAdapter.RecyclerViewAdapter holder, int position) {
        EtiketlerModel etiketlerModel=etiketlerModels.get(position);
        holder.etiketAdi.setText(etiketlerModel.getEtiketAdi());
        holder.etiketSayi.setText(String.valueOf(etiketlerModel.getEtiketSayisi())+" soru");
    }

    @Override
    public int getItemCount() {
        return etiketlerModels.size();
    }

    public class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView etiketAdi;
        TextView etiketSayi;
        ItemClickListener itemClickListener;
        ConstraintLayout etiketlerLayout;
        public RecyclerViewAdapter(View view, ItemClickListener itemClickListener) {
            super(view);
            this.itemClickListener=itemClickListener;
            etiketAdi=view.findViewById(R.id.etiketAdi);
            etiketSayi=view.findViewById(R.id.etiketSayi);
            etiketlerLayout=view.findViewById(R.id.etiketlerLayout);
            etiketlerLayout.setOnClickListener(this);
            etiketAdi.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.etiketAdi:
                     kullaniciIcerikFragment=KullaniciIcerikFragment.newInstance(etiketlerModels.get(getAdapterPosition()).getEtiketAdi(),etiketlerModels.get(getAdapterPosition()).getId(),-1);
                    ((HomeActivity)context).loadFragment(kullaniciIcerikFragment,"AramaAsama-2");
                    break;
                case R.id.etiketlerLayout:
                    kullaniciIcerikFragment=KullaniciIcerikFragment.newInstance(etiketlerModels.get(getAdapterPosition()).getEtiketAdi(),etiketlerModels.get(getAdapterPosition()).getId(),-1);
                    ((HomeActivity)context).loadFragment(kullaniciIcerikFragment,"AramaAsama-2");
                    break;
            }
        }
    }
    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
