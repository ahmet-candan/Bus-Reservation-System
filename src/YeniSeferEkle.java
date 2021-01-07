import jdk.internal.icu.text.UnicodeSet;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

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

    public YeniSeferEkle() {

        add(panel7);
        setSize(800,500);
        setTitle("Otobüs Bilet Sistemi");

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);


        panel7.add(datePicker);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
