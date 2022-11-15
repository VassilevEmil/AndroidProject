package com.example.androidproject.UI.Wallet;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.Entities.Wallet.Transaction;

import com.example.androidproject.Entities.Wallet.User;
import com.example.androidproject.R;

import com.example.androidproject.ViewModel.WalletVM.WalletAdapter;
import com.example.androidproject.ViewModel.WalletVM.WalletViewModel;
import com.example.androidproject.databinding.FragmentWalletBinding;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import lombok.NonNull;

public class WalletFragment extends Fragment {
    private FragmentWalletBinding binding;
    private WalletViewModel viewModel;
    //dialog
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    //--

    RecyclerView recyclerView;
    Button buyButton;
    Button sellButton;


    TextView totalAmmount;

    User localUser;
    TextView userID;

    //adapter
    WalletAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(WalletViewModel.class);

        binding = FragmentWalletBinding.inflate(inflater, container, false);

        localUser= new User();
        View root = binding.getRoot();
        totalAmmount = root.findViewById(R.id.portfolio_totalammount);
        recyclerView = root.findViewById(R.id.portfolio_transactions_list);
        userID = root.findViewById(R.id.test);
        recyclerView.hasFixedSize();
//

        //get userID session
        viewModel.getCurrentUser().observeForever(new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null){
                    userID.setText(firebaseUser.getUid());
                }else{
                    viewModel.loginAccount("goformusicro@gmail.com","test1234567");
                }

                setTransactionList();
            }
        });


        buyButton = root.findViewById(R.id.portfolio_buy);
        sellButton = root.findViewById(R.id.portfolio_sell);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnPortfolioBuy(view);
            }
        });

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnPortfolioSell(view);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));

        return root;
    }

    private void setTransactionList()
    {
        viewModel.getTransactions(userID.getText().toString()).observeForever(transactions ->  {
            adapter = new WalletAdapter(binding.getRoot().getContext(),transactions);


            float amount = 0.0f;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setUpGraph(transactions);
            }
            for(Transaction item:transactions)
            {
                if(item.isBuy()) {
                    amount += item.getAmount();

                }else{
                    amount -= item.getAmount();
                }
            }

            recyclerView.setAdapter(adapter);
            totalAmmount.setText(String.valueOf(amount)+" $");
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setUpGraph(List<Transaction> transactionsList){
        Map<String, PieEntry> words = new HashMap<>();
        List<Integer> colorsList = new ArrayList<>();

        for(Transaction item: transactionsList){
            if(item.isBuy()){
                List<PieEntry> pieChartList = new ArrayList<>();

                words.compute(item.getCryptoName(), (key,value) -> value ==null? new PieEntry(item.getAmount(),item.getCryptoName()) : new PieEntry(item.getAmount()+value.getValue(),item.getCryptoName()));

                pieChartList.addAll(words.values());
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                colorsList.add(color);

                PieDataSet pieDataSet = new PieDataSet(pieChartList,"Crypto in your account");
                pieDataSet.setColors(colorsList);
                pieDataSet.setDrawIcons(false);
                pieDataSet.setSliceSpace(10f);
                pieDataSet.setIconsOffset(new MPPointF(0, 40));
                pieDataSet.setSelectionShift(3f);
                PieData pieData = new PieData(pieDataSet);

                binding.portfolioChart.setData(pieData);
                binding.portfolioChart.invalidate();
            }
        }
    }

    private void OnPortfolioBuy(View view){
        dialogBuilder = new AlertDialog.Builder(getContext());
        final View popUp = getLayoutInflater().inflate(R.layout.porfolio_buy,null);
        dialogBuilder.setView(popUp);
        dialog=dialogBuilder.create();
        dialog.setCancelable(false);
        dialog.show();

        //cancel button
        Button cancelButton = popUp.findViewById(R.id.portfolio_buy_popupcancelButton);
        //save button
        Button saveButton = popUp.findViewById(R.id.portfolio_buy_popupSaveButton);

        //fields
        TextView note = popUp.findViewById(R.id.portfolio_buy_note);
        TextView amount = popUp.findViewById(R.id.portfolio_buy_amount);
        TextView cryptoName = popUp.findViewById(R.id.portfolio_buy_cryptoName);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Transaction localTr = new Transaction();
                localTr.setNote(note.getText().toString());
                localTr.setBuy(true);
                localTr.setDate(new Date().toString());
                localTr.setCryptoName(cryptoName.getText().toString());
                localTr.setAmount(Float.parseFloat(amount.getText().toString()));
                viewModel.registerATransaction(userID.getText().toString(),localTr);
                setTransactionList();
                dialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void OnPortfolioSell(View view) {
        dialogBuilder = new AlertDialog.Builder(getContext());
        final View popUp = getLayoutInflater().inflate(R.layout.porfolio_sell,null);
        dialogBuilder.setView(popUp);
        dialog=dialogBuilder.create();
        dialog.setCancelable(false);
        dialog.show();

        //cancel button
        Button cancelButton = popUp.findViewById(R.id.portfolio_sell_popupcancelButton);
        //save button
        Button saveButton = popUp.findViewById(R.id.portfolio_sell_popupSaveButton);

        //fields
        TextView note = popUp.findViewById(R.id.portfolio_sell_note);
        TextView amount = popUp.findViewById(R.id.portfolio_sell_amount);
        TextView cryptoName = popUp.findViewById(R.id.portfolio_sell_cryptoName);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transaction localTr = new Transaction();
                localTr.setNote(note.getText().toString());
                localTr.setBuy(false);
                localTr.setDate(new Date().toString());
                localTr.setCryptoName(cryptoName.getText().toString());
                localTr.setAmount(Float.parseFloat(amount.getText().toString()));
                viewModel.registerATransaction(userID.getText().toString(),localTr);
                setTransactionList();
                dialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
