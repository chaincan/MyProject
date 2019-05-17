package com.practice.basic;

public class Test012_this {
	
	static class A {
		public String getClassName(){
			return this.getClass().getName();
		}
	}
	// 静态内部类使用场景一般是当外部类需要使用内部类，而内部类无需外部类资源，并且内部类可以单独创建的时候
	static class B extends A{
		@Override
		public String getClassName(){
			return super.getClassName();
		}
	}

	public static void main(String[] args) {

		B b = new B();
		System.out.println("1->"+b.getClass().getName());
		System.out.println("2->"+b.getClassName());
	}

	
	
}
