package com.practice.basic;
import java.util.concurrent.atomic.AtomicInteger;


public class Test011_Atomic {

	private static int intNum = 0;
	private static AtomicInteger aInt = new AtomicInteger(0);
	
	public static void main(String[] args) {
 
		
	
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				long beginTime1 = System.currentTimeMillis();
				System.out.println("getAndIncrement begin:");
				for(int i=0;i<100000000;i++){
					aInt.getAndIncrement();
				}
				System.out.println("getAndIncrement end:consume total time-> "+(System.currentTimeMillis()-beginTime1));
				
				long beginTime2 = System.currentTimeMillis();
				System.out.println("num++ begin:");
				for(int i=0;i<100000000;i++){
					intNum++;
				}
				System.out.println("num++ end:consume total time-> "+(System.currentTimeMillis()-beginTime2));
			}
		},"thread1");
		
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				long beginTime1 = System.currentTimeMillis();
				System.out.println("getAndAdd begin:");
				for(int i=0;i<100000000;i++){
					aInt.getAndAdd(1);
				}
				System.out.println("getAndAdd end:consume total time-> "+(System.currentTimeMillis()-beginTime1));
				
				long beginTime2 = System.currentTimeMillis();
				System.out.println("num++2 begin:");
				for(int i=0;i<100000000;i++){
					intNum++;
				}
				System.out.println("num++2 end:consume total time-> "+(System.currentTimeMillis()-beginTime2));
			}
		}, "thread2");

		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("final aInt:"+aInt.get());
		System.out.println("final num:"+intNum);
	}

}
