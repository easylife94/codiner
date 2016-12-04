package com.huihuilove.codiner.core.filter.impl;

import com.huihuilove.codiner.core.filter.Filter;

/**
 * 注释过滤器
 * 会过滤掉单行注释和多行注释，多行注释每行要以 * 开头才能被识别
 * @author chenwei
 * @github https://github.com/easylife94
 */
public class AnnotationFilter implements Filter{
	@Override
	public Object filter(Object obj) {
		if(obj != null){
			String line = (String) obj;
			line = line.trim();
			if(line.startsWith("//") ||line.startsWith("/*")|| line.startsWith("*")||
					line.startsWith("/**") || line.startsWith("*/")){//判断是否是注释
				return null;
			}
		}
		return obj;
	}
}
