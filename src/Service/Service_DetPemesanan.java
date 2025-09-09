
package Service;

import Model.Model_DetPemesanan;
import java.util.List;

public interface Service_DetPemesanan {
    void tambahData     (Model_DetPemesanan mod_detpsn);
    void sumTotal       (Model_DetPemesanan mod_detpsn);
    void hapusSementara (Model_DetPemesanan mod_detpsn);
    
    Model_DetPemesanan getByid (String id);
    List<Model_DetPemesanan> getData(String id);
    
    List<Model_DetPemesanan> pencarian(String id);
    
}
