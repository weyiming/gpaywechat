package com.lionnet.gpay.entity;

/**
 * Created with IntelliJ IDEA.
 * User: weiyiming
 * Date: 13-6-12
 * Time: 下午10:33
 * To change this template use File | Settings | File Templates.
 */
public class AccountBalance {
    private String gpayAccount;
    private String queryTime;
    private String balance;

    public String getGpayAccount() {
        return gpayAccount;
    }

    public void setGpayAccount(String gpayAccount) {
        this.gpayAccount = gpayAccount;
    }

    public String getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
