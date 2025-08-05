/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package p6_2371150001_zahranasywazain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

/**
 *
 * @author Zahra Nasywa Zain
 */
public class FormPeminjaman extends javax.swing.JFrame {
    private DefaultTableModel model;
    private String username;
    private Map<String, String> bukuMap = new HashMap<>();
    private String loggedUser;
    
public FormPeminjaman(String loggedUser) {
    initComponents();
    setLocationRelativeTo(null);
    this.loggedUser = loggedUser;
    this.username = loggedUser;

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>());
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    model = (DefaultTableModel) jTable1.getModel();
    
    loadBukuTersedia();
    loadData(); 
    
    jDateChooser1.setDate(new java.util.Date());
}

    private void loadData() {
    model.setRowCount(0); // Kosongkan tabel terlebih dahulu
    
    try (Connection conn = koneksi.getkoneksi();
         PreparedStatement ps = conn.prepareStatement(
             "SELECT p.idPinjam, p.username, b.judul, p.tanggalPinjam, " +
             "p.tanggalKembali, p.denda " +
             "FROM peminjaman p JOIN buku b ON p.idBuku = b.idBuku " +
             "WHERE p.username = ?")) { // Hapus filter status
        
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("idPinjam"),
                rs.getString("username"),
                rs.getString("judul"),
                dateFormat.format(rs.getDate("tanggalPinjam")),
                rs.getDate("tanggalKembali") != null 
                    ? dateFormat.format(rs.getDate("tanggalKembali")) 
                    : "-",
                rs.getInt("denda")
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal load data: " + e.getMessage());
    }
}
    
    private void loadData(String keyword) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);

    String query = "SELECT p.idPinjam, p.username, b.judul, p.tanggalPinjam, " +
                   "p.tanggalKembali, p.denda " +
                   "FROM peminjaman p JOIN buku b ON p.idBuku = b.idBuku " +
                   "WHERE p.username = ? " +  
                   "AND (p.idPinjam LIKE ? OR b.judul LIKE ?)";

    try (Connection conn = koneksi.getkoneksi();
         PreparedStatement ps = conn.prepareStatement(query)) {

        String param = "%" + keyword + "%";
        ps.setString(1, username);
        ps.setString(2, param);
        ps.setString(3, param);

        ResultSet rs = ps.executeQuery();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("idPinjam"),
                rs.getString("username"),
                rs.getString("judul"),
                dateFormat.format(rs.getDate("tanggalPinjam")),
                rs.getDate("tanggalKembali") != null 
                    ? dateFormat.format(rs.getDate("tanggalKembali")) 
                    : "-",
                rs.getInt("denda")
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error saat mencari: " + e.getMessage());
    }
}
    
    private void loadBukuTersedia() {
    jComboBox1.removeAllItems();
    bukuMap.clear();

    try (Connection conn = koneksi.getkoneksi()) {
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "Gagal terkoneksi ke database");
            return;
        }
        
        try (PreparedStatement ps = conn.prepareStatement(
                 "SELECT idBuku, judul, stok FROM buku WHERE stok > 0")) {
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("idBuku");
                String judul = rs.getString("judul");
                int stok = rs.getInt("stok");

                String display = judul + " (Stok: " + stok + ")";
                jComboBox1.addItem(display);
                bukuMap.put(display, id);
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal load buku: " + e.getMessage());
        e.printStackTrace();
    }
}
    
    private void resetForm() {
        jTextField1.setText("");          
        jComboBox1.setSelectedIndex(-1);   
        jDateChooser1.setDate(new java.util.Date()); 
        jDateChooser2.setDate(null); 
    }
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("ID Pinjam");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        jLabel2.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Judul Buku");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, -1, -1));

        jLabel3.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tanggal Pinjam");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, -1, -1));

        jLabel4.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tanggal Kembali");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, -1));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 190, -1));
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 370, 20, 20));
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 190, -1));
        jPanel1.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 190, -1));

        jButton1.setBackground(new java.awt.Color(102, 102, 255));
        jButton1.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, -1, -1));

        jButton2.setBackground(new java.awt.Color(102, 102, 255));
        jButton2.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, -1, -1));

        jTable1.setBackground(new java.awt.Color(203, 226, 209));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Pinjam", "Username", "Judul Buku", "Tanggal Pinjam", "Tanggal Kembali", "Denda"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 640, 290));

        jLabel6.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 51, 0));
        jLabel6.setText("Form Peminjaman Buku");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, -1, -1));

        jLabel7.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 51, 0));
        jLabel7.setText("Daftar Peminjaman Buku");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, -1, -1));

        jButton3.setBackground(new java.awt.Color(102, 102, 255));
        jButton3.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Kembali");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 190, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\Zahra Nasywa Zain\\Downloads\\buku.png")); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 130, 110));
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 180, -1));

        jButton4.setBackground(new java.awt.Color(102, 102, 255));
        jButton4.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, -1, -1));

        jButton5.setBackground(new java.awt.Color(102, 102, 255));
        jButton5.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Reset");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 320, -1, -1));

        jLabel8.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Cari Buku");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 700, 50, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            if (jTextField1.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "ID Pinjam harus diisi!");
        return;
    }

    String selectedBuku = (String) jComboBox1.getSelectedItem();
    if (selectedBuku == null) {
        JOptionPane.showMessageDialog(this, "Pilih buku terlebih dahulu!");
        return;
    }
    if (jDateChooser2.getDate() == null) {
        JOptionPane.showMessageDialog(this, "Tanggal kembali harus diisi!");
        return;
    }

    String idPinjam = jTextField1.getText().trim();
    String idBuku = bukuMap.get(selectedBuku);

    try (Connection conn = koneksi.getkoneksi()) {
        // Insert ke peminjaman
        String insertSql = "INSERT INTO peminjaman (idPinjam, username, idBuku, tanggalPinjam, tanggalKembali, denda) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement psInsert = conn.prepareStatement(insertSql);
        psInsert.setString(1, idPinjam);
        psInsert.setString(2, username);
        psInsert.setString(3, idBuku);
        psInsert.setDate(4, new java.sql.Date(jDateChooser1.getDate().getTime()));
        psInsert.setDate(5, new java.sql.Date(jDateChooser2.getDate().getTime()));
        psInsert.setInt(6, 0); // Denda awal = 0

        psInsert.executeUpdate();
        String updateStokSql = "UPDATE buku SET stok = stok - 1 WHERE idBuku = ?";
        PreparedStatement psStok = conn.prepareStatement(updateStokSql);
        psStok.setString(1, idBuku);
        psStok.executeUpdate();

        JOptionPane.showMessageDialog(this, "Peminjaman berhasil disimpan.");

        // Refresh tampilan
        loadData();
        loadBukuTersedia();
        resetForm();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Gagal simpan: " + ex.getMessage());
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        resetForm();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new FormHome(loggedUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String keyword = jTextField2.getText();
        loadData(keyword);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jTextField2.setText("");  // Kosongkan field pencarian
        loadData();
    }//GEN-LAST:event_jButton5ActionPerformed

    
    
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FormPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FormPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FormPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FormPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//        public void run() {
//        new FormPeminjaman("username_default").setVisible(true);
//    }
//});
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
