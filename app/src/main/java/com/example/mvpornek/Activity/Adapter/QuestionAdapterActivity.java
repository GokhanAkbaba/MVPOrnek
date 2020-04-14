package com.example.mvpornek.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpornek.Activity.StartActivity;
import com.example.mvpornek.Model.Kullanıcı.QuestionModel;
import com.example.mvpornek.R;

import java.util.List;

public class QuestionAdapterActivity extends RecyclerView.Adapter<QuestionAdapterActivity.MyViewHolder>
{
    private List<QuestionModel> questionModelList;
    private Context context;
    public QuestionAdapterActivity(List<QuestionModel> questionModelList)
    {
        this.questionModelList=questionModelList;

    }
    @NonNull
    @Override
    public QuestionAdapterActivity.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();

        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.sorular_icerik,null));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapterActivity.MyViewHolder holder, int position) {

        QuestionModel questionModel= questionModelList.get(position);
        String kullaniciAdi=questionModel.getKullaniciAdi();
        String soru=questionModel.getSoru();
        String yorumSayisi=questionModel.getYorumSayisi();
        int kullaniciProfilResmi=questionModel.getKullaniciProfilResmi();
        String etiket=questionModel.getEtiket();
        holder.adSoyad.setText(kullaniciAdi);
        holder.yorum_sayisi.setText(yorumSayisi);
        holder.etiket.setText(etiket);
        holder.sorular.setText(soru);
        holder.profilResmi.setImageResource(kullaniciProfilResmi);

    }

    @Override
    public int getItemCount() {
        return questionModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView adSoyad,etiket,yorum_sayisi,sorular;
        ImageView profilResmi;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            adSoyad=itemView.findViewById(R.id.kullaniciAdSoyad);
            etiket=itemView.findViewById(R.id.textView11);
            yorum_sayisi=itemView.findViewById(R.id.yorumSayisiTxt);
            sorular=itemView.findViewById(R.id.textView12);
            profilResmi=itemView.findViewById(R.id.kullaniciResmi);
        }
    }
}
