package com.IMSTask;

/**
 * Created by yegorm on 30.09.2016.
 */
public class History {
    private int idHistory;
    private int transactionId;
    private String username;
    private double balanceRequest;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBalanceRequest() {
        return balanceRequest;
    }

    public void setBalanceRequest(double balanceRequest) {
        this.balanceRequest = balanceRequest;
    }
}
