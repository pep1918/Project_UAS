package TableModel;

import Model.Model_JenisBarang;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TblMod_JenisBarang extends AbstractTableModel{

    private List<Model_JenisBarang> list = new ArrayList<>();
    
    public void tambahData (Model_JenisBarang mokat) {
        list.add(mokat);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Insert Data Berhasil");
    }
    
    public void perbaruiData (int row, Model_JenisBarang mokat) {
        list.set(row, mokat);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Update Data Berhasil");
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
     
    public void setData(List<Model_JenisBarang> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_JenisBarang mokat) {
        list.set (index, mokat);
        fireTableRowsUpdated (index, index);
    }
   
    public Model_JenisBarang getData(int index) {
        return list.get(index);
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return list.get(rowIndex).getKode_jenis();
            case 1: return list.get(rowIndex).getNama_jenis();
            
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Kode";
            case 1: return "Nama";
            
            default: return null;
        }
    }
}
