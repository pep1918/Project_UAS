package DAO;

import Config.Koneksi;
import Model.Model_Barang;
import Model.Model_DetPemesanan;
import Model.Model_Distributor;
import Model.Model_SemPemesanan;
import Model.Model_JenisBarang;
import Model.Model_SemPemesanan;
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
import Service.Service_SemPemesanan;
import javax.swing.JOptionPane;

public class DAO_SemPemesanan implements Service_SemPemesanan{

    private Connection connection;
    
    public DAO_SemPemesanan(){
        connection = Koneksi.getConnection();
    }
    
    @Override
    public void tambahData(Model_SemPemesanan modsem) {
        PreparedStatement st = null;
        String sql = "INSERT INTO sementara_pesan (kode_barang, nama_barang, harga, jml_pesan, subtotal_pesan, status) VALUES (?,?,?,?,?,?)";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, modsem.getMdl_barang().getKode_barang());
            st.setString(2, modsem.getMdl_barang().getNama_barang());
            st.setLong  (3, modsem.getMdl_barang().getHarga());
            st.setLong  (4, modsem.getMdl_detpesan().getJml_pesan());
            st.setLong  (5, modsem.getMdl_detpesan().getSubtotal());
            st.setString(6, modsem.getMdl_detpesan().getStatus());
            
            st.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
                      
            }
        }
    }

    
    @Override
    public Model_SemPemesanan getByid(String id) 
    {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM sementara_pesan";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()) {
                Model_SemPemesanan smt = new Model_SemPemesanan();
                Model_Barang brg = new Model_Barang();
                Model_DetPemesanan det_psn = new Model_DetPemesanan();
                
                brg.setKode_barang  (rs.getString ("kode_barang"));
                brg.setNama_barang  (rs.getString ("nama_barang"));
                brg.setHarga        (rs.getLong   ("harga"));
                
                det_psn.setJml_pesan(rs.getLong    ("jml_pesan"));
                det_psn.setSubtotal (rs.getLong   ("subtotal_pesan"));
                
                smt.setMdl_barang(brg);
                smt.setMdl_detpesan(det_psn);
                
            }return null;
        }catch (SQLException ex) 
            {
                java.util.logging.Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
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
                        java.util.logging.Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            if (rs!=null) 
            {
                try 
                {
                    rs.close();
                } catch (SQLException ex) 
                    {
                    java.util.logging.Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
    }

    @Override
    public List<Model_SemPemesanan> getData() {
        
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT * FROM sementara_pesan";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_SemPemesanan smt = new Model_SemPemesanan();
                Model_Barang brg = new Model_Barang();
                Model_DetPemesanan det_psn = new Model_DetPemesanan();
                
                brg.setKode_barang  (rs.getString ("kode_barang"));
                brg.setNama_barang  (rs.getString ("nama_barang"));
                brg.setHarga        (rs.getLong ("harga"));
                
                det_psn.setJml_pesan(rs.getLong    ("jml_pesan"));
                det_psn.setSubtotal (rs.getLong   ("subtotal_pesan"));
                
                smt.setMdl_barang(brg);
                smt.setMdl_detpesan(det_psn);
                
                list.add(smt);
            }
          return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }

    @Override
    public void perbaruiData(Model_SemPemesanan modsem) {
        PreparedStatement st = null;
        String sql = "UPDATE sementara_pesan SET nama_barang=?, harga=?, jml_pesan=?, subtotal_pesan=? WHERE kode_barang='"+modsem.getMdl_barang().getKode_barang()+"'";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, modsem.getMdl_barang().getNama_barang());
            st.setLong  (2, modsem.getMdl_barang().getHarga());
            st.setLong  (3, modsem.getMdl_detpesan().getJml_pesan());
            st.setLong  (4, modsem.getMdl_detpesan().getSubtotal());
            
            st.executeUpdate();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Perbarui Data Gagal");
            Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_SemPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    @Override
    public void hapusData(Model_SemPemesanan modsem) {
        PreparedStatement st = null;
        String sql = "DELETE FROM sementara_pesan WHERE kode_barang=?";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, modsem.getMdl_barang().getKode_barang());
            
            st.executeUpdate();
        }catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    
}