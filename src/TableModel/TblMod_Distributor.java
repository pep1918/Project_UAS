package TableModel;

import Model.Model_Distributor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TblMod_Distributor extends AbstractTableModel{

    private List<Model_Distributor> list = new ArrayList<>();
    
    public void tambahData (Model_Distributor modis) {
        list.add(modis);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Insert Data Berhasil");
    }
    
    public void perbaruiData (int row, Model_Distributor modis) {
        list.set(row, modis);
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
     
    public void setData(List<Model_Distributor> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_Distributor modis) {
        list.set (index, modis);
        fireTableRowsUpdated (index, index);
    }
   
    public Model_Distributor getData(int index) {
        return list.get(index);
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return list.get(rowIndex).getId_distributor();
            case 1: return list.get(rowIndex).getNama_distributor();
            case 2: return list.get(rowIndex).getTelp_distributor();
            case 3: return list.get(rowIndex).getAlamat_distributor();
            
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID Distributor";
            case 1: return "Nama Distributor";
            case 2: return "Telepon Distributor";
            case 3: return "Alamat Distributor";
            
            default: return null;
        }
    }
}
