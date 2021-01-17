import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminLogin extends JFrame {
    private JPanel panel2;
    private JButton girişYapButton;
    private JTextField admin_username;
    private JButton userButton;
    private JPasswordField passwordField1;
    private Connection con = null;
    private PreparedStatement preparedStatement = null;

    public AdminLogin() {

        add(panel2);
        setSize(800, 500);
        setTitle("Otobüs Bilet Sistemi");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserLogin ul = new UserLogin();
                ul.setLocationRelativeTo(null);
                ul.setVisible(true);
            }
        });
        girişYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = admin_username.getText();
                String parola = passwordField1.getText();

                String url = "jdbc:mysql://localhost:3306/bus";


                try {
                    String userid = "null";
                    String passid = "null";

                    con = DriverManager.getConnection(url, "root", "");
                    String sorgu = "Select username,parola from admin_login where `username` = ? and `parola` = ?";

                    try {
                        preparedStatement = con.prepareStatement(sorgu);
                    } catch (Exception ex) {
                        System.out.println(ex + "hata ");
                    }
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, parola);

                    ResultSet rs = preparedStatement.executeQuery();

                    while (rs.next()) {

                        userid = rs.getString("username");

                        passid = rs.getString("parola");

                    }

                    if (userid.equals(username) && passid.equals(parola)) {

                        bilgiMesajı("Hoşgeldin " + userid + "", "Bilgi Mesajı");
                        dispose();
                        AdmibnControlPanel acp = new AdmibnControlPanel();
                        acp.setLocationRelativeTo(null);
                        acp.setVisible(true);

                    }

                    else {

                        bilgiMesajı("Giriş bilgileri yanlış yeni bir hesap açabilirsin", "Bilgi Mesajı");
                    }

                } catch (SQLException throwables) {

                    throwables.printStackTrace();

                }

            }
        });

    }

    public void bilgiMesajı(String message, String tittle) {
        JOptionPane.showMessageDialog(null, message, tittle, JOptionPane.INFORMATION_MESSAGE);
    }
}
