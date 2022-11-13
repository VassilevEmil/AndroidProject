package com.example.androidproject.Model.Wallet;

import android.app.Activity;
import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.DAO.Wallet.IUserDAO;
import com.example.androidproject.DAO.Wallet.UserDAO;
import com.example.androidproject.Entities.wallet.User;
import com.google.firebase.auth.FirebaseUser;


public class UserRepository {
    private static UserRepository instance;
    private static IUserDAO userDAO;


    private UserRepository(Application app) {
        userDAO = UserDAO.getInstance(app);

    }


    public static UserRepository getInstance(Application app) {
        if (instance == null) {
            instance = new UserRepository(app);
        }
        return instance;
    }


    public void registerAccount(Activity activity, User user, String password) {
        userDAO.addUser(activity, user, password);
    }

    public MutableLiveData<User> getUser(String uid) {
        return userDAO.getUser(uid);
    }

    public MutableLiveData<User> getUser() {
        return userDAO.getUserModal();
    }

    public void removeUser(User user)
    {
        userDAO.removeUser(user);
    }

    public void updateUser(User user)
    {
        userDAO.updateUser(user);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userDAO.getCurrentUser();
    }
}
