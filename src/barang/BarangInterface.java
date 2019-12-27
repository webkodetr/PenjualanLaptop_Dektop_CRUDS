package barang;

import javax.swing.JTable;

/**
 *
 * @author codetr
 */
public interface BarangInterface {
    public void read(JTable jt);
    public void create(Barang b);
    public void update(Barang b);
    public void delete(int id);
    public void search(JTable jt, String nama);
}
