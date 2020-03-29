package com.example.mvpornek.WebService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static RetrofitClientInstance mIstance;
    private static final String BASE_URL = "http://192.168.0.14:8000/";

    private RetrofitClientInstance()
    {
        retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }
    public static synchronized RetrofitClientInstance getInstance(){
        if(mIstance == null)
        {
            mIstance =new RetrofitClientInstance();
        }
        return mIstance;
    }

    public GetDataService getDataService()
    {
        return retrofit.create(GetDataService.class);
    }
  /*  public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(KULLANICI_KAYIT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }*/
}