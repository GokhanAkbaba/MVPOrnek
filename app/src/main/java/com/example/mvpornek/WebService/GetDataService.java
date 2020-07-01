package com.example.mvpornek.WebService;


import com.example.mvpornek.Models.CommentModel;
import com.example.mvpornek.Response.KullaniciGetirResponse;
import com.example.mvpornek.Models.QuestionModel;
import com.example.mvpornek.Response.CevapKaydetResponse;
import com.example.mvpornek.Response.CevapSilResponse;
import com.example.mvpornek.Response.EtiketResponse;
import com.example.mvpornek.Response.KullaniciResponse;
import com.example.mvpornek.Response.LikeModel;
import com.example.mvpornek.Response.SifreResponse;
import com.example.mvpornek.Response.SoruKaydetResponse;
import com.example.mvpornek.Response.SoruSilResponse;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
    );

    @FormUrlEncoded
    @POST("sorulariGetir.php")
    Call<List<QuestionModel>> sorulariGetir(
            @Field("kullaniciId") int kullaniciId

    );

    @FormUrlEncoded
    @POST("cevapKayit.php")
    Call<CevapKaydetResponse> cevapKaydet(

            @Field("kullanici_id") int kullaniciId,
            @Field("soru_id") int soruId,
            @Field("cevap") String soru
    );

    @FormUrlEncoded
    @POST("yorumlariGetir.php")
    Call<List<CommentModel>> yorumlariGetir(
            @Field("soruId") int soruId

    );

    @FormUrlEncoded
    @POST("cevapSil.php")
    Call<CevapSilResponse> cevapSil(
            @Field("cevap_id") int cevapId
    );
    @FormUrlEncoded
    @POST("soruSil.php")
    Call<SoruSilResponse> soruSil(
            @Field("soruId") int soruId
    );


    @FormUrlEncoded
    @POST("begenislemleri.php")
    Call<LikeModel> begeniYap(
            @Field("cevap_id") int cevapId,
            @Field("kullanici_id") int kullaniciId,
            @Field("begeni") int begeni,
            @Field("durum") int durum
    );

    @FormUrlEncoded
    @POST("menuSorulariGetir.php")
    Call<List<QuestionModel>> menuSorulariGetir(
            @Field("etiketId") int etiketId

    );

    @FormUrlEncoded
    @POST("kullaniciGetir.php")
    Call<KullaniciGetirResponse> kullaniciGetir(
            @Field("kullaniciId") int kullaniciId
    );

    @FormUrlEncoded
    @POST("kullaniciSorularimGetir.php")
    Call<List<QuestionModel>> kullaniciSorulariGetir(
            @Field("kullaniciId") int kullaniciId

    );



}