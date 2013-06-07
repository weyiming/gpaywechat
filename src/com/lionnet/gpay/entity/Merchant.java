package com.lionnet.gpay.entity;

/**
 * Created with IntelliJ IDEA.
 * User: weiyiming
 * Date: 13-6-4
 * Time: 下午6:13
 * To change this template use File | Settings | File Templates.
 */

/* 特约商户对应的实体类，用以xml转java对象 */
public class Merchant {
    private String area;        //商户所在区域
    private String name;        //商户名
    private String address;     //商户地址
    private String tel;         //商户电话
    private String type;        //商户类型

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {

        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
