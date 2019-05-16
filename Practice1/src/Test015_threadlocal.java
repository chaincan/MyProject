import java.lang.reflect.Method;



public class Test015_threadlocal {
	public static void main(String[] args) {
		
		MyThreadLocal myThreadLocal = new MyThreadLocal();
		System.out.println(myThreadLocal.getCountLocal().get());
		myThreadLocal.addCount();
		
		Runnable runnable = new Runnable(){
			@Override
			public void run() {
				System.out.println(myThreadLocal.getCountLocal().get());
				for(int i = 0;i<10;i++){
					myThreadLocal.addCount();
				}
				System.out.println(Thread.currentThread().getName()+"  : "+myThreadLocal.getCount());
			}
		};
		
		Thread thread1 = new Thread(runnable);
		Thread thread2 = new Thread(() ->{
			System.out.println(myThreadLocal.getCountLocal().get());
			for(int i = 0;i<10;i++){
				myThreadLocal.addCount();
			}
			System.out.println(Thread.currentThread().getName()+"  : "+myThreadLocal.getCount());
		});
		
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName()+"  : "+myThreadLocal.getCount());
		//主线程用完remove,若不执行remove,将myThreadLoacal对象中的countLocal变量置为空时会出现 内存泄漏 （没有引用，但内存无法释放），因为每个thread中都有一个ThreadLocalMap对象，
		//ThreadLocalMap的key为countLocal的弱引用，countLocal会被回收，但value不会回收
		myThreadLocal.removeThreadLocal();
	}

}

class MyThreadLocal{
	private ThreadLocal<Integer> countLocal = new ThreadLocal<>();
	
	public ThreadLocal<Integer> getCountLocal() {
		return countLocal;
	}

	public void setCountLocal(ThreadLocal<Integer> countLocal) {
		this.countLocal = countLocal;
	}

	public void addCount(){
		if(countLocal.get() == null){
			countLocal.set(0);
		}
		countLocal.set(countLocal.get()+1);
	}
	
	public int getCount(){
		return countLocal.get();
	}
	
	public void removeThreadLocal(){
		countLocal.remove();//执行ThreadLocalMap的remove
	}
	
	private String getName(){
		return "MyThreadLocal";
	}
}
