package com.example.androidproject.UI.Market;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.Entities.Market.Market;

import com.example.androidproject.Model.Market.MarketAdapter;
import com.example.androidproject.Model.Market.MarketServiceGenerator;
import com.example.androidproject.R;
import com.example.androidproject.UI.Responses.MarketResponse;
import com.example.androidproject.ViewModel.MarketVM.MarketViewModel;
import com.example.androidproject.databinding.FragmentMarketBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarketFragment extends Fragment {
    private FragmentMarketBinding binding;
    private MarketViewModel viewModel;
    RecyclerView recyclerView;
    List<Market> market = new ArrayList<Market>();




    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(MarketViewModel.class);

        binding = FragmentMarketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recycleView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        viewModel.getMarketData().observe(getViewLifecycleOwner(), number->{

            MarketAdapter adapter = new MarketAdapter(market); // passing List<Market> which includes data from api
            recyclerView.setAdapter(adapter);
        }
             );

        findMarket();
        return root;
    }

    private void findMarket() {
        MarketServiceGenerator.getMarketApi().searchMarket("usd", "market_cap_desc", "50").enqueue(new Callback<MarketResponse>() {
            @Override
            public void onResponse(Call<MarketResponse> call, Response<MarketResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("MARKET_RESPONSE_BODY","SUCCESSFUL REQUEST");

                    assert response.body() != null;
                    Log.d("API_DATA_TEST","PASSED");
                    market.add(response.body().getMarket());
                } else Log.d("MARKET_RESPONSE_BODY","UNSUCCESSFUL REQUEST"+ response.code());

            }

            @Override
            public void onFailure(Call<MarketResponse> call, Throwable t) {
                Toast.makeText(binding.getRoot().getContext(), "Error: ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
