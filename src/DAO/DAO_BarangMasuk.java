package DAO;

import Config.Koneksi;
import Model.Model_Distributor;
import Model.Model_BarangMasuk;
import Model.Model_JenisBarang;
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
import Service.Service_BarangMasuk;
import javax.swing.JOptionPane;

public class DAO_BarangMasuk implements Service_BarangMasuk{

    private Connection connection;
    
    public DAO_BarangMasuk(){
        connection = Koneksi.getConnection();
    }
    
    @Override
    public void tambahData(Model_BarangMasuk modmasuk) {
        PreparedStatement st = null;
        String sql = "INSERT INTO barang_masuk (no_masuk, tgl_masuk, total_masuk, id_distributor, id_pengguna) VALUES (?,?,?,?,?)";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, modmasuk.getNo_masuk());
            st.setString(2, modmasuk.getTgl_masuk());
            st.setLong  (3, modmasuk.getTotal_masuk());
            st.setString(4, modmasuk.getMdl_dis().getId_distributor());
            st.setString(5, modmasuk.getMdl_peng().getId_pengguna());
            
            st.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                }
                      
            }
        }
    }

    
    @Override
    public Model_BarangMasuk getByid(String id) 
    {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM barang_masuk WHERE no_masuk=?";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()) {
                Model_BarangMasuk psn = new Model_BarangMasuk();
                Model_Distributor dst = new Model_Distributor();
                Model_Pengguna pgn = new Model_Pengguna();

                psn.setNo_masuk      (rs.getString ("no_masuk"));
                psn.setTgl_masuk     (rs.getString ("tgl_masuk"));
                psn.setTotal_masuk   (rs.getLong   ("total_masuk"));
                dst.setId_distributor(rs.getString ("id_distributor"));
                pgn.setId_pengguna   (rs.getString ("id_pengguna"));
                
                psn.setMdl_dis(dst);
                psn.setMdl_peng(pgn);
                
            }return null;
        }catch (SQLException ex) 
            {
                java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
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
                        java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            if (rs!=null) 
            {
                try 
                {
                    rs.close();
                } catch (SQLException ex) 
                    {
                    java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
    }

    @Override
    public List<Model_BarangMasuk> getData() {
        
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT * FROM barang_masuk";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_BarangMasuk psn = new Model_BarangMasuk();
                Model_Distributor dst = new Model_Distributor();
                Model_Pengguna pgn = new Model_Pengguna();

                psn.setNo_masuk         (rs.getString ("no_masuk"));
                psn.setTgl_masuk        (rs.getString ("tgl_masuk"));
                psn.setTotal_masuk      (rs.getLong   ("total_masuk"));
                dst.setId_distributor   (rs.getString ("id_distributor"));
                pgn.setId_pengguna      (rs.getString ("id_pengguna"));
                
                psn.setMdl_dis(dst);
                psn.setMdl_peng(pgn);
                
                list.add(psn);
            }
          return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }

    @Override
    public String nomor() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String urutan = null;
        Date now = new Date();
        SimpleDateFormat tanggal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat noformat = new SimpleDateFormat("yyMMdd");
        String tgl =tanggal.format(now);
        String no =noformat.format(now);
        String sql = "SELECT RIGHT (no_masuk,3)+1 AS Nomor FROM barang_masuk ORDER BY Nomor desc";
        try{
            st = connection.prepareStatement(sql);
            rs= st.executeQuery();
            if(rs.next()){
                urutan=rs.getString(1);
                while (urutan.length()<3)
                   urutan="0"+urutan;
                   urutan ="BM"+no+urutan;
                }else{
                    urutan ="BM"+no+"001";
                }
            }catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                if (st!=null) {
                    try {
                        st.close();
                    } catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }
            }
        return urutan;
    }

    @Override
    public List<Model_BarangMasuk> pencarian(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT * FROM barang_masuk WHERE no_masuk LIKE '%"+id+"%' OR tgl_masuk LIKE '%"+id+"%' OR total_masuk LIKE '%"+id+"%' OR id_distributor LIKE '%"+id+"%'";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_BarangMasuk psn = new Model_BarangMasuk();
                Model_Distributor dst = new Model_Distributor();
                Model_Pengguna pgn = new Model_Pengguna();

                psn.setNo_masuk         (rs.getString ("no_masuk"));
                psn.setTgl_masuk        (rs.getString ("tgl_masuk"));
                psn.setTotal_masuk      (rs.getLong   ("total_masuk"));
                dst.setId_distributor   (rs.getString ("id_distributor"));
                pgn.setId_pengguna      (rs.getString ("id_pengguna"));
                
                psn.setMdl_dis(dst);
                psn.setMdl_peng(pgn);
                                
                list.add(psn);
            }
          return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }

    @Override
    public void perbaruiStatus(String kd_barang) {
        PreparedStatement st = null;
        String sql = "UPDATE detail_pemesanan SET status='Barang Telah Datang' WHERE kode_barang='"+kd_barang+"'";
    
        try{
            st = connection.prepareStatement(sql);
            st.executeUpdate();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal Perbarui Status Barang");
            java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    
}