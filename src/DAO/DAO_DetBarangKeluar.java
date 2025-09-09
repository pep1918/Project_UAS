package DAO;

import Config.Koneksi;
import Model.Model_Barang;
import Model.Model_BarangKeluar;
import Model.Model_Distributor;
import Model.Model_DetBarangKeluar;
import Model.Model_JenisBarang;
import Model.Model_DetBarangKeluar;
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
import Service.Service_DetBarangKeluar;
import Service.Service_BarangKeluar;
import javax.swing.JOptionPane;

public class DAO_DetBarangKeluar implements Service_DetBarangKeluar{

    private Connection connection;
    
    public DAO_DetBarangKeluar(){
        connection = Koneksi.getConnection();
    }
    
    @Override
    public void tambahData(Model_DetBarangKeluar mod_detpsn) {
        PreparedStatement st = null;
        String sql = "INSERT INTO detail_barang_keluar (no_keluar, kode_barang, jml_keluar, subtotal_keluar)SELECT '"+mod_detpsn.getMdl_keluar().getNo_keluar()+"', kode_barang, jml_keluar, subtotal_keluar FROM sementara_keluar";
        try{
            st = connection.prepareStatement(sql);
            st.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(DAO_DetBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_DetBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
                }
                      
            }
        }
    }

    
    @Override
    public Model_DetBarangKeluar getByid(String id) 
    {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM barang_keluar WHERE no_keluar=?";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()) {
                Model_DetBarangKeluar psn = new Model_DetBarangKeluar();
                Model_Distributor dst = new Model_Distributor();
                Model_Pengguna pgn = new Model_Pengguna();

//                psn.setNo_keluar      (rs.getString ("no_keluar"));
//                psn.setTgl_keluar     (rs.getString ("tgl_keluar"));
//                psn.setTotal_keluar   (rs.getLong   ("total_keluar"));
//                dst.setId_distributor(rs.getString ("id_distributor"));
//                pgn.setId_pengguna   (rs.getString ("id_pengguna"));
//                
//                psn.setMdl_dis(dst);
//                psn.setMdl_peng(pgn);
                
            }return null;
        }catch (SQLException ex) 
            {
                java.util.logging.Logger.getLogger(DAO_DetBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
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
                        java.util.logging.Logger.getLogger(DAO_DetBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            if (rs!=null) 
            {
                try 
                {
                    rs.close();
                } catch (SQLException ex) 
                    {
                    java.util.logging.Logger.getLogger(DAO_DetBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
    }

    @Override
    public List<Model_DetBarangKeluar> getData(String id) {
        
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT det_keluar.no_keluar, det_keluar.kode_barang, "
                + "brg.nama_barang, brg.harga, det_keluar.jml_keluar, "
                + "det_keluar.subtotal_keluar "
                + "FROM detail_barang_keluar det_keluar "
                + "INNER JOIN barang_keluar keluar ON keluar.no_keluar=det_keluar.no_keluar "
                + "INNER JOIN barang brg ON brg.kode_barang=det_keluar.kode_barang "
                + "WHERE det_keluar.no_keluar='"+id+"' ORDER BY no_keluar ASC";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_BarangKeluar keluar = new Model_BarangKeluar();
                Model_DetBarangKeluar det_keluar = new Model_DetBarangKeluar();
                Model_Barang brg = new Model_Barang();
                
                keluar.setNo_keluar(String.valueOf(rs.getString("det_keluar.no_keluar")));
                det_keluar.setMdl_keluar(keluar);
                
                brg.setKode_barang      (rs.getString ("kode_barang"));
                brg.setNama_barang      (rs.getString ("nama_barang"));
                brg.setHarga            (rs.getLong   ("harga"));
                det_keluar.setJml_keluar    (rs.getInt   ("jml_keluar"));
                det_keluar.setSubtotal_keluar(rs.getLong   ("subtotal_keluar"));
                                
                det_keluar.setMdl_barang(brg);
                
                list.add(det_keluar);
            }
          return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_DetBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_DetBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_DetBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }

    
    @Override
    public void sumTotal(Model_DetBarangKeluar mod_detpsn) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT SUM(subtotal_keluar) FROM sementara_keluar";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
                mod_detpsn.setSubtotal_keluar(rs.getLong(1));
            }
        }catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_DetBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_DetBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    @Override
    public void hapusSementara(Model_DetBarangKeluar mod_detpsn) {
        PreparedStatement st = null;
        String sql = "DELETE FROM sementara_keluar";
        try{
            st = connection.prepareStatement(sql);
            st.executeUpdate();
        }catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_SemBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_SemBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    @Override
    public List<Model_DetBarangKeluar> pencarian(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT det_keluar.kode_barang, brg.nama_barang, brg.harga, "
                + "det_keluar.jml_keluar, det_keluar.subtotal_keluar "
                + "FROM detail_BarangKeluar det_keluar "
                + "INNER JOIN barang brg ON brg.kode_barang=det_keluar.kode_barang "
                + "WHERE brg.kode_barang LIKE '%"+id+"%' OR brg.nama_barang LIKE '%"+id+"%'";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_DetBarangKeluar det_keluar = new Model_DetBarangKeluar();
                Model_Barang brg = new Model_Barang();
                
                brg.setKode_barang      (rs.getString ("kode_barang"));
                brg.setNama_barang      (rs.getString ("nama_barang"));
                brg.setHarga            (rs.getLong   ("harga"));
                det_keluar.setJml_keluar    (rs.getInt   ("jml_keluar"));
                det_keluar.setSubtotal_keluar(rs.getLong   ("subtotal_keluar"));
                                
                det_keluar.setMdl_barang(brg);
                
                list.add(det_keluar);
            }
          return list;
        } catch (SQLException ex) {
            
            java.util.logging.Logger.getLogger(DAO_DetBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_DetBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_DetBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }

    @Override
    public boolean validasiStok(Model_DetBarangKeluar mod_detkeluar) {
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean valid = false;
        String sql = "SELECT stok FROM barang WHERE kode_barang='"+mod_detkeluar.getMdl_barang().getKode_barang()+"' AND (stok<'"+mod_detkeluar.getJml_keluar()+"')";
        try{
            st = connection.prepareStatement(sql);
            rs= st.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Stok Barang "+mod_detkeluar.getMdl_barang().getNama_barang()+" tidak tersedia !!!","Peringatan",JOptionPane.WARNING_MESSAGE);
            }else {
                valid=true;
            }
        }catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DAO_DetBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                if (st!=null) {
                    try {
                        st.close();
                    } catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(DAO_DetBarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }
            }
        return valid;
    }

    
}