package com.example.mvpornek.WebService;

import org.json.JSONObject;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetDataService {

    @GET("/users/{login}")
    Call<JSONObject> getKullanici(@Path("login") String login);

    @GET("/users")
    Call<List<JSONObject>> getKullanicilar();

    @FormUrlEncoded
    @POST("createuser")
    Call<ResponseBody> kullaniciOlustur(

            @Field("adi") String adi,
            @Field("soyadi") String soyadi,
            @Field("kullanici_sifre") String kullanici_sifre,
            @Field("kulllanici_sifre_tekrar") String kullanici_sifre_tekar,
            @Field("kullanici_adi") String kullanici_adi

    );

}