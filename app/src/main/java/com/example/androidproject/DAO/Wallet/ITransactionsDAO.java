package com.example.androidproject.DAO.Wallet;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.wallet.Transaction;
import com.example.androidproject.Entities.wallet.User;

import java.util.List;

public interface ITransactionsDAO {
    void addTransaction(String userUID,Transaction transaction);
    void removeTransaction(Transaction transaction);
    void updateTransaction(Transaction newTransaction);
    MutableLiveData<Transaction> getTransaction(String uid);
    MutableLiveData<List<Transaction>> getTransactionModal();
}
