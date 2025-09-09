package TableModel;

import Model.Model_Pemesanan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TblMod_Pemesanan extends AbstractTableModel{

    private List<Model_Pemesanan> list = new ArrayList<>();
    
    public void tambahData (Model_Pemesanan modpem) {
        list.add(modpem);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
    }
    
    public void perbaruiData (int row, Model_Pemesanan modpem) {
        list.set(row, modpem);
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
     
    public void setData(List<Model_Pemesanan> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_Pemesanan modpem) {
        list.set (index, modpem);
        fireTableRowsUpdated (index, index);
    }
   
    public Model_Pemesanan getData(int index) {
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
            case 0: return list.get(rowIndex).getNo_pesan();
            case 1: return list.get(rowIndex).getTgl_pesan();
            case 2: return list.get(rowIndex).getTotal_pesan();
            case 3: return list.get(rowIndex).getMdl_dis().getId_distributor();
            case 4: return list.get(rowIndex).getMdl_peng().getId_pengguna();
            
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "No Pemesanan";
            case 1: return "Tanggal";
            case 2: return "Total Pemesanan";
            case 3: return "ID Distributor";
            case 4: return "ID Pengguna";
            
            default: return null;
        }
    }
}
