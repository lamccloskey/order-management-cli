package com.loganmccloskey;

import java.io.IOException;

import com.loganmccloskey.common.CustomerMenuOptions;
import com.loganmccloskey.common.MenuOptions;
import com.loganmccloskey.common.OrderItemMenuOptions;
import com.loganmccloskey.common.OrderMenuOptions;
import com.loganmccloskey.common.TopMenuOptions;
import com.loganmccloskey.controller.CustomerController;
import com.loganmccloskey.controller.MenuController;
import com.loganmccloskey.controller.OrderController;
import com.loganmccloskey.controller.OrderItemController;

public class App {

	public static void main(String[] args) throws IOException {

		MenuController menuController = new MenuController();
		CustomerController customerController = new CustomerController();
		OrderController orderController = new OrderController();
		OrderItemController orderItemController = new OrderItemController();

		menuController.printWelcome();
		int selection = -1;

		do {
			selection = menuController.inputControl(MenuOptions.TOP_MENU);
			if (selection == TopMenuOptions.CUSTOMERS.getKey()) {
				selection = menuController.inputControl(MenuOptions.CUSTOMER_MENU);
				if (selection == CustomerMenuOptions.RETURN.getKey()) {
					selection = -1;
				} else if (selection == CustomerMenuOptions.FIND_ONE_ID.getKey()) {
					customerController.printForm(CustomerMenuOptions.FIND_ONE_ID);
					if (menuController.getScanner().hasNextLine()) {
						customerController.printCustomer(menuController.getScanner().nextLine());
					}
				} else if (selection == CustomerMenuOptions.FIND_ONE_FN_LN.getKey()) {
					customerController.printForm(CustomerMenuOptions.FIND_ONE_FN_LN);
					if (menuController.getScanner().hasNextLine()) {
						customerController.printCustomerName(menuController.getScanner().nextLine());
					}
				} else if (selection == CustomerMenuOptions.FIND_ALL.getKey()) {
					customerController.printForm(CustomerMenuOptions.FIND_ALL);
					customerController.printCustomers();
				} else if (selection == CustomerMenuOptions.CREATE_ONE.getKey()) {
					customerController.printForm(CustomerMenuOptions.CREATE_ONE);
					if (menuController.getScanner().hasNextLine()) {
						customerController.printCreateCustomer(menuController.getScanner().nextLine());
					}
				} else if (selection == CustomerMenuOptions.UPDATE_ONE.getKey()) {
					customerController.printForm(CustomerMenuOptions.UPDATE_ONE);
					if (menuController.getScanner().hasNextLine()) {
						customerController.printUpdateCustomer(menuController.getScanner().nextLine());
					}
				} else if (selection == CustomerMenuOptions.DELETE_ONE.getKey()) {
					customerController.printForm(CustomerMenuOptions.DELETE_ONE);
					if (menuController.getScanner().hasNextLine()) {
						customerController.printDeleteCustomer(menuController.getScanner().nextLine());
					}
				}
			} else if (selection == TopMenuOptions.ORDERS.getKey()) {
				selection = menuController.inputControl(MenuOptions.ORDER_MENU);
				if (selection == OrderMenuOptions.RETURN.getKey()) {
					selection = -1;
				} else if (selection == OrderMenuOptions.FIND_ONE_ID.getKey()) {
					orderController.printForm(OrderMenuOptions.FIND_ONE_ID);
					if (menuController.getScanner().hasNextLine()) {
						orderController.printOrder(menuController.getScanner().nextLine());
					}
				} else if (selection == OrderMenuOptions.FIND_ALL.getKey()) {
					orderController.printForm(OrderMenuOptions.FIND_ALL);
					orderController.printOrders();
				} else if (selection == OrderMenuOptions.CREATE_ONE.getKey()) {
					orderController.printForm(OrderMenuOptions.CREATE_ONE);
					if (menuController.getScanner().hasNextLine()) {
						orderController.printCreateOrder(menuController.getScanner().nextLine());
					}
				} else if (selection == OrderMenuOptions.UPDATE_ONE.getKey()) {
					orderController.printForm(OrderMenuOptions.UPDATE_ONE);
					if (menuController.getScanner().hasNextLine()) {
						orderController.printUpdateOrder(menuController.getScanner().nextLine());
					}
				} else if (selection == OrderMenuOptions.DELETE_ONE.getKey()) {
					orderController.printForm(OrderMenuOptions.DELETE_ONE);
					if (menuController.getScanner().hasNextLine()) {
						orderController.printDeleteOrder(menuController.getScanner().nextLine());
					}
				}
			} else if (selection == TopMenuOptions.ORDER_ITEMS.getKey()) {
				selection = menuController.inputControl(MenuOptions.ORDER_ITEM_MENU);
				if (selection == OrderItemMenuOptions.RETURN.getKey()) {
					selection = -1;
				} else if (selection == OrderItemMenuOptions.FIND_ONE_ID.getKey()) {
					orderItemController.printForm(OrderItemMenuOptions.FIND_ONE_ID);
					if (menuController.getScanner().hasNextLine()) {
						orderItemController.printOrderItem(menuController.getScanner().nextLine());
					}
				} else if (selection == OrderItemMenuOptions.FIND_ALL.getKey()) {
					orderItemController.printForm(OrderItemMenuOptions.FIND_ALL);
					orderItemController.printOrderItems();
				} else if (selection == OrderItemMenuOptions.CREATE_ONE.getKey()) {
					orderItemController.printForm(OrderItemMenuOptions.CREATE_ONE);
					if (menuController.getScanner().hasNextLine()) {
						orderItemController.printCreateOrderItem(menuController.getScanner().nextLine());
					}
				} else if (selection == OrderItemMenuOptions.UPDATE_ONE.getKey()) {
					orderItemController.printForm(OrderItemMenuOptions.UPDATE_ONE);
					if (menuController.getScanner().hasNextLine()) {
						orderItemController.printUpdateOrderItem(menuController.getScanner().nextLine());
					}
				} else if (selection == OrderItemMenuOptions.DELETE_ONE.getKey()) {
					orderItemController.printForm(OrderItemMenuOptions.DELETE_ONE);
					if (menuController.getScanner().hasNextLine()) {
						orderItemController.printDeleteOrderItem(menuController.getScanner().nextLine());
					}
				}
			}
		} while (selection != 0);

		menuController.printGoodbye();
	}

}
