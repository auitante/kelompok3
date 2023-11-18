package com.mbd.clouddatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.mbd.clouddatabase.Conn.connect;

public class Delete extends Component {
    private JButton buttonDelete;
    private JLabel notelp;
    private JLabel alamat;
    private JLabel nama;
    private JLabel id;
    private JLabel Judul;
    private JTextField idPegawai;
    private JTextField namaPegawai;
    private JTextField alamatPegawai;
    private JTextField NoTelp;

    public Delete() {
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDataPegawai();}
            });
    }
    private void deleteDataPegawai() {
        try (Connection connection = connect()) {
            int id = Integer.parseInt(idPegawai.getText());

            String query = "DELETE FROM pegawai WHERE id=?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data pegawai berhasil dihapus.");
            }
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

}
