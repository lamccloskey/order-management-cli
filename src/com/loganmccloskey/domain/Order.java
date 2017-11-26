package com.loganmccloskey.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8219548652480443147L;

	private long id;
	private long customerId;
	private Date orderDate;
	private double totalAmount;
	private List<OrderItem> orderItems;
	

	public Order() {
		orderItems = new ArrayList<>();
	}
	
	
	/**
	 * @param id
	 * @param customerId
	 * @param orderDate
	 * @param totalAmount
	 * @param orderItems
	 */
	public Order(long id, long customerId, Date orderDate, double totalAmount, List<OrderItem> orderItems) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.orderItems = orderItems;
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
	 * @return the customerId
	 */
	public long getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	/**
	 * @return the orderItems
	 */
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	/**
	 * @param orderItems the orderItems to set
	 */
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	/**
	 * @param orderItem the orderItem to set
	 */
	public void setOrderItem(OrderItem orderItem) {
		this.orderItems.add(orderItem);
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
		return "Order [id=" + id + ", customerId=" + customerId + ", orderDate=" + orderDate + ", totalAmount="
				+ totalAmount + ", orderItems=" + orderItems + "]";
	}
	
	
}
