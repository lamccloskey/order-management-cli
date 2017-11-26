package com.loganmccloskey.common;

public enum TopMenuOptions {
	EXIT(0, "Exit"),
	CUSTOMERS(1,"Customers"),
	ORDERS(2,"Orders"),
	ORDER_ITEMS(3, "Order Items");
	
	private int key;
	private String value;
	
	private TopMenuOptions(int key, String value) {
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
