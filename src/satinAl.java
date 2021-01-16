import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class satinAl extends JFrame{
    private JButton satin_al;
    private JPanel panel9;
    private JTextField textField1;

    private Connection con = null;
    private PreparedStatement preparedStatement = null;


    public satinAl() {

        add(panel9);
        setSize(800, 500);
        setTitle("Otobüs Bilet Sistemi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        satin_al.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UserControlPanel a = new UserControlPanel();
                System.out.println( a.selected);

                String koltuk =textField1.getText();
                String databaseUrl = "jdbc:mysql://localhost:3306/bus?useUnicode=true&characterEncoding=utf-8";

                try {
                    con = DriverManager.getConnection(databaseUrl,"root","");
                    String sorgu0 = "SELECT koltuk_no FROM bilet_al WHERE koltuk_no = ?";

                    preparedStatement = con.prepareStatement(sorgu0);
                    preparedStatement.setString(1,koltuk);

                    ResultSet rs = preparedStatement.executeQuery();

                    if (rs.next()){
                        bilgiMesaji("Bu koltuk satın alınmış,başka seçin","Bilgi");
                    }

                    else{
                        String sorgu1 = "INSERT INTO bilet_al (koltuk_no) VALUES (?)";

                        preparedStatement = con.prepareStatement(sorgu1);
                        preparedStatement.setString(1,koltuk);
                        System.out.println("2. sorgu doğru");

                       int x = preparedStatement.executeUpdate();

                       if (x==1){
                           bilgiMesaji("Satın alma işlemleri tamamlandı","Bilgi");
                       }

                       else {
                           bilgiMesaji("Bilet alma başarısız","Hata");
                       }

                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                /*String sorgu2 = "SELECT kullanici_bilgileri.ad, kullanici_bilgileri.soyad, terminal.otobus_no,terminal.otobus_adi,terminal.kalkis_noktasi,terminal.varis_noktasi,terminal.sefer_tarihi,terminal.sefer_saati,terminal.ucret FROM kullanici_bilgileri,terminal WHERE kullanici_bilgileri.id=terminal.id ORDER BY kullanici_bilgileri.id";
                try {
                    preparedStatement = con.prepareStatement(sorgu2);
                    ResultSet rs2 = preparedStatement.executeQuery();
                    while (rs2.next()){
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }*/
                
            }
        });
    }
    public void bilgiMesaji(String mesaj,String baslik){
        JOptionPane.showMessageDialog(null,mesaj,baslik,JOptionPane.INFORMATION_MESSAGE);
    }
}
