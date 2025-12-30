package com.app.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.app.entity.Quiz;


import com.app.util.DbUtil;

public class QuizDao implements AutoCloseable {

    private Connection connection;

    public QuizDao() throws SQLException {
        connection = DbUtil.getConnection();
    }

    public int insertQuiz(Quiz quiz) throws SQLException {
        String sql = "INSERT INTO quizzes (title, creator_id) VALUES (?, ?)";
        try (PreparedStatement stmt =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, quiz.getTitle());
            stmt.setInt(2, quiz.getCreator_id());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        }
    }




    @Override
    public void close() throws SQLException {
        if (connection != null)
            connection.close();
    }
}
