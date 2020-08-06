package services;

import com.sun.jdi.event.StepEvent;

import java.sql.*;

public class CarService {
    private Connection con;

    public CarService() {
    }

    public CarService(Connection con) {
        this.con = con;
    }
    public void xe5Cho() {
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT DongXe,SoChoNgoi FROM DONGXE WHERE SoChoNgoi>5";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String dongXe = rs.getString(1);
                String soChoNgoi = rs.getString(2);
                System.out.println(dongXe + ' ' + soChoNgoi);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void thongTinNCC() {
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT distinct ncc.MaNhaCC,ncc.DiaChi,ncc.TenNhaCC,ncc.SoDT,ncc.MaSoThue,dx.HangXe,mp.DonGia FROM NHACUNGCAP ncc" +
                    " JOIN DANGKYCUNGCAP dkcc ON ncc.MaNhaCC = dkcc.MaNhaCC JOIN MUCPHI mp ON dkcc.MaMP = mp.MaMP " +
                    "JOIN DONGXE dx ON dx.DongXe = dkcc.DongXe WHERE dx.HangXe='KIA' and mp.DonGia=20000 or dx.Hangxe like  'Toyota' And mp.DonGia=15000";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                String maNCC = rs.getString(1);
                String diaChi= rs.getString(2);
                String tenNCC = rs.getString(3);
                String soDT = rs.getString(4);
                String mST = rs.getString(5);
                String hangXe = rs.getString(6);
                String donGia = rs.getString(7);
                System.out.println(maNCC+"  "+diaChi+"  "+tenNCC+"  "+soDT+"  "+mST+"  "+hangXe+"  "+donGia);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void sapXep(){
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM NHACUNGCAP ORDER BY TenNhaCC ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                String maNCC = rs.getNString(1);
                String tenNCC = rs.getNString(2);
                String diaChi = rs.getNString(3);
                int sDT = rs.getInt(4);
                int maST = rs.getInt(5);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
     public void dangKyCC(){
         try {
             Statement stmt = con.createStatement();
             String sql = "SELECT TenNhaCC,count(*) AS SoLuongDangKy FROM (SELECT DANGKYCUNGCAP.MaNhaCC,NHACUNGCAP.TenNhaCC FROM NHACUNGCAP,DANGKYCUNGCAP\n" +
                     "\tWHERE NHACUNGCAP.MaNhaCC = DANGKYCUNGCAP.MaNhaCC and DANGKYCUNGCAP.NgayBatDauCungCap='2015-11-20 00:00:00') AS A group by TenNhaCC";
             ResultSet rs = stmt.executeQuery(sql);
             while (rs.next()){
                 String tenNCC = rs.getString(1);
                 int slDangKy = rs.getInt(2);
                 System.out.println(tenNCC+" "+slDangKy);
             }
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         }
     }
     public void hangXe(){
         try {
             Statement stmt = con.createStatement();
             String sql ="SELECT DISTINCT DONGXE.HangXe FROM DONGXE";
             ResultSet rs = stmt.executeQuery(sql);
             while (rs.next()){
                 String xe = rs.getString(1);
                 System.out.println("Hang xe : ");
                 System.out.println(xe);
             }
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         }
     }
     public void phuongtienDKCC(){
         try {
             Statement stmt = con.createStatement();
             String sql = "SELECT dkcc.MaDKCC, ncc.MaNhaCC, ncc.TenNhaCC, ncc.DiaChi, ncc.MaSoThue, ldv.TenLoaiDV, mp.DonGia,\n" +
                     "dx.HangXe, dkcc.NgayBatDauCungCap, dkcc.NgayKetThucCungCap FROM NHACUNGCAP ncc\n" +
                     "LEFT JOIN DANGKYCUNGCAP dkcc on ncc.MaNhaCC = dkcc.MaNhaCC\n" +
                     "LEFT JOIN MUCPHI mp on dkcc.MaMP = mp.MaMP\t\n" +
                     "LEFT JOIN LOAIDICHVU ldv ON dkcc.MaLoaiDV = ldv.MaLoaiDV\n" +
                     "LEFT JOIN DONGXE dx ON dkcc.DongXe = dx.DongXe";
             ResultSet rs = stmt.executeQuery(sql);
             while (rs.next()){
                 String maDKCC = rs.getNString(1);
                 String maNCC = rs.getNString(2);
                 String tenNCC = rs.getNString(3);
                 String diaChi = rs.getNString(4);
                 int maST = rs.getInt(5);
                 String tenDV = rs.getNString(6);
                 int donGia = rs.getInt(7);
                 String hangX = rs.getNString(8);
                 Date ngayBDCC = rs.getDate(9);
                 Date ngayKTCC = rs.getDate(10);
                 System.out.println(maDKCC+" "+maNCC+" "+tenNCC+" "+diaChi+" "+maST+" "+tenDV+" "+donGia+" "+hangX+" "+ngayBDCC+" "+ngayKTCC);
             }
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         }
     }
     public void hiaceCeratoDKCC(){
         try {
             Statement stmt = con.createStatement();
             String sql = "SELECT distinct ncc.MaNhaCC,ncc.DiaChi,ncc.TenNhaCC,ncc.SoDT,ncc.MaSoThue,dx.DongXe,mp.DonGia FROM NHACUNGCAP ncc JOIN DANGKYCUNGCAP dkcc ON ncc.MaNhaCC = dkcc.MaNhaCC \n" +
                     "JOIN MUCPHI mp ON dkcc.MaMP = mp.MaMP\n" +
                     "JOIN DONGXE dx ON dx.DongXe = dkcc.DongXe\n" +
                     "WHERE dx.DongXe='Hiace'  or dx.DongXe\t like  'Cerato'";
             ResultSet rs = stmt.executeQuery(sql);
             while(rs.next()){
                 String maNCC = rs.getNString(1);
                 String diaChi = rs.getNString(2);
                 String tenNCC = rs.getNString(3);
                 long sDT= rs.getLong(4);
                 int maST = rs.getInt(5);
                 String dongXe = rs.getNString(6);
                 int donGia = rs.getInt(7);
                 System.out.println(maNCC+" "+diaChi+" "+tenNCC+" "+sDT+" "+maST+" "+dongXe+" "+donGia);
             }
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         }
     }
     public void nCCChuaDK(){
         try {
             Statement stmt = con.createStatement();
             String sql = "SELECT ncc.MaNhaCC,ncc.TenNhaCC,ncc.DiaChi,ncc.SoDT,ncc.MaSoThue FROM NHACUNGCAP ncc left JOIN DANGKYCUNGCAP dkcc\n" +
                     "ON ncc.MaNhaCC = dkcc.MaNhaCC where dkcc.MaDKCC is null";
             ResultSet rs = stmt.executeQuery(sql);
             while (rs.next()){
                 String maNCC = rs.getString(1);
                 String tenNCC = rs.getString(2);
                 String diaChi = rs.getString(3);
                 long soDT = rs.getLong(4);
                 int maST = rs.getInt(5);
                 System.out.println(maNCC+" "+tenNCC+" "+diaChi+" "+soDT+" "+maST);
             }
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         }
     }

}