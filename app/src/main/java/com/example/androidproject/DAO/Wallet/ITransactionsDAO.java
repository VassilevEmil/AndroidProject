package com.example.androidproject.DAO.Wallet;

import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.Entities.Wallet.Transaction;

import java.util.List;

public interface ITransactionsDAO {
    void addTransaction(String userUID,Transaction transaction);
    void removeTransaction(Transaction transaction);
    void updateTransaction(Transaction newTransaction);
    MutableLiveData<List<Transaction>> getTransactions(String uid);
    MutableLiveData<List<Transaction>> getTransactionModal();
}
