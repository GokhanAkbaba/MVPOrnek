package com.example.mvpornek.WebService;


import android.media.session.MediaSession;

import com.example.mvpornek.Models.AnswersModel;
import com.example.mvpornek.Models.BildirimlerBegenilerModel;
import com.example.mvpornek.Models.CommentModel;
import com.example.mvpornek.Models.LikesModel;
import com.example.mvpornek.Models.NotificationCommetAndQuestionModel;
import com.example.mvpornek.Models.SearchQuestionModel;
import com.example.mvpornek.Models.SelectionControlModel;
import com.example.mvpornek.Response.AramaArsivKayitResponse;
import com.example.mvpornek.Response.AramaGecmisSilResponse;
import com.example.mvpornek.Response.BildirimlerCevaplarModel;
import com.example.mvpornek.Response.KullaniciGetirResponse;
import com.example.mvpornek.Models.QuestionModel;
import com.example.mvpornek.Response.CevapKaydetResponse;
import com.example.mvpornek.Response.CevapSilResponse;
import com.example.mvpornek.Response.EtiketResponse;
import com.example.mvpornek.Response.KullaniciResponse;
import com.example.mvpornek.Response.LikeModel;
import com.example.mvpornek.Response.NotificationCommentAndLikeModel;
import com.example.mvpornek.Response.NotificationResponse;
import com.example.mvpornek.Response.SearchListResponse;
import com.example.mvpornek.Response.SifreResponse;
import com.example.mvpornek.Response.SifremiUnuttumResponse;
import com.example.mvpornek.Response.SoruKaydetResponse;
import com.example.mvpornek.Response.SoruSilResponse;
import com.example.mvpornek.Response.TokenOlusturResponse;
import com.example.mvpornek.Response.TokenSilResponse;
import com.example.mvpornek.Response.UserSearchListResponse;


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
            @Field("cevap") String cevap
    );

    @FormUrlEncoded
    @POST("yorumlariGetir.php")
    Call<List<CommentModel>> yorumlariGetir(
            @Field("soruId") int soruId

    );
    @FormUrlEncoded
    @POST("bildirimlerCevaplariGetir.php")
    Call<List<BildirimlerCevaplarModel>> bildirimCevaplarGetir(
            @Field("kullaniciID") int kullaniciID

    );
    @FormUrlEncoded
    @POST("bildirimlerBegenileriGetir.php")
    Call<List<BildirimlerBegenilerModel>> bildirimBegenileriGetir(
            @Field("kullaniciID") int kullaniciID

    );
    @FormUrlEncoded
    @POST("kullaniciSecimKontrol.php")
    Call<SelectionControlModel> secimKontrol(
            @Field("kullanici_id") int kullanici_id

    );

    @FormUrlEncoded
    @POST("cevapSil.php")
    Call<CevapSilResponse> cevapSil(
            @Field("cevap_id") int cevapId
    );


    @FormUrlEncoded
    @POST("tokenSil.php")
    Call<TokenSilResponse> tokenSil(
            @Field("kullaniciID") int kullaniciID
    );

    @FormUrlEncoded
    @POST("aramaGecmisSil.php")
    Call<AramaGecmisSilResponse> aramaGecmisSil(
            @Field("kayitId") int kayitId
    );

    @FormUrlEncoded
    @POST("soruSil.php")
    Call<SoruSilResponse> soruSil(
            @Field("soruId") int soruId
    );
    @FormUrlEncoded
    @POST("tokenOlustur.php")
    Call<TokenOlusturResponse> tokenOlustur(
            @Field("kullanici_id") int kullaniciID,
            @Field("token") String token
    );
    @FormUrlEncoded
    @POST("sendSinglePush.php")
    Call<NotificationResponse> bildirimGonder(
            @Field("bildirimYapanKullaniciID") int bildirimYapanKullaniciID,
            @Field("bildirimYapılanKullaniciID") int bildirimYapılanKullaniciID,
            @Field("bildirimYapılanCevapID") int bildirimYapılanCevapID,
            @Field("bildirimYapılanSoruID") int bildirimYapılanSoruID,
            @Field("ileti") String ileti,
            @Field("durum") String durum,
            @Field("bildirimTuru") int bildirimTuru
    );

    @FormUrlEncoded
    @POST("notificationCommentAndQuestion.php")
    Call<List<NotificationCommetAndQuestionModel>> bildirimYorumIcerikGetir(
            @Field("soruID") int soruID

    );
    @FormUrlEncoded
    @POST("notificationCommentAndLike.php")
    Call<List<NotificationCommentAndLikeModel>> bildirimBegeniIcerikGetir(
            @Field("soruID") int soruID,
            @Field("cevapID") int cevapID

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

    @FormUrlEncoded
    @POST("profilCevaplariGetir.php")
    Call<List<AnswersModel>> kullaniciCevaplariGetir(
            @Field("kullaniciId") int kullaniciId
    );

    @FormUrlEncoded
    @POST("profilBegeniGetir.php")
    Call<List<LikesModel>> kullaniciBegenileriGetir(
            @Field("kullaniciId") int kullaniciId
    );

    @FormUrlEncoded
    @POST("aramaKullaniciGetir.php")
    Call<List<SearchListResponse>> aramaKullaniciGetir(
            @Field("kullaniciId") int kullaniciId,
            @Field("kullaniciAdi") String kullaniciAdi,
            @Field("secim") int secim
    );

    @FormUrlEncoded
    @POST("aramaArsivKayit.php")
    Call<AramaArsivKayitResponse> aramaArsivKayit(
            @Field("kullaniciId") int kullaniciId,
            @Field("arananIcerik") String arananIcerik
    );

    @FormUrlEncoded
    @POST("aramaSorulariGetir.php")
    Call<List<SearchQuestionModel>> aramaSorulariGetir(
            @Field("kullaniciAdi") String kullaniciAdi
    );

    @FormUrlEncoded
    @POST("aramaKullanicilariGetir.php")
    Call<List<UserSearchListResponse>> aramaKullanicilariGetir(
            @Field("kullaniciAdi") String kullaniciAdi
    );

    @FormUrlEncoded
    @POST("aramaSoruGetir.php")
    Call<List<SearchQuestionModel>> aramaSoruGetir(
            @Field("ifade") String ifade
    );

    @FormUrlEncoded
    @POST("sifremiUnuttum.php")
    Call<SifremiUnuttumResponse> sifremiUnuttum(
            @Field("eposta") String ePosta
    );


}