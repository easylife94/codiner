package com.huihuilove.codiner.core;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.huihuilove.codiner.core.filter.impl.FileSuffixExcludeFilter;
import com.huihuilove.codiner.core.filter.impl.FileSuffixIncludeFilter;

/**
 * 文件扫描器
 * @author chenwei
 * @github https://github.com/easylife94
 */
public class Scaner {
	
	private FileSuffixIncludeFilter fileSuffixIncludeFilter;
	private FileSuffixExcludeFilter fileSuffixExcludeFilter;
	
	public Scaner(String[] includeSuffix,String[] excludeSuffix) {
		fileSuffixIncludeFilter  = new FileSuffixIncludeFilter(includeSuffix);
		fileSuffixExcludeFilter  = new FileSuffixExcludeFilter(excludeSuffix);
	}
	
	
	/**
	 * 扫描单个文件夹
	 * @param rootPath 文件夹根路径 
	 * @return 返回一组文件路径
	 */
	public List<String> dirScan(String rootPath){
		
		List<String>  paths = new ArrayList<String>();
		File file = new File(rootPath);
		if(file.isDirectory()){
			File[] files = file.listFiles();
			
			for(File f : files){
				if(f.isDirectory()){
					List<String> ps= dirScan(f.getAbsolutePath());
					for(String p : ps){
						paths.add(p);
					}
				}else{
					if(fileSuffixIncludeFilter.filter(f) != null && fileSuffixExcludeFilter.filter(f) != null) paths.add(f.getAbsolutePath());
				}
			}
		}
		 return paths;
	}
	/**
	 * 扫描一组文件夹
	 * @param rootPath 文件夹根路径 
	 * @return 返回一组文件路径
	 */
	public List<String>  dirScan(String[] rootPaths){
		List<String>  paths = new ArrayList<String>();
		Set<String> pathSet = new HashSet<String>();
		for(String rp : rootPaths){
			if(!pathSet.contains(rp)){
				List<String>  ps  = dirScan(rp);
				for(String p : ps){
					paths.add(p);
				}
				pathSet.add(rp);
			}
		}
		return paths;
	}
	
//	public static void main(String[] args) {
//		Scaner scaner = new Scaner(new String[]{"java"},null);
//		Counter counter = new Counter();
//		String[] rootPaths = new String[]{"/Users/chenwei/Documents/workspace/codiner/","/Users/chenwei/Documents/workspace/myapp/"};
//		List<String> paths = scaner.dirScan(rootPaths);
//		for(String path : paths){
//			System.out.println(path+" count line:"+counter.countLine(new File(path)));
//			
//		}
//	}
}
