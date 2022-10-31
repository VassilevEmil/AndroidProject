package com.example.androidproject.UI.Market;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MarketViewModel extends ViewModel {
    private MutableLiveData<Integer> name;

    public MarketViewModel(){
        name = new MutableLiveData<>(0);
    }

    public void Increment(){
        name.postValue(name.getValue()+1);
    }

    public LiveData<Integer> getText(){
        return name;
    }
}
