package com.example.androidproject.UI.News;

import android.app.Activity;
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



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recycleviewofNews);
        recyclerView.hasFixedSize();


        viewModel.getSearchedNews().observe(getViewLifecycleOwner(),new Observer<List<NewsModel>>() {
            @Override
            public void onChanged(List<NewsModel> newsModels) {
                System.out.println(newsModels);
                adapter = new NewsAdapter(binding.getRoot().getContext(),newsModels);
                recyclerView.setAdapter(adapter);
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));



        return root;

    }

//    private void findNews() {
//        NewsServiceGenerator.getNewsApi().searchNews(Credentials.API_KEY_NEWS, "crypto", "en").enqueue(new Callback<NewsResponse>() {
//            @Override
//            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
//                if (response.isSuccessful()){
//                    //newsModel.add(response.body().getNews());
//                    System.out.println("\n something????"+response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NewsResponse> call, Throwable t) {
//                System.out.println(t.getMessage());
//            }
//        });
//    }
}