package com.it.tst;

import java.util.Scanner;

public class TTT {
	
	
	 public static  String replacexie(String str1,String str2,String str3){
	        String strtmp="";
	        int i=0,f;
	        for(i=0;;i+=str2.length()){
	            f=str1.indexOf(str2,i);
	            if (f==-1) {
	                strtmp+=str1.substring(i);
	                break;
	            }else{
	                strtmp+=str1.substring(i,f);
	                strtmp+=str3;
	                i=f;
	            }
	        }
	        return strtmp;
	    }
	
	 
	public static void main(String[] args) {
		TTT ttt = new TTT();
		Scanner scanner  = new Scanner(System.in);
		String str= scanner.next();
		str=replacexie(str,"/","");   
		System.out.print("/"+str);
		
	}

}
