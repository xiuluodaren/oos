package com.shiqi.oos.entity;

import java.util.List;

public class SqMenu implements Comparable<SqMenu> {
    private String id;

    private String menuname;

    private String parentid;

    private String state = "closed";
    
    private List<SqMenu> children;
    
    private String url;

    private Integer zindex;
    
    private SqMenu parentMenu;
    
    public Integer getZindex() {
        return zindex;
    }

    public void setZindex(Integer zindex) {
        this.zindex = zindex;
    }

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

	public List<SqMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SqMenu> children) {
		if (children != null && children.size() == 0)
		{
			this.setState(null);
		}
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

	@Override
	public int compareTo(SqMenu menu) {
		
		if (this.zindex > menu.zindex) {
			return 1;
		}else {
			return -1;
		}
		
	}

	public SqMenu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(SqMenu parentMenu) {
		if (parentMenu != null) {
			parentMenu.setChildren(null);
		}
		this.parentMenu = parentMenu;
	}
    
}