import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdmibnControlPanel extends JFrame{
    private JPanel panel6;
    private JButton yeniSeferEkle;
    private JButton biletleriGörüntüleButton;
    private JTable table1;
    private JButton seferiDüzenleButton;
    private JButton seferiSilButton1;

    public AdmibnControlPanel() {

        add(panel6);
        setSize(800,500);
        setTitle("Otobüs Bilet Sistemi");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        yeniSeferEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                YeniSeferEkle yse = new YeniSeferEkle();
                yse.setLocationRelativeTo(null);
                yse.setVisible(true);

            }
        });
        yeniSeferEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
