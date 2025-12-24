package com.app.service;
import java.util.Scanner;
import com.app.dao.AdminDao;
import com.app.mainMenu.AdminMenu;

public class AdminService {

	    public void loginAdmin(Scanner sc) {
	        System.out.print("Enter the E-mail : ");
	        String email = sc.next();

	        System.out.print("Enter the Password : ");
	        String password = sc.next();

	        try (AdminDao ad = new AdminDao()) {
	            int adminId = ad.adminLogin(email, password);

	            if (adminId != -1) {
	                System.out.println("Admin Login Successfully");
	                AdminMenu.adminMenu(sc, adminId);
	            } else {
	                System.out.println("Admin Login Failed");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }


}
