package com.example.androidproject.UI.Settings;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.androidproject.Entities.Wallet.User;
import com.example.androidproject.R;
import com.example.androidproject.ViewModel.Login_RegisterVM.LoginRegisterVM;
import com.example.androidproject.ViewModel.WalletVM.WalletViewModel;
import com.example.androidproject.databinding.FragmentSettingsBinding;
import com.example.androidproject.databinding.FragmentWalletBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import lombok.NonNull;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private WalletViewModel walletViewModel;

    private Switch darkModeSwitch;
    private TextView darkModeText;
    private Button signOutButton;
    private Button updateUserButton;
    private Button deleteUserButton;

    private TextView firstNameEditText;
    private TextView lastNameEditText;

    private String userID;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        walletViewModel = new ViewModelProvider(this).get(WalletViewModel.class);
        View root = binding.getRoot();
        userID = new String();
        darkModeSwitch = root.findViewById(R.id.navigation_settings_darkMode_switch);
        darkModeText = root.findViewById(R.id.navigation_settings_darkMode);
        signOutButton = root.findViewById(R.id.navigation_settings_sign_out);
        updateUserButton = root.findViewById(R.id.navigation_settings_user_update);
        deleteUserButton = root.findViewById(R.id.navigation_settings_user_delete);
        firstNameEditText = root.findViewById(R.id.navigation_settings_user_firstName);
        lastNameEditText = root.findViewById(R.id.navigation_settings_user_lastName);


        System.out.println(userID.toString());

        darkModeSwitch.setOnCheckedChangeListener((compoundButton, isSwitch) -> {
            if(isSwitch){
                darkModeText.setText("Change to Light Mode");
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }else{
                darkModeText.setText("Change to Dark Mode");
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        //get userID session
        walletViewModel.getCurrentUser().observeForever(new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null){
                    userID = firebaseUser.getUid();
                    walletViewModel.getUser(userID).observeForever(new Observer<User>() {
                        @Override
                        public void onChanged(User user) {
                            if(user!=null){
                                firstNameEditText.setText(user.getFirstName());
                                lastNameEditText.setText(user.getLastName());
                            }
                        }
                    });
                }
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                walletViewModel.signOut();
                Navigation.findNavController(view).navigate(R.id.action_signOutFragment_to_signInFragment);
            }
        });

        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                walletViewModel.removeUser(userID);
                Navigation.findNavController(view).navigate(R.id.action_signOutFragment_to_signInFragment);
            }
        });

        updateUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User local = walletViewModel.getUser(userID).getValue();
                local.setFirstName(firstNameEditText.getText().toString());
                local.setLastName(lastNameEditText.getText().toString());
                walletViewModel.updateUser(local);
            }
        });

        return root;
    }
}
