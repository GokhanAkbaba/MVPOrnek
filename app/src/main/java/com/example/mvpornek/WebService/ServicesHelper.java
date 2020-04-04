package com.example.mvpornek.WebService;

import com.example.mvpornek.BirineSorHelper.BirineSorUtil;

import org.json.JSONObject;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesHelper {

    private GetDataService service;
    private List<JSONObject> kullaniciList;
    private JSONObject kullanici;

    public ServicesHelper(){
        this.service = RetrofitClientInstance.getInstance().getDataService();
    }

    public List<JSONObject> tumKullanicilariGetir(){
        service.getKullanicilar().enqueue(new Callback<List<JSONObject>>() {
            @Override
            public void onResponse(Call<List<JSONObject>> call, Response<List<JSONObject>> response) {
                kullaniciList = response.body();
            }

            @Override
            public void onFailure(Call<List<JSONObject>> call, Throwable t) {
                //Hata Durumu
            }
        });
        return kullaniciList;
    }

    public Object kullaniciGetir(String id){
        service.getKullanici(id).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                kullanici = response.body();
                BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBitir();
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                //Hata durumu
            }
        });
        return kullanici;
    }

}
