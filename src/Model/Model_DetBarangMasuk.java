package Model;

import java.util.ArrayList;
import java.util.List;


public class Model_DetBarangMasuk {

    private Model_BarangMasuk mdl_masuk;
    private Model_Barang mdl_barang;
    private int jml_masuk;
    private Long subtotal_masuk;

    public Model_BarangMasuk getMdl_masuk() {
        return mdl_masuk;
    }

    public void setMdl_masuk(Model_BarangMasuk mdl_masuk) {
        this.mdl_masuk = mdl_masuk;
    }

    public Model_Barang getMdl_barang() {
        return mdl_barang;
    }

    public void setMdl_barang(Model_Barang mdl_barang) {
        this.mdl_barang = mdl_barang;
    }

    public int getJml_masuk() {
        return jml_masuk;
    }

    public void setJml_masuk(int jml_masuk) {
        this.jml_masuk = jml_masuk;
    }

    public Long getSubtotal_masuk() {
        return subtotal_masuk;
    }

    public void setSubtotal_masuk(Long subtotal_masuk) {
        this.subtotal_masuk = subtotal_masuk;
    }

}
