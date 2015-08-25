package com.center.buuluu.cache.aop;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.center.buuluu.annotation.CacheKey;
import com.center.buuluu.annotation.Cacheable;
import com.center.buuluu.annotation.Cacheable.TypeMode;

@Aspect  
@Component 
public class CacheableAop {

	@Autowired 
	private RedisTemplate<Serializable, Serializable> redisTemplate;
    
    @Around("@annotation(cache)")  
    public Object cached(final ProceedingJoinPoint pjp,Cacheable cache) throws Throwable {  
          
        String key=getCacheKey(pjp, cache);  
        Object value= cacheValue(cache, key);    //从缓存获取数据  
        if(value!=null) return value;       //如果有数据,则直接返回  
        /*
         * start   
         */
        value = pjp.proceed();     //如果没有数据，跳过缓存,到后端查询数据  
        /*
         * 查询数据库后，value还是没有值，表示该信息不存在
         */
        if (value==null||value.toString().equals("")) 
			return value;
		/*
		 * end   目前这点代码看不懂，不知道proceed（）方法的功能是干什么的，
		 */
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("key", key);//key值不变
        map.put("value", value);//修改value的值
        putCacheValue(cache,map);//将新查询出来的数据保存到缓存中
         return value;  
     }  
     
     private void putCacheValue(Cacheable cache,Map<String,Object> map) throws InstantiationException, IllegalAccessException{
     	String key = map.get("key").toString();
     	Serializable value = (Serializable) map.get("value");
     	if (cache.type()==TypeMode.STRING) {
     		  ValueOperations<Serializable, Serializable> valueOper = redisTemplate.opsForValue();
     	      valueOper.set(key,  value); 
 		}else if (cache.type()==TypeMode.HASH) {
 			   HashOperations<Serializable, Object, Object> valueOper = redisTemplate.opsForHash();
 			   String tableName = cache.table().getSimpleName();
 			   valueOper.put(tableName, key, value);
 		}	  
// 		}else if (cache.type()==TypeMode.LIST) {
// 			ListOperations<Serializable, Serializable> valueOper = redisTemplate.opsForList();
// 			valueOper.set(arg0, arg1, arg2);
// 		}else if (cache.type()==TypeMode.SET) {
// 			SetOperations<Serializable, Serializable> valueOper = redisTemplate.opsForSet();
// 			 value = value =value=valueOper.getOperations();
// 		}
     	
     }
    private Object cacheValue(Cacheable cache,String key){
    	Object value = null;
    	if (cache.type()==TypeMode.STRING) {
    		  ValueOperations<Serializable, Serializable> valueOper = redisTemplate.opsForValue();
    	        value = valueOper.get(key);    
		}else if (cache.type()==TypeMode.HASH) {
			   HashOperations<Serializable, Object, Object> valueOper = redisTemplate.opsForHash();
			   String tableName = cache.table().getSimpleName();//得到缓存时候存的表的名字
 	            value = valueOper.get(tableName, key);//得到缓存中的数据
		}else if (cache.type()==TypeMode.LIST) {
			ListOperations<Serializable, Serializable> valueOper = redisTemplate.opsForList();
			value =valueOper.getOperations();
		}else if (cache.type()==TypeMode.SET) {
			SetOperations<Serializable, Serializable> valueOper = redisTemplate.opsForSet();
			value=valueOper.getOperations();
		}
    	
    	return value;
    }
    
    /** 
     * 获取缓存的key值 
     * @param pjp 
     * @param cache 
     * @return 
     */  
    private String getCacheKey(ProceedingJoinPoint pjp,Cacheable cache) { 
    	String key = "";
    	Object[] args=pjp.getArgs(); //得到函数自变量的集合（map中 key的值）
    	  Annotation[][] pas=((MethodSignature)pjp.getSignature()).getMethod().getParameterAnnotations(); //得到请求参数的值（controller）
          for(int i=0;i<pas.length;i++) {  
              for(Annotation an:pas[i]) {  
                  if(an instanceof CacheKey) {  
                      key =args[i].toString();  
                      break;  
                  }  
              }  
          }
          return key;
    }
    
}
