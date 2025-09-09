package TableModel;

import Model.Model_SemPemesanan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TblMod_SemPemesanan extends AbstractTableModel{

    private List<Model_SemPemesanan> list = new ArrayList<>();
    
    public void tambahData (Model_SemPemesanan modsem) {
        list.add(modsem);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Insert Data Berhasil");
    }
    
    public void perbaruiData (int row, Model_SemPemesanan modsem) {
        list.set(row, modsem);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Perbarui Data Berhasil");
    }
    
    public void hapusData (int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
        JOptionPane.showMessageDialog(null, "Delete Data Berhasil");
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
     
    public void setData(List<Model_SemPemesanan> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_SemPemesanan modsem) {
        list.set (index, modsem);
        fireTableRowsUpdated (index, index);
    }
   
    public Model_SemPemesanan getData(int index) {
        return list.get(index);
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return list.get(rowIndex).getMdl_barang().getKode_barang();
            case 1: return list.get(rowIndex).getMdl_barang().getNama_barang();
            case 2: return list.get(rowIndex).getMdl_barang().getHarga();
            case 3: return list.get(rowIndex).getMdl_detpesan().getJml_pesan();
            case 4: return list.get(rowIndex).getMdl_detpesan().getSubtotal();
            
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Kode ";
            case 1: return "Nama";
            case 2: return "Harga";
            case 3: return "Jumlah";
            case 4: return "Subtotal";
            
            default: return null;
        }
    }
}
