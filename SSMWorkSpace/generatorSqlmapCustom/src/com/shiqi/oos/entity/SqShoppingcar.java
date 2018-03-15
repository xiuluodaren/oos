package com.shiqi.oos.entity;

import java.util.Date;

public class SqShoppingcar {
    private String id;

    private Date createtime;

    private Date updatetime;

    private String userid;

    private Integer shopnum;

    private Integer totalprice;

    private String diningtableid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Integer getShopnum() {
        return shopnum;
    }

    public void setShopnum(Integer shopnum) {
        this.shopnum = shopnum;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public String getDiningtableid() {
        return diningtableid;
    }

    public void setDiningtableid(String diningtableid) {
        this.diningtableid = diningtableid == null ? null : diningtableid.trim();
    }
}