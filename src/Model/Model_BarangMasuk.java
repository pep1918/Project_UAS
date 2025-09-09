package Model;

import java.util.ArrayList;
import java.util.List;


public class Model_BarangMasuk {

    private String no_masuk;
    private String tgl_masuk;
    private Long total_masuk;
    private Model_Distributor mdl_dis;
    private Model_Pengguna mdl_peng;

    public String getNo_masuk() {
        return no_masuk;
    }

    public void setNo_masuk(String no_masuk) {
        this.no_masuk = no_masuk;
    }

    public String getTgl_masuk() {
        return tgl_masuk;
    }

    public void setTgl_masuk(String tgl_masuk) {
        this.tgl_masuk = tgl_masuk;
    }

    public Long getTotal_masuk() {
        return total_masuk;
    }

    public void setTotal_masuk(Long total_masuk) {
        this.total_masuk = total_masuk;
    }

    public Model_Distributor getMdl_dis() {
        return mdl_dis;
    }

    public void setMdl_dis(Model_Distributor mdl_dis) {
        this.mdl_dis = mdl_dis;
    }

    public Model_Pengguna getMdl_peng() {
        return mdl_peng;
    }

    public void setMdl_peng(Model_Pengguna mdl_peng) {
        this.mdl_peng = mdl_peng;
    }

    
}
