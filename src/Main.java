
import javax.swing.*;

public class Main extends Thread {
    private Thread t;
    public static String threadName;

    Main(String name) {
        threadName = name;
        System.out.println("Oluşturuluyor " +  threadName );

    }
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                Main T1 = new Main( "Otobüs Giriş Paneli");
                long startTime = System.nanoTime();
                T1.start();
                System.out.println("Çalıştırılıyor " +  threadName );
                Main T2 = new Main( "Zamanlayıcı");
                T2.start();
                System.out.println("Çalıştırılıyor " +  threadName );

                AnaEkran ae=new AnaEkran();
                ae.setLocationRelativeTo(null);
                ae.setVisible(true);
                long stopTime = System.nanoTime();
                System.out.println((stopTime - startTime)/60);

            }
        });

    }
}
