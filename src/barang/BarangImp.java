package barang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;

/**
 *
 * @author codetr
 */
public class BarangImp implements BarangInterface {

    private Koneksi kon = new Koneksi();
    private String[] kolom = {"ID", "NAMA", "HARGA", "STOK", "DESKRIPSI"};
    private PreparedStatement ps;

    @Override
    public void read(JTable jt) {
        try {
            DefaultTableModel dtm = new DefaultTableModel(null, kolom);
            Statement s = kon.getCon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM tbl_barang");

            while (rs.next()) {
                Object[] os = new Object[5];
                os[0] = rs.getInt("id");
                os[1] = rs.getString("nama");
                os[2] = rs.getInt("harga");
                os[3] = rs.getInt("stok");
                os[4] = rs.getString("deskripsi");
                dtm.addRow(os);
            }

            jt.setModel(dtm);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public void create(Barang b) {
        try {
            ps = kon.getCon().prepareStatement("INSERT INTO tbl_barang (nama, deskripsi, harga, stok) VALUES (?,?,?,?)");
            ps.setString(1, b.getNama());
            ps.setString(2, b.getDeskripsi());
            ps.setInt(3, b.getHarga());
            ps.setInt(4, b.getStok());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil disimpan");
        } catch (SQLException ex) {
            Logger.getLogger(BarangImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Barang b) {
        try {
            ps = kon.getCon().prepareStatement("UPDATE tbl_barang SET nama=?, deskripsi=?, harga=?, stok=? WHERE id=?");
            ps.setString(1, b.getNama());
            ps.setString(2, b.getDeskripsi());
            ps.setInt(3, b.getHarga());
            ps.setInt(4, b.getStok());
            ps.setInt(5, b.getId());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Berhasil diubah");
        } catch (SQLException ex) {
            Logger.getLogger(BarangImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            ps = kon.getCon().prepareStatement("DELETE FROM tbl_barang WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil dihapus");
        } catch (SQLException ex) {
            Logger.getLogger(BarangImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void search(JTable jt, String nama) {
        try {
            DefaultTableModel dtm = new DefaultTableModel(null, kolom);
            Statement s = kon.getCon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM tbl_barang WHERE nama LIKE '%" + nama + "%'");

            while (rs.next()) {
                Object[] os = new Object[5];
                os[0] = rs.getInt("id");
                os[1] = rs.getString("nama");
                os[2] = rs.getInt("harga");
                os[3] = rs.getInt("stok");
                os[4] = rs.getString("deskripsi");
                dtm.addRow(os);
            }

            jt.setModel(dtm);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
