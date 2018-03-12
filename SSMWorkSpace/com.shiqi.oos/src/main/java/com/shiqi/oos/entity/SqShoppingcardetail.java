package com.shiqi.oos.entity;

import java.util.Date;

public class SqShoppingcardetail {
    private String id;

    private String shoppingcarid;

    private String shopid;

    private Date createtime;

    private Date updatetime;

    private Integer shopnum;

    private Integer xiaoji;

    private String status;
    
    private SqDishes dishes;
    
    public SqDishes getDishes() {
		return dishes;
	}

	public void setDishes(SqDishes dishes) {
		this.dishes = dishes;
	}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getShoppingcarid() {
        return shoppingcarid;
    }

    public void setShoppingcarid(String shoppingcarid) {
        this.shoppingcarid = shoppingcarid == null ? null : shoppingcarid.trim();
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid == null ? null : shopid.trim();
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

    public Integer getShopnum() {
        return shopnum;
    }

    public void setShopnum(Integer shopnum) {
        this.shopnum = shopnum;
    }

    public Integer getXiaoji() {
        return xiaoji;
    }

    public void setXiaoji(Integer xiaoji) {
        this.xiaoji = xiaoji;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}