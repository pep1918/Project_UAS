package TableModel;

import Model.Model_Barang;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TblMod_DataBarang extends AbstractTableModel{

    private List<Model_Barang> list = new ArrayList<>();
    
    public void tambahData (Model_Barang mobar) {
        list.add(mobar);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Insert Data Berhasil");
    }
    
    public void perbaruiData (int row, Model_Barang mobar) {
        list.set(row, mobar);
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
     
    public void setData(List<Model_Barang> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_Barang mobar) {
        list.set (index, mobar);
        fireTableRowsUpdated (index, index);
    }
   
    public Model_Barang getData(int index) {
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
            case 0: return list.get(rowIndex).getKode_barang();
            case 1: return list.get(rowIndex).getNama_barang();
            case 2: return list.get(rowIndex).getSatuan();
            case 3: return list.get(rowIndex).getHarga();
            case 4: return list.get(rowIndex).getStok();
            
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Kode Barang";
            case 1: return "Nama Barang";
            case 2: return "Satuan";
            case 3: return "Harga";
            case 4: return "Stok";
            
            default: return null;
        }
    }
}
