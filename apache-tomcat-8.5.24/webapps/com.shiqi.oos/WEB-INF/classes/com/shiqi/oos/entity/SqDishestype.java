package com.shiqi.oos.entity;

public class SqDishestype implements Comparable<SqDishestype> {
    private String id;

    private String name;

    private String isshowonhome;

    public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getIsshowonhome() {
		return isshowonhome;
	}



	public void setIsshowonhome(String isshowonhome) {
		this.isshowonhome = isshowonhome;
	}



	public Integer getZindex() {
		return zindex;
	}



	public void setZindex(Integer zindex) {
		this.zindex = zindex;
	}



	private Integer zindex;

    
    
	@Override
	public int compareTo(SqDishestype type) {
		if (this.zindex > type.zindex) {
			return 1;
		}else {
			return -1;
		}
		
	}
    
}