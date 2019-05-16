
class Parent{
    public static int pStaticField = pCreateFiled(101);
    public int pNoStaticField = pCreateFiled(100);
    
    static{
    	System.out.println("parent "+pStaticField);
    	//System.out.println(pNoStaticField);
    }
    {
    	System.out.println("parent "+pNoStaticField);
    }
    public Parent(){
    	System.out.println("parent "+pStaticField+"   "+pNoStaticField);
    }
    public static int pCreateFiled(int i){
    	System.out.println("parent"+" "+i);
    	return i;
    }
}

class Child extends Parent{
	public static int cStaticField = cCreateFiled(201);
    public int cNoStaticField = cCreateFiled(200);
    
    static{
    	System.out.println("child "+cStaticField);
    	//System.out.println(pNoStaticField);
    }
    {
    	System.out.println("child "+cNoStaticField);
    }
    public Child(){
    	System.out.println("child "+cStaticField+"   "+cNoStaticField);
    }
    public static int cCreateFiled(int i){
    	System.out.println("child "+" "+i);
    	return i;
    }
}

//public class Test001 {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Child child=new Child();
//	}
//
//}
