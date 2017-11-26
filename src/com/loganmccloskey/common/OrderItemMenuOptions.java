package com.loganmccloskey.common;

public enum OrderItemMenuOptions {
	RETURN(0, "Return"),
	FIND_ONE_ID(1,"Find one by ID"),
	FIND_ALL(2,"Find all"),
	CREATE_ONE(3,"Create one"),
	UPDATE_ONE(4,"Update one"),
	DELETE_ONE(5,"Delete one");
	
	private int key;
	private String value;
	
	private OrderItemMenuOptions(int key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public int getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
}
