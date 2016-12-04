package com.huihuilove.codiner.core.filter;

public interface Filter {
	/**
	 * 对字符串进行过滤
	 * @param obj 待过滤对象
	 * @return 不匹配的则返回原对象，匹配成功则返回null
	 */
	Object filter(Object obj);
}
