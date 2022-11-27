package com.example.androidproject.UI;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;


import com.example.androidproject.Model.Market.MarketAdapter;

import com.example.androidproject.R;
import com.example.androidproject.ViewModel.Login_RegisterVM.LoginRegisterVM;
import com.example.androidproject.ViewModel.MarketVM.MarketViewModel;
import com.example.androidproject.databinding.FragmentCryptoChartBinding;

import com.google.android.material.textfield.TextInputEditText;
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
        Button searchButt;
        EditText text;

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
                super.onViewCreated(view, savedInstanceState);

                text = view.findViewById(R.id.ticker_field);
                searchButt = view.findViewById(R.id.searchButton);
                series.setDrawDataPoints(true);
                viewModel = new ViewModelProvider(this).get(MarketViewModel.class);

                searchButt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                // HIDES KEYBOARD AFTER BUTTON PRESS, SO USER CAN FOCUS ON THE CHART RIGHT AWAY
                                InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                keyboard.hideSoftInputFromWindow(text.getWindowToken(), 0);
                                // HIDES KEYBOARD AFTER BUTTON PRESS, SO USER CAN FOCUS ON THE CHART RIGHT AWAY
                                viewModel.getSparklineData(text.getText().toString()).observeForever(new Observer<List<Double>>() {
                                        @Override
                                        public void onChanged(List<Double> doubles) {

                                                series.resetData(new DataPoint[] {}); // VERY IMPORTANT BEFORE THE FOR LOOP.
                                                // We clear the data in series, before getting the new one
                                                for (int i = 0; i < doubles.size(); i++) {

                                                        series.appendData(new DataPoint(i, doubles.get(i)), false, doubles.size());

                                                }

                                        }

                                });
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

                graphView.addSeries(series);

                graphView.setTitleColor(R.color.purple_200);

                graphView.setTitleTextSize(60);

                return root;
    }
}
