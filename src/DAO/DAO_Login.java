/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Main.Menu_Utama;
import Config.Koneksi;
import Model.Model_Login;
import Service.Service_Login;
import View.Form_Login;
import View.Form_Home;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author UH
 */
public class DAO_Login implements Service_Login{

    private Connection conn;
    
    public DAO_Login(){
        conn = Koneksi.getConnection();
    }
            
    @Override
    public void prosesLogin(Model_Login log) {
        PreparedStatement st = null;
        ResultSet rs    = null;
        String Id       = null;
        String Nama     = null;
        String Level2   = null;
        String sql = "SELECT * FROM pengguna WHERE (id_pengguna='"+log.getId_user()+"' OR username='"+log.getUsername()+"') AND password ='"+log.getPass_user()+"'";
        try{            
            st = conn.prepareStatement(sql);
            rs= st.executeQuery();
            if(rs.next()){
                Id   = rs.getString("id_pengguna");
                Nama = rs.getString("nama_pengguna");
                Level2 = rs.getString("level");
                
                Menu_Utama menu   = new Menu_Utama(Id, Nama, Level2);
                menu.setVisible(true);
                menu.revalidate();
                
                Form_Login lg       = new Form_Login();
                lg.tutup = true;
            }else{
                JOptionPane.showMessageDialog(null, "Username dan Password Salah","Pesan",JOptionPane.INFORMATION_MESSAGE);
                Form_Login lg = new Form_Login();
                lg.tutup =false;
            }
        }catch (SQLException ex) {
            Logger.getLogger(DAO_Login.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAO_Login.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }
    
}
