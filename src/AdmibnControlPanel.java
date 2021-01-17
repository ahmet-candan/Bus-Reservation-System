import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.Result;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class AdmibnControlPanel extends JFrame{
    private JPanel panel6;
    private JButton yeniSeferEkle;
    private JButton biletleriGörüntüleButton;
    private JTable table1;
    private JButton seferiDüzenleButton;
    private JButton seferiSilButton1;
    private JButton seferleriYenileButton;
    private JButton çıkışYapButton;

    private Connection con = null;
    private PreparedStatement preparedStatement = null;

    public AdmibnControlPanel() {


        add(panel6);

        setSize(1300,800);
        setTitle("Otobüs Bilet Sistemi");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createTable();

        yeniSeferEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                YeniSeferEkle yse = new YeniSeferEkle();
                yse.setLocationRelativeTo(null);
                yse.setVisible(true);

            }
        });

        seferiDüzenleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SeferDuzenle sd = new SeferDuzenle();
                sd.setLocationRelativeTo(null);
                sd.setVisible(true);
            }
        });

        seferleriYenileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTable();
            }
        });


        seferiSilButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                int rows = table1.getSelectedRow();
                String selected = model.getValueAt(rows, 0).toString();
                model.removeRow(rows);
                String sorgu = "DELETE FROM terminal WHERE id = ? ";
                try {
                    preparedStatement = con.prepareStatement(sorgu);
                    preparedStatement.setString(1, selected);
                    preparedStatement.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    bilgiMesaji("Herhangi bir sefer seçilmedi!","Hata");
                }
            }

        });
        çıkışYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bilgiMesaji("Admin hesabından çıkılıyor...","Bilgi");
                dispose();
                AnaEkran ae = new AnaEkran();
                ae.setLocationRelativeTo(null);
                ae.setVisible(true);
            }
        });
        biletleriGörüntüleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTable2();
            }
        });
    }
    public void createTable() {
        String databaseUrl = "jdbc:mysql://localhost:3306/bus?useUnicode=true&characterEncoding=utf-8";
        try {
            con = DriverManager.getConnection(databaseUrl,"root","");
            String sorgu = "SELECT * FROM terminal";
            preparedStatement = con.prepareStatement(sorgu);
            ResultSet rs = preparedStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void createTable2() {
        String databaseUrl = "jdbc:mysql://localhost:3306/bus?useUnicode=true&characterEncoding=utf-8";
        try {
            con = DriverManager.getConnection(databaseUrl,"root","");
            String sorgu0 = "SELECT * FROM bilet_al";
            preparedStatement = con.prepareStatement(sorgu0);
            ResultSet rs = preparedStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void bilgiMesaji(String mesaj,String baslik){
        JOptionPane.showMessageDialog(null,mesaj,baslik,JOptionPane.INFORMATION_MESSAGE);
    }
}
