package com.birinesor.mvpornek.WebService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static RetrofitClientInstance mIstance;
    //private static final String BASE_URL = "https://inamemrah.com/birineSorWebServis/";
    private static final String BASE_URL = "https://birnesor.com/api/";

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

    public GetDataService getDataService(){
        return retrofit.create(GetDataService.class);
    }
}