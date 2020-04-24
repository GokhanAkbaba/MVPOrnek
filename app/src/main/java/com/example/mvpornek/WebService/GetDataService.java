package com.example.mvpornek.WebService;


import com.example.mvpornek.Model.searchUsersResponse;

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
    @POST("kullaniciKayit.php")
    Call<ResponseBody> kullaniciKayit(

            @Field("ad_soyad") String adSoyad,
            @Field("kullaniciAdi") String kullaniciAdi,
            @Field("kullanici_eposta") String ePosta,
            @Field("kullanici_sifre") String sifre,
            @Field("kullanici_tekrar_sifre") String sifreTekari

    );

}