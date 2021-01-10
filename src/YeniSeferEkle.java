
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;

public class YeniSeferEkle extends JFrame{
    private JButton kayit;
    private JPanel panel7;
    private JTextField kalkis_noktasi;
    private JTextField varis_noktasi;
    private JTextField otobus_no;
    private JTextField toplam_fiyat;
    private JTextField sefer_saati;
    private JPanel tarihpaneli;
    private JTextField otobus_adi;
    private Connection con = null;
    private PreparedStatement preparedStatement = null;


    JDateChooser tarih = new JDateChooser();

    public YeniSeferEkle() {

        tarih.setDateFormatString("dd/MM/yyyy");
        tarihpaneli.add(tarih);

        add(panel7);
        setSize(800,500);
        setTitle("Otobüs Bilet Sistemi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        kayit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String otobusNo = otobus_no.getText();
                String otobusAdi = otobus_adi.getText();
                String kalkisNoktasi = kalkis_noktasi.getText();
                String varisNoktasi = varis_noktasi.getText();
                String seferSaati = sefer_saati.getText();
                String fiyat = toplam_fiyat.getText();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dt = sdf.format(tarih.getDate());

                String databaseUrl = "jdbc:mysql://localhost:3306/bus?useUnicode=true&characterEncoding=utf-8";
                try {
                    con = DriverManager.getConnection(databaseUrl, "root", "");

                    String kontrolSorgu = "SELECT otobus_no FROM terminal WHERE otobus_no = ? ";
                    preparedStatement = con.prepareStatement(kontrolSorgu);
                    preparedStatement.setString(1, otobusNo);

                    ResultSet rs = preparedStatement.executeQuery();

                    if (rs.next()) {
                        bilgiMesaji("Bu kullanıcı zaten bulunuyor ", "Bilgi");
                    }
                    else {
                    String sorgu = "INSERT INTO terminal (otobus_no,otobus_adi,kalkis_noktasi,varis_noktasi,sefer_tarihi,sefer_saati,ucret) VALUES (?,?,?,?,?,?,?)";

                    preparedStatement = con.prepareStatement(sorgu);
                    preparedStatement.setString(1, otobusNo);
                    preparedStatement.setString(2, otobusAdi);
                    preparedStatement.setString(3, kalkisNoktasi);
                    preparedStatement.setString(4, varisNoktasi);
                    preparedStatement.setString(5, dt);
                    preparedStatement.setString(6, seferSaati);
                    preparedStatement.setString(7, fiyat);

                    int x = preparedStatement.executeUpdate();

                    if (x == 1) {
                        bilgiMesaji("Kaydınız Yapıldı", "Bilgilerndirme");
                    } else {
                        bilgiMesaji("Kayıt başarısız", "Bilgilendirme ");
                    }
                }

                    } catch(SQLException throwables){
                        throwables.printStackTrace();
                    }
                }

        });
    }

    public void bilgiMesaji(String mesaj,String baslik){
        JOptionPane.showMessageDialog(null,mesaj,baslik,JOptionPane.INFORMATION_MESSAGE);
    }
}
