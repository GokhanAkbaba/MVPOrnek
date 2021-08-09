package com.birinesor.mvpornek.Presenter.SelectionControl;

import com.birinesor.mvpornek.Model.SelectionControl.SelectionControlnteractor;
import com.birinesor.mvpornek.View.SelectionControl;

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
