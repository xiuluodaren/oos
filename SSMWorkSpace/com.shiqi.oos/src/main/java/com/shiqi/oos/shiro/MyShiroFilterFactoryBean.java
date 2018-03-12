package com.shiqi.oos.shiro;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.shiqi.oos.entity.SqMenu;
import com.shiqi.oos.service.IMenuService;


public class MyShiroFilterFactoryBean extends ShiroFilterFactoryBean{

	 public static final String PREMISSION_STRING="perms[\"{0}\"]";  
     
	    private String definitions;  
	      
	    public String getDefinitions() {
			return definitions;
		}

		public String getOldDefinnitions() {
			return oldDefinnitions;
		}

		private String oldDefinnitions;
	    
	    @Autowired
	    private IMenuService menuService;    
	      
	    @Resource  
	    public void setFilterChainDefinitions(String definitions) {  
	    	
	    	if (StringUtils.isNotBlank(definitions)) {
	    		this.oldDefinnitions = definitions;
			}else{
				definitions = this.oldDefinnitions;
			}
	    	
	        String fiter="";//改正后的url配置  
	        List<SqMenu> list = menuService.findAll();  
	        for (Iterator<SqMenu> it = list.iterator(); it.hasNext();) {  
		        	SqMenu menu = it.next();  
		        	if (StringUtils.isNotBlank(menu.getUrl())) {
		        		fiter += menu.getUrl()+" = " +MessageFormat.format(PREMISSION_STRING,menu.getUrl()) +"\n";
				}
	        }  
	        
	        System.out.println(definitions + fiter + "/* = authc\n");
	        this.definitions = definitions+fiter;  
	        
	        //调用父类方法对将权限加入shiro
	        super.setFilterChainDefinitions(this.definitions);
	    }  
	    
//	    public Section getObject(){  
//	        Ini ini = new Ini();//网上好多都是在这里配置URL的。但是发现是错误的。  
//	        ini.load(filterChainDefinitions);  
//	        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);  
//	        return section;  
//	    }  
//	  
//	    public Class<?> getObjectType() {  
//	        return this.getClass();  
//	    }  
//	  
//	    public boolean isSingleton() {  
//	        return false;  
//	    }  
	
}
