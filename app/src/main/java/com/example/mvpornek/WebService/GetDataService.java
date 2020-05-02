package com.example.mvpornek.WebService;


import com.example.mvpornek.Model.Kullanıcı.EtiketResponse;
import com.example.mvpornek.Model.Kullanıcı.KullaniciKayit.KullaniciResponse;


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

}