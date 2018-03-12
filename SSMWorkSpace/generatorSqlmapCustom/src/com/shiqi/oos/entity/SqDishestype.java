package com.shiqi.oos.entity;

public class SqDishestype {
    private String id;

    private String name;

    private String isshowonhome;

    private Integer zindex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIsshowonhome() {
        return isshowonhome;
    }

    public void setIsshowonhome(String isshowonhome) {
        this.isshowonhome = isshowonhome == null ? null : isshowonhome.trim();
    }

    public Integer getZindex() {
        return zindex;
    }

    public void setZindex(Integer zindex) {
        this.zindex = zindex;
    }
}