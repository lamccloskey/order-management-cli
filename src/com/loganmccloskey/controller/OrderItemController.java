package com.loganmccloskey.controller;

import java.util.Arrays;
import java.util.List;

import com.loganmccloskey.common.OrderItemMenuOptions;
import com.loganmccloskey.domain.OrderItem;
import com.loganmccloskey.service.OrderItemService;

public class OrderItemController extends BaseController {

//	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private OrderItemService orderItemService;

	public OrderItemController() {
		orderItemService = new OrderItemService();
	}

	public void printForm(OrderItemMenuOptions form) {
		if (form == OrderItemMenuOptions.FIND_ONE_ID) {
			System.out.print("ID: ");
		} else if (form == OrderItemMenuOptions.CREATE_ONE) {
			System.out.print("ORDER ID, UNIT PRICE, QUANTITY: ");
		} else if (form == OrderItemMenuOptions.UPDATE_ONE) {
			System.out.print("ID, ORDER ID, UNIT PRICE, QUANTITY: ");
		} else if (form == OrderItemMenuOptions.DELETE_ONE) {
			System.out.print("ID: ");
		}
	}

	public void printOrderItem(String entry) {
		long value = parseLong(entry);
		OrderItem orderItem = orderItemService.findOne(value);
		if (orderItem.getId() != 0) {
			printResultsLine();
			printOrderItemLine(orderItem);
		} else {
			printNoResultsLine();
		}

	}

	public void printOrderItems() {
		List<OrderItem> orderItems = orderItemService.findAll();
		printResultsLine();
		orderItems.stream().forEach(orderItem -> printOrderItemLine(orderItem));
	}

	public void printCreateOrderItem(String entry) {
		List<String> values = cleanInput(Arrays.asList(entry.split(",")));
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(parseLong(values.get(0)));
		orderItem.setUnitPrice(parseDouble(values.get(1)));
		orderItem.setQuantity(parseInt(values.get(2)));
		orderItemService.createOne(orderItem);
		printSuccessLine();
	}

	public void printUpdateOrderItem(String entry) {
		List<String> values = cleanInput(Arrays.asList(entry.split(",")));
		long id = parseLong(values.get(0));
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(parseLong(values.get(1)));
		orderItem.setUnitPrice(parseDouble(values.get(2)));
		orderItem.setQuantity(parseInt(values.get(3)));
		orderItemService.updateOne(id, orderItem);
		printSuccessLine();
	}

	public void printDeleteOrderItem(String entry) {
		long value = parseLong(entry);
		orderItemService.deleteOne(value);
		printSuccessLine();
	}

	private void printOrderItemLine(OrderItem orderItem) {
		System.out.println("ID: " + orderItem.getId() + ", ORDER ID: " + orderItem.getOrderId() + ", UNIT PRICE: "
				+ orderItem.getUnitPrice() + ", QUANTITY: " + orderItem.getQuantity());
	}

}
