package com.example.androidproject.UI.News;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.Entities.NewsModel;
import com.example.androidproject.Model.NewsModel.NewsAdapter;
import com.example.androidproject.Model.NewsModel.NewsServiceGenerator;
import com.example.androidproject.Model.utils.Credentials;
import com.example.androidproject.R;
import com.example.androidproject.UI.Responses.NewsResponse;
import com.example.androidproject.ViewModel.NewsVM.NewsViewModel;
import com.example.androidproject.databinding.FragmentNewsBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private NewsViewModel viewModel;
    private RecyclerView recyclerView;
    TextView textView;
    List<NewsModel> newsModel;
    NewsAdapter adapter;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.recycleviewofNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));

        viewModel.getNews().observe(getViewLifecycleOwner(), category ->{
          //   textView.setText(String.valueOf(category));

//            List<NewsModel> newsModels = new ArrayList<>();
//            newsModels.add(new NewsModel(newsModels.))

            adapter = new NewsAdapter(getContext(), newsModel);
            recyclerView.setAdapter(adapter);

                    findNews();
        }

                );





        return root;

    }

    private void findNews() {
        NewsServiceGenerator.getNewsApi().searchNews(Credentials.API_KEY_NEWS, "crypto", "en").enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()){
                    newsModel.add(response.body().getNews());


                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

            }
        });
    }
}