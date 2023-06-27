package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Pesanan_item {
    private int id;
    private int id_pesanan;
    private int id_barang;
    private int jumlah;
    private int subtotal;

    public Pesanan_item(int id, int id_pesanan, int id_barang, int jumlah, int subtotal) {
        this.id = id;
        this.id_pesanan = id_pesanan;
        this.id_barang = id_barang;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_pesanan() {
        return id_pesanan;
    }

    public void setId_pesanan(int id_pesanan) {
        this.id_pesanan = id_pesanan;
    }

    public int getId_barang() {
        return id_barang;
    }

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    // Database operations

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO Pesanan_item (id_pesanan, id_barang, jumlah, subtotal) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_pesanan);
        statement.setInt(2, id_barang);
        statement.setInt(3, jumlah);
        statement.setInt(4, subtotal);
        statement.executeUpdate();
        statement.close();
    }

    public void update(Connection connection) throws SQLException {
        String query = "UPDATE Pesanan_item SET id_pesanan = ?, id_barang = ?, jumlah = ?, subtotal = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_pesanan);
        statement.setInt(2, id_barang);
        statement.setInt(3, jumlah);
        statement.setInt(4, subtotal);
        statement.setInt(5, id);
        statement.executeUpdate();
        statement.close();
    }

    public void delete(Connection connection) throws SQLException {
        String query = "DELETE FROM Pesanan_item WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
    }
}
