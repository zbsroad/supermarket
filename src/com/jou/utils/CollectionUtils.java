package com.jou.utils;

import java.util.Collection;

/**
 * 集合工具类
 * @author zj223
 *
 */
public class CollectionUtils {
	
	public static boolean isEmpty(Collection<?> collection){		
		return collection == null || collection.size() == 0;		
	}

}