package com.huihuilove.codiner.core.filter.impl;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.huihuilove.codiner.core.filter.Filter;
/**
 * 过滤掉后缀不匹配的文件
 * @author chenwei
 * @github https://github.com/easylife94
 */
public class FileSuffixIncludeFilter implements Filter {
	public final static String  REGEX = ".*\\.${suffix}";
	private String[] filterSuffixs;
	
	public FileSuffixIncludeFilter(String filterSuffix) {
		this.filterSuffixs = new String[]{filterSuffix};
	}
	public FileSuffixIncludeFilter(String[] filterSuffixs) {
		this.filterSuffixs = filterSuffixs;
	}
	
	@Override
	public Object filter(Object obj) {
		File file = (File) obj;
		String fileName = file.getName();
		if(filterSuffixs != null){
			for(String suffix : filterSuffixs){
				 String reg = REGEX.replace("${suffix}", suffix);
				 Pattern pattern = Pattern.compile(reg);
				 Matcher match = pattern.matcher(file.getName()); 
				 if(match.matches()){//匹配成功则返回对象
					 return obj;
				 }
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Filter filter = new FileSuffixIncludeFilter(new String[]{"javac","xml"});
		File f = (File) filter.filter(new File("/Users/chenwei/Documents/workspace/codiner/src/main/java/com/huihuilove/codiner/core/Scaner.java"));
		if(f == null){
			System.out.println("拦截成功");
		}else{
			System.out.println("拦截失败");
		}
	}	

}
