package com.example.mvpornek.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpornek.Model.Kullanıcı.QuestionModel;
import com.example.mvpornek.Model.Kullanıcı.SearchModel;
import com.example.mvpornek.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    private List<SearchModel> searchModelList;
    Context context;
    private SearchQuestionSelected searchQuestionSelected;

    public SearchAdapter(List<SearchModel> searchModelList,SearchQuestionSelected searchQuestionSelected) {
        this.searchModelList=searchModelList;
        this.searchQuestionSelected=searchQuestionSelected;

    }

    @NonNull
    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new SearchAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.sorular_icerik,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MyViewHolder holder, int position) {
        SearchModel searchModel=searchModelList.get(position);
        String kullaniciAdi=searchModel.getKullaniciAdi();
        String soru=searchModel.getSoru();
        String yorumSayisi=searchModel.getYorumSayisi();
        int kullaniciProfilResmi=searchModel.getKullaniciProfilResmi();
        String etiket=searchModel.getEtiket();
        holder.adSoyad.setText(kullaniciAdi);
        holder.yorum_sayisi.setText(yorumSayisi);
        holder.etiket.setText(etiket);
        holder.sorular.setText(soru);
        holder.profilResmi.setImageResource(kullaniciProfilResmi);
    }

    @Override
    public int getItemCount() {
        return searchModelList.size();
    }
    public interface SearchQuestionSelected{
        void searchQuestionSelected(SearchModel searchModel);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView adSoyad,etiket,yorum_sayisi,sorular;
        ImageView profilResmi;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            adSoyad=itemView.findViewById(R.id.kullaniciAdSoyad);
            etiket=itemView.findViewById(R.id.textView11);
            yorum_sayisi=itemView.findViewById(R.id.yorumSayisiTxt);
            sorular=itemView.findViewById(R.id.textView12);
            profilResmi=itemView.findViewById(R.id.kullaniciResmi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    searchQuestionSelected.searchQuestionSelected(searchModelList.get(getAdapterPosition()));
                }
            });
        }
    }

}
