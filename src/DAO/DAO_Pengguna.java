package DAO;

import Config.Koneksi;
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
import Service.Service_Pengguna;

public class DAO_Pengguna implements Service_Pengguna{

    private Connection connection;
    
    public DAO_Pengguna(){
        connection = Koneksi.getConnection();
    }
    
    @Override
    public void tambahData(Model_Pengguna moduser) {
        PreparedStatement st = null;
        String sql = "INSERT INTO pengguna(id_pengguna, nama_pengguna, username, password, telp_pengguna, alamat_pengguna, level) VALUES (?,?,?,?,?,?,?)";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, moduser.getId_pengguna());
            st.setString(2, moduser.getNama_pengguna());
            st.setString(3, moduser.getUsername());
//            st.setString(4, moduser.getPassword());
            st.setString(5, moduser.getTelp_pengguna());
            st.setString(6, moduser.getAlamat_pengguna());
            st.setString(7, moduser.getLevel());
            
            st.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, ex);
                }
                      
            }
        }
    }

    @Override
    public void perbaruiData(Model_Pengguna moduser) {
        PreparedStatement st = null;
        String sql = "UPDATE pengguna SET nama_pengguna=?, username=?, password=?, telp_pengguna=?, alamat_pengguna=?, level=? WHERE id_pengguna='"+moduser.getId_pengguna()+"'";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, moduser.getNama_pengguna());
            st.setString(2, moduser.getUsername());
//            st.setString(3, Encrypt.getmd5Java(moduser.getPassword()));
            st.setString(4, moduser.getTelp_pengguna());
            st.setString(5, moduser.getAlamat_pengguna());
            st.setString(6, moduser.getLevel());
            
            st.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    @Override
//start <modive by irwan>
    public void risetpswd(Model_Pengguna moduser) {
        PreparedStatement st = null;
//        String sql = "UPDATE pengguna SET nama_pengguna=?, username=?, password=?, telp_pengguna=?, alamat_pengguna=?, level=? WHERE id_pengguna='"+moduser.getId_pengguna()+"'";
        String sql = "UPDATE pengguna SET nama_pengguna=?, username=?, password=? WHERE id_pengguna='"+moduser.getId_pengguna()+"'";        
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, moduser.getNama_pengguna());
            st.setString(2, moduser.getUsername());
//            st.setString(3, Encrypt.getmd5Java(moduser.getPassword()));
            st.setString(3, moduser.getPassword());            
//            st.setString(4, moduser.getTelp_pengguna());
//            st.setString(5, moduser.getAlamat_pengguna());
//            st.setString(6, moduser.getLevel());
            
            st.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    @Override    
//end <modive by irwan>
    public void hapusData(Model_Pengguna moduser) {
        PreparedStatement st = null;
        String sql = "DELETE FROM pengguna WHERE id_pengguna=?";
        try{
            st = connection.prepareStatement(sql);
            st.setString(1, moduser.getId_pengguna());
            
            
            st.executeUpdate();
        }catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    @Override
    public List<Model_Pengguna> getData() {
        
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT * FROM pengguna";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_Pengguna moduser = new Model_Pengguna();
                moduser.setId_pengguna     (rs.getString ("id_pengguna"));
                moduser.setNama_pengguna   (rs.getString ("nama_pengguna"));
                moduser.setUsername        (rs.getString ("username"));
                moduser.setPassword        (rs.getString ("password"));
                moduser.setTelp_pengguna   (rs.getString ("telp_pengguna"));
                moduser.setAlamat_pengguna (rs.getString ("alamat_pengguna"));
                moduser.setLevel           (rs.getString ("level"));
                
                list.add(moduser);
            }
          return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, ex);
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
        String sql = "SELECT RIGHT (id_pengguna,3)+1 AS Nomor FROM pengguna ORDER BY Nomor desc";
        try{
            st = connection.prepareStatement(sql);
            rs= st.executeQuery();
            if(rs.next()){
                urutan=rs.getString(1);
                while (urutan.length()<3)
                   urutan="0"+urutan;
                   urutan ="USR"+no+urutan;
                }else{
                    urutan ="USR"+no+"001";
                }
            }catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                if (st!=null) {
                    try {
                        st.close();
                    } catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }
            }
        return urutan;
    }

    @Override
    public List<Model_Pengguna> pencarian(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT * FROM pengguna WHERE id_pengguna LIKE '%"+id+"%' OR nama_pengguna LIKE '%"+id+"%' OR username LIKE '%"+id+"%'";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_Pengguna moduser = new Model_Pengguna();
                moduser.setId_pengguna     (rs.getString ("id_pengguna"));
                moduser.setNama_pengguna   (rs.getString ("nama_pengguna"));
                moduser.setUsername        (rs.getString ("username"));
                moduser.setPassword        (rs.getString ("password"));
                moduser.setTelp_pengguna   (rs.getString ("telp_pengguna"));
                moduser.setAlamat_pengguna (rs.getString ("alamat_pengguna"));
                moduser.setLevel           (rs.getString ("level"));
                                
                list.add(moduser);
            }
          return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Pengguna.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }

    @Override
    public Model_Pengguna getByid(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM pengguna WHERE id_pengguna=?";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()) {
                Model_Pengguna moduser = new Model_Pengguna();
                moduser.setId_pengguna     (rs.getString ("id_pengguna"));
                moduser.setNama_pengguna   (rs.getString ("nama_pengguna"));
                moduser.setUsername        (rs.getString ("username"));
                moduser.setPassword        (rs.getString ("password"));
                moduser.setTelp_pengguna   (rs.getString ("telp_pengguna"));
                moduser.setAlamat_pengguna (rs.getString ("alamat_pengguna"));
                moduser.setLevel           (rs.getString ("level"));
                
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