package models;

public class Statistic {
    private String maker;
    private int sold;
    private Float totalMoney;

    @Override
    public String toString() {
        return "Hang : " + maker +
                ", Da ban : " + sold;
    }

    public Statistic() {
    }

    public Statistic(String maker, int sold, Float totalMoney) {
        this.maker = maker;
        this.sold = sold;
        this.totalMoney = totalMoney;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public Float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Float totalMoney) {
        this.totalMoney = totalMoney;
    }
}
