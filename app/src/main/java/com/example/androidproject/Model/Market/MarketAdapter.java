package com.example.androidproject.Model.Market;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.Entities.Market.Market;
import com.example.androidproject.R;

import java.util.ArrayList;
import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.ViewHolder> {

    Context context;
    List<Market> markets;

    public MarketAdapter(Context context, List<Market> markets){
        this.markets = markets; // "cryptos" would be better naming
        this.context = context;
    }

    @NonNull
    @Override
    public MarketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_crypto, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MarketAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
    holder.rank.setText(Integer.toString(markets.get(position).getMarketCapRank()));
    holder.symbol.setText(markets.get(position).getSymbol());
    holder.currentPrice.setText(String.valueOf(markets.get(position).getCurrentPrice()));
    holder.price_change_percentage_24h.setText(String.valueOf(markets.get(position).getPriceChangePercentage24h()));
    holder.market_cap.setText(String.valueOf(markets.get(position).getMarketCap()));
    }

    @Override
    public int getItemCount() {
        return markets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

            TextView rank;
            // tokenImage_url; NEED TO SEE HOW TO ADD IMAGES
            TextView symbol;
            TextView currentPrice;
            TextView price_change_percentage_24h;
            TextView market_cap;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rank = itemView.findViewById(R.id.rank);
            symbol = itemView.findViewById(R.id.coin);
            currentPrice = itemView.findViewById(R.id.price);
            price_change_percentage_24h = itemView.findViewById(R.id.percentage_change);
            market_cap = itemView.findViewById(R.id.market_cap);
        }
    }
}
