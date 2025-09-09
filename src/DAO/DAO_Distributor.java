package DAO;

import Config.Koneksi;
import Model.Model_Distributor;
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
import Service.Service_Distributor;

public class DAO_Distributor implements Service_Distributor{

    private Connection connection;
    
    public DAO_Distributor(){
        connection = Koneksi.getConnection();
    }
    
    @Override
    public void tambahData(Model_Distributor modis) {
        PreparedStatement st = null;
        String sql = "INSERT INTO distributor(id_distributor, nama_distributor, telp_distributor, alamat_distributor) VALUES (?,?,?,?)";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, modis.getId_distributor());
            st.setString(2, modis.getNama_distributor());
            st.setString(3, modis.getTelp_distributor());
            st.setString(4, modis.getAlamat_distributor());
            
            st.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
                }
                      
            }
        }
    }

    @Override
    public void perbaruiData(Model_Distributor modis) {
        PreparedStatement st = null;
        String sql = "UPDATE distributor SET nama_distributor=?, telp_distributor=?, alamat_distributor=? WHERE id_distributor='"+modis.getId_distributor()+"'";
        try{
            st = connection.prepareStatement(sql);
            st.setString(1, modis.getNama_distributor());
            st.setString(2, modis.getTelp_distributor());
            st.setString(3, modis.getAlamat_distributor());
            
            st.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    @Override
    public void hapusData(Model_Distributor modis) {
        PreparedStatement st = null;
        String sql = "DELETE FROM distributor WHERE id_distributor=?";
        try{
            st = connection.prepareStatement(sql);
            st.setString(1, modis.getId_distributor());
            
            
            st.executeUpdate();
        }catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    @Override
    public List<Model_Distributor> getData() {
        
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT * FROM distributor";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_Distributor modis = new Model_Distributor();
                modis.setId_distributor     (rs.getString ("id_distributor"));
                modis.setNama_distributor   (rs.getString ("nama_distributor"));
                modis.setTelp_distributor   (rs.getString ("telp_distributor"));
                modis.setAlamat_distributor (rs.getString ("alamat_distributor"));
                
                
                list.add(modis);
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

    @Override
    public String nomor() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String urutan = null;
        Date now = new Date();
        SimpleDateFormat tanggal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat noformat = new SimpleDateFormat("yyMM");
        String tgl =tanggal.format(now);
        String no =noformat.format(now);
        String sql = "SELECT RIGHT (id_distributor,3)+1 AS Nomor FROM distributor ORDER BY Nomor desc";
        try{
            st = connection.prepareStatement(sql);
            rs= st.executeQuery();
            if(rs.next()){
                urutan=rs.getString(1);
                while (urutan.length()<3)
                   urutan="0"+urutan;
                   urutan ="DST"+no+urutan;
                }else{
                    urutan ="DST"+no+"001";
                }
            }catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                if (st!=null) {
                    try {
                        st.close();
                    } catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }
            }
        return urutan;
    }

    @Override
    public List<Model_Distributor> pencarian(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT * FROM distributor WHERE id_distributor LIKE '%"+id+"%' OR nama_distributor LIKE '%"+id+"%'";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_Distributor modis = new Model_Distributor();
                
                modis.setId_distributor     (rs.getString ("id_distributor"));
                modis.setNama_distributor   (rs.getString ("nama_distributor"));
                modis.setTelp_distributor   (rs.getString ("telp_distributor"));
                modis.setAlamat_distributor (rs.getString ("alamat_distributor"));
                                
                list.add(modis);
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

    @Override
    public Model_Distributor getByid(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM distributor WHERE id_distributor=?";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()) {
                Model_Distributor modis = new Model_Distributor();
                
                modis.setId_distributor     (rs.getString ("id_distributor"));
                modis.setNama_distributor   (rs.getString ("nama_distributor"));
                modis.setTelp_distributor   (rs.getString ("telp_distributor"));
                modis.setAlamat_distributor (rs.getString ("alamat_distributor"));
                
            }return null;
        }catch (SQLException ex) 
            {
                java.util.logging.Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
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
                        java.util.logging.Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            if (rs!=null) 
            {
                try 
                {
                    rs.close();
                } catch (SQLException ex) 
                    {
                    java.util.logging.Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
    }

}