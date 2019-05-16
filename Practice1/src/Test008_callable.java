import java.util.concurrent.*;


public class Test008_callable {

	public static void main(String[] args) {
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(5000l);
				return "callable return";
			}
		};

		FutureTask<String> future = new FutureTask<String>(callable);
		new Thread(future,"testcallable").start();
		try {
			System.out.println(future.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(3000l);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("main thread");
		
	}

}
