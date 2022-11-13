package com.example.androidproject.UI.Wallet;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.Entities.wallet.Transaction;

import com.example.androidproject.Entities.wallet.User;
import com.example.androidproject.R;

import com.example.androidproject.ViewModel.WalletVM.WalletAdapter;
import com.example.androidproject.ViewModel.WalletVM.WalletViewModel;
import com.example.androidproject.databinding.FragmentWalletBinding;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(WalletViewModel.class);
        binding = FragmentWalletBinding.inflate(inflater, container, false);

        localUser= new User();
        View root = binding.getRoot();
        totalAmmount = root.findViewById(R.id.portfolio_totalammount);
        recyclerView = root.findViewById(R.id.portfolio_transactions_list);
        userID = root.findViewById(R.id.test);
        recyclerView.hasFixedSize();


        //get userID session
        viewModel.getCurrentUser().observeForever(user->{
            if(user != null){
                userID.setText(user.getUid());
            }
        });

        //populate transactions list
        viewModel.getTransactions(userID.getText().toString()).observeForever(transactionsList -> {
            adapter = new WalletAdapter(binding.getRoot().getContext(),transactionsList);

            float amount = 0.0f;

            for(Transaction item:transactionsList)
            {
                if(item.isBuy()) {
                    amount += item.getAmount();
                }else{
                    amount -= item.getAmount();
                }
            }


            totalAmmount.setText(String.valueOf(amount));


            recyclerView.setAdapter(adapter);
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
        setUpGraph();

        return root;
    }

    private void setUpGraph(){

        long x =10;

        List<PieEntry> pieChartList = new ArrayList<>();
        List<Integer> colorsList = new ArrayList<>();

        pieChartList.add(new PieEntry(x,"X"));
        colorsList.add(getResources().getColor(R.color.purple_200));
        pieChartList.add(new PieEntry(5,"Y"));
        colorsList.add(getResources().getColor(R.color.black));

        PieDataSet pieDataSet = new PieDataSet(pieChartList,"");
        pieDataSet.setColors(colorsList);
        pieDataSet.setDrawIcons(false);
        pieDataSet.setSliceSpace(10f);
        pieDataSet.setIconsOffset(new MPPointF(0, 40));
        pieDataSet.setSelectionShift(3f);
        PieData pieData = new PieData(pieDataSet);

        binding.portfolioChart.setData(pieData);
        binding.portfolioChart.invalidate();
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
                viewModel.getUser(userID.getText().toString()).observe(getViewLifecycleOwner(), user -> {
                    Transaction localTr = new Transaction();
                    localTr.setNote(note.getText().toString());
                    localTr.setBuy(true);
                    localTr.setDate(new Date().toString());
                    localTr.setCryptoName(cryptoName.getText().toString());
                    localTr.setAmount(Float.parseFloat(amount.getText().toString()));
                    viewModel.registerATransaction(user.getUid(),localTr);
                });
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
                viewModel.getUser(userID.getText().toString()).observe(getViewLifecycleOwner(), user -> {
                    Transaction localTr = new Transaction();
                    localTr.setNote(note.getText().toString());
                    localTr.setBuy(false);
                    localTr.setDate(new Date().toString());
                    localTr.setCryptoName(cryptoName.getText().toString());
                    localTr.setAmount(Float.parseFloat(amount.getText().toString()));
                    viewModel.registerATransaction(user.getUid(),localTr);
                });
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
