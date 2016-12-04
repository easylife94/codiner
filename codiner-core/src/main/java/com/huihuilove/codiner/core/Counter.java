package com.huihuilove.codiner.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.huihuilove.codiner.core.filter.impl.AnnotationFilter;
/**
 * 计数器
 * @author chenwei
 * @github https://github.com/easylife94
 */
public class Counter {
	
	private static AnnotationFilter annotationFilter = new AnnotationFilter();
	
	public int countLine(File file){
		int count = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			
			String line = br.readLine();
			while(line != null){
				if(StringUtils.isNotBlank(line)){
					if(annotationFilter.filter(line) != null){
						count ++;
					}
				}
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return count;
	}
//	public static void main(String[] args) {
//		 File f = new File("/Users/chenwei/Documents/workspace/codiner/src/main/java/com/huihuilove/codiner/core/Scaner.java");
//		 
//		 
//		 Counter counter = new Counter();
//		 int count = counter.countLine(f);
//		 System.out.println(f.getName()+" code line = "+count);
//	}
}
