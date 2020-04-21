package com.example.mvpornek.Model;

import com.example.mvpornek.Model.Kullanıcı.Kullanici;

import java.util.List;

public class searchUsersResponse {
    private boolean error;
    private List<Kullanici> kullanici;

    public searchUsersResponse(boolean error, List<Kullanici> kullanici)
    {
        this.error=error;
        this.kullanici=kullanici;
    }

    public boolean isError() {
        return error;
    }

    public List<Kullanici> getKullanici() {
        return kullanici;
    }
}
