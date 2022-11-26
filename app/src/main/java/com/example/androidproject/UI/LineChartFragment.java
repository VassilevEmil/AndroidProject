package com.example.androidproject.UI;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.androidproject.Model.Market.MarketAdapter;

import com.example.androidproject.R;
import com.example.androidproject.ViewModel.Login_RegisterVM.LoginRegisterVM;
import com.example.androidproject.ViewModel.MarketVM.MarketViewModel;
import com.example.androidproject.databinding.FragmentCryptoChartBinding;

import com.google.firebase.auth.FirebaseUser;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

import lombok.Data;


public class LineChartFragment extends Fragment {

        private MarketViewModel viewModel;

        FragmentCryptoChartBinding binding;
        GraphView graphView;
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();


        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                series.setDrawDataPoints(true);

                viewModel = new ViewModelProvider(this).get(MarketViewModel.class);

                viewModel.getSparklineData().observeForever(new Observer<List<Double>>() {
                        @Override
                        public void onChanged(List<Double> doubles) {

                                //DataPoint[] dp = new DataPoint[doubles.size()];
                                series.resetData(new DataPoint[] {}); // VERY IMPORTANT BEFORE THE FOR LOOP.
                                // We clear the data in series, before getting the new one
                                for (int i = 0; i < doubles.size(); i++) {

                                        //dp[i] = new DataPoint(i, doubles.get(i));
                                        series.appendData(new DataPoint(i, doubles.get(i)), false, doubles.size());

                                }
                                //series.resetData(dp);

                                }

                        });
        }


        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {

                viewModel = new ViewModelProvider(this).get(MarketViewModel.class);
                binding = FragmentCryptoChartBinding.inflate(inflater, container, false);



                View root = binding.getRoot();

                graphView = root.findViewById(R.id.cryptoGraph);
                // after adding data to our line graph series.
                // on below line we are setting
                // title for our graph view.

                graphView.addSeries(series);

                graphView.setTitle("My Graph View");

                // on below line we are setting
                // text color to our graph view.
                graphView.setTitleColor(R.color.purple_200);

                // on below line we are setting
                // our title text size.
                graphView.setTitleTextSize(18);

                return root;
    }
}
