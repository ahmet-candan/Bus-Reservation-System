import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class AnaEkran extends JFrame {
    private JPanel panel;
    private JButton adminGirişiButton;
    private JButton kullanıcıGirişiButton;


    public AnaEkran() {

        add(panel);
        setSize(800,500);
        setTitle("Otobüs Bilet Sistemi");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        adminGirişiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminLogin al = new AdminLogin();
                al.setLocationRelativeTo(null);
                al.setVisible(true);

            }
        });
        kullanıcıGirişiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UserLogin ul = new UserLogin();
                ul.setLocationRelativeTo(null);
                ul.setVisible(true);
            }
        });
    }
}
