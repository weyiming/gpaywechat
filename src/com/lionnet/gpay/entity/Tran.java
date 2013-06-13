package com.lionnet.gpay.entity;

/**
 * Created with IntelliJ IDEA.
 * Author: weiyiming
 * Date: 13-6-13
 * Time: 上午10:46
 */
public class Tran {
    private String gpayAccount;
    private String date;
    private String trantime;
    private String tranType;
    private String tranStat;
    private String merchantName;
    private String tranAmt;
    private String trace;

    public String getGpayAccount() {
        return gpayAccount;
    }

    public void setGpayAccount(String gpayAccount) {
        this.gpayAccount = gpayAccount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrantime() {
        return trantime;
    }

    public void setTrantime(String trantime) {
        this.trantime = trantime;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getTranStat() {
        return tranStat;
    }

    public void setTranStat(String tranStat) {
        this.tranStat = tranStat;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(String tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }
}
