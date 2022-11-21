package com.example.androidproject.UI.Login_Register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidproject.R;
import com.example.androidproject.ViewModel.Login_RegisterVM.LoginRegisterVM;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    private EditText emailEdit, passEdit;
    private TextView signUpText;
    private Button signInBtn;
    private LoginRegisterVM viewModel;
    private NavController navController;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoginRegisterVM.class);
        viewModel.getUserData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null){
                    //navController.navigate(R.id.action_signIn_to_main);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailEdit = view.findViewById(R.id.emailEditSignIn);
        passEdit = view.findViewById(R.id.passEditSignIn);
        signUpText = view.findViewById(R.id.signUpText);
        signInBtn = view.findViewById(R.id.signInBtn);

        navController = Navigation.findNavController(view);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_signInFragment_to_signUpFragment);
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEdit.getText().toString();
                String pass = passEdit.getText().toString();

                if (!email.isEmpty() && !pass.isEmpty()){
                    viewModel.signIn(email , pass);
                }
            }
        });
    }
}