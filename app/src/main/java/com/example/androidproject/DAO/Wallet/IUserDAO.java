package com.example.androidproject.DAO.Wallet;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.Wallet.User;
import com.google.firebase.auth.FirebaseUser;

public interface IUserDAO {
    void addUser(Activity activity, User user, String password);
    void removeUser(User user);
    void updateUser(User newUser);
    MutableLiveData<User> getUser(String uid);
    LiveData<FirebaseUser> getCurrentUser();
    MutableLiveData<User> getUserModal();
    void loginAccount(Activity activity, String email, String password);
}
