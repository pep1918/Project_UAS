package TableModel;

import Model.Model_DetBarangKeluar;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TblMod_DetBarangKeluar extends AbstractTableModel{

    private List<Model_DetBarangKeluar> list = new ArrayList<>();
    
    public void tambahData (Model_DetBarangKeluar mod_detkeluar) {
        list.add(mod_detkeluar);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
    }
    
    public void perbaruiData (int row, Model_DetBarangKeluar mod_detkeluar) {
        list.set(row, mod_detkeluar);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui");
    }
    
    public void hapusData (int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
     
    public void setData(List<Model_DetBarangKeluar> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_DetBarangKeluar mod_detkeluar) {
        list.set (index, mod_detkeluar);
        fireTableRowsUpdated (index, index);
    }
   
    public Model_DetBarangKeluar getData(int index) {
        return list.get(index);
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return list.get(rowIndex).getMdl_keluar().getNo_keluar();
            case 1: return list.get(rowIndex).getMdl_barang().getKode_barang();
            case 2: return list.get(rowIndex).getMdl_barang().getNama_barang();
            case 3: return list.get(rowIndex).getMdl_barang().getHarga();
            case 4: return list.get(rowIndex).getJml_keluar();
            case 5: return list.get(rowIndex).getSubtotal_keluar();
            
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "No keluar";
            case 1: return "Kode Barang";
            case 2: return "Nama Barang";
            case 3: return "Harga";
            case 4: return "Jumlah keluar";
            case 5: return "Subtotal";
            
            default: return null;
        }
    }
}
