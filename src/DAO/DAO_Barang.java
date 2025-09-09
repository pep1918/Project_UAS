package DAO;

import Config.Koneksi;
import Model.Model_Barang;
import Model.Model_JenisBarang;
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
import javax.swing.JOptionPane;

public class DAO_Barang implements Service_Barang{

    private Connection connection;
    
    public DAO_Barang(){
        connection = Koneksi.getConnection();
    }
    
    @Override
    public void tambahData(Model_Barang mobar) {
        PreparedStatement st = null;
        String sql = "INSERT INTO barang (kode_barang, kode_jenis, nama_barang,   satuan, harga, stok) VALUES (?,?,?,?,?,?)";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, mobar.getKode_barang());
            st.setString(2, mobar.getJns_barang().getKode_jenis());
            st.setString(3, mobar.getNama_barang());
            st.setString(4, mobar.getSatuan());
            st.setLong  (5, mobar.getHarga());
            st.setInt   (6, mobar.getStok());
            
            st.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
                }
                      
            }
        }
    }

    @Override
    public void perbaruiData(Model_Barang mobar) {
        PreparedStatement st = null;
        String sql = "UPDATE barang SET  kode_jenis=?, nama_barang=?, satuan=?, harga=?, stok=? WHERE kode_barang='"+mobar.getKode_barang()+"'";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, mobar.getJns_barang().getKode_jenis());
            st.setString(2, mobar.getNama_barang());
            st.setString(3, mobar.getSatuan());
            st.setLong  (4, mobar.getHarga());
            st.setInt   (5, mobar.getStok());
            
            st.executeUpdate();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Perbarui Data Gagal");
            Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    @Override
    public void hapusData(Model_Barang mobar) {
        PreparedStatement st = null;
        String sql = "DELETE FROM barang WHERE kode_barang=?";
        try{
            st = connection.prepareStatement(sql);
            
            st.setString(1, mobar.getKode_barang());
            
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

    @Override
    public Model_Barang getByid(String id) 
    {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM barang WHERE kode_barang=?";
        try{
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()) {
                Model_Barang mobar = new Model_Barang();
                Model_JenisBarang jbr = new Model_JenisBarang();

                mobar.setKode_barang    (rs.getString ("kode_barang"));
                jbr.setKode_jenis       (rs.getString ("kode_jenis"));
                mobar.setNama_barang    (rs.getString ("nama_barang"));
                mobar.setSatuan         (rs.getString ("satuan"));
                mobar.setHarga          (rs.getLong   ("harga"));
                mobar.setStok           (rs.getInt    ("stok"));
                
                mobar.setJns_barang(jbr);
                
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

    @Override
    public List<Model_Barang> getData() {
        
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT bg.kode_barang,bg.kode_jenis,jb.nama_jenis,bg.nama_barang,bg.satuan,bg.harga,bg.stok FROM barang bg INNER JOIN jenis_barang jb ON jb.kode_jenis=bg.kode_jenis";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_Barang mobar = new Model_Barang();
                Model_JenisBarang jbr = new Model_JenisBarang();

                mobar.setKode_barang    (rs.getString ("kode_barang"));
                jbr.setKode_jenis       (rs.getString ("kode_jenis"));
                jbr.setNama_jenis       (rs.getString ("nama_jenis"));
                mobar.setNama_barang    (rs.getString ("nama_barang"));
                mobar.setSatuan         (rs.getString ("satuan"));
                mobar.setHarga          (rs.getLong   ("harga"));
                mobar.setStok           (rs.getInt    ("stok"));
                
                mobar.setJns_barang(jbr);
                
                list.add(mobar);
            }
          return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
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
        String sql = "SELECT RIGHT (kode_barang,3)+1 AS Nomor FROM barang ORDER BY Nomor desc";
        try{
            st = connection.prepareStatement(sql);
            rs= st.executeQuery();
            if(rs.next()){
                urutan=rs.getString(1);
                while (urutan.length()<3)
                   urutan="0"+urutan;
                   urutan ="B"+no+urutan;
                }else{
                    urutan ="B"+no+"001";
                }
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
        return urutan;
    }

    @Override
    public List<Model_Barang> pencarian(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT bg.kode_barang,bg.kode_jenis,jb.nama_jenis, "
                + "bg.nama_barang,bg.satuan,bg.harga,bg.stok FROM barang bg "
                + "INNER JOIN jenis_barang jb ON jb.kode_jenis=bg.kode_jenis WHERE kode_barang LIKE '%"+id+"%' OR nama_barang LIKE '%"+id+"%'";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_Barang mobar = new Model_Barang();
                Model_JenisBarang jb = new Model_JenisBarang();
                mobar.setKode_barang    (rs.getString ("kode_barang"));
                jb.setKode_jenis        (rs.getString ("kode_jenis"));
                jb.setNama_jenis        (rs.getString ("nama_jenis"));
                mobar.setNama_barang    (rs.getString ("nama_barang"));
                mobar.setSatuan         (rs.getString ("satuan"));
                mobar.setHarga          (rs.getLong   ("harga"));
                mobar.setStok           (rs.getInt    ("stok"));
                                
                mobar.setJns_barang(jb);
                list.add(mobar);
            }
          return list;
        } catch (SQLException ex) {
            
            java.util.logging.Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }

    @Override
    public List<Model_Barang> getData2() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT * FROM barang";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_Barang mobar = new Model_Barang();
                Model_JenisBarang jbr = new Model_JenisBarang();

                mobar.setKode_barang    (rs.getString ("kode_barang"));
                mobar.setNama_barang    (rs.getString ("nama_barang"));
                mobar.setSatuan         (rs.getString ("satuan"));
                mobar.setHarga          (rs.getLong   ("harga"));
                mobar.setStok           (rs.getInt    ("stok"));
                
                mobar.setJns_barang(jbr);
                
                list.add(mobar);
            }
          return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }

    @Override
    public List<Model_Barang> pencarian2(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql ="SELECT * FROM barang WHERE kode_barang LIKE '%"+id+"%' OR nama_barang LIKE '%"+id+"%'";
        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()) {
                Model_Barang mobar = new Model_Barang();
                Model_JenisBarang jbr = new Model_JenisBarang();

                mobar.setKode_barang    (rs.getString ("kode_barang"));
                mobar.setNama_barang    (rs.getString ("nama_barang"));
                mobar.setSatuan         (rs.getString ("satuan"));
                mobar.setHarga          (rs.getLong   ("harga"));
                
                mobar.setJns_barang(jbr);
                
                list.add(mobar);
            }
          return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_Barang.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }

    @Override
    public String nomor2() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String urutan = null;
        
        String sql = "SELECT RIGHT (kode_barang,3)+1 AS Nomor FROM barang ORDER BY Nomor desc";
        try{
            st = connection.prepareStatement(sql);
            rs= st.executeQuery();
            if(rs.next()){
                urutan=rs.getString(1);
                while (urutan.length()<3)
                   urutan="0"+urutan;
                   urutan =urutan;
                }else{
                    urutan ="001";
                }
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
        return urutan;
    }

}