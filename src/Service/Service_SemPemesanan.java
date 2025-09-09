
package Service;

import Model.Model_SemPemesanan;
import java.util.List;

public interface Service_SemPemesanan {
    void tambahData     (Model_SemPemesanan modsem);
    void perbaruiData   (Model_SemPemesanan modsem);
    void hapusData      (Model_SemPemesanan modsem);
    
    Model_SemPemesanan getByid (String id);
    List<Model_SemPemesanan> getData();
    
}
