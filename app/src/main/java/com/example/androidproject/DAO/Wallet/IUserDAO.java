package com.example.androidproject.DAO.Wallet;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.wallet.User;

public interface IUserDAO {
    void addUser(Activity activity, User user, String password);
    void removeUser(User user);
    void updateUser(User newUser);
    MutableLiveData<User> getUser(String uid);
    MutableLiveData<User> getUserModal();

}
