package com.IMSTask;

/**
 * Created by yegorm on 16.09.2016.
 */
public class Player {

    private int id;
    private String username;
    private int balance;
    private int balance_version;

    public Player() {

    }

    public Player(int id, String username, int balance, int balance_version) {
        this.id = id;
        this.username = username;
        this.balance = balance;
        this.balance_version = balance_version;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getBalance() {
        return balance;
    }

    public int getBalance_version() {
        return balance_version;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setBalance_version(int balance_version) {
        this.balance_version = balance_version;
    }

}
