package com.wzy.shiro.handler.impl;

public enum  ResourceType {
	MENU("�˵�"),BUTTON("��ť");
	
	private final String info;
	
	private ResourceType(String info){
		this.info=info;
	}
	
	public String getInfo(){
		return info;
	}
	
	
}
