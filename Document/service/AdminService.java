package com.sunbeam.quiz.service;


import com.sunbeam.quiz.model.Questions;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import com.sunbeam.quiz.dao.AdminDao;
import com.sunbeam.quiz.dao.QuizDao;
import com.sunbeam.quiz.dao.QuestionsDao;
import com.sunbeam.quiz.menu.AdminMenu;
import com.sunbeam.quiz.model.Questions;
import com.sunbeam.quiz.model.Quizzes;


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

    public void createQuiz(Scanner sc, int adminId) {

        try {
            sc.nextLine();

            System.out.print("Enter Quiz Title : ");
            String title = sc.nextLine();

            System.out.print("Enter Question File Path : ");
            String filePath = sc.nextLine();

            try (QuizDao quizDao = new QuizDao();
                 QuestionsDao quesDao = new QuestionsDao();
                 BufferedReader br = new BufferedReader(new FileReader(filePath))) {

                Quizzes quiz = new Quizzes();
                quiz.setTitle(title);
                quiz.setCreator_id(adminId);

                int quizId = quizDao.insertQuiz(quiz);

                String line;
                while ((line = br.readLine()) != null) {

                    if (line.isBlank())
                        continue;

                    String question = line;
                    String optA = br.readLine().substring(3);
                    String optB = br.readLine().substring(3);
                    String optC = br.readLine().substring(3);
                    String optD = br.readLine().substring(3);
                    String answer = String.valueOf(
                            br.readLine().split(":")[1].trim().charAt(0)
                    );

                    Questions q = new Questions();
                    q.quiz_id = quizId;
                    q.setText(question);
                    q.setA(optA);
                    q.setB(optB);
                    q.setC(optC);
                    q.setD(optD);
                    q.setCorrect(answer);

                    quesDao.insertQuestion(q);
                }

                System.out.println("Quiz created successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void listQuizzes() {

        try (QuizDao quizDao = new QuizDao()) {

            List<Quizzes> quizList = quizDao.selectQuiz();

            if (quizList.isEmpty()) {
                System.out.println("No quizzes available.");
                return;
            }

            System.out.println("\nAvailable Quizzes:");
            int index = 1;
            for (Quizzes quiz : quizList) {
                System.out.println(index++ + ". " + quiz.getTitle());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }  


    public void deleteQuiz(Scanner sc) {

        try {
            sc.nextLine(); 
            System.out.print("Enter Quiz Title to Delete : ");
            String title = sc.nextLine();

            try (QuizDao quizDao = new QuizDao()) {
                quizDao.deleteQuiz(title);
                System.out.println("Quiz deleted successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
}
}