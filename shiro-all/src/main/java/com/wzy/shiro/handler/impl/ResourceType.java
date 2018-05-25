package com.wzy.shiro.handler.impl;

public enum  ResourceType {
	MENU("²Ëµ¥"),BUTTON("°´Å¥");
	
	private final String info;
	
	private ResourceType(String info){
		this.info=info;
	}
	
	public String getInfo(){
		return info;
	}
	
	
}
