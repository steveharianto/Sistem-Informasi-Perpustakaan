package project;

import java.sql.*;

public class Member {
    private int id;
    private String nama;

    public Member(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    // Database operations

    public int insert(Connection connection) throws SQLException {
        String query = "INSERT INTO Member (nama) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, nama);
        statement.executeUpdate();

        int generatedId = -1; // default value if the ID retrieval fails
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            generatedId = generatedKeys.getInt(1); // assuming ID is of type INT
        }

        generatedKeys.close();
        statement.close();

        return generatedId;
    }


    public void update(Connection connection) throws SQLException {
        String query = "UPDATE Member SET nama = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, nama);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
    }

    public void delete(Connection connection) throws SQLException {
        String query = "DELETE FROM Member WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
    }
}

