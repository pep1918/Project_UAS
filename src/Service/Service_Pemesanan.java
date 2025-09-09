
package Service;

import Model.Model_Pemesanan;
import java.util.List;

public interface Service_Pemesanan {
    void tambahData     (Model_Pemesanan modpem);
    
    Model_Pemesanan getByid (String id);
    List<Model_Pemesanan> getData();
    List<Model_Pemesanan> pencarian(String id);
    String nomor();
    
}
