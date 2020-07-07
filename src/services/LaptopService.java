package services;

import models.LaptopEntity;

import java.sql.Connection;
import java.sql.ResultSet;
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

    public List<LaptopEntity> resultSet(String sql) {
        List<LaptopEntity> laptopEntities = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
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
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        if (laptopEntities.isEmpty()){
            System.out.println("Không có sản phẩm phù hợp");
        }else {
            System.out.println("|                                   Thong Tin                                                    |"
                                +"|  Hang Sx  |" +"|      Type      |"+"|       Gia       |"+"| Da Ban  |"+" Do Phan Giai |"
            );
            for (LaptopEntity le:laptopEntities
            ) {
                System.out.println(le.toString());
            }
        }
        return laptopEntities;
    }

    public List<LaptopEntity> findAllByMaker(String maker) {
        return resultSet("SELECT * FROM laptop WHERE maker='" + maker + "'");
    }

    public List<LaptopEntity> findByPrice(Long price, Long price2) {
        String sql = "";
        if (price != null && price2 == null) {
            sql = "Select*from laptop where price>=" + price;
        } else if (price2 != null && price == null) {
            sql = "Select*from laptop where price<=" + price2;
        } else if (price != null && price2 != null) {
            sql = "Select*from laptop where price between " + price + " and " + price2;
        }
        return resultSet(sql);
    }

    public List<LaptopEntity> findByHddAndName(Integer hdd, Integer ssd, String ten) {
        String sql = "";
        if (hdd != null) {
            sql = "Select * from laptop where hdd=" + hdd + " and maker='" + ten + "'";

        } else {
            sql = "Select * from laptop where ssd=" + ssd + " and maker='" + ten + "'";
        }
        return resultSet(sql);
    }

    public List<LaptopEntity> findByUserInput(Long priceFrom, Long priceTo, String maker, Float screenSize,
                                              String ram, String cpu, String type, String gpu, String sort) {
        String sql="select * from laptop where ";
        if (priceFrom!=null && priceTo!=null){
            sql+="price between "+priceFrom+" and "+priceTo+" ";
        }
        if (priceFrom!=null && priceTo==null){
            sql+="price>="+priceFrom+" ";
        }
        if (priceFrom==null && priceTo!=null){
            sql+="price<="+priceTo+" ";
        }
        if ( maker!=null){
            sql+=" and maker='"+maker+"' ";
        }
        if (screenSize!=null){
            sql+="and screensize="+screenSize+" ";
        }
        if (ram!=null){
            sql+="and ram="+ram+" ";
        }
        if (cpu!=null){
            sql+="and cpu like '%"+cpu+"%' ";
        }
        if (type!=null){
            sql+="and type like '%"+type+"%' ";
        }
        if (gpu!=null){
            sql+="and card='%"+gpu+"%' ";
        }
        if (priceFrom!=null || priceTo != null || maker!=null || screenSize != null || ram != null || cpu != null || type != null || gpu != null && sort!=null) {
            if (sort=="asc"){
                sql+="Order by price asc";
            }
            if (sort=="desc"){
                sql+="Order by price desc";
            }
        }
        if (sort!=null && priceFrom==null && priceTo == null && maker==null && screenSize == null && ram == null && cpu == null && type == null && gpu == null){
            if (sort=="asc"){
                sql="select*from laptop order by price asc";
            }
            if (sort=="desc"){
                sql="select * from laptop order by price desc";
            }
        }
        if (priceFrom==null && priceTo==null){
            String[] s = sql.split("and");
            for (int i = 0; i < s.length-1; i++) {

                if (i==0){
                    sql=s[0]+s[1];
                }else {
                    sql+="and"+s[i+1];
                }
            }
        }
        return resultSet(sql);
    }

    public List<LaptopEntity> orderBySold() {
       return resultSet("select * from laptop order by sold desc");
    }
}
