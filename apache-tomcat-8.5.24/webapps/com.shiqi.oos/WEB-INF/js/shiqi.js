/**
 * @ClassName shiqi
 * @Description 
 * @Author 修罗
 * @Date 2018年2月6日 下午4:35:23
 */

//13位时间戳转日期 yyyy-MM-dd hh:mm:ss
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = (date.getDate() < 10 ? '0'+(date.getDate()) : date.getDate()) + ' ';
    h = (date.getHours() < 10 ? '0'+(date.getHours()) : date.getHours()) + ':';
    m = (date.getMinutes() < 10 ? '0'+(date.getMinutes()) : date.getMinutes()) + ':';
    s = (date.getSeconds() < 10 ? '0'+(date.getSeconds()) : date.getSeconds());
    return Y+M+D+h+m+s;
}

//将对象属性值的null替换成""
function displayProp(obj){   
	var retObj = {};
          
    for(var name in obj){       
       	var value = obj[name];
       	if(value != null && value != "null")
		{
			retObj[name] = value;
		}else{
			retObj[name] = "";
		}
    }  
    
    return retObj;
}

//将对象属性值的""替换成null
function playProp(obj){   
	var retObj = {};
          
    for(var name in obj){       
       	var value = obj[name];
       	if(value != null && value != "")
		{
			retObj[name] = value;
		}else{
			retObj[name] = null;
		}
    }  
    
    return retObj;
}