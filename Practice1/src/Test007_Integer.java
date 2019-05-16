
public class Test007_Integer {

	public static void main(String[] args) {
		Float floatNum = new Float(3.6f);
		System.out.println(floatNum.intValue());
		
		//下面两个等价，Use "java.util.Random.nextInt()" instead，推荐用后者
		//产生100以内的随机数
//		System.out.println((int)(Math.random()*100));
//		System.out.println(new Random().nextInt(100));

	}

}
