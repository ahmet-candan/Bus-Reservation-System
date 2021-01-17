import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class satinAl extends JFrame{
    private JButton satin_al;
    private JPanel panel9;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;

    private Connection con = null;
    private PreparedStatement preparedStatement = null;

    String isim = "null";
    String soyisim = "null";


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

                        UserLogin userLogin = new UserLogin();


                        String sorgu9 = "SELECT * From kullanici_bilgileri WHERE username = ?";

                        preparedStatement = con.prepareStatement(sorgu9);
                        preparedStatement.setString(1,userLogin.b);

                        ResultSet rm = preparedStatement.executeQuery();

                        while (rm.next()){
                             isim = rm.getString("ad");
                             soyisim = rm.getString("soyad");

                        }
                        System.out.println(isim);

                        String sorgu1 = "INSERT INTO bilet_al (ad,soyisim,otobus_no,otobus_adi,kalkis_noktasi,varis_noktasi,sefer_tarihi,sefer_saati,ucret,koltuk_no) VALUES ( ?,?,?,?,?,?,?,?,?,?)";

                        preparedStatement = con.prepareStatement(sorgu1);
                        preparedStatement.setString(1,isim);
                        preparedStatement.setString(2,soyisim);
                        preparedStatement.setString(3, a.selected1);
                        preparedStatement.setString(4, a.selected2);
                        preparedStatement.setString(5, a.selected3);
                        preparedStatement.setString(6, a.selected4);
                        preparedStatement.setString(7, a.selected5);
                        preparedStatement.setString(8, a.selected6);
                        preparedStatement.setString(9, a.selected7);
                        preparedStatement.setString(10, koltuk);



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

                /*String sorgu2 = "INSERT INTO bilet_al (ad,soyisim,otobus_no,otobus_adi,kalkis_noktasi,varis_noktasi,sefer_tarihi,sefer_saati,ucret,koltuk_no) SELECT kullanici_bilgileri.ad, kullanici_bilgileri.soyad, terminal.otobus_no,terminal.otobus_adi,terminal.kalkis_noktasi,terminal.varis_noktasi,terminal.sefer_tarihi,terminal.sefer_saati,terminal.ucret,bilet_al.koltuk_no FROM kullanici_bilgileri,terminal,bilet_al WHERE kullanici_bilgileri.id=bilet_al.koltuk_no ORDER BY kullanici_bilgileri.id";

                try {
                    preparedStatement = con.prepareStatement(sorgu2);
                    preparedStatement.executeUpdate();

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
