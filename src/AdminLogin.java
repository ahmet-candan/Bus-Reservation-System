import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class AdminLogin extends JFrame{
    private JPanel panel2;
    private JButton girişYapButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton userButton;
    //URL url = getClass().getResource("C:\\Users\\Lenovo\\Desktop\\Bus Reservation\\src\\Images");

    public AdminLogin() {

        add(panel2);
        setSize(800,500);
        setTitle("Otobüs Bilet Sistemi");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        girişYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


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

            }
        });
    }
}
