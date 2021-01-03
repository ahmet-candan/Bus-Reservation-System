import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewUser extends  JFrame{
    private JPanel panel3;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JButton girişEkranınınaGeçButton;
    private JTextField textField3;

    public NewUser() {
        add(panel3);
        setSize(800,500);
        setTitle("Otobüs Bilet Sistemi");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
}
