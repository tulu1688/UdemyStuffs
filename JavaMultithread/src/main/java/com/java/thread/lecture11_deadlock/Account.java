package com.java.thread.lecture11_deadlock;

/**
 * Created by trankhai on 7/12/17.
 */
public class Account {
    private int balance = 10000;

    public void deposit(int amount){
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer(Account acc1, Account acc2, int amount){
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }
}
