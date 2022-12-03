package com.example.androidproject.Model.Market;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidproject.Entities.Market.Market;
import com.example.androidproject.R;

import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter < MarketAdapter.ViewHolder > {

    Context context;
    List < Market > markets;

    public MarketAdapter(Context context, List < Market > markets) {
        this.markets = markets; // "cryptos" would be better naming
        this.context = context;
    }

    @NonNull@Override
    public MarketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_crypto, parent,
                false);
        return new ViewHolder(view);
    }

    @SuppressLint({
            "SetTextI18n",
            "DefaultLocale"
    })@Override
    public void onBindViewHolder(@NonNull MarketAdapter.ViewHolder holder,
                                 @SuppressLint("RecyclerView") int position) {

        double percentage = (markets.get(position).getPriceChangePercentage24h());
        Glide.with(holder.itemView.getContext()).load(markets.get(position).getImage())
                .error(R.drawable.crypto_symbol_background).into(holder.tokenImage);

        holder.rank.setText(Integer.toString(markets.get(position).getMarketCapRank()));

        holder.symbol.setText(markets.get(position).getSymbol());

        holder.currentPrice.setText(String.format("%.3f", markets.get(position).getCurrentPrice()));
        if (percentage > 0) {
            holder.price_change_percentage_24h.setTextColor(context.getResources()
                    .getColor(R.color.green));
            holder.price_change_percentage_24h.setText("+" + String.format("%.2f", percentage) + "%");
        } else {
            holder.price_change_percentage_24h.setTextColor(context.getResources()
                    .getColor(R.color.red));
            holder.price_change_percentage_24h.setText(String.format("%.2f", percentage) + "%");
        }

        holder.market_cap.setText((markets.get(position).getMarketCap() / 1000000000 + "B"));
    }

    @Override
    public int getItemCount() {
        return markets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView rank;
        ImageView tokenImage;
        TextView symbol;
        TextView currentPrice;
        TextView price_change_percentage_24h;
        TextView market_cap;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tokenImage = itemView.findViewById(R.id.cryptoSymbolImage);
            rank = itemView.findViewById(R.id.rank);
            symbol = itemView.findViewById(R.id.coin);
            currentPrice = itemView.findViewById(R.id.price);
            price_change_percentage_24h = itemView.findViewById(R.id.percentage_change);
            market_cap = itemView.findViewById(R.id.market_cap);
        }
    }
}