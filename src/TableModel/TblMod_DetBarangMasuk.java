package TableModel;

import Model.Model_DetBarangMasuk;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TblMod_DetBarangMasuk extends AbstractTableModel{

    private List<Model_DetBarangMasuk> list = new ArrayList<>();
    
    public void tambahData (Model_DetBarangMasuk mod_detmasuk) {
        list.add(mod_detmasuk);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
    }
    
    public void perbaruiData (int row, Model_DetBarangMasuk mod_detmasuk) {
        list.set(row, mod_detmasuk);
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
     
    public void setData(List<Model_DetBarangMasuk> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_DetBarangMasuk mod_detmasuk) {
        list.set (index, mod_detmasuk);
        fireTableRowsUpdated (index, index);
    }
   
    public Model_DetBarangMasuk getData(int index) {
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
            case 0: return list.get(rowIndex).getMdl_masuk().getNo_masuk();
            case 1: return list.get(rowIndex).getMdl_barang().getKode_barang();
            case 2: return list.get(rowIndex).getMdl_barang().getNama_barang();
            case 3: return list.get(rowIndex).getMdl_barang().getHarga();
            case 4: return list.get(rowIndex).getJml_masuk();
            case 5: return list.get(rowIndex).getSubtotal_masuk();
            
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "No Masuk";
            case 1: return "Kode Barang";
            case 2: return "Nama Barang";
            case 3: return "Harga";
            case 4: return "Jumlah Masuk";
            case 5: return "Subtotal";
            
            default: return null;
        }
    }
}
