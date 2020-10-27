package com.example.mvpornek.Model;

public interface SelectionControlnteractor {
    interface onSelectionControlnteractorListener{
        void onSuccess();
        void onFailed();
    }
    void controlSelection(int kullaniciId,onSelectionControlnteractorListener listener);
}
