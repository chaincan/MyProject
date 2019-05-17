package com.practice.basic;
import java.util.Date;


public class Test006_Question {

	public static void main(String[] args){
		
		Date date = new Date(1556456353119l);
		System.out.println(date.toString());
		
		int number = 707829217;
		for(int i=2;i<26606;i++){
			if(isZhiShu(i)){
				if((number%i==0) && isZhiShu(number/i)){
					System.out.println("两个质数分别为："+i+" 和   "+number/i);
					break;
				}else{
					continue;
				}
			}else{
				continue;
			}
		}
		
	}
	
	private static boolean isZhiShu(int num){
		boolean result = true;
		for(int i=2;i<num;i++){
			if(num%i == 0){
				result = false;
				break;
			}else{
				continue;
			}
		}
		return result;	
	}
}
