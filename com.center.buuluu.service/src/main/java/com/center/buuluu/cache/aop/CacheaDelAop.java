package com.center.buuluu.cache.aop;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.center.buuluu.annotation.CacheDel;
import com.center.buuluu.annotation.CacheDel.DelsTypeMode;
import com.center.buuluu.annotation.CacheKey;
@Aspect  
@Component 
public class CacheaDelAop {

	@Autowired 
	private RedisTemplate<Serializable, Serializable> redisTemplate;
	
	@AfterReturning("@annotation(cache)")  
    public void cached(final JoinPoint point,CacheDel cache) throws Throwable {  
          
		delCacheValue(cache, getCache(point, cache));    
    }  
    private void delCacheValue(CacheDel cache,List<Object> keys) throws InstantiationException, IllegalAccessException{
    	if (cache.type()==DelsTypeMode.STRING) {
    				for (Object obj : keys) {
    					redisTemplate.delete((Serializable)obj);
					}
		}else if (cache.type()==DelsTypeMode.HASH) {
			   HashOperations<Serializable, Object, Object> valueOper = redisTemplate.opsForHash();
			   String tableName = cache.table().getSimpleName();
			   for (Object obj : keys) {
				   valueOper.delete(tableName, obj.toString());
				}
		}	  
//		}else if (cache.type()==TypeMode.LIST) {
//			ListOperations<Serializable, Serializable> valueOper = redisTemplate.opsForList();
//			valueOper.set(arg0, arg1, arg2);
//		}else if (cache.type()==TypeMode.SET) {
//			SetOperations<Serializable, Serializable> valueOper = redisTemplate.opsForSet();
//			 value = value =value=valueOper.getOperations();
//		}
    	
    }
    
    /** 
     * 获取缓存的key值 
     * @param pjp 
     * @param cache 
     * @return 
     */  
    private List<Object> getCache(JoinPoint point,CacheDel cache) {
    	List<Object>  keyList = new ArrayList<Object>();
    	Object[] args=point.getArgs(); 
    	  Annotation[][] pas=((MethodSignature)point.getSignature()).getMethod().getParameterAnnotations();  
          for(int i=0;i<pas.length;i++) {  
              for(Annotation an:pas[i]) {  
                  if(an instanceof CacheKey) {  
                    Object key =args[i].toString(); 
                    keyList.add(key);
                  }  
              }  
          }
          return keyList;
    }
    
}
