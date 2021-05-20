/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sumingjie.service;

import com.sumingjie.serviceexception.InsertingException;
import com.sumingjie.serviceexception.InvalidEntityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class PostgreSQLDAO {

    private Connection connection;

    PostgreSQLDAO() {
        this.connection = ConnectionUtil.getConnection();
    }

    public List<Picture> getAllPictures() {
        return executeQuery("SELECT * FROM pictures");
    }

    public List<Picture> findPictures(MyRequest request) {
        String sql = "SELECT * FROM pictures WHERE " +
                "(id = " + request.getId() + " OR " + request.getId() + " = 0) AND " +
                "(name = '" + request.getName() + "' OR '" + request.getName() + "' = '' OR '" + request.getName() + "' = '?') AND " +
                "(author = '" + request.getAuthor() + "' OR '" + request.getAuthor() + "' = '' OR '" + request.getAuthor() + "' = '?') AND " +
                "(year = " + request.getYear() + " OR " + request.getYear() + " = 0) AND " +
                "(material = '" + request.getMaterial() + "' OR '" + request.getMaterial() + "' = '' OR '" + request.getMaterial() + "' = '?') AND " +
                "(height = " + request.getHeight() + " OR " + request.getHeight() + " = 0) AND " +
                "(width = " + request.getWidth() + " OR " + request.getWidth() + " = 0)";

        return executeQuery(sql);
    }

    public int createPicture(String name, String author, int year, String material, float height, float width) throws InsertingException {
        String sql = "INSERT INTO pictures (name, author, year, material, height, width) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        int id = 0;
        try {
            preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, year);
            preparedStatement.setString(4, material);
            preparedStatement.setFloat(5, height);
            preparedStatement.setFloat(6, width);

            int affectedRows = preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = (int) generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (id == 0) {
            PictureServiceFault fault = PictureServiceFault.defaultInstance();
            fault.setMessage("Error During creation entity");
            throw new InsertingException("Error During creation entity", fault);
        }

        return id;
    }

    public int updatePicture(int id, MyRequest request) throws InvalidEntityException {
        String sql = "UPDATE pictures SET" + createUpdateQuery(request) + " WHERE id=?";

        PreparedStatement preparedStatement = null;
        int affectedRows = 0;
        try {
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (affectedRows == 0) {
            PictureServiceFault fault = PictureServiceFault.defaultInstance();
            fault.setMessage("Invalid entity");
            throw new InvalidEntityException("Invalid entity", fault);
        }

        return affectedRows;
    }

    public int deletePicture(int id) throws InvalidEntityException {
        String sql = "DELETE FROM pictures WHERE id = ?";

        PreparedStatement preparedStatement = null;
        int affectedRows = 0;
        try {
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (affectedRows == 0) {
            PictureServiceFault fault = PictureServiceFault.defaultInstance();
            fault.setMessage("Invalid entity");
            throw new InvalidEntityException("Invalid entity", fault);
        }

        return affectedRows;
    }

    private List<Picture> executeQuery(String sql) {
        List<Picture> pictures;
        pictures = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                int year = rs.getInt("year");
                String material = rs.getString("material");
                float height = rs.getFloat("height");
                float width = rs.getFloat("width");
                Picture picture = new Picture(id, name, author, year, material, height, width);
                pictures.add(picture);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pictures;
    }

    private String createUpdateQuery(MyRequest request) {
        StringBuilder stringBuilderField = new StringBuilder("(");
        StringBuilder stringBuilderValues = new StringBuilder("(");
        if (request.getId() > 0) {
            stringBuilderField.append("id,");
            stringBuilderValues.append(request.getId()).append(",");
        }

        if (!request.getName().equals("?") && !request.getName().equals("")) {
            stringBuilderField.append("name,");
            stringBuilderValues.append("'").append(request.getName()).append("',");
        }

        if (!request.getAuthor().equals("?") && !request.getAuthor().equals("")) {
            stringBuilderField.append("author,");
            stringBuilderValues.append("'").append(request.getAuthor()).append("',");
        }

        if (request.getYear() > 0) {
            stringBuilderField.append("year,");
            stringBuilderValues.append(request.getYear()).append(",");
        }

        if (!request.getMaterial().equals("?") && !request.getMaterial().equals("")) {
            stringBuilderField.append("material,");
            stringBuilderValues.append("'").append(request.getMaterial()).append("',");
        }

        if (request.getHeight() > 0) {
            stringBuilderField.append("height,");
            stringBuilderValues.append(request.getHeight()).append(",");
        }

        if (request.getWidth() > 0) {
            stringBuilderField.append("width,");
            stringBuilderValues.append(request.getWidth()).append(",");
        }

        if (stringBuilderField.toString().endsWith(",")) {
            stringBuilderField.setLength(stringBuilderField.length() - 1);
            stringBuilderValues.setLength(stringBuilderValues.length() - 1);
        }

        stringBuilderField.append(")");
        stringBuilderValues.append(")");

        return stringBuilderField.toString() + " = " + stringBuilderValues.toString();
    }
}
