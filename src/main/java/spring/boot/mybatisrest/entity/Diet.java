package spring.boot.mybatisrest.entity;


import java.sql.Timestamp;

public class Diet {

    public Diet(long dietId, long userId, Timestamp dietStart,
                Timestamp dietEnd, String stapleKind, long stapleAmount,
                long water, long protein, String vegetableKind, long vegetableAmount) {
        this.dietId = dietId;
        this.userId = userId;
        this.dietStart = dietStart;
        this.dietEnd = dietEnd;
        this.stapleKind = stapleKind;
        this.stapleAmount = stapleAmount;
        this.water = water;
        this.protein = protein;
        this.vegetableKind = vegetableKind;
        this.vegetableAmount = vegetableAmount;
    }

    private long dietId;
    private long userId;
    private java.sql.Timestamp dietStart;
    private java.sql.Timestamp dietEnd;
    private String stapleKind;
    private long stapleAmount;
    private long water;
    private long protein;
    private String vegetableKind;
    private long vegetableAmount;


    public long getDietId() {
        return dietId;
    }

    public void setDietId(long dietId) {
        this.dietId = dietId;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public java.sql.Timestamp getDietStart() {
        return dietStart;
    }

    public void setDietStart(java.sql.Timestamp dietStart) {
        this.dietStart = dietStart;
    }


    public java.sql.Timestamp getDietEnd() {
        return dietEnd;
    }

    public void setDietEnd(java.sql.Timestamp dietEnd) {
        this.dietEnd = dietEnd;
    }


    public String getStapleKind() {
        return stapleKind;
    }

    public void setStapleKind(String stapleKind) {
        this.stapleKind = stapleKind;
    }


    public long getStapleAmount() {
        return stapleAmount;
    }

    public void setStapleAmount(long stapleAmount) {
        this.stapleAmount = stapleAmount;
    }


    public long getWater() {
        return water;
    }

    public void setWater(long water) {
        this.water = water;
    }


    public long getProtein() {
        return protein;
    }

    public void setProtein(long protein) {
        this.protein = protein;
    }


    public String getVegetableKind() {
        return vegetableKind;
    }

    public void setVegetableKind(String vegetableKind) {
        this.vegetableKind = vegetableKind;
    }


    public long getVegetableAmount() {
        return vegetableAmount;
    }

    public void setVegetableAmount(long vegetableAmount) {
        this.vegetableAmount = vegetableAmount;
    }

}
