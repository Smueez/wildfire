package com.example.wildfire.ui.fire_map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FireMapViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FireMapViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is FireMap fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}