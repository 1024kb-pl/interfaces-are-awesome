package dao;

import model.Comment;

import java.sql.*;
import java.util.*;

public class CommentDaoDatabase implements CommentDao {

    private Connection connection;
    private final String databaseName = "blog";
    private final String tableName = "comments";
    private final String user = "root";
    private final String password = "Admin!000!";

    public CommentDaoDatabase() {
        init();
    }

    private void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseName+"?useSSL=false", user, password);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public Comment save(Comment comment) {
        PreparedStatement statement;
        try {
            String query = "insert into " + tableName + " (owner, text) values(?, ?)";
            statement = connection.prepareStatement(query);

            statement.setString(1, comment.getOwner());
            statement.setString(2, comment.getText());

            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comment;
    }

    @Override
    public Collection<Comment> getAllComments() {
        List<Comment> commentList = new LinkedList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "select * from " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String text = resultSet.getString("text");
                String owner = resultSet.getString("owner");

                Comment comment = new Comment(id, owner, text);
                commentList.add(comment);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentList;
    }

    @Override
    public Optional<Comment> getCommentById(Long id) {
        List<Comment> commentList = new LinkedList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "select * from " + tableName + " where id='" + id + "'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String ownerDatabase = resultSet.getString("owner");
                String text = resultSet.getString("text");

                Comment comment = new Comment(id, ownerDatabase, text);
                commentList.add(comment);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.of(commentList.get(0));
    }

    @Override
    public Collection<Comment> getCommentsByOwner(String owner) {
        List<Comment> commentList = new LinkedList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "select * from " + tableName + " where owner='" + owner + "'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String text = resultSet.getString("text");

                Comment comment = new Comment(id, owner, text);
                commentList.add(comment);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentList;
    }

    @Override
    public void deleteCommentById(Long id) {
        PreparedStatement statement;
        try {
            String query = "delete from " + tableName + " where id=?";
            statement = connection.prepareStatement(query);

            statement.setLong(1, id);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCommentsByOwner(String owner) {
        PreparedStatement statement;
        try {
            String query = "delete from " + tableName + " where owner=?";
            statement = connection.prepareStatement(query);

            statement.setString(1, owner);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Comment update(Comment comment) {
        PreparedStatement statement;
        try {
            String query = "update " + tableName + " set owner = ?, text = ? where id=?";
            statement = connection.prepareStatement(query);

            statement.setString(1, comment.getOwner());
            statement.setString(2, comment.getText());
            statement.setLong(3, comment.getId());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comment;
    }
}
