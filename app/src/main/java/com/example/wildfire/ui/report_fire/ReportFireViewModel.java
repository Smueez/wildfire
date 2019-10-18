package com.example.wildfire.ui.report_fire;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReportFireViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReportFireViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is report fire fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}