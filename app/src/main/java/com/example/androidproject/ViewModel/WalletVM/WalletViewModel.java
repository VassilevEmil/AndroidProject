package com.example.androidproject.ViewModel.WalletVM;

import android.app.Activity;
import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.wallet.User;
import com.example.androidproject.Model.Wallet.UserRepository;

public class WalletViewModel extends AndroidViewModel {
    UserRepository repository;

    public WalletViewModel(Application app){
        super(app);
        repository = UserRepository.getInstance(app);
    }

    public void registerAccount(Activity activity, User user, String password)
    {
        repository.registerAccount(activity,user,password);
    }

    public MutableLiveData<User> getUser(String uid)
    {
        return repository.getUser(uid);
    }

    public MutableLiveData<User> getUser()
    {
        return repository.getUser();
    }

    public void removeUser(User user)
    {
        repository.removeUser(user);
    }

    public void updateUser(User user)
    {
        repository.updateUser(user);
    }
}
