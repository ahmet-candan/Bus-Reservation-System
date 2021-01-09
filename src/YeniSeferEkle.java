
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.demo.TestDateEvaluator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YeniSeferEkle extends JFrame{
    private JButton button1;
    private JPanel panel7;
    private JTextField kalkis_noktasi;
    private JTextField varis_noktasi;
    private JTextField otobus_no;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel tarihpaneli;


    JDateChooser tarih = new JDateChooser();

    public YeniSeferEkle() {

        tarihpaneli.add(tarih);

        add(panel7);
        setSize(800,500);
        setTitle("Otobüs Bilet Sistemi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
