package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Barang {
    private int id;
    private String nama;
    private int harga;
    private int jumlah;

    public Barang(int id, String nama, int harga, int jumlah) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.jumlah = jumlah;
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

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    // Database operations

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO Barang (nama, harga, jumlah) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, nama);
        statement.setInt(2, harga);
        statement.setInt(3, jumlah);
        statement.executeUpdate();
        statement.close();
    }

    public void update(Connection connection) throws SQLException {
        String query = "UPDATE Barang SET nama = ?, harga = ?, jumlah = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, nama);
        statement.setInt(2, harga);
        statement.setInt(3, jumlah);
        statement.setInt(4, id);
        statement.executeUpdate();
        statement.close();
    }

    public void delete(Connection connection) throws SQLException {
        String query = "DELETE FROM Barang WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
    }
    
    public void refresh(Connection connection) throws SQLException {
        String query = "SELECT * FROM Barang WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            nama = resultSet.getString("nama");
            harga = resultSet.getInt("harga");
            jumlah = resultSet.getInt("jumlah");
        }

        resultSet.close();
        statement.close();
    }
}

