package com.example.wildfire.ui.fire_fighters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FireFightersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FireFightersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}