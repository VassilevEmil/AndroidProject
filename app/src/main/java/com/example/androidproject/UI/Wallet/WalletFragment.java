package com.example.androidproject.UI.Wallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.androidproject.R;
import com.example.androidproject.databinding.FragmentWalletBinding;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

public class WalletFragment extends Fragment {
    private FragmentWalletBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentWalletBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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
}
