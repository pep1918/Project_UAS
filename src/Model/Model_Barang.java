package Model;

import java.util.ArrayList;
import java.util.List;


public class Model_Barang {

    private String kode_barang;
    private Model_JenisBarang jns_barang;
    private String nama_barang;
    private String satuan;
    private Long harga;
    private Integer stok;

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }
    
    
    public String getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(String kode_barang) {
        this.kode_barang = kode_barang;
    }

    public Model_JenisBarang getJns_barang() {
        return jns_barang;
    }

    public void setJns_barang(Model_JenisBarang jns_barang) {
        this.jns_barang = jns_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public Long getHarga() {
        return harga;
    }

    public void setHarga(Long harga) {
        this.harga = harga;
    }


    
}
