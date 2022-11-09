package com.example.androidproject.UI.Market;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.Entities.Market.Market;
import com.example.androidproject.Model.Market.MarketAdapter;
import com.example.androidproject.R;
import com.example.androidproject.ViewModel.MarketVM.MarketViewModel;
import com.example.androidproject.databinding.FragmentMarketBinding;

import java.util.ArrayList;
import java.util.List;

public class MarketFragment extends Fragment {
    private FragmentMarketBinding binding;
    private MarketViewModel viewModel;
    RecyclerView recyclerView;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(MarketViewModel.class);

        binding = FragmentMarketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recycleView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));

        viewModel.getMarketData().observe(getViewLifecycleOwner(),number->{
            //textView.setText(String.valueOf(number));

            List<Market> markets = new ArrayList<>();
            markets.add(new Market(1, "something.com", "BTC", 1900.52, 0.08, 100000));
            markets.add(new Market(2, "something.com", "ETH", 10000, 0.08, 100000));
            markets.add(new Market(3, "something.com", "LTC", 85, 8000, 5050505));
            markets.add(new Market(4, "something.com", "LUNAC", 9000000, 5000, 50505));

            MarketAdapter adapter = new MarketAdapter(markets);
            recyclerView.setAdapter(adapter);
        });


        return root;
    }

}
