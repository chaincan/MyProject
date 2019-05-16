import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Test009_Executors {
	
	public static void main(String[] args) {
		
		//interface ExecutorService extends Executor
		
		//创建线程数固定的线程池
		ExecutorService fixedExecutorService = Executors.newFixedThreadPool(10);
//        return new ThreadPoolExecutor(nThreads, nThreads,
//                0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>());
//        }
		//创建可缓存的的线程池，可回收空闲线程
		ExecutorService cacheExecutorService = Executors.newCachedThreadPool();
//		return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//                60L, TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>());
		//创建单线程化的线程池，只用一个工作线程，保证任务按顺序执行
		ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();
//		 return new FinalizableDelegatedExecutorService
//		            (new ThreadPoolExecutor(1, 1,
//		                                    0L, TimeUnit.MILLISECONDS,
//		                                    new LinkedBlockingQueue<Runnable>()));
		//创建大小固定的可延时和定时执行任务的线程池
		//interface ScheduledExecutorService extends ExecutorService 
		ScheduledExecutorService scheduleExecutorService = Executors.newScheduledThreadPool(10);
//		return new ScheduledThreadPoolExecutor(corePoolSize);
//		scheduleAtFixedRate ，是以上一个任务开始的时间计时，period时间过去后，检测上一个任务是否执行完毕，如果上一个任务执行完毕，则当前任务立即执行，如果上一个任务没有执行完毕，则需要等上一个任务执行完毕后立即执行。
//		scheduleWithFixedDelay，是以上一个任务结束时开始计时，period时间过去后，立即执行。
	
		Runnable runnable = new Runnable() {	
			@Override
			public void run() {
				System.out.println("-> "+Thread.currentThread().getName());
			}
		};
		
		for(int i = 0;i<20;i++){
//			fixedExecutorService.execute(runnable);
//			cacheExecutorService.execute(runnable);
//			singleExecutorService.execute(runnable);
//			scheduleExecutorService.schedule(runnable,3000l,TimeUnit.MILLISECONDS);//延时三秒后执行
		}
//		scheduleExecutorService.scheduleAtFixedRate(runnable, 5000l, 3000l, TimeUnit.MILLISECONDS);
//		scheduleExecutorService.scheduleWithFixedDelay(runnable, 5000l, 3000l, TimeUnit.MILLISECONDS);
		
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(3000l);
				System.out.println(Thread.currentThread().getName());
				return "chencan";
			}
		};
		for(int i=0;i<20;i++){
			Future<String> ft = fixedExecutorService.submit(callable);
			try {
				System.out.println("-> "+ft.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("这是主线程，如果这条语句没有连续打印，则说明主线程阻塞");
		}
		
	}
}
