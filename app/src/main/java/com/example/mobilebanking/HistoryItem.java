package com.example.mobilebanking;

public class HistoryItem {
    private String recipientAccount;
    private String recipientName;
    private String amount;
    private String description;

    public HistoryItem(String recipientAccount, String recipientName, String amount, String description) {
        this.recipientAccount = recipientAccount;
        this.recipientName = recipientName;
        this.amount = amount;
        this.description = description;
    }

    public String getRecipientAccount() {
        return recipientAccount;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
