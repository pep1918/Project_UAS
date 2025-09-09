
package Service;

import Model.Model_SemBarangMasuk;
import java.util.List;

public interface Service_SemBarangMasuk {
    void tambahData     (Model_SemBarangMasuk modsem);
    void perbaruiData   (Model_SemBarangMasuk modsem);
    void hapusData      (Model_SemBarangMasuk modsem);
    
    Model_SemBarangMasuk getByid (String id);
    List<Model_SemBarangMasuk> getData();
    
}
