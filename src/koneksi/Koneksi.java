package koneksi;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {

    private Connection con;
    private String url = "jdbc:mysql://localhost:3306/penjualan_laptop";
    private String user = "root";
    private String pass = "";

    public Koneksi() {
        if (con == null) {
            try {
                DriverManager.registerDriver(new Driver());
               con = DriverManager.getConnection(url, user, pass);
 
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }

    public Connection getCon() {
        return con;
    }
    
    
    public static void main(String[] args) {
       new Koneksi();
    }

}
