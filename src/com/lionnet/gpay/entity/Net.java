package com.lionnet.gpay.entity;

/**
 * Created with IntelliJ IDEA.
 * User: weiyiming
 * Date: 13-6-4
 * Time: 下午6:16
 * To change this template use File | Settings | File Templates.
 */

/* 购卡网点对应的实体类，用以xml转java对象 */
public class Net {
    private String netName;         //网点名
    private String netTel;          //网点电话
    private String netAddr;         //网点地址
    private String company;         //网点所属公司
    private String companyAddr;     //公司地址

    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName;
    }

    public String getNetTel() {
        return netTel;
    }

    public void setNetTel(String netTel) {
        this.netTel = netTel;
    }

    public String getNetAddr() {
        return netAddr;
    }

    public void setNetAddr(String netAddr) {
        this.netAddr = netAddr;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }
}