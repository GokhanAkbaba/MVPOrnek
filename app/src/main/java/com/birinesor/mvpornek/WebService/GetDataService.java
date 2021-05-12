package com.birinesor.mvpornek.WebService;


import com.birinesor.mvpornek.AramaAyarlari.AramaAyarlariGetir.AramaAyarlariGetirModel;
import com.birinesor.mvpornek.Models.AnswersModel;
import com.birinesor.mvpornek.Models.BildirimlerBegenilerModel;
import com.birinesor.mvpornek.Models.CommentModel;
import com.birinesor.mvpornek.Models.EtiketlerModel;
import com.birinesor.mvpornek.Models.IlgiAlanlariGetirModel;
import com.birinesor.mvpornek.Models.KazancCevap;
import com.birinesor.mvpornek.Models.KazancSoru;
import com.birinesor.mvpornek.Models.LikesModel;
import com.birinesor.mvpornek.Models.NotificationCommetAndQuestionModel;
import com.birinesor.mvpornek.Models.SearchQuestionModel;
import com.birinesor.mvpornek.Models.SelectionControlModel;
import com.birinesor.mvpornek.Models.YorumAyrintiSorusuModel;
import com.birinesor.mvpornek.Models.YorumAyrintiSorusuYorumlarModel;
import com.birinesor.mvpornek.Response.AramaArsivKayitResponse;
import com.birinesor.mvpornek.Response.AramaGecmisSilResponse;
import com.birinesor.mvpornek.Response.BildirimlerCevaplarModel;
import com.birinesor.mvpornek.Response.IlgiAlaniKayitModel;
import com.birinesor.mvpornek.Response.KullaniciGetirResponse;
import com.birinesor.mvpornek.Models.QuestionModel;
import com.birinesor.mvpornek.Response.CevapKaydetResponse;
import com.birinesor.mvpornek.Response.CevapSilResponse;
import com.birinesor.mvpornek.Response.EtiketResponse;
import com.birinesor.mvpornek.Response.KullaniciResponse;
import com.birinesor.mvpornek.Response.LikeModel;
import com.birinesor.mvpornek.Response.NotificationCommentAndLikeModel;
import com.birinesor.mvpornek.Response.NotificationResponse;
import com.birinesor.mvpornek.Response.SearchListResponse;
import com.birinesor.mvpornek.Response.SifreResponse;
import com.birinesor.mvpornek.Response.SifremiUnuttumResponse;
import com.birinesor.mvpornek.Response.SoruKaydetResponse;
import com.birinesor.mvpornek.Response.SoruSilResponse;
import com.birinesor.mvpornek.Response.TokenOlusturResponse;
import com.birinesor.mvpornek.Response.TokenSilResponse;
import com.birinesor.mvpornek.Response.UserSearchListResponse;


import java.util.ArrayList;
import java.util.List;

import com.birinesor.mvpornek.AramaAyarlari.AramaAyarlariModel;
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
    @POST("getQuestionsMoneyCount.php")
    Call<List<KazancSoru>> kazancSoruGetir(
            @Field("kullaniciId") int kullaniciId

    );
    @FormUrlEncoded
    @POST("getAnswersMoneyCount.php")
    Call<List<KazancCevap>> kazancCevapGetir(
            @Field("kullaniciId") int kullaniciId

    );
    @FormUrlEncoded
    @POST("yorumlariGetir.php")
    Call<List<CommentModel>> yorumlariGetir(
            @Field("soruId") int soruId

    );
    @FormUrlEncoded
    @POST("ilgiAlanlariGetir.php")
    Call<List<IlgiAlanlariGetirModel>> ilgiAlanlariGetir(
            @Field("kullaniciID") int kullaniciID

    );
    @FormUrlEncoded
    @POST("aramaAyarlariGetir.php")
    Call<List<AramaAyarlariGetirModel>> aramaAyarlariGetir(
            @Field("kullaniciID") int kullaniciID

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
    @POST("ilgiAlaniSecimIslemi.php")
    Call<IlgiAlaniKayitModel> IlgiAlaniKayit(
            @Field("kullaniciId") int kullaniciId,
            @Field("etiketId") int etiketId,
            @Field("il") int il,
            @Field("durum") int durum

    );

    @FormUrlEncoded
    @POST("cevapSil.php")
    Call<CevapSilResponse> cevapSil(
            @Field("cevap_id") int cevapId
    );
    @FormUrlEncoded
    @POST("hesabiSil.php")
    Call<CevapSilResponse> hesapSil(
            @Field("kullaniciID") int kullaniciID
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
    @POST("kullaniciIPKayit.php")
    Call<TokenOlusturResponse> ipKayitOlustur(
            @Field("kullanici_id") int kullaniciID,
            @Field("ipV4")  String iPv4,
            @Field("ipV6") String ipV6,
            @Field("macAdres") String macAdress,
            @Field("macAdres2") String macAdress2

    );
    @FormUrlEncoded
    @POST("aramaAyarlariKayit.php")
    Call<AramaAyarlariModel> aramaAyarlariKayit(
            @Field("kullanici_id") int kullaniciID,
            @Field("kod_id") int kodID,
            @Field("secim") int secim
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
    @POST("etiketleriGetir.php")
    Call<List<EtiketlerModel>> etiketleriGetir(
            @Field("kullaniciID") int kullaniciID


    );
    @FormUrlEncoded
    @POST("notificationCommentAndLike.php")
    Call<List<NotificationCommentAndLikeModel>> bildirimBegeniIcerikGetir(
            @Field("soruID") int soruID,
            @Field("cevapID") int cevapID

    );
    @FormUrlEncoded
    @POST("yorumAyrintiYorumlariGetir.php")
    Call<List<YorumAyrintiSorusuYorumlarModel>> yorumAyrintiSorusuYorumlarGetir(
            @Field("soruId") int soruID

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
    @POST("soruAyrintiYorumGetir.php")
    Call<List<YorumAyrintiSorusuModel>> yorumAyrintiSorusuGetir(
            @Field("soruId") int soruId

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
            @Field("kullaniciAdi") String kullaniciAdi,
            @Field("etiketID") int etiketID,
            @Field("secenek") int secenek

    );

    @FormUrlEncoded
    @POST("aramaSoruGetir.php")
    Call<List<SearchQuestionModel>> aramaSoruGetir(
            @Field("ifade") String ifade,
            @Field("etiketID") int etiketID,
            @Field("secenek") int secenek
    );

    @FormUrlEncoded
    @POST("sifremiUnuttum.php")
    Call<SifremiUnuttumResponse> sifremiUnuttum(
            @Field("eposta") String ePosta
    );


}