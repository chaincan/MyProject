import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class Test013_generic {

	public static void main(String[] args) {
		printClassName(new SubGenericA<String>());
//		printClassName(new SubGenericB<String >());
	}
	
	public static void getName(Generic<?> generic){
		generic.printGenerateName();
	}
	
	public static void printClassName(Generic<?> generic){
		Class<?> clazz = generic.getClass();
		System.out.println("this_ "+clazz.getName()+"  super_ "+clazz.getSuperclass().getName());
		//interface ParameterizedType extends Type
		ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
		System.out.println("super_ "+parameterizedType.getTypeName());
		Type type = parameterizedType.getActualTypeArguments()[0];
		System.out.println("generate type name:"+type.getTypeName());
	}

}

abstract class Generic<T>{
	public abstract void printGenerateName();
	public abstract Class getGenericClass(); 
}

class SubGenericA<String> extends Generic<String>{

	@Override
	public Class getGenericClass() {
		return this.getClass();
	}

	@Override
	public void printGenerateName() {
		System.out.println("this generic type is string");
	}
	
}
class SubGenericB<Integer> extends Generic<Integer>{

	@Override
	public Class getGenericClass() {
		return this.getClass();
	}

	@Override
	public void printGenerateName() {
		System.out.println("this generic type is integer");
		
	}
	
}