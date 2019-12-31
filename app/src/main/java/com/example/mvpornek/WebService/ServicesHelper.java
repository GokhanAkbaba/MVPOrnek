package com.example.mvpornek.WebService;

import com.example.mvpornek.Model.Kullanici;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesHelper {

    private GetDataService service;
    private List<Kullanici> kullaniciList;
    private Kullanici kullanici;

    public ServicesHelper(){
        this.service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
    }

    public List<Kullanici> tumKullanicilariGetir(){
        service.getKullanicilar().enqueue(new Callback<List<Kullanici>>() {
            @Override
            public void onResponse(Call<List<Kullanici>> call, Response<List<Kullanici>> response) {
                kullaniciList = response.body();
            }

            @Override
            public void onFailure(Call<List<Kullanici>> call, Throwable t) {
                //Hata Durumu
            }
        });
        return kullaniciList;
    }

    public Kullanici kullaniciGetir(String id){
        service.getKullanici(id).enqueue(new Callback<Kullanici>() {
            @Override
            public void onResponse(Call<Kullanici> call, Response<Kullanici> response) {
                kullanici = response.body();
            }

            @Override
            public void onFailure(Call<Kullanici> call, Throwable t) {
                //Hata durumu
            }
        });
        return kullanici;
    }

}
