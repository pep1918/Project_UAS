package TableModel;

import Model.Model_DetPemesanan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TblMod_DetPemesanan extends AbstractTableModel{

    private List<Model_DetPemesanan> list = new ArrayList<>();
    
    public void tambahData (Model_DetPemesanan mod_detpsn) {
        list.add(mod_detpsn);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
    }
    
    public void perbaruiData (int row, Model_DetPemesanan mod_detpsn) {
        list.set(row, mod_detpsn);
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
     
    public void setData(List<Model_DetPemesanan> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_DetPemesanan mod_detpsn) {
        list.set (index, mod_detpsn);
        fireTableRowsUpdated (index, index);
    }
   
    public Model_DetPemesanan getData(int index) {
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
            case 0: return list.get(rowIndex).getMdl_pesan().getNo_pesan();
            case 1: return list.get(rowIndex).getMdl_barang().getKode_barang();
            case 2: return list.get(rowIndex).getMdl_barang().getNama_barang();
            case 3: return list.get(rowIndex).getMdl_barang().getHarga();
            case 4: return list.get(rowIndex).getJml_pesan();
            case 5: return list.get(rowIndex).getSubtotal();
            case 6: return list.get(rowIndex).getStatus();
            
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "No Pesan";
            case 1: return "Kode Barang";
            case 2: return "Nama Barang";
            case 3: return "Harga";
            case 4: return "Jumlah Pesan";
            case 5: return "Subtotal";
            case 6: return "Keterangan";
            
            default: return null;
        }
    }
}
