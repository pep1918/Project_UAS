
package Service;

import Model.Model_BarangKeluar;
import java.util.List;

public interface Service_BarangKeluar {
    void tambahData     (Model_BarangKeluar modkeluar);
    
    Model_BarangKeluar getByid (String id);
    List<Model_BarangKeluar> getData();
    List<Model_BarangKeluar> pencarian(String id);
    String nomor();
    
    
}
