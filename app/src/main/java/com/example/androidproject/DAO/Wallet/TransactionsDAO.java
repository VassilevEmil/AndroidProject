package com.example.androidproject.DAO.Wallet;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;


import com.example.androidproject.Entities.Wallet.Transaction;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TransactionsDAO implements ITransactionsDAO{

    private static final String collectionPath = "transactions";
    private final Application app;
    private static TransactionsDAO instance;

    MutableLiveData<List<Transaction>> transactionsMutableList;

    //Firebase Database
    private FirebaseFirestore firebaseDatabase;

    private TransactionsDAO(Application app) {
        this.app = app;


        transactionsMutableList=new MutableLiveData<>(new ArrayList<>());

        firebaseDatabase = FirebaseFirestore.getInstance();
    }

    public static TransactionsDAO getInstance(Application app) {
        if (instance == null) {
            instance = new TransactionsDAO(app);

        }
        return instance;
    }

    @Override
    public void addTransaction(String userUID,Transaction transaction) {
        if(userUID==null) return;

        Transaction local = new Transaction();
        local.setUid(transaction.getUid());
        local.setAmount(transaction.getAmount());
        local.setDate(new Date().toString());
        local.setCryptoName(transaction.getCryptoName());
        local.setNote(transaction.getNote());
        local.setBuy(transaction.isBuy());
        local.setUserUID(userUID);


        firebaseDatabase.collection(collectionPath).document(transaction.getUid()).set(local)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Transaction created successfully!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing the transaction", e);
                    }
                });
    }


    @Override
    public void removeTransaction(Transaction transaction) {

    }

    @Override
    public void updateTransaction(Transaction newTransaction) {

    }

    @Override
    public MutableLiveData<List<Transaction>> getTransactions(String uid) {
        firebaseDatabase.collection(collectionPath).whereEqualTo("userUID",uid).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Transaction> local = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        Transaction localtr = document.toObject(Transaction.class);
                        local.add(localtr);
                    }
                    transactionsMutableList.postValue(local);
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
        return transactionsMutableList;
    }

    @Override
    public MutableLiveData<List<Transaction>> getTransactionModal() {
        return transactionsMutableList;
    }
}
