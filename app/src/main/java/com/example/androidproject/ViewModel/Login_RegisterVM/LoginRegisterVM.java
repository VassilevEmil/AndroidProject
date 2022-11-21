package com.example.androidproject.ViewModel.Login_RegisterVM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Model.Login_Register.LoginRegisterRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoginRegisterVM extends AndroidViewModel {

    private LoginRegisterRepository repository;
    private MutableLiveData<FirebaseUser> userData;
    private MutableLiveData<Boolean> loggedStatus;

    public MutableLiveData<FirebaseUser> getUserData() {
        return userData;
    }

    public MutableLiveData<Boolean> getLoggedStatus() {
        return loggedStatus;
    }

    public LoginRegisterVM(@NonNull  Application application) {
        super(application);
        repository = new LoginRegisterRepository(application);
        userData = repository.getFirebaseUserMutableLiveData();
        loggedStatus = repository.getUserLoggedMutableLiveData();
    }

    public void register(String email , String pass){
        repository.register(email, pass);
    }
    public void signIn(String email , String pass){
        repository.login(email, pass);
    }
    public void signOut(String email, String pass){
        repository.signOut(email, pass);
    }

}
