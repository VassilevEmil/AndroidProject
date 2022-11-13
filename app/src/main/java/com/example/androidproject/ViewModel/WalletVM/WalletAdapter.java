package com.example.androidproject.ViewModel.WalletVM;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.Entities.wallet.Transaction;
import com.example.androidproject.Model.NewsModel.NewsAdapter;
import com.example.androidproject.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.NonNull;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.ViewHolder> {
    Context context;
    List<Transaction> transactions=new ArrayList<>();

    public WalletAdapter(Context context, List<Transaction> transactions){
        this.context=context;
        this.transactions=transactions;
    }

    @Override
    public WalletAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_wallet_transactions, null, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(WalletAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {

        if(transactions!=null){
            viewHolder.note.setText(transactions.get(i).getNote());
            viewHolder.cryptoName.setText(transactions.get(i).getCryptoName());
            viewHolder.date.setText(transactions.get(i).getDate());

            if(transactions.get(i).isBuy()){
                viewHolder.ammount.setTextColor(context.getResources().getColor(R.color.green));
                viewHolder.ammount.setText("+"+transactions.get(i).getAmount()+" $");
            }else{
                viewHolder.ammount.setTextColor(context.getResources().getColor(R.color.red));
                viewHolder.ammount.setText("-"+transactions.get(i).getAmount()+" $");
            }
        }
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView note, cryptoName, date, ammount;

        public ViewHolder(View itemView) {
            super(itemView);
            note = itemView.findViewById(R.id.portfolio_transaction_note);
            cryptoName = itemView.findViewById(R.id.portfolio_transaction_crptopName);
            date = itemView.findViewById(R.id.portfolio_transaction_date);
            ammount = itemView.findViewById(R.id.portfolio_transaction_amount);
        }
    }
}
