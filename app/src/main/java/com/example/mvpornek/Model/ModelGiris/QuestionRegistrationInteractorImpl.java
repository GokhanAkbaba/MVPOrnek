package com.example.mvpornek.Model.ModelGiris;

import android.content.Context;
import android.util.Log;

import com.example.mvpornek.Model.Response.SoruKaydetArrayList;
import com.example.mvpornek.Model.Response.SoruKaydetResponse;
import com.example.mvpornek.WebService.RetrofitClientInstance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionRegistrationInteractorImpl implements QuestionRegistrationInteractor {
    Context context;

    public QuestionRegistrationInteractorImpl(Context context) {
        this.context = context;
    }


    @Override
    public void QuestionRegistration(int id, String soru, ArrayList<Integer> etiket, ArrayList<Integer> il, onQuestionRegistrationListener listener) {

        if(il.isEmpty()){
            listener.onIlSecimHatasi();
            return;
        }
        if(etiket.isEmpty()){
            listener.onEtiketSecimHatasi();
            return;
        }


        Call<SoruKaydetResponse> call= RetrofitClientInstance
                .getInstance()
                .getDataService()
                .soruKaydet(id,soru,etiket,il);
        call.enqueue(new Callback<SoruKaydetResponse>() {

            @Override
            public void onResponse(Call<SoruKaydetResponse> call, Response<SoruKaydetResponse> response) {
                SoruKaydetResponse soruKaydetResponse=response.body();
                try{
                    listener.onSuccess();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(Call<SoruKaydetResponse> call, Throwable t) {
                t.printStackTrace();


            }
        });
    }
}
