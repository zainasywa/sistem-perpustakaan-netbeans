/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package p6_2371150001_zahranasywazain;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import com.toedter.calendar.JDateChooser;

/**
 *
 * @author Zahra Nasywa Zain
 */
public class FormPengembalian extends javax.swing.JFrame {
    private DefaultTableModel model;
    private String loggedUser;
    private String hiddenIdBuku;

    public FormPengembalian(String loggedUser) {
        this.loggedUser = loggedUser;
        initComponents();
        setLocationRelativeTo(null);
        model = (DefaultTableModel) jTable1.getModel();
        loadData();
        jDateChooser1.setDate(new java.util.Date());
        jDateChooser2.setDate(null);

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
    }
    
private void loadData() {
    model.setRowCount(0); // Kosongkan tabel terlebih dahulu
    
    try (Connection conn = koneksi.getkoneksi()) {
        String sql = "SELECT p.idPinjam, p.idBuku, b.judul, p.tanggalPinjam, " +
                     "p.tanggalKembali, p.status " +
                     "FROM peminjaman p JOIN buku b ON p.idBuku = b.idBuku " +
                     "WHERE p.username = ?"; // Hapus filter status
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, loggedUser);
        
        ResultSet rs = ps.executeQuery();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("idPinjam"),
                rs.getString("idBuku"),
                rs.getString("judul"),
                sdf.format(rs.getDate("tanggalPinjam")),
                rs.getDate("tanggalKembali") != null ? 
                    sdf.format(rs.getDate("tanggalKembali")) : "-",
                rs.getString("status")
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error load data: " + e.getMessage());
        e.printStackTrace();
    }
}

