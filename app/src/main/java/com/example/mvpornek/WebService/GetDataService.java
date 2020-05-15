package com.example.mvpornek.WebService;


import com.example.mvpornek.Model.Response.EtiketResponse;
import com.example.mvpornek.Model.Response.KullaniciResponse;
import com.example.mvpornek.Model.Response.SifreResponse;
import com.example.mvpornek.Model.Response.SoruKaydetArrayList;
import com.example.mvpornek.Model.Response.SoruKaydetResponse;
import com.google.gson.JsonObject;


import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetDataService {



    @FormUrlEncoded
    @POST("kullaniciKayit.php")
    Call<KullaniciResponse> kullaniciKayit(

            @Field("ad_soyad") String adSoyad,
            @Field("kullaniciAdi") String kullaniciAdi,
            @Field("kullanici_eposta") String ePosta,
            @Field("kullanici_sifre") String sifre,
            @Field("kullanici_tekrar_sifre") String sifreTekari

    );

    @FormUrlEncoded
    @POST("secenekKayit.php")
    Call<EtiketResponse> etiketKayit(

            @Field("id") int id,
            @Field("etiket") int etiket,
            @Field("il") int il

    );

    @FormUrlEncoded
    @POST("kullaniciGiris.php")
    Call<KullaniciResponse> kullaniciGiris(

            @Field("ePosta") String ePosta,
            @Field("sifre") String sifre

    );

    @FormUrlEncoded
    @POST("profilGuncelle.php")
    Call<KullaniciResponse> profilGuncelle(

            @Field("id")int kullaniciId,
            @Field("kullaniciAdSoyad") String kullanciAdSoyad,
            @Field("kullaniciAdi") String kullanciAdi,
            @Field("kullaniciEposta") String kullanciEPosta,
            @Field ("profilResmi") String kullaniciResmi

    );

    @FormUrlEncoded
    @POST("sifreGuncelle.php")
    Call<SifreResponse> sifreGuncelle(

            @Field("kullaniciId")int kullaniciId,
            @Field("mevcutSifre") String mevcutSifre,
            @Field("yeniSifre") String yeniSifre,
            @Field("yeniTekrarSifre") String yeniTekararSifre


    );

    @FormUrlEncoded
    @POST("soruKayit.php")
    Call<SoruKaydetResponse> soruKaydet(

            @Field("kullaniciId")int kullaniciId,
            @Field("soru") String soru,
            @Field("etiket[]") ArrayList<Integer> etiket,
            @Field("il[]") ArrayList<Integer> il
           /// @Body JsonObject kullanicisoru

            );



}