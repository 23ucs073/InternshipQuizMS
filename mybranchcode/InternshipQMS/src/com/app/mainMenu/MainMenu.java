package com.app.mainMenu;

import java.util.Scanner;

import com.app.service.AdminService;

public class MainMenu {
	private static AdminService ads = new AdminService();

	private static int menuOptions(Scanner sc) {
		System.out.println();
		System.out.println();
		System.out.println("0. EXIT");
		System.out.println("1. Admin Login");
		System.out.println("2. Student Registration");
		System.out.println("3. Student Login");
		System.out.print("Enter your choice -");

		return sc.nextInt();
	}

	public static void mainMenu(Scanner sc) {
		int choice;
		while ((choice = menuOptions(sc)) != 0) {

			switch (choice) {
			case 1:
				ads.loginAdmin(sc);
				break;
			}
		}
	}
}

