
package Service;

import Model.Model_DetBarangMasuk;
import java.util.List;

public interface Service_DetBarangMasuk {
    void tambahData     (Model_DetBarangMasuk mod_detmasuk);
    void sumTotal       (Model_DetBarangMasuk mod_detmasuk);
    void hapusSementara (Model_DetBarangMasuk mod_detmasuk);
    
    Model_DetBarangMasuk getByid (String id);
    List<Model_DetBarangMasuk> getData(String id);
    
}
