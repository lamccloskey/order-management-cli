package com.loganmccloskey.controller;

import java.util.Arrays;
import java.util.List;
import com.loganmccloskey.common.CustomerMenuOptions;
import com.loganmccloskey.domain.Customer;
import com.loganmccloskey.service.CustomerService;

public class CustomerController extends BaseController {

//	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private CustomerService customerService;

	public CustomerController() {
		customerService = new CustomerService();
	}

	public void printForm(CustomerMenuOptions form) {
		if (form == CustomerMenuOptions.FIND_ONE_ID) {
			System.out.print("ID: ");
		} else if (form == CustomerMenuOptions.FIND_ONE_FN_LN) {
			System.out.print("FIRST NAME, LAST NAME: ");
		} else if (form == CustomerMenuOptions.CREATE_ONE) {
			System.out.print("FIRST NAME, LAST NAME, CITY, COUNTRY, PHONE: ");
		} else if (form == CustomerMenuOptions.UPDATE_ONE) {
			System.out.print("ID, FIRST NAME, LAST NAME, CITY, COUNTRY, PHONE: ");
		} else if (form == CustomerMenuOptions.DELETE_ONE) {
			System.out.print("ID: ");
		}
	}

	public void printCustomer(String entry) {
		long value = parseLong(entry);
		Customer customer = customerService.findOne(value);
		if (customer.getId() != 0) {
			printResultsLine();
			printCustomerLine(customer);
		} else {
			printNoResultsLine();
		}
	}

	public void printCustomerName(String entry) {
		List<String> values = cleanInput(Arrays.asList(entry.split(",")));
		Customer customer = customerService.findOne(values.get(0), values.get(1));
		if (customer.getId() != 0) {
			printResultsLine();
			printCustomerLine(customer);
		} else {
			printNoResultsLine();
		}
	}

	public void printCustomers() {
		List<Customer> customers = customerService.findAll();
		printResultsLine();
		customers.stream()
				.forEach(customer -> printCustomerLine(customer));
	}
	
	public void printCreateCustomer(String entry) {
		List<String> values = cleanInput(Arrays.asList(entry.split(",")));
		Customer customer = new Customer();
		customer.setFirstName(values.get(0));
		customer.setLastName(values.get(1));
		customer.setCity(values.get(2));
		customer.setCountry(values.get(3));
		customer.setPhone(values.get(4));
		customerService.createOne(customer);
		printSuccessLine();
	}

	public void printUpdateCustomer(String entry) {
		List<String> values = cleanInput(Arrays.asList(entry.split(",")));
		long id = parseLong(values.get(0));
		Customer customer = new Customer();
		customer.setFirstName(values.get(1));
		customer.setLastName(values.get(2));
		customer.setCity(values.get(3));
		customer.setCountry(values.get(4));
		customer.setPhone(values.get(5));
		customerService.updateOne(id, customer);
		printSuccessLine();
	}

	public void printDeleteCustomer(String entry) {
		long value = parseLong(entry);
		customerService.deleteOne(value);
		printSuccessLine();
	}
	
	private void printCustomerLine(Customer customer) {
		System.out.println("ID: " + customer.getId() + ", FIRST NAME: " + customer.getFirstName() + ", LAST NAME: "
				+ customer.getLastName() + ", CITY: " + customer.getCity() + ", COUNTRY: " + customer.getCountry() + ", PHONE: "
				+ customer.getPhone());
	}
	
	

}
