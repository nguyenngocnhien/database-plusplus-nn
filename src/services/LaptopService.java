package services;

import models.Counter;
import models.LaptopEntity;
import models.Statistic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
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
        String sql="select * from laptop where true ";
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
        if (sort!=null) {
            if (sort=="asc"){
                sql+="Order by price asc";
            }
            if (sort=="desc"){
                sql+="Order by price desc";
            }
        }
        return resultSet(sql);
    }

    public List<LaptopEntity> orderBySold() {
       return resultSet("select * from laptop order by sold desc limit 1");
    }
    public  List<Counter> getCounterByMaker(){
        try {
            List<Counter> counters = new ArrayList<>();
            String sql = "SELECT maker , count(*) AS quantity from laptop GROUP BY maker ORDER BY quantity Desc";
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                Counter counter = new Counter();
                counter.setMaker(rs.getString(1));
                counter.setQuantity(rs.getInt(2));
                counters.add(counter);
            }
            return counters;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public  List<Statistic> getStatisticByMaker(){
        List<Statistic> statistics = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String sql ="SELECT MAKER,SUM(Sold) AS Sold , SUM(Price*Sold) AS totalMoney FROM laptop GROUP BY MAKER ORDER BY totalMoney";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                Statistic statistic = new Statistic();
                statistic.setMaker(rs.getString(1));
                statistic.setSold(rs.getInt(2));
                statistic.setTotalMoney(rs.getFloat(3));
                statistics.add(statistic);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statistics;
    }
}
