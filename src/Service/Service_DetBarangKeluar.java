
package Service;

import Model.Model_DetBarangKeluar;
import java.util.List;

public interface Service_DetBarangKeluar {
    void tambahData     (Model_DetBarangKeluar mod_detkeluar);
    void sumTotal       (Model_DetBarangKeluar mod_detkeluar);
    void hapusSementara (Model_DetBarangKeluar mod_detkeluar);
    
    Model_DetBarangKeluar getByid (String id);
    List<Model_DetBarangKeluar> getData(String id);
    
    List<Model_DetBarangKeluar> pencarian(String id);
    boolean validasiStok(Model_DetBarangKeluar mod_detkeluar);
}
