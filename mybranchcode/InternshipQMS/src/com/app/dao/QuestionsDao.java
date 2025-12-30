package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.entity.Questions;
import com.app.util.DbUtil;

public class QuestionsDao implements AutoCloseable {

    private Connection connection;

    public QuestionsDao() throws SQLException {
        connection = DbUtil.getConnection();
    }

    
    public void insertQuestion(Questions question) throws SQLException {

        String sql = """
            INSERT INTO questions
            (quiz_id, question_text, option_a, option_b, option_c, option_d, correct_option)
            VALUES (?,?,?,?,?,?,?)
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, question.quiz_id);
            stmt.setString(2, question.getText());
            stmt.setString(3, question.getA());
            stmt.setString(4, question.getB());
            stmt.setString(5, question.getC());
            stmt.setString(6, question.getD());
            stmt.setString(7, question.getCorrect());
            stmt.executeUpdate();
        }
    }

    // ================= FETCH QUESTIONS FOR QUIZ =================
    public List<Questions> getQuestionsByQuizId(int quizId) throws SQLException {

        List<Questions> list = new ArrayList<>();

        String sql = "SELECT * FROM questions WHERE quiz_id=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quizId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Questions q = new Questions();
                q.question_id = rs.getInt("question_id");
                q.quiz_id = quizId;
                q.setText(rs.getString("question_text"));
                q.setA(rs.getString("option_a"));
                q.setB(rs.getString("option_b"));
                q.setC(rs.getString("option_c"));
                q.setD(rs.getString("option_d"));
                q.setCorrect(rs.getString("correct_option"));
                list.add(q);
            }
        }
        return list;
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }
}
