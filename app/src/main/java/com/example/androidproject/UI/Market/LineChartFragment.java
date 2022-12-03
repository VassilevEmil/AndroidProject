package com.example.androidproject.UI.Market;


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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.androidproject.R;
import com.example.androidproject.ViewModel.MarketVM.MarketViewModel;
import com.example.androidproject.databinding.FragmentCryptoChartBinding;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;



public class LineChartFragment extends Fragment {

        private MarketViewModel viewModel;

        FragmentCryptoChartBinding binding;
        GraphView graphView;
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        Button searchButt;
        EditText text;
        Button backButton;
        private NavController navController;

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

                                        series.resetData(new DataPoint[]{});
                                        // HIDES KEYBOARD AFTER BUTTON PRESS,
                                        // SO USER CAN FOCUS ON THE CHART RIGHT AWAY:
                                        InputMethodManager keyboard = (InputMethodManager) getActivity()
                                                .getSystemService(Context.INPUT_METHOD_SERVICE);
                                        keyboard.hideSoftInputFromWindow(text.getWindowToken(), 0);
                                        // END
                                        viewModel.getSparklineData(text.getText().toString())
                                                .observe(getViewLifecycleOwner(), new Observer<List<Double>>() {
                                                        @Override
                                                        public void onChanged(List<Double> doubles) {
                                                                // VERY IMPORTANT BEFORE THE FOR LOOP:
                                                                series.resetData(new DataPoint[]{});
                                                                // We clear the data in series, before getting the new one

                                                                for (int i = 80; i < doubles.size(); i++) {

                                                                        series.appendData(new DataPoint(i, doubles
                                                                                .get(i)), false, doubles.size());
                                                                }
                                                        }
                                                });
                                }


        });
                backButton = view.findViewById(R.id.backButton);

                navController = Navigation.findNavController(view);

                backButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                navController.navigate(R.id.navigation_market);
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

                return root;
    }
}
