package com.loganmccloskey.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.loganmccloskey.common.CustomerMenuOptions;
import com.loganmccloskey.common.MenuOptions;
import com.loganmccloskey.common.OrderItemMenuOptions;
import com.loganmccloskey.common.OrderMenuOptions;
import com.loganmccloskey.common.TopMenuOptions;

public class MenuController {

//	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final Scanner scanner;

	public MenuController() {
		scanner = new Scanner(System.in);
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void printWelcome() {
		System.out.printf("\n-------------------------------------------\n");
		System.out.println("WELCOME TO THE ORDER MANAGEMENT APPLICATION");
		System.out.printf("-------------------------------------------\n");
	}

	public void printGoodbye() {
		System.out.printf("\n---------------------------\n");
		System.out.println("GOODBYE, HAVE A NICE DAY!!!");
		System.out.printf("---------------------------\n");
	}

	public int inputControl(MenuOptions menu) {
		String entry = null;
		int count = 0;
		do {
			if (count > 0) {
				this.printError(entry);
			}

			this.printMenuOptions(menu);

			entry = this.getScanner().nextLine();
			count++;
		} while (this.invalid(menu, entry));

		return Integer.parseInt(entry);
	}

	private void printMenuOptions(MenuOptions menu) {
		if (menu == MenuOptions.TOP_MENU) {
			System.out.printf("\n%s\n", MenuOptions.TOP_MENU.getValue());
			System.out.printf("\nPLEASE SELECT AN OPTION:\n------------------------\n");
			for (TopMenuOptions value : TopMenuOptions.values()) {
				System.out.printf("%d: %s\n", value.getKey(), value.getValue());
			}
			System.out.println();
			System.out.print("SELECTION: ");
		} else if (menu.equals(MenuOptions.CUSTOMER_MENU)) {
			System.out.printf("\n%s\n", MenuOptions.CUSTOMER_MENU.getValue());
			System.out.printf("\nPLEASE SELECT AN OPTION:\n------------------------\n");
			for (CustomerMenuOptions value : CustomerMenuOptions.values()) {
				System.out.printf("%d: %s\n", value.getKey(), value.getValue());
			}
			System.out.println();
			System.out.print("SELECTION: ");
		} else if (menu.equals(MenuOptions.ORDER_MENU)) {
			System.out.printf("\n%s\n", MenuOptions.ORDER_MENU.getValue());
			System.out.printf("\nPLEASE SELECT AN OPTION:\n------------------------\n");
			for (OrderMenuOptions value : OrderMenuOptions.values()) {
				System.out.printf("%d: %s\n", value.getKey(), value.getValue());
			}
			System.out.println();
			System.out.print("SELECTION: ");
		} else if (menu.equals(MenuOptions.ORDER_ITEM_MENU)) {
			System.out.printf("\n%s\n", MenuOptions.ORDER_ITEM_MENU.getValue());
			System.out.printf("\nPLEASE SELECT AN OPTION:\n------------------------\n");
			for (OrderItemMenuOptions value : OrderItemMenuOptions.values()) {
				System.out.printf("%d: %s\n", value.getKey(), value.getValue());
			}
			System.out.println();
			System.out.print("SELECTION: ");
		}
	}

	private boolean invalid(MenuOptions menu, String entry) {
		List<Integer> validEntries = populateValidEntries(menu);

		for (Integer e : validEntries) {
			try {
				if (e == Integer.parseInt(entry)) {
					return false;
				}
			} catch (NumberFormatException ex) {
				return true;
			}

		}
		return true;
	}

	private List<Integer> populateValidEntries(MenuOptions menu) {
		List<Integer> validEntries = new ArrayList<>();
		if (menu == MenuOptions.TOP_MENU) {
			for (TopMenuOptions value : TopMenuOptions.values()) {
				validEntries.add(value.getKey());
			}
		} else if (menu == MenuOptions.CUSTOMER_MENU) {
			for (CustomerMenuOptions value : CustomerMenuOptions.values()) {
				validEntries.add(value.getKey());
			}
		} else if (menu == MenuOptions.ORDER_MENU) {
			for (OrderMenuOptions value : OrderMenuOptions.values()) {
				validEntries.add(value.getKey());
			}
		} else if (menu == MenuOptions.ORDER_ITEM_MENU) {
			for (OrderItemMenuOptions value : OrderItemMenuOptions.values()) {
				validEntries.add(value.getKey());
			}
		}

		return validEntries;
	}

	private void printError(String entry) {
		System.err.printf("\n///// INCORRECT ENTRY - PLEASE ENTER A VALID VALUE /////\n");
	}

}
