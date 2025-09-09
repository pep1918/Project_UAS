package DAO;

import Config.Koneksi;
import Model.Model_Barang;
import Model.Model_Distributor;
import Model.Model_DetBarangMasuk;
import Model.Model_JenisBarang;
import Model.Model_DetBarangMasuk;
import Model.Model_DetBarangMasuk;
import Model.Model_BarangMasuk;
import Model.Model_Pengguna;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Service.Service_Barang;
import Service.Service_DetBarangMasuk;
import Service.Service_BarangMasuk;
import javax.swing.JOptionPane;

public class DAO_DetBarangMasuk implements Service_DetBarangMasuk{

    private Connection connection;
    
    public DAO_DetBarangMasuk(){
        connection = Koneksi.getConnection();
    }
    
    @Override
    public void tambahData(Model_DetBarangMasuk mod_detmasuk) {
        PreparedStatement st = null;
        String sql = "INSERT INTO detail_barang_masuk (no_masuk, kode_barang, jml_masuk, subtotal_masuk)SELECT '"+mod_detmasuk.getMdl_masuk().getNo_masuk()+"', kode_barang, jml_masuk, subtotal_masuk FROM sementara_barang_masuk";
        try{
            st = connection.prepareStatement(sql);
            st.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                }
                      
            }
        }
    }

    
    @Override
    public Model_DetBarangMasuk getByid(String id) 
    {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM BarangMasuk WHERE no_masuk=?";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()) {
                Model_DetBarangMasuk masuk = new Model_DetBarangMasuk();
                Model_Distributor dst = new Model_Distributor();
                Model_Pengguna pgn = new Model_Pengguna();

//                masuk.setNo_masuk      (rs.getString ("no_masuk"));
//                masuk.setTgl_masuk     (rs.getString ("tgl_masuk"));
//                masuk.setTotal_masuk   (rs.getLong   ("total_masuk"));
//                dst.setId_distributor(rs.getString ("id_distributor"));
//                pgn.setId_pengguna   (rs.getString ("id_pengguna"));
//                
//                masuk.setMdl_dis(dst);
//                masuk.setMdl_peng(pgn);
                
            }return null;
        }catch (SQLException ex) 
            {
                java.util.logging.Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }finally   
        {
            if (st!=null) 
            {
                try 
                {
                    st.close();
                } catch (SQLException ex) 
                    {
                        java.util.logging.Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            if (rs!=null) 
            {
                try 
                {
                    rs.close();
                } catch (SQLException ex) 
                    {
                    java.util.logging.Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
    }

    @Override
    public List<Model_DetBarangMasuk> getData(String id) {
        
        
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT det_masuk.no_masuk, det_masuk.kode_barang, "
                + "brg.nama_barang, brg.harga, det_masuk.jml_masuk, "
                + "det_masuk.subtotal_masuk "
                + "FROM detail_barang_masuk det_masuk "
                + "INNER JOIN barang_masuk masuk ON masuk.no_masuk=det_masuk.no_masuk "
                + "INNER JOIN barang brg ON brg.kode_barang=det_masuk.kode_barang "
                + "WHERE det_masuk.no_masuk='"+id+"' ORDER BY no_masuk ASC";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_BarangMasuk masuk = new Model_BarangMasuk();
                Model_DetBarangMasuk det_masuk = new Model_DetBarangMasuk();
                Model_Barang brg = new Model_Barang();
                
                masuk.setNo_masuk(String.valueOf(rs.getString("det_masuk.no_masuk")));
                det_masuk.setMdl_masuk(masuk);
                
                brg.setKode_barang      (rs.getString ("kode_barang"));
                brg.setNama_barang      (rs.getString ("nama_barang"));
                brg.setHarga            (rs.getLong   ("harga"));
                det_masuk.setJml_masuk    (rs.getInt   ("jml_masuk"));
                det_masuk.setSubtotal_masuk(rs.getLong   ("subtotal_masuk"));
                                
                det_masuk.setMdl_barang(brg);
                
                list.add(det_masuk);
            }
          return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }

    
    @Override
    public void sumTotal(Model_DetBarangMasuk mod_detmasuk) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT SUM(subtotal_masuk) FROM sementara_barang_masuk";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
                mod_detmasuk.setSubtotal_masuk(rs.getLong(1));
            }
        }catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_DetBarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    @Override
    public void hapusSementara(Model_DetBarangMasuk mod_detmasuk) {
        PreparedStatement st = null;
        String sql = "DELETE FROM sementara_barang_masuk";
        try{
            st = connection.prepareStatement(sql);
            st.executeUpdate();
        }catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_SemBarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_SemBarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    
    
}