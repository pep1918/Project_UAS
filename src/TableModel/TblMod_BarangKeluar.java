package TableModel;

import Model.Model_BarangKeluar;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TblMod_BarangKeluar extends AbstractTableModel{

    private List<Model_BarangKeluar> list = new ArrayList<>();
    
    public void tambahData (Model_BarangKeluar modkeluar) {
        list.add(modkeluar);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
    }
    
    public void perbaruiData (int row, Model_BarangKeluar modkeluar) {
        list.set(row, modkeluar);
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
     
    public void setData(List<Model_BarangKeluar> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_BarangKeluar modkeluar) {
        list.set (index, modkeluar);
        fireTableRowsUpdated (index, index);
    }
   
    public Model_BarangKeluar getData(int index) {
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
            case 0: return list.get(rowIndex).getNo_keluar();
            case 1: return list.get(rowIndex).getTgl_keluar();
            case 2: return list.get(rowIndex).getTotal_keluar();           
            case 3: return list.get(rowIndex).getMdl_peng().getId_pengguna();           
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "No Barang Keluar";
            case 1: return "Tanggal";
            case 2: return "Total Barang Keluar";
            case 3: return "ID Pengguna";
            
            default: return null;
        }
    }
}
