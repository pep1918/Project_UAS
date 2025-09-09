
package Service;

import Model.Model_SemBarangKeluar;
import java.util.List;

public interface Service_SemBarangKeluar {
    void tambahData     (Model_SemBarangKeluar modsem);
    void perbaruiData   (Model_SemBarangKeluar modsem);
    void hapusData      (Model_SemBarangKeluar modsem);
    
    Model_SemBarangKeluar getByid (String id);
    List<Model_SemBarangKeluar> getData();
    
}
