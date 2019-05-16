
public class Test007_Integer {

	public static void main(String[] args) {
//		Float floatNum = new Float(3.6f);
//		System.out.println(floatNum.intValue());
		
		//下面两个等价，Use "java.util.Random.nextInt()" instead，推荐用后者
		//产生100以内的随机数
//		System.out.println((int)(Math.random()*100));
//		System.out.println(new Random().nextInt(100));
		System.out.println("mix int value: "+Integer.MIN_VALUE);
		System.out.println("max int value: "+Integer.MAX_VALUE+" after +1 :"+(Integer.MAX_VALUE+1));
		
		//Java中没有无符号数；
		//java中，采用补码的形式表示负数。对于负数来讲，它的反码就是除去符号位取反，然后加1就得到了它的补码。(负数取反加一)
		int positiveNum = 1;
		int nagetiveNum = -1;
		System.out.println(positiveNum+" binary format: "+Integer.toBinaryString(positiveNum));
		System.out.println(nagetiveNum+" binary format: "+Integer.toBinaryString(nagetiveNum));

	}

}
