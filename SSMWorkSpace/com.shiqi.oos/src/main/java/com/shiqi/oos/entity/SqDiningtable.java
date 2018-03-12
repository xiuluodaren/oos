package com.shiqi.oos.entity;

public class SqDiningtable {
    private String id;

    private String shopcarid;

    private String renshu;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getShopcarid() {
        return shopcarid;
    }

    public void setShopcarid(String shopcarid) {
        this.shopcarid = shopcarid == null ? null : shopcarid.trim();
    }

    public String getRenshu() {
        return renshu;
    }

    public void setRenshu(String renshu) {
        this.renshu = renshu == null ? null : renshu.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}