package com.example.androidproject.Model.Wallet;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.DAO.Wallet.ITransactionsDAO;
import com.example.androidproject.DAO.Wallet.TransactionsDAO;
import com.example.androidproject.DAO.Wallet.UserDAO;
import com.example.androidproject.Entities.wallet.Transaction;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class TransactionRepository {
    private static TransactionRepository instance;
    private static ITransactionsDAO transactionsDAO;


    private TransactionRepository(Application app) {
        transactionsDAO = TransactionsDAO.getInstance(app);

    }


    public static TransactionRepository getInstance(Application app) {
        if (instance == null) {
            instance = new TransactionRepository(app);
        }
        return instance;
    }


    public void addTransaction(String userUID,Transaction transaction){
        transactionsDAO.addTransaction(userUID,transaction);
    }
    public void removeTransaction(Transaction transaction){
        transactionsDAO.removeTransaction(transaction);
    }
    public void updateTransaction(Transaction newTransaction){
        transactionsDAO.updateTransaction(newTransaction);
    }
    public MutableLiveData<List<Transaction>> getTransactions(String uid){
        return transactionsDAO.getTransactions(uid);
    }
    public MutableLiveData<List<Transaction>> getTransactionModal(){
        return transactionsDAO.getTransactionModal();
    }
}
