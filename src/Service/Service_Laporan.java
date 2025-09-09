
package Service;

import Model.Model_Barang;
import java.util.List;
import javax.swing.JPanel;

public interface Service_Laporan {
    void lapSuratPemesananBarang                (String no);
    void lapSuratPemesananBarangPerTransaksi    (JPanel jp, String no);
    void lapPemesananBarangPeriode              (JPanel jp, String tgl1, String tgl2);
    void lapPemesananBarangPerbulan             (JPanel jp, String dt1, int dt2);
    void lapPemesananBarangPerDistributor       (JPanel jp, String id_dis);
    
    void lapBarangMasuk                     (String no);
    void lapBarangMasukPerTransaksi         (JPanel jp, String no);
    void lapBarangMasukPeriode              (JPanel jp, String tgl1, String tgl2);
    void lapBarangMasukPerbulan             (JPanel jp, String dt1, int dt2);
    void lapBarangMasukPerDistributor       (JPanel jp, String id_dis);
    
    void lapBarangKeluar                    (String no);
    void lapBarangKeluarPerTransaksi        (JPanel jp, String no);
    void lapBarangKeluarPeriode             (JPanel jp, String tgl1, String tgl2);
    void lapBarangKeluarPerbulan            (JPanel jp, String dt1, int dt2);
    
    void lapDataBarang                      (JPanel jp);
    void lapTransaksiStokBarang             (JPanel jp);

    void lapDataPengguna                    (JPanel jp);
    void lapDataDistributor                 (JPanel jp);
    
    
    
}
