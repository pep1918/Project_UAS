
package Service;

import Model.Model_Barang;
import java.util.List;

public interface Service_Barang {
    void tambahData     (Model_Barang mobar);
    void perbaruiData   (Model_Barang mobar);
    void hapusData      (Model_Barang mobar);
    
    Model_Barang getByid (String id);
    
    List<Model_Barang> getData();
    List<Model_Barang> getData2();
    
    List<Model_Barang> pencarian(String id);
    List<Model_Barang> pencarian2(String id);
    
    String nomor();
    String nomor2();
}
