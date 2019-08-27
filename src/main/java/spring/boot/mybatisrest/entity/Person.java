package spring.boot.mybatisrest.entity;


import java.sql.Timestamp;

public class Person {

    public Person(long userId, String name, Timestamp birthday,
                  String edu, String marry, long income, String gender,
                  String career) {
        this.userId = userId;
        this.name = name;
        this.birthday = birthday;
        this.edu = edu;
        this.marry = marry;
        this.income = income;
        this.gender = gender;
        this.career = career;
    }

    private long userId;
    private String name;
    private java.sql.Timestamp birthday;
    private String edu;
    private String marry;
    private long income;
    private String gender;
    private String career;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public java.sql.Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Timestamp birthday) {
        this.birthday = birthday;
    }


    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }


    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry;
    }


    public long getIncome() {
        return income;
    }

    public void setIncome(long income) {
        this.income = income;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

}
