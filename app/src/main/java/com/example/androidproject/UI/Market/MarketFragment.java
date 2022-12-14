package com.example.androidproject.UI.Market;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.Entities.Market.Market;
import com.example.androidproject.Model.Market.MarketAdapter;
import com.example.androidproject.R;
import com.example.androidproject.ViewModel.MarketVM.MarketViewModel;
import com.example.androidproject.databinding.FragmentMarketBinding;

import java.util.List;

public class MarketFragment extends Fragment {

    private FragmentMarketBinding binding;
    private MarketViewModel viewModel;
    RecyclerView recyclerView;
    MarketAdapter adapter;
    TextView priceText;
    Button buttonToFun;
    private NavController navController;
    ProgressBar progressBar;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(MarketViewModel.class);
        binding = FragmentMarketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.recycleView);
        recyclerView.hasFixedSize();
        priceText = root.findViewById(R.id.priceText);
        progressBar = root.findViewById(R.id.progress_circular);

        buttonToFun = root.findViewById(R.id.buttonToFun);

        Spinner spinner = root.findViewById(R.id.currency_spinner);

        progressBar.setVisibility(View.VISIBLE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id) {
                String currency = spinner.getSelectedItem().toString();
                viewModel.getMarket(currency, "market_cap_desc", 30,
                        "24h", true);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //Then nothing, lol
            }
        });
        viewModel.getMarketData().observe(getViewLifecycleOwner(), new Observer<List<Market>>() {
            @Override
            public void onChanged(List<Market> markets) {
                adapter = new MarketAdapter(binding.getRoot().getContext(), markets);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        buttonToFun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_crypto_charts);
            }
        });
    }
}
