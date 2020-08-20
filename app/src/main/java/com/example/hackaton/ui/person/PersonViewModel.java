package com.example.hackaton.ui.person;

import android.graphics.drawable.Drawable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PersonViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private Drawable image;
    public PersonViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }
    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
    public LiveData<String> getText() {
        return mText;
    }

}