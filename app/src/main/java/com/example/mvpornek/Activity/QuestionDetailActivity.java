package com.example.mvpornek.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mvpornek.Model.Kullanıcı.SearchModel;
import com.example.mvpornek.R;

import java.util.ArrayList;

public class QuestionDetailActivity extends Activity {
    TextView denemeTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_question_detail);

        denemeTxt=findViewById(R.id.ornekDeger);

        Intent intent=getIntent();
        if(intent.getExtras()!= null)
        {
            SearchModel searchModel=(SearchModel) intent.getExtras().getSerializable("data");
            denemeTxt.setText(searchModel.getKullaniciAdi());
        }

    }
}
