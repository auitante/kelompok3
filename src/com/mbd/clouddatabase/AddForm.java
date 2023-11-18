package com.mbd.clouddatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.mbd.clouddatabase.Conn.connect;

public class Add extends Component {
    private JButton buttonAdd;
    private JLabel Judul;
    private JLabel id;
    private JLabel nama;
    private JLabel notelp;
    private JLabel alamat;
    private JTextField idPegawai;
    private JTextField namaPegawai;
    private JTextField alamatPegawai;
    private JTextField NoTelp;


    public Add() {
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataPegawai();
            }
        });
    }

    private void addDataPegawai() {
        try (Connection connection = connect()) {
            int id = Integer.parseInt(idPegawai.getText());
            String nama = namaPegawai.getText();
            String alamat = alamatPegawai.getText();
            String noTelp = NoTelp.getText();

            String query = "INSERT INTO pegawai (id, nama, alamat, no_telp) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, id);
                pstmt.setString(2, nama);
                pstmt.setString(3, alamat);
                pstmt.setString(4, noTelp);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Pegawai berhasil ditambahkan.");
            }
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

}
