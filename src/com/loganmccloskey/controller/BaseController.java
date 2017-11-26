package com.loganmccloskey.controller;

import java.util.List;
import java.util.stream.Collectors;

public class BaseController {

	protected List<String> cleanInput(List<String> list) {
		return list.stream().map(String::trim).collect(Collectors.toList());
	}

	protected long parseLong(String entry) {
		return Long.parseLong(entry);
	}

	protected int parseInt(String entry) {
		return Integer.parseInt(entry);
	}

	protected void removeElementsFromFrontOfList(List<String> list, int numberOfElements) {
		if (list.size() >= numberOfElements) {
			int count = 0;
			while (count < numberOfElements) {
				list.remove(0);
				count++;
			}
		}
	}

	protected double parseDouble(String entry) {
		return Double.parseDouble(entry);
	}

	protected void printResultsLine() {
		System.out.printf("\n///// RESULTS /////\n");
	}

	protected void printNoResultsLine() {
		System.out.printf("\n///// NO RESULTS FOUND /////\n");
	}

	protected void printSuccessLine() {
		System.out.printf("\n///// SUCCESS /////\n");
	}

}
