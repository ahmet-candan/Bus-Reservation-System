import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class satinAl extends JFrame{
    private JButton button1;
    private JPanel panel9;
    private JTextField textField1;

    private Connection con = null;
    private PreparedStatement preparedStatement = null;

    public satinAl() {

        add(panel9);
        setSize(800, 500);
        setTitle("Otobüs Bilet Sistemi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UserControlPanel a = new UserControlPanel();
                System.out.println( a.selected);

                String koltuk =textField1.getText();

                String sorgu = "SELECT * FROM terminal where id = ?";

                String databaseUrl = "jdbc:mysql://localhost:3306/bus?useUnicode=true&characterEncoding=utf-8";

                
            }
        });
    }
}
