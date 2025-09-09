package DAO;

import Config.Koneksi;
import Model.Model_Distributor;
import Model.Model_BarangKeluar;
import Model.Model_JenisBarang;
import Model.Model_BarangKeluar;
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
import Service.Service_BarangKeluar;
import javax.swing.JOptionPane;

public class DAO_BarangKeluar implements Service_BarangKeluar{

    private Connection connection;
    
    public DAO_BarangKeluar(){
        connection = Koneksi.getConnection();
    }
    
    @Override
    public void tambahData(Model_BarangKeluar modpem) {
        PreparedStatement st = null;
        String sql = "INSERT INTO barang_keluar (no_keluar, tgl_keluar, total_keluar, id_pengguna) VALUES (?,?,?,?)";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, modpem.getNo_keluar());
            st.setString(2, modpem.getTgl_keluar());
            st.setLong  (3, modpem.getTotal_keluar());
            st.setString(4, modpem.getMdl_peng().getId_pengguna());
            
            st.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
                }
                      
            }
        }
    }

    
    @Override
    public Model_BarangKeluar getByid(String id) 
    {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM barang_keluar WHERE no_keluar=?";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()) {
                Model_BarangKeluar keluar = new Model_BarangKeluar();
                Model_Distributor dst = new Model_Distributor();
                Model_Pengguna pgn = new Model_Pengguna();

                keluar.setNo_keluar      (rs.getString ("no_keluar"));
                keluar.setTgl_keluar     (rs.getString ("tgl_keluar"));
                keluar.setTotal_keluar   (rs.getLong   ("total_keluar"));
                pgn.setId_pengguna       (rs.getString ("id_pengguna"));
                
                keluar.setMdl_peng(pgn);
                
            }return null;
        }catch (SQLException ex) 
            {
                java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
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
                        java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            if (rs!=null) 
            {
                try 
                {
                    rs.close();
                } catch (SQLException ex) 
                    {
                    java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
    }

    @Override
    public List<Model_BarangKeluar> getData() {
        
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT * FROM barang_keluar";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_BarangKeluar keluar = new Model_BarangKeluar();
                Model_Distributor dst = new Model_Distributor();
                Model_Pengguna pgn = new Model_Pengguna();

                keluar.setNo_keluar         (rs.getString ("no_keluar"));
                keluar.setTgl_keluar        (rs.getString ("tgl_keluar"));
                keluar.setTotal_keluar      (rs.getLong   ("total_keluar"));
                pgn.setId_pengguna          (rs.getString("id_pengguna"));
                
                keluar.setMdl_peng(pgn);
                list.add(keluar);
            }
          return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
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
        String sql = "SELECT RIGHT (no_keluar,3)+1 AS Nomor FROM barang_keluar ORDER BY Nomor desc";
        try{
            st = connection.prepareStatement(sql);
            rs= st.executeQuery();
            if(rs.next()){
                urutan=rs.getString(1);
                while (urutan.length()<3)
                   urutan="0"+urutan;
                   urutan ="BK"+no+urutan;
                }else{
                    urutan ="BK"+no+"001";
                }
            }catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                if (st!=null) {
                    try {
                        st.close();
                    } catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }
            }
        return urutan;
    }

    @Override
    public List<Model_BarangKeluar> pencarian(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT * FROM barang_keluar WHERE no_keluar LIKE '%"+id+"%' OR tgl_keluar LIKE '%"+id+"%' OR total_keluar LIKE '%"+id+"%'";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_BarangKeluar keluar = new Model_BarangKeluar();
                Model_Distributor dst = new Model_Distributor();
                Model_Pengguna pgn = new Model_Pengguna();

                keluar.setNo_keluar         (rs.getString ("no_keluar"));
                keluar.setTgl_keluar        (rs.getString ("tgl_keluar"));
                keluar.setTotal_keluar      (rs.getLong   ("total_keluar"));
                pgn.setId_pengguna          (rs.getString("id_pengguna"));
                
                keluar.setMdl_peng(pgn);
                                
                list.add(keluar);
            }
          return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }

   
    
}