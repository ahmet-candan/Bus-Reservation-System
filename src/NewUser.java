import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class NewUser extends JFrame {
    private static Connection con = null;
    private static PreparedStatement preparedStatement = null;
    private JPanel panel3;
    private JTextField isimField;
    private JTextField soyisimField;
    private JButton kayıt;
    private JButton girişEkranınınaGeçButton;
    private JTextField parolaField;
    private JTextField usernameField;
    private JTextField emailField;


    public NewUser() {
        add(panel3);
        setSize(800, 500);
        setTitle("Otobüs Bilet Sistemi");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        kayıt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String isim = isimField.getText();
                String soyisim = soyisimField.getText();
                String username = usernameField.getText();
                String parola = parolaField.getText();
                String email = emailField.getText();

                String databaseUrl = "jdbc:mysql://localhost:3306/bus?useUnicode=true&characterEncoding=utf-8";


                try {
                    con = DriverManager.getConnection(databaseUrl, "root", "");
                    System.out.println("bağlantı başarılı");

                    setPreparedStatement(isim, soyisim, username, parola, email);

                    // prapere statement kullandık bu uzun yola gerek kalmadı
                    /*String insertQuery = "INSERT INTO kullanici_bilgileri (ad,soyad,username,parola,email) VALUES (" + "'" +isim+ "'," + "'" +soyisim+"',"+ "'"+ username +"'," +"'"+parola+"',"+"'"+email+"')";
                    statement = con.createStatement();
                    int x = statement.executeUpdate(insertQuery);
                    System.out.println(x);*/


                } catch (SQLException ex) {
                    System.out.println(ex);
                }

            }
        });
        girişEkranınınaGeçButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserLogin ul = new UserLogin();
                ul.setLocationRelativeTo(null);
                ul.setVisible(true);

            }
        });
    }

    private void setPreparedStatement(String isim, String soyisim, String username, String parola, String email) throws SQLException {

        String kontrolSorgu = "SELECT * FROM kullanici_bilgileri WHERE username= ? and parola = ? ";
        preparedStatement = con.prepareStatement(kontrolSorgu);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, parola);
        ResultSet rs = preparedStatement.executeQuery();
        System.out.println(rs);

        if (rs.next()) {
            bilgiMesaji("Bu kullanıcı zaten bulunuyor ", "Bilgi");
        } else {
            String sorgu = "INSERT INTO kullanici_bilgileri (ad,soyad,username,parola,email) VALUES (?,?,?,?,?)";

            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, isim);
            preparedStatement.setString(2, soyisim);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, parola);
            preparedStatement.setString(5, email);
            int x = preparedStatement.executeUpdate();

            if (x == 1) {
                bilgiMesaji("Kaydınız Yapıldı", "Bilgilerndirme");
            } else {
                bilgiMesaji("Kayıt başarısız", "Bilgilendirme ");
            }
        }

    }

    public void bilgiMesaji(String mesaj, String baslik) {
        JOptionPane.showMessageDialog(null, mesaj, baslik, JOptionPane.INFORMATION_MESSAGE);
    }


}

