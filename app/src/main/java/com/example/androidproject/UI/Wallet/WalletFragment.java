package com.example.androidproject.UI.Wallet;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class WalletFragment extends Fragment {
    private FragmentWalletBinding binding;
    private WalletViewModel viewModel;
    //dialog
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    //--
    RecyclerView recyclerView;
    Button buyButton;

    TextView totalAmmount;

    User localUser;
    TextView userID;

    FirebaseAuth firebaseAuth;

    //adapter
    WalletAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        localUser= new User();
        viewModel = new ViewModelProvider(this).get(WalletViewModel.class);
        binding = FragmentWalletBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        totalAmmount = root.findViewById(R.id.portfolio_totalammount);
        recyclerView = root.findViewById(R.id.portfolio_transactions_list);
        userID = root.findViewById(R.id.test);
        recyclerView.hasFixedSize();





        //viewModel.registerAccount((Activity) binding.getRoot().getContext(),local,"test1234567");
        //



        viewModel.getCurrentUser().observeForever(user->{
            if(user != null){
                userID.setText(user.getUid());
                System.out.println(userID.getText().toString());
            }
        });




        //adapter = new WalletAdapter(binding.getRoot().getContext(),transactions);
        //recyclerView.setAdapter(adapter);

        //working??



        //


        buyButton = root.findViewById(R.id.portfolio_buy);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnPortfolioBuy(view);
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
        dialogBuilder = new AlertDialog.Builder(this.getContext());
        final View popUp = getLayoutInflater().inflate(R.layout.porfolio_buy,null);
        dialogBuilder.setView(popUp);
        dialog=dialogBuilder.create();
        dialog.setCancelable(false);
        dialog.show();

        //cancel button
        Button cancelButton = popUp.findViewById(R.id.popupcancelbutton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getUser(userID.getText().toString()).observe(getViewLifecycleOwner(),user -> {
                    Transaction localTr = new Transaction();
                    localTr.setNote("TestWork?");
                    localTr.setBuy(true);
                    localTr.setDate(new Date().toString());
                    localTr.setAmount(50);
                    viewModel.registerATransaction(user.getUid(),localTr);
                });
                dialog.dismiss();
            }
        });

        Toast.makeText(this.getContext(),"Click on floating plus", Toast.LENGTH_LONG).show();
        Log.d(TAG,"onCreate was called");
    }
}
