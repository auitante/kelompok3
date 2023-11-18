package com.mbd.clouddatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.mbd.clouddatabase.Conn.connect;

public class Update extends Component {
    private JButton buttonUpdate;
    private JLabel notelp;
    private JLabel alamat;
    private JLabel nama;
    private JLabel id;
    private JLabel Judul;
    private JTextField idPegawai;
    private JTextField namaPegawai;
    private JTextField alamatPegawai;
    private JTextField NoTelp;

    public Update() {
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDataPegawai();
            }
        });
    }

    private void updateDataPegawai() {
        try (Connection connection = connect()) {
            int id = Integer.parseInt(idPegawai.getText());
            String nama = namaPegawai.getText();
            String alamat = alamatPegawai.getText();
            String noTelp = NoTelp.getText();

            String query = "UPDATE pegawai SET nama=?, alamat=?, no_telp=? WHERE id=?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, nama);
                pstmt.setString(2, alamat);
                pstmt.setString(3, noTelp);
                pstmt.setInt(4, id);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data pegawai berhasil diupdate.");
            }
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
