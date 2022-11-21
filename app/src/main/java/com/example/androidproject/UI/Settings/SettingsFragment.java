package com.example.androidproject.UI.Settings;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.example.androidproject.R;
import com.example.androidproject.ViewModel.WalletVM.WalletViewModel;
import com.example.androidproject.databinding.FragmentSettingsBinding;
import com.example.androidproject.databinding.FragmentWalletBinding;
import com.google.firebase.auth.FirebaseUser;

import lombok.NonNull;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private WalletViewModel walletViewModel;

    private Switch darkModeSwitch;
    private TextView darkModeText;
    private TextView userDetails;
    private Button signOutButton;
    private NavController navController;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        walletViewModel = new ViewModelProvider(this).get(WalletViewModel.class);
        View root = binding.getRoot();

        darkModeSwitch = root.findViewById(R.id.navigation_settings_darkMode_switch);
        darkModeText = root.findViewById(R.id.navigation_settings_darkMode);
        userDetails = root.findViewById(R.id.navigation_settings_user_details);
        signOutButton = root.findViewById(R.id.navigation_settings_sign_out);

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
                    userDetails.setText(walletViewModel.getUser(firebaseUser.getUid()).getValue().toString());
                }else{
                    //test until the login will be done
                    //walletViewModel.loginAccount((Activity) root.getContext(),"goformusicro@gmail.com","test1234567");
                }
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                walletViewModel.signOut();
                navController.navigate(R.id.action_signOutFragment_to_signInFragment);
            }
        });

        return root;
    }
}
