package Model;

import java.util.ArrayList;
import java.util.List;


public class Model_Pemesanan {

    private String no_pesan;
    private String tgl_pesan;
    private Long total_pesan;
    private Model_Distributor mdl_dis;
    private Model_Pengguna mdl_peng;

    public Model_Pengguna getMdl_peng() {
        return mdl_peng;
    }

    public void setMdl_peng(Model_Pengguna mdl_peng) {
        this.mdl_peng = mdl_peng;
    }

    public String getNo_pesan() {
        return no_pesan;
    }

    public void setNo_pesan(String no_pesan) {
        this.no_pesan = no_pesan;
    }

    public String getTgl_pesan() {
        return tgl_pesan;
    }

    public void setTgl_pesan(String tgl_pesan) {
        this.tgl_pesan = tgl_pesan;
    }

    public Long getTotal_pesan() {
        return total_pesan;
    }

    public void setTotal_pesan(Long total_pesan) {
        this.total_pesan = total_pesan;
    }

    public Model_Distributor getMdl_dis() {
        return mdl_dis;
    }

    public void setMdl_dis(Model_Distributor mdl_dis) {
        this.mdl_dis = mdl_dis;
    }
}
