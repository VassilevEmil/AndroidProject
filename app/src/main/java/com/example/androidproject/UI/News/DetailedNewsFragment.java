package com.example.androidproject.UI.News;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.androidproject.R;
import com.example.androidproject.ViewModel.NewsVM.NewsViewModel;
import com.example.androidproject.databinding.FragmentDetailedNewsBinding;


public class DetailedNewsFragment extends Fragment {

    WebView webView;
    private FragmentDetailedNewsBinding binding;
    private NewsViewModel newsViewModel;
    Bundle bundle = new Bundle();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailedNewsBinding.inflate(inflater, container, false);
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        View root = binding.getRoot();

        webView = root.findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("url");


      //   WebSettings webSettings = webView.getSettings();




//        String url = getArguments().getString("url");
//        bundle.putString("url", url);
//        DetailedNewsFragment fragment = new DetailedNewsFragment();
//        fragment.setArguments(bundle);

       // return inflater.inflate(R.layout.fragment_detailed_news, container, false);

        return root;

    }
}