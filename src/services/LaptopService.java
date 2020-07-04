package services;

import models.LaptopEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LaptopService {
    private Connection con;

    public LaptopService() {
    }

    public LaptopService(Connection con) {
        this.con = con;
    }
    public List<LaptopEntity> findAllByMaker(String maker) {
        try {
            List<LaptopEntity> laptopEntities = new ArrayList<>();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM laptop WHERE maker='" + maker + "'");
            while (rs.next()) {
                LaptopEntity laptopEntity = new LaptopEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getFloat(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getInt(14),
                        rs.getTimestamp(15),
                        rs.getTimestamp(16)
                );
                laptopEntities.add(laptopEntity);
            }
            return laptopEntities;
        } catch (Exception e) {
            System.out.println("Error when find laptop by maker. " + e);
        }
        return null;
    }

    public List<LaptopEntity> findByPrice(Long price, Long price2) {
        List<LaptopEntity> laptopEntities = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String sql = "";
            if (price != null && price2 == null) {
                sql = "Select*from laptop where price>=" + price;
            } else if (price2 != null && price == null) {
                sql = "Select*from laptop where price<=" + price2;
            } else if (price != null && price2 != null) {
                sql = "Select*from laptop where price between " + price + " and " + price2;
            }
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                LaptopEntity laptopEntity = new LaptopEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getFloat(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getInt(14),
                        rs.getTimestamp(15),
                        rs.getTimestamp(16)
                );
                laptopEntities.add(laptopEntity);
            }
        }
            catch(SQLException e){
                e.printStackTrace();
            }
            return laptopEntities;
        }

        public List<LaptopEntity> findByHddAndName(Integer hdd,Integer ssd, String ten) {
        List<LaptopEntity> laptopEntities = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String sql="";
            if(hdd!=null){
               sql= "Select * from laptop where hdd="+hdd+" and maker='"+ten+"'";

            }else{
                sql = "Select * from laptop where ssd="+ssd+" and maker='"+ten+"'";
            }
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                LaptopEntity laptopEntity = new LaptopEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getFloat(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getInt(14),
                        rs.getTimestamp(15),
                        rs.getTimestamp(16)
                );
                laptopEntities.add(laptopEntity);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return laptopEntities;
    }
    public List<LaptopEntity> findByUserInput(Long priceFrom,Long priceTo,String maker,String screenSize,
                                                      String ram,String cpu,String type,String sort,String gpu)
    {
        List <LaptopEntity> laptopEntities = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String sql="";
            if (priceFrom==null){
                sql = "Select * from laptop where price<"+priceTo+" and maker ="+maker+" and screen_isze ="+screenSize+"";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
