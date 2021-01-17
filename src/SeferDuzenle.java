import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;

public class SeferDuzenle extends JFrame {
    String otobusno = "";
    String otobusadi = "";
    String kalkis_n = "";
    String varis_n = "";
    String sefertarihi = "";
    String sefersaati = "";
    String fiyat = "";
    JDateChooser tarih = new JDateChooser();


    private JPanel panel8;
    private JPanel panel7;
    private JTextField otobusNo;
    private JButton otobüsüBulButton;
    private JPanel tarihpaneli;
    private JTextField a;
    private JTextField b;
    private JTextField c;
    private JTextField d;
    private JTextField f;
    private JTextField g;
    private JButton kayit;
    private Connection con = null;
    private PreparedStatement preparedStatement = null;
    private final Statement statement = null;

    public SeferDuzenle() {

        tarih.setDateFormatString("dd/MM/yyyy");
        tarihpaneli.add(tarih);

        add(panel8);
        setSize(800, 500);
        setTitle("Otobüs Bilet Sistemi");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        otobüsüBulButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String otobus_no = otobusNo.getText();
                String databaseUrl = "jdbc:mysql://localhost:3306/bus?useUnicode=true&characterEncoding=utf-8";

                try {
                    con = DriverManager.getConnection(databaseUrl, "root", "");

                    String otobus_sorgu = "SELECT otobus_no FROM terminal WHERE otobus_no = ?";
                    preparedStatement = con.prepareStatement(otobus_sorgu);
                    preparedStatement.setString(1, otobus_no);

                    ResultSet rs = preparedStatement.executeQuery();

                    if (rs.next()) {

                        String sorgu = "SELECT * FROM terminal where otobus_no = ?";
                        preparedStatement = con.prepareStatement(sorgu);
                        preparedStatement.setString(1, otobus_no);
                        ResultSet ss = preparedStatement.executeQuery();

                        while (ss.next()) {
                            otobusno = ss.getString("otobus_no");
                            otobusadi = ss.getString("otobus_adi");
                            kalkis_n = ss.getString("kalkis_noktasi");
                            varis_n = ss.getString("varis_noktasi");
                            sefertarihi = ss.getString("sefer_tarihi");
                            sefersaati = ss.getString("sefer_saati");
                            fiyat = ss.getString("ucret");
                        }

                        a.setText(otobusno);
                        b.setText(otobusadi);
                        c.setText(kalkis_n);
                        d.setText(varis_n);
                        f.setText(sefersaati);
                        g.setText(fiyat);

                    } else {
                        bilgiMesaji("Böyle bir kayıt bulunamadı", "Uyarı");
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });


        kayit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String databaseUrl = "jdbc:mysql://localhost:3306/bus?useUnicode=true&characterEncoding=utf-8";
                try {

                    con = DriverManager.getConnection(databaseUrl, "root", "");

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String ds = sdf.format(tarih.getDate());

                    String otobusNo = a.getText();
                    String otobusAdi = b.getText();
                    String kalkisNoktasi = c.getText();
                    String varisNoktasi = d.getText();
                    String seferSaati = f.getText();
                    String fiyat = g.getText();

                    String duzenleme_sorgusu = "UPDATE terminal SET otobus_no = ?, otobus_adi=?,kalkis_noktasi = ?, varis_noktasi = ?,sefer_tarihi = ?, sefer_saati = ?,ucret = ? WHERE otobus_no = ?";
                    preparedStatement = con.prepareStatement(duzenleme_sorgusu);
                    preparedStatement.setString(1, otobusNo);
                    preparedStatement.setString(2, otobusAdi);
                    preparedStatement.setString(3, kalkisNoktasi);
                    preparedStatement.setString(4, varisNoktasi);
                    preparedStatement.setString(5, ds);
                    preparedStatement.setString(6, seferSaati);
                    preparedStatement.setString(7, fiyat);
                    preparedStatement.setString(8, otobusNo);

                    int x = preparedStatement.executeUpdate();

                    if (x == 1) {
                        bilgiMesaji("Düzenleme işlemi tamamlandı", "Bilgi");
                    } else {
                        bilgiMesaji("Hata işlem yaptınız", "Hata");
                    }


                } catch (Exception ex) {
                    bilgiMesaji("Eksik bilgi girdiniz", "Hata");
                }


            }
        });
    }

    public void bilgiMesaji(String mesaj, String baslik) {
        JOptionPane.showMessageDialog(null, mesaj, baslik, JOptionPane.INFORMATION_MESSAGE);
    }
}
