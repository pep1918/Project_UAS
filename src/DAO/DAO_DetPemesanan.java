package DAO;

import Config.Koneksi;
import Model.Model_Barang;
import Model.Model_Distributor;
import Model.Model_DetPemesanan;
import Model.Model_JenisBarang;
import Model.Model_DetPemesanan;
import Model.Model_Pemesanan;
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
import Service.Service_DetPemesanan;
import Service.Service_Pemesanan;
import static java.util.Collections.list;
import javax.swing.JOptionPane;

public class DAO_DetPemesanan implements Service_DetPemesanan{

    private Connection connection;
    
    public DAO_DetPemesanan(){
        connection = Koneksi.getConnection();
    }
    
    @Override
    public void tambahData(Model_DetPemesanan mod_detpsn) {
        PreparedStatement st = null;
        String sql = "INSERT INTO detail_pemesanan (no_pesan, kode_barang, jml_pesan, subtotal_pesan, status)SELECT '"+mod_detpsn.getMdl_pesan().getNo_pesan()+"', kode_barang, jml_pesan, subtotal_pesan, status FROM sementara_pesan";
        try{
            st = connection.prepareStatement(sql);
            st.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(DAO_DetPemesanan.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_DetPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
                      
            }
        }
    }

    
    @Override
    public Model_DetPemesanan getByid(String id) 
    {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM detail_pemesanan dp "
                + "INNER JOIN barang brg ON brg.kode_barang=dp.kode_barang "
                + "WHERE no_pesan='"+id+"' ORDER BY id ASC";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            while(rs.next()) {
                Model_Pemesanan psn = new Model_Pemesanan();
                Model_DetPemesanan det_psn = new Model_DetPemesanan();
                Model_Barang brg = new Model_Barang();
                
                psn.setNo_pesan(String.valueOf(rs.getString("dp.no_pesan")));
                det_psn.setMdl_pesan(psn);
                
                brg.setKode_barang      (rs.getString ("kode_barang"));
                brg.setNama_barang      (rs.getString ("nama_barang"));
                brg.setHarga            (rs.getLong   ("harga"));
                det_psn.setJml_pesan    (rs.getLong   ("jml_pesan"));
                det_psn.setSubtotal     (rs.getLong   ("subtotal_pesan"));
                det_psn.setStatus       (rs.getString ("status"));
                                
                det_psn.setMdl_barang(brg);
                
                
            }return null;
        }catch (SQLException ex) 
            {
                java.util.logging.Logger.getLogger(DAO_DetPemesanan.class.getName()).log(Level.SEVERE, null, ex);
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
                        java.util.logging.Logger.getLogger(DAO_DetPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            if (rs!=null) 
            {
                try 
                {
                    rs.close();
                } catch (SQLException ex) 
                    {
                    java.util.logging.Logger.getLogger(DAO_DetPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
    }

    @Override
    public List<Model_DetPemesanan> getData(String id) {
        
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT det_psn.no_pesan, det_psn.kode_barang, brg.nama_barang, "
                + "brg.harga, det_psn.jml_pesan, det_psn.subtotal_pesan, det_psn.status "
                + "FROM detail_pemesanan det_psn "
                + "INNER JOIN pemesanan psn ON psn.no_pesan=det_psn.no_pesan "
                + "INNER JOIN barang brg ON brg.kode_barang=det_psn.kode_barang "
                + "WHERE det_psn.no_pesan='"+id+"' ORDER BY no_pesan ASC";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_Pemesanan psn = new Model_Pemesanan();
                Model_DetPemesanan det_psn = new Model_DetPemesanan();
                Model_Barang brg = new Model_Barang();
                
                psn.setNo_pesan(String.valueOf(rs.getString("det_psn.no_pesan")));
                det_psn.setMdl_pesan(psn);
                
                brg.setKode_barang      (rs.getString ("kode_barang"));
                brg.setNama_barang      (rs.getString ("nama_barang"));
                brg.setHarga            (rs.getLong   ("harga"));
                det_psn.setJml_pesan    (rs.getLong   ("jml_pesan"));
                det_psn.setSubtotal     (rs.getLong   ("subtotal_pesan"));
                det_psn.setStatus       (rs.getString ("status"));
                                
                det_psn.setMdl_barang(brg);
                
                list.add(det_psn);
            }
          return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_DetPemesanan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_DetPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_DetPemesanan.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }

    
    @Override
    public void sumTotal(Model_DetPemesanan mod_detpsn) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT SUM(subtotal_pesan) FROM sementara_pesan";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
                mod_detpsn.setSubtotal(rs.getLong(1));
            }
        }catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_DetPemesanan.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_DetPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    @Override
    public void hapusSementara(Model_DetPemesanan mod_detpsn) {
        PreparedStatement st = null;
        String sql = "DELETE FROM sementara_pesan";
        try{
            st = connection.prepareStatement(sql);
            st.executeUpdate();
        }catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    @Override
    public List<Model_DetPemesanan> pencarian(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT det_psn.kode_barang, brg.nama_barang, brg.harga, "
                + "det_psn.jml_pesan, det_psn.subtotal_pesan "
                + "FROM detail_pemesanan det_psn "
                + "INNER JOIN barang brg ON brg.kode_barang=det_psn.kode_barang "
                + "WHERE brg.kode_barang LIKE '%"+id+"%' OR brg.nama_barang LIKE '%"+id+"%'";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_DetPemesanan det_psn = new Model_DetPemesanan();
                Model_Barang brg = new Model_Barang();
                Model_Distributor dst = new Model_Distributor();
                
                dst.setId_distributor   (rs.getString("id_distributor"));
                dst.setNama_distributor (rs.getString("nama_distributor"));
                
                brg.setKode_barang      (rs.getString ("kode_barang"));
                brg.setNama_barang      (rs.getString ("nama_barang"));
                brg.setHarga            (rs.getLong   ("harga"));
                det_psn.setJml_pesan    (rs.getLong   ("jml_pesan"));
                det_psn.setSubtotal     (rs.getLong   ("subtotal_pesan"));
                                
                det_psn.setMdl_barang(brg);
                det_psn.setMdl_dis(dst);
                
                list.add(det_psn);
            }
          return list;
        } catch (SQLException ex) {
            
            java.util.logging.Logger.getLogger(DAO_DetPemesanan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_DetPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_DetPemesanan.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }

    
}