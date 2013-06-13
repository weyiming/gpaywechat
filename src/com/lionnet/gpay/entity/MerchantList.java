package com.lionnet.gpay.entity;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: weiyiming
 * Date: 13-6-4
 * Time: 下午7:26
 * To change this template use File | Settings | File Templates.
 */
public class MerchantList {
    private ArrayList<Merchant> merchantList;

    public ArrayList<Merchant> getMerchants() {
        return merchantList;
    }

    public void setMerchants(ArrayList<Merchant> merchantList) {
        this.merchantList = merchantList;
    }
}
