package DAO;

import Config.Koneksi;
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
import Service.Service_Laporan;
import java.awt.BorderLayout;
import java.io.InputStream;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

public class DAO_Laporan implements Service_Laporan{

    private Connection connection;
    
    public DAO_Laporan(){
        connection = Koneksi.getConnection();
    }
    
    
    @Override
    public void lapSuratPemesananBarang(String no) {
        try{
            String file = "src/Report/LapPesan.jasper";
            HashMap parameter = new HashMap();
            parameter.put("no", no);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
                JasperViewer.viewReport(print, false);
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
    
    @Override
    public void lapSuratPemesananBarangPerTransaksi(JPanel jp, String no) {
        String file = "src/Report/LapPesan.jasper";
        try{
            InputStream Report;
            Report = getClass().getResourceAsStream(file);
            HashMap parameter = new HashMap();
            parameter.put("no", no);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
            //tampil panel
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
   
    @Override
    public void lapPemesananBarangPeriode(JPanel jp, String tgl1, String tgl2) {
        String file = "src/Report/LapPesanPerPeriode.jasper";
        try{
            InputStream Report;
            Report = getClass().getResourceAsStream(file);
            HashMap parameter = new HashMap();
            parameter.put("tgl1", tgl1);
            parameter.put("tgl2", tgl2);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
            //tampil panel
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    @Override
    public void lapPemesananBarangPerbulan(JPanel jp, String dt1, int dt2) {
        String file = "src/Report/LapPesanPerPerbulan.jasper";
        try{
            InputStream Report;
            Report = getClass().getResourceAsStream(file);
            HashMap parameter = new HashMap();
            parameter.put("dt1", dt1);
            parameter.put("dt2", dt2);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
            //tampil panel
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    @Override
    public void lapPemesananBarangPerDistributor(JPanel jp, String id_dis) {
        String file = "src/Report/LapPesanPerDistributor.jasper";
        try{
            InputStream Report;
            Report = getClass().getResourceAsStream(file);
            HashMap parameter = new HashMap();
            parameter.put("id", id_dis);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
            //tampil panel
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    @Override
    public void lapBarangMasuk(String no) {
        try{
            String file = "src/Report/LapBarangMasuk.jasper";
            HashMap parameter = new HashMap();
            parameter.put("no", no);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
                JasperViewer.viewReport(print, false);
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
    
    @Override
    public void lapBarangMasukPerTransaksi(JPanel jp, String no) {
        String file = "src/Report/LapBarangMasuk.jasper";
        try{
            InputStream Report;
            Report = getClass().getResourceAsStream(file);
            HashMap parameter = new HashMap();
            parameter.put("no", no);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
            
            //tampil panel
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
    
    @Override
    public void lapBarangMasukPeriode(JPanel jp, String tgl1, String tgl2) {
        String file = "src/Report/LapMasukPerPeriode.jasper";
        try{
            InputStream Report;
            Report = getClass().getResourceAsStream(file);
            HashMap parameter = new HashMap();
            parameter.put("tgl1", tgl1);
            parameter.put("tgl2", tgl2);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
            //tampil panel
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    @Override
    public void lapBarangMasukPerbulan(JPanel jp, String dt1, int dt2) {
        String file = "src/Report/LapMasukPerPerbulan.jasper";
        try{
            InputStream Report;
            Report = getClass().getResourceAsStream(file);
            HashMap parameter = new HashMap();
            parameter.put("dt1", dt1);
            parameter.put("dt2", dt2);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
            //tampil panel
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    @Override
    public void lapBarangMasukPerDistributor(JPanel jp, String id_dis) {
        String file = "src/Report/LapMasukPerDistributor.jasper";
        try{
            InputStream Report;
            Report = getClass().getResourceAsStream(file);
            HashMap parameter = new HashMap();
            parameter.put("id", id_dis);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
            //tampil panel
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    @Override
    public void lapBarangKeluar(String no) {
        try{
            String file = "src/Report/LapBarangKeluar.jasper";
            HashMap parameter = new HashMap();
            parameter.put("no", no);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
                JasperViewer.viewReport(print, false);
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    @Override
    public void lapBarangKeluarPerTransaksi(JPanel jp, String no) {
        String file = "src/Report/LapBarangKeluar.jasper";
        try{
            InputStream Report;
            Report = getClass().getResourceAsStream(file);
            HashMap parameter = new HashMap();
            parameter.put("no", no);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
            
            //tampil panel
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    @Override
    public void lapBarangKeluarPeriode(JPanel jp, String tgl1, String tgl2) {
        String file = "src/Report/LapKeluarPerPeriode.jasper";
        try{
            InputStream Report;
            Report = getClass().getResourceAsStream(file);
            HashMap parameter = new HashMap();
            parameter.put("tgl1", tgl1);
            parameter.put("tgl2", tgl2);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
            //tampil panel
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    @Override
    public void lapBarangKeluarPerbulan(JPanel jp, String dt1, int dt2) {
        String file = "src/Report/LapKeluarPerPerbulan.jasper";
        try{
            InputStream Report;
            Report = getClass().getResourceAsStream(file);
            HashMap parameter = new HashMap();
            parameter.put("dt1", dt1);
            parameter.put("dt2", dt2);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
            //tampil panel
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    @Override
    public void lapDataBarang(JPanel jp) {
        String file = "src/Report/LapDataBarang.jasper";
        try{
            InputStream Report;
            Report = getClass().getResourceAsStream(file);
            HashMap parameter = new HashMap();
            //parameter.put("no", no);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
            //tampil panel
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    @Override
    public void lapDataPengguna(JPanel jp) {
        String file = "src/Report/LapDataPengguna.jasper";
        try{
            InputStream Report;
            Report = getClass().getResourceAsStream(file);
            HashMap parameter = new HashMap();
            //parameter.put("no", no);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
            //tampil panel
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }    

    @Override
    public void lapDataDistributor(JPanel jp) {
        String file = "src/Report/LapDataDistributor.jasper";
        try{
            InputStream Report;
            Report = getClass().getResourceAsStream(file);
            HashMap parameter = new HashMap();
            //parameter.put("no", no);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
            //tampil panel
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }     
    
    @Override
    public void lapTransaksiStokBarang(JPanel jp) {
        String file = "src/Report/LapStokBarang.jasper";
        try{
            InputStream Report;
            Report = getClass().getResourceAsStream(file);
            HashMap parameter = new HashMap();
            //parameter.put("no", no);
            JasperPrint print = JasperFillManager.fillReport(file, parameter, connection);
            //tampil panel
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

}