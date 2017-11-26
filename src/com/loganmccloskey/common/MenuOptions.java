package com.loganmccloskey.common;


public enum MenuOptions {
	TOP_MENU("~~~~~ TOP MENU ~~~~~~"),
	CUSTOMER_MENU("~~~~~ CUSTOMER MENU ~~~~~~"),
	ORDER_MENU("~~~~~ ORDER MENU ~~~~~~"),
	ORDER_ITEM_MENU("~~~~~ ORDER ITEM MENU ~~~~~~");
	
	private String value;

	private MenuOptions(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
