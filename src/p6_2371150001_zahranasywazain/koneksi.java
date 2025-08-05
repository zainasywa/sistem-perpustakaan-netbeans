/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p6_2371150001_zahranasywazain;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Zahra Nasywa Zain
 */
public class koneksi {
    private static Connection koneksi;

     public static Connection getkoneksi() {
        try {
            // Cek jika koneksi null/telah ditutup
            if (koneksi == null || koneksi.isClosed()) {
                String url = "jdbc:mysql://localhost:3306/perpustakaan?autoReconnect=true&useSSL=false"; 
                String user = "root";
                String password = ""; 

                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi berhasil dibuat!");
            }
        } catch (SQLException e) {
            System.out.println("Error koneksi: " + e.getMessage());
            koneksi = null; // Reset koneksi jika gagal
        }
        return koneksi;
    }   
}
