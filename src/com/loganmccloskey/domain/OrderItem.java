package com.loganmccloskey.domain;

import java.io.Serializable;

public class OrderItem implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4064281552916626614L;

	private long id;
	private long orderId;
	private double unitPrice;
	private int quantity;
	

	public OrderItem() {}
	
	/**
	 * @param id
	 * @param orderId
	 * @param unitPrice
	 * @param quantity
	 */
	public OrderItem(long id, long orderId, double unitPrice, int quantity) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the orderId
	 */
	public long getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the unitPrice
	 */
	public double getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", orderId=" + orderId + ", unitPrice=" + unitPrice + ", quantity=" + quantity
				+ "]";
	}
	
}
