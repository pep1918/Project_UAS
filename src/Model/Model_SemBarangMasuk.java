package Model;

import java.util.ArrayList;
import java.util.List;


public class Model_SemBarangMasuk {

    private Model_Barang mdl_barang;
    private Model_DetBarangMasuk mdl_detmasuk;
    private Model_Distributor mdl_dis;

    public Model_Distributor getMdl_dis() {
        return mdl_dis;
    }

    public void setMdl_dis(Model_Distributor mdl_dis) {
        this.mdl_dis = mdl_dis;
    }

    public Model_Barang getMdl_barang() {
        return mdl_barang;
    }

    public void setMdl_barang(Model_Barang mdl_barang) {
        this.mdl_barang = mdl_barang;
    }

    public Model_DetBarangMasuk getMdl_detmasuk() {
        return mdl_detmasuk;
    }

    public void setMdl_detmasuk(Model_DetBarangMasuk mdl_detmasuk) {
        this.mdl_detmasuk = mdl_detmasuk;
    }

    
}
