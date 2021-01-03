import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLogin extends JFrame{
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton girişYapButton;
    private JButton kayıtOlButton;
    private JButton adminButton;

    public UserLogin() {
        add(panel1);
        setSize(800,500);
        setTitle("Otobüs Bilet Sistemi");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        girişYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
}
