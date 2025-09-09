package DAO;

import Config.Koneksi;
import Model.Model_Distributor;
import Model.Model_Pemesanan;
import Model.Model_JenisBarang;
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
import Service.Service_Pemesanan;
import javax.swing.JOptionPane;

public class DAO_Pemesanan implements Service_Pemesanan{

    private Connection connection;
    
    public DAO_Pemesanan(){
        connection = Koneksi.getConnection();
    }
    
    @Override
    public void tambahData(Model_Pemesanan modpem) {
        PreparedStatement st = null;
        String sql = "INSERT INTO pemesanan (no_pesan, tgl_pesan, total_pesan, id_distributor, id_pengguna) VALUES (?,?,?,?,?)";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, modpem.getNo_pesan());
            st.setString(2, modpem.getTgl_pesan());
            st.setLong  (3, modpem.getTotal_pesan());
            st.setString(4, modpem.getMdl_dis().getId_distributor());
            st.setString(5, modpem.getMdl_peng().getId_pengguna());
            
            st.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(DAO_Pemesanan.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_Pemesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
                      
            }
        }
    }

    
    @Override
    public Model_Pemesanan getByid(String id) 
    {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM pemesanan WHERE no_pesan=?";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()) {
                Model_Pemesanan psn = new Model_Pemesanan();
                Model_Distributor dst = new Model_Distributor();
                Model_Pengguna pgn = new Model_Pengguna();

                psn.setNo_pesan      (rs.getString ("no_pesan"));
                psn.setTgl_pesan     (rs.getString ("tgl_pesan"));
                psn.setTotal_pesan   (rs.getLong   ("total_pesan"));
                dst.setId_distributor(rs.getString ("id_distributor"));
                pgn.setId_pengguna   (rs.getString ("id_pengguna"));
                
                psn.setMdl_dis(dst);
                psn.setMdl_peng(pgn);
                
            }return null;
        }catch (SQLException ex) 
            {
                java.util.logging.Logger.getLogger(DAO_Pemesanan.class.getName()).log(Level.SEVERE, null, ex);
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
                        java.util.logging.Logger.getLogger(DAO_Pemesanan.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            if (rs!=null) 
            {
                try 
                {
                    rs.close();
                } catch (SQLException ex) 
                    {
                    java.util.logging.Logger.getLogger(DAO_Pemesanan.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
    }

    @Override
    public List<Model_Pemesanan> getData() {
        
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT * FROM pemesanan";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_Pemesanan psn = new Model_Pemesanan();
                Model_Distributor dst = new Model_Distributor();
                Model_Pengguna pgn = new Model_Pengguna();

                psn.setNo_pesan         (rs.getString ("no_pesan"));
                psn.setTgl_pesan        (rs.getString ("tgl_pesan"));
                psn.setTotal_pesan      (rs.getLong   ("total_pesan"));
                dst.setId_distributor   (rs.getString ("id_distributor"));
                pgn.setId_pengguna      (rs.getString ("id_pengguna"));
                
                psn.setMdl_dis(dst);
                psn.setMdl_peng(pgn);
                
                list.add(psn);
            }
          return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Pemesanan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Pemesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Pemesanan.class.getName()).log(Level.SEVERE, null, ex);
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
        String sql = "SELECT RIGHT (no_pesan,3)+1 AS Nomor FROM pemesanan ORDER BY Nomor desc";
        try{
            st = connection.prepareStatement(sql);
            rs= st.executeQuery();
            if(rs.next()){
                urutan=rs.getString(1);
                while (urutan.length()<3)
                   urutan="0"+urutan;
                   urutan ="PB"+no+urutan;
                }else{
                    urutan ="PB"+no+"001";
                }
            }catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DAO_Pemesanan.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                if (st!=null) {
                    try {
                        st.close();
                    } catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(DAO_Pemesanan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }
            }
        return urutan;
    }

    @Override
    public List<Model_Pemesanan> pencarian(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT * FROM pemesanan WHERE no_pesan LIKE '%"+id+"%' OR tgl_pesan LIKE '%"+id+"%' OR total_pesan LIKE '%"+id+"%' OR id_distributor LIKE '%"+id+"%'";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_Pemesanan psn = new Model_Pemesanan();
                Model_Distributor dst = new Model_Distributor();
                Model_Pengguna pgn = new Model_Pengguna();

                psn.setNo_pesan         (rs.getString ("no_pesan"));
                psn.setTgl_pesan        (rs.getString ("tgl_pesan"));
                psn.setTotal_pesan      (rs.getLong   ("total_pesan"));
                dst.setId_distributor   (rs.getString ("id_distributor"));
                pgn.setId_pengguna      (rs.getString("id_pengguna"));
                
                psn.setMdl_dis(dst);
                psn.setMdl_peng(pgn);
                                
                list.add(psn);
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