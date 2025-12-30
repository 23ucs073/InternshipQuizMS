package com.app.main;

import java.util.Scanner;

import com.app.mainMenu.MainMenu;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MainMenu.mainMenu(sc);
		sc.close();
	}

}

