package com.example.mobilebanking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<HistoryItem> historyItems;

    public HistoryAdapter(List<HistoryItem> historyItems) {
        this.historyItems = historyItems;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        HistoryItem historyItem = historyItems.get(position);
        holder.recipientName.setText(historyItem.getRecipientName());
        holder.recipientAccount.setText(historyItem.getRecipientAccount());
        holder.amount.setText(historyItem.getAmount());

        holder.userImage.setImageResource(R.drawable.ic_user); // Update this with actual image loading logic
    }

    @Override
    public int getItemCount() {
        return historyItems.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        ImageView userImage;
        TextView recipientName, recipientAccount, amount;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.user_image);
            recipientName = itemView.findViewById(R.id.recipient_name);
            recipientAccount = itemView.findViewById(R.id.recipient_account);
            amount = itemView.findViewById(R.id.amount);
        }
    }
}
