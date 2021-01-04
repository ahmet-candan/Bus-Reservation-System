import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddCostumer extends JFrame{
    private JPanel panel6;
    private JTextField c_isim;
    private JTextField c_soyisim;
    private JTextField c_tc;
    private JTextField c_telefon;
    private JButton resimYükleButton;
    private JButton iptalButton;
    private JButton ekleButton;

    private Connection con = null;
    private Statement statement = null;

    public AddCostumer() throws SQLException {

        add(panel6);
        setSize(800,500);
        setTitle("Otobüs Bilet Sistemi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String url = "jdbc:mysql://localhost:3306/bus";
        con = DriverManager.getConnection(url,"root","");
        String sorgu = "SELECT isim,soyisim FROM kullanici_bilgileri ";

        statement = con.createStatement();
        ResultSet rs =  statement.executeQuery(sorgu);

        while (rs.next()){
            String isimid = rs.getString("isim");
            String soyisimid = rs.getString("soyisim");


        }

        resimYükleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
