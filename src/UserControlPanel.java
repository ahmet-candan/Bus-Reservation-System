import com.toedter.calendar.JDateChooser;
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;

public class UserControlPanel extends JFrame {
    private JPanel panel5;
    private JButton musteri_ekle;
    private JButton seferAlButton;
    private JTable table2;
    private JTextField varis_Noktasi;
    private JTextField kalkis_Noktasi;
    private JButton biletSorgula;
    private JTextField new_tarih;

    private Connection con = null;
    private PreparedStatement preparedStatement = null;

    public static String selected = "aaaa";

    JDateChooser tarih_user = new JDateChooser();


    public UserControlPanel() {

        add(panel5);
        setSize(800, 500);
        setTitle("Otobüs Bilet Sistemi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //createTable();

        seferAlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel model = (DefaultTableModel) table2.getModel();
                int rows = table2.getSelectedRow();
                selected = model.getValueAt(rows, 0).toString();

                satinAl sa = new satinAl();
                sa.setVisible(true);




            }
        });


        biletSorgula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*tarih_user.setDateFormatString("dd/MM/yyyy");
                tarih_Paneli.add(tarih_user);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dtc = sdf.format(tarih_user.getDate());*/


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

    /*public void createTable() {
        String databaseUrl = "jdbc:mysql://localhost:3306/bus?useUnicode=true&characterEncoding=utf-8";
        try {
            con = DriverManager.getConnection(databaseUrl, "root", "");
            String sorgu = "SELECT * FROM terminal";
            preparedStatement = con.prepareStatement(sorgu);
            ResultSet rs = preparedStatement.executeQuery();
            table2.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/
}