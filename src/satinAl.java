import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class satinAl extends JFrame {
    String isim = "null";
    String soyisim = "null";
    private JButton satin_al;
    private JPanel panel9;
    private JTextField textField1;
    private Connection con = null;
    private PreparedStatement preparedStatement = null;


    public satinAl() {

        add(panel9);
        setSize(800, 500);
        setTitle("Otobüs Bilet Sistemi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        satin_al.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UserControlPanel a = new UserControlPanel();

                String koltuk = textField1.getText();
                String databaseUrl = "jdbc:mysql://localhost:3306/bus?useUnicode=true&characterEncoding=utf-8";

                try {
                    con = DriverManager.getConnection(databaseUrl, "root", "");
                    String sorgu0 = "SELECT koltuk_no FROM bilet_al WHERE koltuk_no = ?";

                    preparedStatement = con.prepareStatement(sorgu0);
                    preparedStatement.setString(1, koltuk);

                    ResultSet rs = preparedStatement.executeQuery();

                    if (rs.next()) {
                        bilgiMesaji("Bu koltuk satın alınmış,başka seçin", "Bilgi");
                    } else {

                        UserLogin userLogin = new UserLogin();


                        String sorgu9 = "SELECT * From kullanici_bilgileri WHERE username = ?";

                        preparedStatement = con.prepareStatement(sorgu9);
                        preparedStatement.setString(1, UserLogin.b);

                        ResultSet rm = preparedStatement.executeQuery();

                        while (rm.next()) {
                            isim = rm.getString("ad");
                            soyisim = rm.getString("soyad");

                        }

                        String sorgu1 = "INSERT INTO bilet_al (ad,soyisim,otobus_no,otobus_adi,kalkis_noktasi,varis_noktasi,sefer_tarihi,sefer_saati,ucret,koltuk_no) VALUES ( ?,?,?,?,?,?,?,?,?,?)";

                        preparedStatement = con.prepareStatement(sorgu1);
                        preparedStatement.setString(1, isim);
                        preparedStatement.setString(2, soyisim);
                        preparedStatement.setString(3, UserControlPanel.selected1);
                        preparedStatement.setString(4, UserControlPanel.selected2);
                        preparedStatement.setString(5, UserControlPanel.selected3);
                        preparedStatement.setString(6, UserControlPanel.selected4);
                        preparedStatement.setString(7, UserControlPanel.selected5);
                        preparedStatement.setString(8, UserControlPanel.selected6);
                        preparedStatement.setString(9, UserControlPanel.selected7);
                        preparedStatement.setString(10, koltuk);


                        int x = preparedStatement.executeUpdate();

                        if (x == 1) {
                            bilgiMesaji("Satın alma işlemleri tamamlandı", "Bilgi");
                        } else {
                            bilgiMesaji("Bilet alma başarısız", "Hata");

                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
    }

    public void bilgiMesaji(String mesaj, String baslik) {
        JOptionPane.showMessageDialog(null, mesaj, baslik, JOptionPane.INFORMATION_MESSAGE);
    }
}
