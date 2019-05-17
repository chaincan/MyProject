package com.practice.basic;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Test003_Collection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Integer> list1=new LinkedList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
//		System.out.println("  linedlist "+list1.get(0));
		
		Collection<Integer> conCollection1=new HashSet<Integer>();
		conCollection1.add(1);
		conCollection1.add(2);
		conCollection1.add(3);
//		System.out.print(" hashset "+conCollection1.iterator().next());
		
		Map<Integer, String> map = new HashMap<Integer, String>();
	    map.put(1, "a");
	    map.put(2, "b");
	    map.put(3, "c");
//	    System.out.println("  "+System.currentTimeMillis());
	    
//	    for(Entry<Integer, String> entry:map.entrySet()){
//	    	System.out.println(entry.getKey()+" : "+entry.getValue());
//	    	map.remove(entry.getKey());
//	    }
	    Iterator<Entry<Integer,String>> iterator=map.entrySet().iterator();
	    while(iterator.hasNext()){
	    	Entry<Integer, String> entry=iterator.next();
//	    	System.out.println(entry.getKey()+" : "+entry.getValue());
//	    	map.remove(entry.getKey());   java.util.ConcurrentModificationException
	    	iterator.remove();
	    }
//	    System.out.println("final size:"+map.size());
	    
//	    System.out.println("  "+System.currentTimeMillis());
	   
	    List<Integer> sortList = new ArrayList<Integer>();
	    for(int i=0;i<20;i++){
	    	int randomNum = (int) (Math.random()*100);
	    	sortList.add(randomNum);
	    }
	    long begin = System.currentTimeMillis();
	    Collections.sort(sortList);
//	    System.out.println("2000个数用collections.sort方法排序用时： "+(System.currentTimeMillis() - begin)+" 毫秒");
	    
	   List<Integer> delList = new ArrayList<Integer>();
	   for(int i=0;i<10;i++){
		   delList.add(i*3-1);
	   }
	   for(Integer i :delList){
		   System.out.println("删除了元素："+ i);
		   delList.remove(i);
	   }
	    
	}

}
