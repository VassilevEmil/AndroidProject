package com.example.androidproject.ViewModel.WalletVM;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.Wallet.Transaction;
import com.example.androidproject.Entities.Wallet.User;
import com.example.androidproject.Model.Login_Register.LoginRegisterRepository;
import com.example.androidproject.Model.Wallet.TransactionRepository;
import com.example.androidproject.Model.Wallet.UserRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class WalletViewModel extends AndroidViewModel  {
    UserRepository userRepository;
    LoginRegisterRepository repository;
    TransactionRepository transactionRepository;

    public WalletViewModel(Application app){
        super(app);
        userRepository = UserRepository.getInstance(app);
        transactionRepository = TransactionRepository.getInstance(app);
    }

    public void registerATransaction(String userUID,Transaction transaction)
    {
        transactionRepository.addTransaction(userUID,transaction);
    }

    public void registerAccount(Activity activity,User user, String password)
    {
        userRepository.registerAccount(activity,user,password);
    }

    public MutableLiveData<List<Transaction>> getTransactions(String userUID){
        return transactionRepository.getTransactions(userUID);
    }

    public MutableLiveData<User> getUser(String uid)
    {
        return userRepository.getUser(uid);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public MutableLiveData<User> getUser()
    {
        return userRepository.getUser();
    }

    public void removeUser(String uid)
    {
        userRepository.removeUser(uid);
    }

    public void updateUser(User user)
    {
        userRepository.updateUser(user);
    }

    public void loginAccount(Activity activity,String email, String password){
        userRepository.loginAccount(activity, email, password);
    }
    public void signOut(){
        userRepository.signOut();
    }



}
