package com.example.mvpornek.Activity.Ayarlar;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;


import com.example.mvpornek.Activity.ImagePickerActivity;
import com.example.mvpornek.BirineSorHelper.BirineSorUtil;
import com.example.mvpornek.GlideApp;
import com.example.mvpornek.Model.Kullanıcı.KullaniciKayit.Kullanici;
import com.example.mvpornek.Model.ModelGiris.ProfilUpdateInteractorImpl;
import com.example.mvpornek.Presenter.ProfilUpdatePresenter;
import com.example.mvpornek.Presenter.ProfilUpdatePresenterImpl;
import com.example.mvpornek.R;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.View.ProfilUpdateView;
import com.google.android.material.textfield.TextInputLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ProfilDuzenleActivity extends Activity implements ProfilUpdateView, View.OnClickListener {
    private ProfilUpdatePresenter profilUpdatePresenter;
    ImageView imageView;
    Bitmap bitmap=null;
    Boolean fotoKontrol=false;
    Uri uri;
    Kullanici kullanici;
    EditText adSoyadTxt,kullaniciAdiTxt,kullaniciEposta;
    TextInputLayout adSoyadInput,kullaniciAdiInput,ePostaInput;
    public static final int REQUEST_IMAGE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profil_duzenle);
        adSoyadInput= findViewById(R.id.adSoyadTextField);
        kullaniciAdiInput=findViewById(R.id.kullaniciAdiTextField);
        ePostaInput=findViewById(R.id.ePostaTextField);
        kullanici= SharedPrefManager.getInstance(this).getKullanici();
        adSoyadTxt=findViewById(R.id.profilDuzenleAdSoyadTxt);
        kullaniciAdiTxt=findViewById(R.id.profilDuzenleKullaniciAdiTxt);
        kullaniciEposta=findViewById(R.id.profilDuzenleEpostaTxt);
        Button profilDuzenleGeriBtn=findViewById(R.id.profilDuzenleGeriBtn);
        Button profilGuncelleButon=findViewById(R.id.profilGuncelleButon);
        profilGuncelleButon.setOnClickListener(this);
        profilDuzenleGeriBtn.setOnClickListener(this);
        imageView=findViewById(R.id.profilDuzenleFotoAlani);
        imageView.setOnClickListener(this);
        loadProfile(kullanici.getProfilFoto());

        adSoyadTxt.setText(kullanici.getAdSoyad());
        kullaniciAdiTxt.setText(kullanici.getKullaniciAdi());
        kullaniciEposta.setText(kullanici.getKullaniciEposta());


        ImagePickerActivity.clearCache(this);

        profilUpdatePresenter=new ProfilUpdatePresenterImpl(this,new ProfilUpdateInteractorImpl(ProfilDuzenleActivity.this));

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
    }

    @Override
    public void onClick(View view) {
        String ePosta,kullaniciAdi,adSoyad;
        adSoyad = adSoyadTxt.getText().toString();
        kullaniciAdi=kullaniciAdiTxt.getText().toString();
        ePosta=kullaniciEposta.getText().toString();


        switch (view.getId())
        {
            case R.id.profilDuzenleGeriBtn:
                onBackPressed();
                break;
            case R.id.profilDuzenleFotoAlani:
                onProfileImageClick();
                break;
            case R.id.profilGuncelleButon:

                 if(fotoKontrol==true){
                     String profilResim=convertToString();
                     profilUpdatePresenter.updateValideCredentals(kullanici.getId(),adSoyad,kullaniciAdi,ePosta,profilResim);
                     fotoKontrol=false;
                 }else{
                     profilUpdatePresenter.updateValideCredentals(kullanici.getId(),adSoyad,kullaniciAdi,ePosta,kullanici.getProfilFoto());
                 }

                break;
        }
    }

    private void loadProfile(String url) {
        GlideApp.with(this).load(url)
                .into(imageView);
    }

    private void loadProfileDefault() {
        GlideApp.with(this).load(R.drawable.rumeli)
                .into(imageView);
    }

    void onProfileImageClick() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            showImagePickerOptions();
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(ProfilDuzenleActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(ProfilDuzenleActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private String convertToString() {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            byte[] imgByte = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
               uri = data.getParcelableExtra("path");
               fotoKontrol=true;
                try {
                        // You can update this bitmap to your server
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        imageView.setImageBitmap(bitmap);
                        // loading profile image from local cache
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfilDuzenleActivity.this);
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    @Override
    public void showProgress() {
        BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBaslat(this,null,"Profil Güncelleniyor");
    }

    @Override
    public void hideProgress() {
        BirineSorUtil.getInstanceBirineSorUtil().yükleniyorBitir();
    }

    @Override
    public void setGuncelleEPostaAdiHatasi() {
        ePostaInput.setError("E-postayı Boş Bırakmayınız");
    }

    @Override
    public void setGuncelleKullaniciAdiHatasi() {
        kullaniciAdiInput.setError("Kullanıcı Adı Boş Bırakmayınız");
    }

    @Override
    public void setGuncelleKullaniciAdiSoyad() {
        adSoyadInput.setError("Ad Soyad Boş Bırakmayınız");
    }

    @Override
    public void setGuncelleKullaniciProfilFoto(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToProfilUpdate() {
        adSoyadInput.setError(null);
        kullaniciAdiInput.setError(null);
        ePostaInput.setError(null);
        Toast.makeText(this,"Profiliniz Güncellendi",Toast.LENGTH_SHORT).show();
        hideProgress();
    }
}
