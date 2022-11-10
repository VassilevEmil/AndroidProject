package com.example.androidproject.DAO.Wallet;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.androidproject.DAO.UserLiveData;
import com.example.androidproject.Entities.wallet.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UserDAO implements IUserDAO{
    private static final String collectionPath = "users";
    private final UserLiveData liveData;
    private final Application app;
    private static UserDAO instance;

    MutableLiveData<User> userMutableLiveData;

    //Authentication
    private FirebaseAuth firebaseAuth;
    //Firebase Database
    private FirebaseFirestore firebaseDatabase;

    private UserDAO(Application app) {
        this.app = app;


        liveData = new UserLiveData();

        userMutableLiveData=new MutableLiveData<>(new User());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseFirestore.getInstance();
    }

    public static UserDAO getInstance(Application app) {
        if (instance == null) {
            instance = new UserDAO(app);

        }
        return instance;
    }


    @Override
    public void addUser(Activity activity, User user, String password) {
        try{
            firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), password)
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success
                                Log.d(TAG, "createUserWithEmail:success");
                                createUser(firebaseAuth.getCurrentUser().getUid(), user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            }
                        }
                    });

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUser(User user) {
        firebaseDatabase.collection(collectionPath).document(user.getUid())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });
    }

    @Override
    public void updateUser(User newUser) {
        DocumentReference docRef = firebaseDatabase.collection(collectionPath).document(newUser.getUid());
        //updates fields
        docRef.update("email",newUser.getEmail());
        docRef.update("lastName",newUser.getLastName());
        docRef.update("firstName",newUser.getFirstName());
        docRef.update("cryptos",newUser.getCryptos());
        docRef.update("walletBallanceUSD",newUser.getWalletBallanceUSD());
    }

    @Override
    public MutableLiveData<User> getUser(String uid) {
        firebaseDatabase.collection("users").whereEqualTo("uid",uid).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        User local = document.toObject(User.class);
                        userMutableLiveData.postValue(local);
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
        return userMutableLiveData;
    }

    @Override
    public MutableLiveData<User> getUserModal() {
        return userMutableLiveData;
    }

    private void createUser(String uid, User userParam) {

        User user = new User();
        user.setEmail(userParam.getEmail());
        user.setLastName(userParam.getLastName());
        user.setFirstName(userParam.getFirstName());
        user.setUid(uid);
        user.setCryptos(null);
        user.setWalletBallanceUSD(0.0f);


        firebaseDatabase.collection(collectionPath).document(uid).set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "User created successfully!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing the user", e);
                    }
                });

    }

}
