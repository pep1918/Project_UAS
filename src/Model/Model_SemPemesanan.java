package Model;

import java.util.ArrayList;
import java.util.List;


public class Model_SemPemesanan {

    private Model_Barang mdl_barang;
    private Model_DetPemesanan mdl_detpesan;

    public Model_Barang getMdl_barang() {
        return mdl_barang;
    }

    public void setMdl_barang(Model_Barang mdl_barang) {
        this.mdl_barang = mdl_barang;
    }

    public Model_DetPemesanan getMdl_detpesan() {
        return mdl_detpesan;
    }

    public void setMdl_detpesan(Model_DetPemesanan mdl_detpesan) {
        this.mdl_detpesan = mdl_detpesan;
    }
}
