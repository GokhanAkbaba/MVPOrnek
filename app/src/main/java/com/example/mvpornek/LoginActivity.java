package com.example.mvpornek;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvpornek.Model.LoginInteractorImpl;
import com.example.mvpornek.Presenter.LoginPrensenter;
import com.example.mvpornek.Presenter.LoginPresenterImpl;
import com.example.mvpornek.View.LoginView;

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText usuario;
    private EditText password;
    private LoginPrensenter prensenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progressBar);
        usuario = findViewById(R.id.edtusername);
        password = findViewById(R.id.sifre);

        findViewById(R.id.btnIngresar).setOnClickListener(this);

        prensenter = new LoginPresenterImpl(this, new LoginInteractorImpl());
    }

    @Override
    protected void onDestroy() {
        prensenter.onDestroyed();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        prensenter.validateCredentials(usuario.getText().toString(),password.getText().toString());
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        usuario.setError("Error de usurio");
    }

    @Override
    public void setPasswordError() {
        password.setError("Error de password");
    }

    @Override
    public void navigateToHome() {
        Toast.makeText(this, "Ingresor",Toast.LENGTH_SHORT).show();
    }
}
