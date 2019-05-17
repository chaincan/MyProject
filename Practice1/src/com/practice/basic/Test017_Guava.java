package com.practice.basic;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;


public class Test017_Guava {


	public static void main(String[] args) {
		
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		
		GuavaCache guavaCache = new GuavaCache();
		guavaCache.init();		
		System.out.println("initial cache content size: "+guavaCache.getAllRolesFromCache().size());
		Role chooseRole = guavaCache.getRoleByCache(105);
		if(chooseRole != null){
			System.out.println("firstQuery from cache:"+chooseRole.toString());
			System.out.println("firstQuery from cache after,cache content size: "+guavaCache.getAllRolesFromCache().size());
		}else{
			System.out.println("ffirstQuery from cache is null");
		}
		executorService.scheduleAtFixedRate(()->{
			
			System.out.println("after timeout,cache content size:"+guavaCache.getAllRolesFromCache().size());
			
		}, 10000, 3000,TimeUnit.MILLISECONDS);
		
	}

}

class GuavaCache{
	private List<Role> roleList = new ArrayList<>();
	//guava中loadingcache缓存
	private LoadingCache<Integer, Optional<Role>> roleCache = CacheBuilder.newBuilder()
			.initialCapacity(50)//初始缓存容量
			.maximumSize(80)//缓存最大值
			.expireAfterAccess(10, TimeUnit.SECONDS)//10秒未读取则过期，清除缓存
			.expireAfterWrite(30, TimeUnit.SECONDS)//30秒未写入则过期，清除缓存
			.removalListener(new RemovalListener<Integer, Optional<Role>>() {//添加缓存移除监听
				@Override
				public void onRemoval(RemovalNotification<Integer, Optional<Role>> notification) {
					System.out.println("guavacache-> "+notification.getKey()+" was removed,cause "+notification.getCause());//？？为什么没执行
				}
			})
			.build(new CacheLoader<Integer, Optional<Role>>(){//缓存未命中时自动加载
				@Override
				public Optional<Role> load(Integer key) throws Exception {
					Role role = getRoleById(key);
					return Optional.fromNullable(role);
				}
	});
	
	public void init(){
		for(int i = 100;i<200;i++){
			Role role = new Role();
			role.setRoleId(i);
			role.setRoleName("robot"+i);
			role.setSex((byte)new Random().nextInt(1));
			roleList.add(role);
		}
	}
	
	private Role getRoleById(int roleId){
		for(int i=0;i<roleList.size();i++){
			Role role = roleList.get(i);
			if(role.getRoleId() == roleId){
				return role;
			}
		}
		return null;
	}
	
	/**
	 * 从缓存获取数据
	 * @param roleId
	 * @return
	 */
	public Role getRoleByCache(int roleId){
		try {
			Optional<Role> roleOptional = roleCache.get(roleId);
			return roleOptional.orNull();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Role> getAllRolesFromCache(){ 
		List<Role> roleList = new ArrayList<Role>();
		ConcurrentMap<Integer, Optional<Role>> roleMap = roleCache.asMap();
		for(Optional<Role> optionalRole : roleMap.values()){
			Role tmpRole = optionalRole.orNull();
			if(tmpRole != null){
				roleList.add(tmpRole);
			}
		}
		return roleList;
	}
	
}

class Role{
	private int roleId;
	private String roleName;
	private byte sex;
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public byte getSex() {
		return sex;
	}
	public void setSex(byte sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return roleId+"-"+roleName+"-"+sex;
	}
}