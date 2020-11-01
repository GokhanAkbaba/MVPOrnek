package com.birinesor.mvpornek.Model.SoruKayit;

import android.content.Context;

import com.birinesor.mvpornek.Response.SoruKaydetResponse;
import com.birinesor.mvpornek.WebService.RetrofitClientInstance;

import java.util.ArrayList;

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
