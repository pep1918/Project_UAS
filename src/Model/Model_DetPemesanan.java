package Model;

import java.util.ArrayList;
import java.util.List;


public class Model_DetPemesanan {

    private Model_Pemesanan mdl_pesan;
    private Model_Barang mdl_barang;
    private Long jml_pesan;
    private Long subtotal;
    private String status;
    private Model_Distributor mdl_dis;

    public Model_Distributor getMdl_dis() {
        return mdl_dis;
    }

    public void setMdl_dis(Model_Distributor mdl_dis) {
        this.mdl_dis = mdl_dis;
    }

    public Model_Pemesanan getMdl_pesan() {
        return mdl_pesan;
    }

    public void setMdl_pesan(Model_Pemesanan mdl_pesan) {
        this.mdl_pesan = mdl_pesan;
    }

    public Model_Barang getMdl_barang() {
        return mdl_barang;
    }

    public void setMdl_barang(Model_Barang mdl_barang) {
        this.mdl_barang = mdl_barang;
    }

    public Long getJml_pesan() {
        return jml_pesan;
    }

    public void setJml_pesan(Long jml_pesan) {
        this.jml_pesan = jml_pesan;
    }

    public Long getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Long subtotal) {
        this.subtotal = subtotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
