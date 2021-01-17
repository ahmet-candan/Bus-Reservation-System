import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserControlPanel extends JFrame {
    public static String selected = "aaaa";
    public static String selected1 = "aaaa";
    public static String selected2 = "aaaa";
    public static String selected3 = "aaaa";
    public static String selected4 = "aaaa";
    public static String selected5 = "aaaa";
    public static String selected6 = "aaaa";
    public static String selected7 = "aaaa";
    private JPanel panel5;

    private JButton seferAlButton;
    private JTable table2;
    private JTextField varis_Noktasi;
    private JTextField kalkis_Noktasi;
    private JButton biletSorgula;
    private JTextField new_tarih;
    private Connection con = null;
    private PreparedStatement preparedStatement = null;


    public UserControlPanel() {

        add(panel5);
        setSize(800, 500);
        setTitle("Otobüs Bilet Sistemi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        seferAlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel model = (DefaultTableModel) table2.getModel();
                int rows = table2.getSelectedRow();
                selected = model.getValueAt(rows, 0).toString();
                selected1 = model.getValueAt(rows, 1).toString();
                selected2 = model.getValueAt(rows, 2).toString();
                selected3 = model.getValueAt(rows, 3).toString();
                selected4 = model.getValueAt(rows, 4).toString();
                selected5 = model.getValueAt(rows, 5).toString();
                selected6 = model.getValueAt(rows, 6).toString();
                selected7 = model.getValueAt(rows, 7).toString();

                satinAl sa = new satinAl();
                sa.setVisible(true);
            }
        });


        biletSorgula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String kalkisnoktasi = kalkis_Noktasi.getText();
                String varisnoktasi = varis_Noktasi.getText();
                String tarih = new_tarih.getText();

                String databaseUrl = "jdbc:mysql://localhost:3306/bus?useUnicode=true&characterEncoding=utf-8";
                try {
                    con = DriverManager.getConnection(databaseUrl, "root", "");
                    String kontrolSorgu = "SELECT * FROM terminal WHERE kalkis_noktasi= ? and varis_noktasi = ? and sefer_tarihi = ?";
                    preparedStatement = con.prepareStatement(kontrolSorgu);
                    preparedStatement.setString(1, kalkisnoktasi);
                    preparedStatement.setString(2, varisnoktasi);
                    preparedStatement.setString(3, tarih);

                    ResultSet rs = preparedStatement.executeQuery();
                    table2.setModel(DbUtils.resultSetToTableModel(rs));

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });
    }

}