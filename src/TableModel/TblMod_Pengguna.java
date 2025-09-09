package TableModel;

import Model.Model_Pengguna;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TblMod_Pengguna extends AbstractTableModel{

    private List<Model_Pengguna> list = new ArrayList<>();
    
    public void tambahData (Model_Pengguna modis) {
        list.add(modis);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Insert Data Berhasil");
    }
    
    public void perbaruiData (int row, Model_Pengguna modis) {
        list.set(row, modis);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Update Data Berhasil");
    }

    public void risetpswd (int row, Model_Pengguna modis) {
        list.set(row, modis);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Riset Pswd Berhasil");
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
     
    public void setData(List<Model_Pengguna> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_Pengguna modis) {
        list.set (index, modis);
        fireTableRowsUpdated (index, index);
    }
   
    public Model_Pengguna getData(int index) {
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
            case 0: return list.get(rowIndex).getId_pengguna();
            case 1: return list.get(rowIndex).getNama_pengguna();
            case 2: return list.get(rowIndex).getUsername();
            case 3: return list.get(rowIndex).getTelp_pengguna();
            case 4: return list.get(rowIndex).getAlamat_pengguna();
            case 5: return list.get(rowIndex).getLevel();
            case 6: return list.get(rowIndex).getPassword();
            
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID Pengguna";
            case 1: return "Nama Pengguna";
            case 2: return "Username";
            case 3: return "Telepon";
            case 4: return "Alamat";
            case 5: return "Level";
            case 6: return "Password";
            
            default: return null;
        }
    }
}
