package com.practice.basic;
import java.util.Random;




public class Test004_quickSort {

	private static int[] quickArray = new int[20];
	
	public static void main(String[] args){
		initArray();
	}
	
	private static void initArray(){
		for (int i = 0; i < quickArray.length; i++) {
			quickArray[i] = new Random().nextInt(100);
		}
	}
	
	private static void quickSort(){
		
	}
}
