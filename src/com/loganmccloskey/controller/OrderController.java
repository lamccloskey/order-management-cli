package com.loganmccloskey.controller;

import java.util.Arrays;
import java.util.List;

import com.loganmccloskey.common.OrderMenuOptions;
import com.loganmccloskey.domain.Order;
import com.loganmccloskey.domain.OrderItem;
import com.loganmccloskey.service.OrderService;

public class OrderController extends BaseController {

//	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private OrderService orderService;

	public OrderController() {
		orderService = new OrderService();
	}

	public void printForm(OrderMenuOptions form) {
		if (form == OrderMenuOptions.FIND_ONE_ID) {
			System.out.print("ID: ");
		} else if (form == OrderMenuOptions.CREATE_ONE) {
			System.out.print("CUSTOMER ID, ORDER AMOUNT, ITEM(S): UNIT PRICE, QUANTITY ... : ");
		} else if (form == OrderMenuOptions.UPDATE_ONE) {
			System.out.print("ID, CUSTOMER ID, ORDER AMOUNT, ITEM(S): ORDER ITEM ID, UNIT PRICE, QUANTITY ... : ");
		} else if (form == OrderMenuOptions.DELETE_ONE) {
			System.out.print("ID: ");
		}
	}

	public void printOrder(String entry) {
		long value = parseLong(entry);
		Order order = orderService.findOneFull(value);
		if (order.getId() != 0) {
			printResultsLine();
			printOrderLine(order);
		} else {
			printNoResultsLine();
		}

	}

	public void printOrders() {
		List<Order> orders = orderService.findAllFull();
		printResultsLine();
		orders.stream().forEach(order -> printOrderLine(order));
	}

	public void printCreateOrder(String entry) {
		List<String> values = cleanInput(Arrays.asList(entry.split(",")));
		Order order = new Order();
		order.setCustomerId(parseLong(values.get(0)));
		order.setTotalAmount(parseDouble(values.get(1)));
		removeElementsFromFrontOfList(values, 2);
		while (values.size() >= 2) {
			OrderItem orderItem = new OrderItem();
			orderItem.setUnitPrice(parseDouble(values.get(0)));
			orderItem.setQuantity(parseInt(values.get(1)));
			order.setOrderItem(orderItem);
			removeElementsFromFrontOfList(values, 2);
		}
		orderService.createOneFull(order);
		printSuccessLine();
	}

	public void printUpdateOrder(String entry) {
		List<String> values = cleanInput(Arrays.asList(entry.split(",")));
		long id = parseLong(values.get(0));
		Order order = new Order();
		order.setCustomerId(parseLong(values.get(1)));
		order.setTotalAmount(parseDouble(values.get(2)));
		removeElementsFromFrontOfList(values, 3);
		while (values.size() >= 3) {
			OrderItem orderItem = new OrderItem();
			orderItem.setId(parseLong(values.get(0)));
			orderItem.setUnitPrice(parseDouble(values.get(1)));
			orderItem.setQuantity(parseInt(values.get(2)));
			order.setOrderItem(orderItem);
			removeElementsFromFrontOfList(values, 3);
		}
		orderService.updateOneFull(id, order);
		printSuccessLine();
	}

	public void printDeleteOrder(String entry) {
		long value = parseLong(entry);
		orderService.deleteOne(value);
		printSuccessLine();
	}

	private void printOrderLine(Order order) {
		System.out.println("ID: " + order.getId() + ", CUSTOMER ID: " + order.getCustomerId() + ", ORDER DATE: "
				+ order.getOrderDate() + ", TOTAL AMOUNT: " + order.getTotalAmount() + ", ORDER ITEMS: "
				+ order.getOrderItems());
	}

}
