import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class satinAl extends JFrame{
    private JButton button1;
    private JPanel panel9;

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

            }
        });
    }
}
