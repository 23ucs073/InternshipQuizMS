package com.app.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.app.util.DbUtil;

public class QuizAttemptsDao implements AutoCloseable {

    private Connection con;

    public QuizAttemptsDao() throws SQLException {
        con = DbUtil.getConnection();
    }

    public List<String> getAllScores() throws SQLException {

        List<String> list = new ArrayList<>();

        String sql = """
            SELECT s.student_id, q.title, qa.final_score, qa.total_questions
            FROM quiz_attempts qa
            JOIN quizzes q ON qa.quiz_id = q.quiz_id
            JOIN students s ON qa.student_id = s.student_id
            ORDER BY s.student_id
        """;

        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add("Student " + rs.getInt(1) + " | " +
                         rs.getString(2) + " : " +
                         rs.getInt(3) + "/" + rs.getInt(4));
            }
        }
        return list;
    }

    public List<String> getStudentScores(int studentId) throws SQLException {

        List<String> list = new ArrayList<>();

        String sql = """
            SELECT q.title, qa.final_score, qa.total_questions
            FROM quiz_attempts qa
            JOIN quizzes q ON qa.quiz_id = q.quiz_id
            WHERE qa.student_id=?
        """;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString(1) + " : " +
                         rs.getInt(2) + "/" + rs.getInt(3));
            }
        }
        return list;
    }

    @Override
    public void close() throws SQLException {
        if (con != null)
            con.close();
    }
}
