package com.loganmccloskey.common;

public enum CustomerMenuOptions {
	RETURN(0, "Return"),
	FIND_ONE_ID(1,"Find one by ID"),
	FIND_ONE_FN_LN(2, "Find one by first name & last name"),
	FIND_ALL(3,"Find all"),
	CREATE_ONE(4,"Create one"),
	UPDATE_ONE(5,"Update one"),
	DELETE_ONE(6,"Delete one");
	
	private int key;
	private String value;
	
	private CustomerMenuOptions(int key, String value) {
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