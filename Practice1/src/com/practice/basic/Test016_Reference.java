package com.practice.basic;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Test016_Reference {
	
	private static ScheduledExecutorService handleExecutorService = Executors.newScheduledThreadPool(1);

	public static void main(String[] args) {
		
		List<Hero> list = new ArrayList<>();
		for(int i=0;i<300;i++){
			list.add(new Hero("test"+i, i));
		}
		
		//-Xmx 最大堆内存
		//-Xms 初始堆内存
		//-Xmn 新生代大小
		//-Xss 每个线程的堆栈大小
		
		//当前堆内存
		long totalMemory = Runtime.getRuntime().totalMemory();
		System.out.println("total memory: "+totalMemory/8+"B");
		//最大堆内存（byte）
		long maxMemory = Runtime.getRuntime().maxMemory();
		System.out.println("max memory: "+maxMemory/8+"B");
		//空余堆大小
		long freeMemory = Runtime.getRuntime().freeMemory();
		System.out.println("free memory: "+freeMemory/8+"B");
		System.out.println("used memory: "+(totalMemory-freeMemory)/8+"B");
		
		//软引用 softReference ,当一个对象只有软引用，若内存空间不够，则回收该对象
		//需引用 weakReference,当一个对象只用需引用，不管内存空间够不够，都回收该对象
		//二者都可以和一个ReferenceQueue联合使用，如果软/弱引用的对象被gc回收，则将引用加入到相关Queue中
		Hero heroStrongRef = new Hero("zed",1000);
		System.out.println("strongRef: "+heroStrongRef);
		
//		SoftReference<People> softRef = new SoftReference<People>(peopleStrongRef);
		ReferenceQueue<Hero> softRefQueue = new ReferenceQueue<>();
		SoftReference<Hero> softRef = new SoftReference<>(heroStrongRef,softRefQueue);
		System.out.println("softRef: "+softRef.get()+"        softRefQueue: "+softRefQueue.poll());
		
//		WeakReference<Hero> weakRef = new WeakReference<Hero>(heroStrongRef);
		ReferenceQueue<Hero> weakRefQueue = new ReferenceQueue<>();
		WeakReference<Hero> weakRef = new WeakReference<>(heroStrongRef,weakRefQueue);
		System.out.println("weakRef: "+weakRef.get()+"        weakRefQueue: "+weakRefQueue.poll());
		
		heroStrongRef = null;
		System.out.println("-- after 'strongRef = null' --");
		System.out.println("strongRef: "+heroStrongRef);
		
	
		for(int i = 0;i < 10;i++){
			list.add(new Hero("testadd", 0));
		}
		
		handleExecutorService.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("softRef: "+softRef.get()+"        softRefQueue: "+softRefQueue.poll());
				System.out.println("weakRef: "+weakRef.get()+"        weakRefQueue: "+weakRefQueue.poll());
				
			}
		}, 0, 3, TimeUnit.SECONDS);//上一个任务开始时计时，一段时间后，检查上一个任务是否执行完成，若未完成则等待上一个任务执行完后再执行
		
		
		
	}

}

class Hero{
	private String name;
	private int age;
	
	public Hero(String name,int age){
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "hero-> "+name+" "+age;
	}
}
