package com.huihuilove.codiner.cui;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.huihuilove.codiner.core.Counter;
import com.huihuilove.codiner.core.Scaner;

/**
 * Hello Codiner
 *
 */
public class App {
	
	private static String ROOT_PATH = "";
	private static String[] PARAM_NAMES = new String[]{"rootPath"};
	
	private static String[] INCLUDE_SUFFIXS = null;
	private static String[] EXCLUDE_SUFFIXS = null;
    public static void main( String[] args ){
    		System.out.println("Welcome use Codiner");
    		if(args != null && args.length > 0){
    			for(int i = 0; i < args.length ;i+= 2){
    				if(args[i].trim().startsWith("-")){
    					String param = args[i].trim().replace("-", "");
    					switch (param) {
    						case "h":
							help();
							return;
						case "rootPath":
							setRootPath(args[i+1]);
							break;
						case "includeSuffix":
							setIncludeSuffix(args[i+1]);
							break;
						case "excludeSuffix":
							setExcludeSuffix(args[i+1]);
							break;
						default:
							System.err.println("param '"+param+"' is unknow.run with param '-h' to look command list");
							break;
						}
    				}else{
    					System.out.println("param's name '"+args[i]+"' is illegal. please try start with '-'");
    				}
    			}
    			if(StringUtils.isNoneBlank(ROOT_PATH)){
					System.out.println("rootPath is :"+ROOT_PATH);
					Scaner scaner = new Scaner(INCLUDE_SUFFIXS,EXCLUDE_SUFFIXS);
					List<String> paths = scaner.dirScan(ROOT_PATH);
					Counter counter = new Counter();
					int total = 0;
					int fileNum = 1;
					for(String path : paths){
						File f = new File(path);
						int line = counter.countLine(f);
						System.out.println("No.["+(fileNum++)+"]\tName["+f.getName() +"]\tLine["+line+"]\tPath["+path+"]");
						total += line;						
					}
					System.out.println("TotalLine["+total+"]");
				}
    		}else{
    			System.out.println("param is null or blank please look follows command list:");
    			help();
		}
    }
    
    private static void setExcludeSuffix(String suffixs) {
    		if(StringUtils.isNotBlank(suffixs)){
			EXCLUDE_SUFFIXS = suffixs.split(",");
		}
		
	}

	private static void setIncludeSuffix(String suffixs) {
		if(StringUtils.isNotBlank(suffixs)){
			INCLUDE_SUFFIXS = suffixs.split(",");
		}
		
	}

	private static void help() {
    		System.out.println("-h                       to look commad list");
		System.out.println("-rootPath             absolute directory path of which been scaned");
		System.out.println("-inculdeSuffix       include file with suffix  ");
		System.out.println("-inculdeSuffix       exclude  file with suffix  ");
	}

	private static void setRootPath(String path){
			File file = new File(path);
			if(file != null && file.exists()){
				ROOT_PATH = file.getAbsolutePath();
			}else{
				System.err.println("directory is not existed or lack of permissions");
			}
    			
    }
}
