package project;

import java.sql.*;


public class Pesanan {
    private int id;
    private int id_user;
    private Date tanggal;
    private int total;

    public Pesanan(int id, int id_user, Date tanggal, int total) {
        this.id = id;
        this.id_user = id_user;
        this.tanggal = tanggal;
        this.total = total;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    

    // Database operations

    public int insert(Connection connection) throws SQLException {
        String query = "INSERT INTO Pesanan (id_user, tanggal, total) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, id_user);
        statement.setDate(2, tanggal);
        statement.setInt(3, total);
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
        String query = "UPDATE Pesanan SET id_user = ?, tanggal = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_user);
        statement.setDate(2, tanggal);
        statement.setInt(3, id);
        statement.executeUpdate();
        statement.close();
    }

    public void delete(Connection connection) throws SQLException {
        String query = "DELETE FROM Pesanan WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
    }
    public void refresh(Connection connection) throws SQLException {
        String query = "SELECT * FROM Pesanan WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            id_user = resultSet.getInt("id_user");
            tanggal = resultSet.getDate("tanggal");
            total = resultSet.getInt("total");
        }

        resultSet.close();
        statement.close();
    }

}
