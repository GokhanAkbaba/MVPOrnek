package com.example.mvpornek.Presenter;

import com.example.mvpornek.Model.SelectionControlnteractor;
import com.example.mvpornek.Models.CommentModel;
import com.example.mvpornek.Models.SelectionControlModel;
import com.example.mvpornek.View.SelectionControl;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class SelectionControlPresenterImpl implements SelectionControlPresenter, SelectionControlnteractor.onSelectionControlnteractorListener {
   private SelectionControl selectionControl;
   private SelectionControlnteractor selectionControlnteractor;

    public SelectionControlPresenterImpl(SelectionControl selectionControl, SelectionControlnteractor selectionControlnteractor) {
        this.selectionControl = selectionControl;
        this.selectionControlnteractor = selectionControlnteractor;
    }

    @Override
    public void onSuccess() {
        if(selectionControl != null)
        {
            selectionControl.succesSelection();
        }
    }

    @Override
    public void onFailed() {
        if(selectionControl != null)
        {
            selectionControl.failedSelection();
        }
    }

    @Override
    public void validateSelectionControl(int kullaniciId) {
        selectionControlnteractor.controlSelection(kullaniciId,this);
    }
}
