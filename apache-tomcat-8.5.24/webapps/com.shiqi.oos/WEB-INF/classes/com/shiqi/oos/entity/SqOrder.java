package com.shiqi.oos.entity;

import java.util.Date;
import java.util.List;

public class SqOrder {
    private String id;

    private Date createtime;

    private Date updatetime;

    private String userid;

    private String username;

    private String totalprice;

    private Integer shopnum;

    private String isdiscount;

    private String discount;

    private String receivable;

    private String realprice;

    private String changemoney;

    private String operatorid;

    private List<SqOrderdetail> detailList;
    
    private SqUser operator;
    
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice == null ? null : totalprice.trim();
    }

    public Integer getShopnum() {
        return shopnum;
    }

    public void setShopnum(Integer shopnum) {
        this.shopnum = shopnum;
    }

    public String getIsdiscount() {
        return isdiscount;
    }

    public void setIsdiscount(String isdiscount) {
        this.isdiscount = isdiscount == null ? null : isdiscount.trim();
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount == null ? null : discount.trim();
    }

    public String getReceivable() {
        return receivable;
    }

    public void setReceivable(String receivable) {
        this.receivable = receivable == null ? null : receivable.trim();
    }

    public String getRealprice() {
        return realprice;
    }

    public void setRealprice(String realprice) {
        this.realprice = realprice == null ? null : realprice.trim();
    }

    public String getChangemoney() {
        return changemoney;
    }

    public void setChangemoney(String changemoney) {
        this.changemoney = changemoney == null ? null : changemoney.trim();
    }

    public String getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(String operatorid) {
        this.operatorid = operatorid == null ? null : operatorid.trim();
    }

	public List<SqOrderdetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<SqOrderdetail> detailList) {
		this.detailList = detailList;
	}

	public SqUser getOperator() {
		return operator;
	}

	public void setOperator(SqUser operator) {
		this.operator = operator;
	}
}