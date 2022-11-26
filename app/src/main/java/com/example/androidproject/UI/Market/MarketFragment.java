package com.example.androidproject.UI.Market;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.androidproject.Model.Market.MarketAdapter;
import com.example.androidproject.R;
import com.example.androidproject.ViewModel.MarketVM.MarketViewModel;
import com.example.androidproject.databinding.FragmentMarketBinding;

public class MarketFragment extends Fragment {

    private FragmentMarketBinding binding;
    private MarketViewModel viewModel;
    RecyclerView recyclerView;
    MarketAdapter adapter;
    TextView priceText;





    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(MarketViewModel.class);
        binding = FragmentMarketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.recycleView);
        recyclerView.hasFixedSize();
        priceText = root.findViewById(R.id.priceText);
        Spinner spinner = root.findViewById(R.id.currency_spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String currency = spinner.getSelectedItem().toString();
                viewModel.getMarket(currency, "market_cap_desc", 30, "24h");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //Then nothing, lol
            }
        });

        viewModel.getMarketData().observeForever(marketList -> {
            adapter = new MarketAdapter(binding.getRoot().getContext(), marketList);
            recyclerView.setAdapter(adapter);
        });

         recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));

        return root;
    }
}
