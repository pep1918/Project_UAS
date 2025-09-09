
package Service;

import Model.Model_Pengguna;
import java.util.List;

public interface Service_Pengguna {
    void tambahData     (Model_Pengguna moduser);
    void perbaruiData   (Model_Pengguna moduser);
    void hapusData      (Model_Pengguna moduser);
    void risetpswd      (Model_Pengguna moduser);
        
    Model_Pengguna getByid (String id);
    
    List<Model_Pengguna> getData();
    List<Model_Pengguna> pencarian(String id);
    
    String nomor();
}
