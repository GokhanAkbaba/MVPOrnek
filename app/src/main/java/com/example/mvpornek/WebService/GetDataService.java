package com.example.mvpornek.WebService;

import org.json.JSONObject;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetDataService {

    @GET("/users/{login}")
    Call<JSONObject> getKullanici(@Path("login") String login);

    @GET("/users")
    Call<List<JSONObject>> getKullanicilar();

}