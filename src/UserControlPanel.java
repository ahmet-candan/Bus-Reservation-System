import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserControlPanel extends JFrame {
    private JPanel panel5;
    private JButton musteri_ekle;


    public UserControlPanel() {
        add(panel5);
        setSize(800,500);
        setTitle("Otobüs Bilet Sistemi");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        musteri_ekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                AddCostumer ad = null;
                try {
                    ad = new AddCostumer();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ad.setLocationRelativeTo(null);
                ad.setVisible(true);


            }
        });
    }
}