private void loadData(String keyword) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);

    String query = "SELECT p.idPinjam, p.idBuku, b.judul, p.tanggalPinjam, " +
                   "p.tanggalKembali, p.status " +
                   "FROM peminjaman p JOIN buku b ON p.idBuku = b.idBuku " +
                   "WHERE p.username = ? AND (p.idPinjam LIKE ? OR p.idBuku LIKE ? OR b.judul LIKE ?)";

    try (Connection conn = koneksi.getkoneksi();
         PreparedStatement ps = conn.prepareStatement(query)) {

        String param = "%" + keyword + "%";
        ps.setString(1, loggedUser);
        ps.setString(2, param);
        ps.setString(3, param);
        ps.setString(4, param);

        ResultSet rs = ps.executeQuery();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("idPinjam"),
                rs.getString("idBuku"),
                rs.getString("judul"),
                sdf.format(rs.getDate("tanggalPinjam")),
                rs.getDate("tanggalKembali") != null ? 
                    sdf.format(rs.getDate("tanggalKembali")) : "-",
                rs.getString("status")
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error saat mencari: " + e.getMessage());
        e.printStackTrace();
    }
}

    private void resetForm() {
        jTextField1.setText("");
        jTextField2.setText("");
        jDateChooser1.setDate(null);  
        jDateChooser2.setDate(null);  
        jComboBox1.setSelectedIndex(0);
    }
    
    private int hitungDenda(Date tanggalKembali, Date tanggalDikembalikan, int dendaPerHari) {
    if (tanggalDikembalikan == null || tanggalKembali == null) return 0;

    // Konversi ke LocalDate (abaikan jam dan menit)
    LocalDate tglKembali = tanggalKembali.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate tglDikembalikan = tanggalDikembalikan.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    // Hitung selisih hari
    long selisihHari = ChronoUnit.DAYS.between(tglKembali, tglDikembalikan);

    // Pastikan tidak negatif (jika dikembalikan lebih awal)
    return (selisihHari > 0) ? (int) selisihHari * dendaPerHari : 0;
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("ID Pinjam");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, -1, -1));

        jLabel2.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ID Buku");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, -1, -1));

        jLabel3.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tanggal Kembali");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, -1));

        jLabel4.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Dikembalikan");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, -1, -1));

        jLabel5.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Status");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, -1, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 200, -1));
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 200, -1));
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 200, -1));

        jButton1.setBackground(new java.awt.Color(102, 102, 255));
        jButton1.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Kembalikan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, -1));

        jButton2.setBackground(new java.awt.Color(102, 102, 255));
        jButton2.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, -1, -1));

        jTable1.setBackground(new java.awt.Color(203, 226, 209));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Pinjam", "ID Buku", "Judul Buku", "Tanggal pinjam", "Tanggal Kembali", "Status"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 640, 290));

        jLabel6.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 51, 0));
        jLabel6.setText("Form Pengembalian Buku");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 190, -1));

        jLabel7.setFont(new java.awt.Font("Kristen ITC", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 51, 0));
        jLabel7.setText("Buku Yang Masih Dipinjam");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 200, -1));
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 750, 40, 20));
        jPanel1.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 200, -1));

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
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 750, 30, 20));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dipinjam", "Dikembalikan" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 200, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\Users\\Zahra Nasywa Zain\\Downloads\\buku.png")); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 130, 110));
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 740, 40, 30));
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 160, -1));

        jButton4.setBackground(new java.awt.Color(102, 102, 255));
        jButton4.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, -1, -1));

        jButton5.setBackground(new java.awt.Color(102, 102, 255));
        jButton5.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Reset");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, -1, -1));

        jLabel12.setFont(new java.awt.Font("Kristen ITC", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Cari Buku");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 740, 40, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTextField1.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Pilih data pinjaman yang mau dikembalikan!");
        return;
    }

    if (jDateChooser2.getDate() == null) {
        JOptionPane.showMessageDialog(this, "Tanggal kembali harus diisi!");
        return;
    }

    try (Connection conn = koneksi.getkoneksi()) {
        // Hitung denda
        java.util.Date tglDikembalikan = jDateChooser2.getDate();
        java.util.Date tglSeharusnyaKembali = jDateChooser1.getDate();
        // Jika tanggal dikembalikan lebih awal dari tanggal seharusnya kembali
    if (tglDikembalikan.before(tglSeharusnyaKembali)) {
        int response = JOptionPane.showConfirmDialog(
            this, 
            "Tanggal pengembalian lebih awal dari tanggal seharusnya kembali.\n" +
            "Apakah Anda yakin ingin melanjutkan?",
            "Konfirmasi Pengembalian",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (response != JOptionPane.YES_OPTION) {
            return; // Batalkan jika user memilih NO
        }
    }
        int dendaPerHari = 5000;
        int denda = hitungDenda(tglSeharusnyaKembali, tglDikembalikan, dendaPerHari);

        // Update data peminjaman
        String sql = "UPDATE peminjaman SET status=?, tanggalKembali=?, denda=? WHERE idPinjam=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "Dikembalikan"); // Langsung set ke Dikembalikan
            ps.setDate(2, new java.sql.Date(tglDikembalikan.getTime()));
            ps.setInt(3, denda);
            ps.setString(4, jTextField1.getText());
            
            int updated = ps.executeUpdate();
            if (updated > 0) {
                // Update stok buku jika peminjaman berhasil diupdate
                String updateStok = "UPDATE buku SET stok = stok + 1 WHERE idBuku=?";
                try (PreparedStatement psStok = conn.prepareStatement(updateStok)) {
                    psStok.setString(1, hiddenIdBuku);
                    psStok.executeUpdate();
                }
                
                JOptionPane.showMessageDialog(this, "Buku berhasil dikembalikan.\nDenda: Rp " + denda);
                loadData();
                resetForm();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengembalikan buku. Data tidak ditemukan.");
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Gagal update: " + ex.getMessage());
        ex.printStackTrace();
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        resetForm();
        jDateChooser1.setDate(new java.util.Date());
        jDateChooser2.setDate(new java.util.Date());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new FormHome(loggedUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String keyword = jTextField3.getText();
        loadData(keyword);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jTextField3.setText("");  // Kosongkan field pencarian
        loadData();
    }//GEN-LAST:event_jButton5ActionPerformed
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {                                     
        int row = jTable1.getSelectedRow();
    if (row >= 0) {
        jTextField1.setText(jTable1.getValueAt(row, 0).toString()); // ID Pinjam
        jTextField2.setText(jTable1.getValueAt(row, 1).toString()); // ID Buku
        hiddenIdBuku = jTable1.getValueAt(row, 1).toString(); // Simpan ID Buku untuk update stok
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            // Tanggal Pinjam (kolom 3) - hanya untuk referensi, tidak diset ke manapun
            String tglPinjamStr = jTable1.getValueAt(row, 3).toString();
            java.util.Date tglPinjam = sdf.parse(tglPinjamStr);
            
            // Tanggal Kembali Seharusnya (kolom 4)
            String tglKembaliStr = jTable1.getValueAt(row, 4).toString(); 
            if (!tglKembaliStr.equals("-")) {
                java.util.Date tglKembali = sdf.parse(tglKembaliStr);
                jDateChooser1.setDate(tglKembali); // Set ke tanggal seharusnya kembali
            } else {
                // Jika tidak ada tanggal kembali di database, hitung 7 hari setelah pinjam
                Calendar cal = Calendar.getInstance();
                cal.setTime(tglPinjam);
                cal.add(Calendar.DATE, 7);
                jDateChooser1.setDate(cal.getTime());
            }
            
            // Set tanggal dikembalikan ke hari ini (selalu)
            jDateChooser2.setDate(new Date()); 
            
            // Set status ke "Dikembalikan"
            jComboBox1.setSelectedItem("Dikembalikan");
            
            // Debug output
            System.out.println("Tanggal Pinjam: " + tglPinjam);
            System.out.println("Tanggal Kembali Seharusnya: " + jDateChooser1.getDate());
            System.out.println("Tanggal Dikembalikan: " + jDateChooser2.getDate());
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    }   
    
    /**
     * @param args the command line arguments
     */
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
//            java.util.logging.Logger.getLogger(FormPengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FormPengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FormPengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FormPengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormPengembalian().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
