package Model;

import java.util.ArrayList;
import java.util.List;


public class Model_DetBarangKeluar {

    private Model_BarangKeluar mdl_keluar;
    private Model_Barang mdl_barang;
    private int jml_keluar;
    private Long subtotal_keluar;

    public Model_BarangKeluar getMdl_keluar() {
        return mdl_keluar;
    }

    public void setMdl_keluar(Model_BarangKeluar mdl_keluar) {
        this.mdl_keluar = mdl_keluar;
    }

    public Model_Barang getMdl_barang() {
        return mdl_barang;
    }

    public void setMdl_barang(Model_Barang mdl_barang) {
        this.mdl_barang = mdl_barang;
    }

    public int getJml_keluar() {
        return jml_keluar;
    }

    public void setJml_keluar(int jml_keluar) {
        this.jml_keluar = jml_keluar;
    }

    public Long getSubtotal_keluar() {
        return subtotal_keluar;
    }

    public void setSubtotal_keluar(Long subtotal_keluar) {
        this.subtotal_keluar = subtotal_keluar;
    }

    
}
