package com.example.androidproject.UI.News;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.Entities.News.NewsModel;
import com.example.androidproject.Model.NewsModel.NewsAdapter;
import com.example.androidproject.R;
import com.example.androidproject.ViewModel.NewsVM.NewsViewModel;
import com.example.androidproject.ViewModel.WalletVM.WalletViewModel;
import com.example.androidproject.databinding.FragmentNewsBinding;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private NewsViewModel viewModel;
    RecyclerView recyclerView;
    TextView textView;
    List<NewsModel> newsModel = new ArrayList<>();
    NewsAdapter adapter;
    TextView userID;

    private WalletViewModel walletViewModel;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recycleviewofNews);
        recyclerView.hasFixedSize();

        viewModel.getNews();
        viewModel.getSearchedNews().observeForever(newsList->{
           adapter = new NewsAdapter(binding.getRoot().getContext(),newsList,viewModel);
            recyclerView.setAdapter(adapter);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));

        return root;
    }




}