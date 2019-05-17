package com.practice.basic;

public class Test014_clone {

	public static void main(String[] args) {
		People people = new People("ganggang", (byte)1, 25);
		Car car = new Car();
		car.setBrand("Ford");
		car.setType("mustang");
		car.setMileage(0);
		car.setOwner(people);
		
		Car copyCar = null;
		try {
			copyCar = (Car) car.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		car.setMileage(100);
		people.setName("mingming");
//		People people2 = new People("mingming", (byte)2, 22);
//		car.setOwner(people2);
		System.out.println(car.toString());
		if(copyCar != null){
			System.out.println(copyCar.toString());
		}
	}

}

class People{
	private String name;
	private byte sex;
	private int age;
	
	public People(String name,byte sex,int age){
		this.setName(name);
		this.sex = sex;
		this.setAge(age);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte getSex() {
		return sex;
	}
	public void setSex(byte sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}

class Car implements Cloneable{
	private String brand;
	private String type;
	private int mileage;
	private People owner;
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
//		return super.clone();
		
		Object object = null;
		//这个方法进行的是浅拷贝
		object = super.clone();//基本类型不变，引用类型变，copy后指向源内存
		
		//进行深拷贝
		//每一层的每个对象都进行浅拷贝=深拷贝。需要people对象实现cloneable接口
		People peopleCopy = new People(owner.getName(),owner.getSex(),owner.getAge());
		((Car)object).setOwner(peopleCopy);
		return object;
		
		
	}

	public People getOwner() {
		return owner;
	}

	public void setOwner(People owner) {
		this.owner = owner;
	}
	@Override
	public String toString() {
		return brand+"_"+type+"_"+mileage+"_"+owner.getName();
	}
}