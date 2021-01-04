import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserLogin extends JFrame{
    private JPanel panel1;
    private JTextField user_login;
    private JTextField pass;
    private JButton girişYapButton;
    private JButton kayıtOlButton;
    private JButton adminButton;

    private Connection con = null;
    private PreparedStatement preparedStatement = null;

    public UserLogin() {
        add(panel1);
        setSize(800,500);
        setTitle("Otobüs Bilet Sistemi");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        girişYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = user_login.getText();
                String parola = pass.getText();

                String url = "jdbc:mysql://localhost:3306/bus";


                try {
                    String userid = null;
                    String passid = null;

                    con = DriverManager.getConnection(url,"root","");
                    String sorgu = "Select username,parola from kullanici_bilgileri where `username` = ? and `parola` = ?";
                    preparedStatement = con.prepareStatement(sorgu);
                    preparedStatement.setString(1,username);
                    preparedStatement.setString(2,parola);
                    ResultSet rs = preparedStatement.executeQuery();

                    while (rs.next()){
                         userid = rs.getString("username");
                         passid = rs.getString("parola");
                        System.out.println(userid);

                    }

                    if (userid.equals(username)&&passid.equals(parola)){
                        System.out.println("buraya geldim");
                        bilgiMesajı("Hoşgeldin "+userid+" !", "Bilgi Mesajı");
                        dispose();
                        UserControlPanel ucp = new UserControlPanel();
                        ucp.setLocationRelativeTo(null);
                        ucp.setVisible(true);

                    }

                    else{
                        System.out.println("buraya geldim ELSE");
                        bilgiMesajı("Giriş bilgileri yanlış yeni bir hesap açabilirsin","Bilgi Mesajı");
                    }
                    System.out.println("buraya geldim else dışı");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
               AdminLogin al = new AdminLogin();
               al.setLocationRelativeTo(null);
               al.setVisible(true);

            }
        });
        kayıtOlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                NewUser nu = new NewUser();
                nu.setLocationRelativeTo(null);
                nu.setVisible(true);



            }
        });
    }
    public void bilgiMesajı(String message, String tittle) {
        JOptionPane.showMessageDialog(null, message, tittle, JOptionPane.INFORMATION_MESSAGE);
    }
}
