package com.lionnet.gpay.entity;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: weiyiming
 * Date: 13-6-4
 * Time: 下午7:30
 * To change this template use File | Settings | File Templates.
 */
public class NetAddressList {
    private ArrayList<NetAddress> netAddresseList;

    public ArrayList<NetAddress> getNetAddresses() {
        return netAddresseList;
    }

    public void setNetAddresses(ArrayList<NetAddress> netAddresseList) {
        this.netAddresseList = netAddresseList;
    }
}
