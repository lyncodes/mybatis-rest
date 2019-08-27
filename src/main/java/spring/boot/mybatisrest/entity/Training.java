package spring.boot.mybatisrest.entity;


import java.sql.Timestamp;

public class Training {

    private long trainingId;
    private long userId;
    private java.sql.Timestamp trainingStart;
    private java.sql.Timestamp trainingEnd;
    private String trainingKind;
    private String coach;
    private String position;
    private String performance;

    public Training(long trainingId, long userId, Timestamp trainingStart,
                    Timestamp trainingEnd, String trainingKind, String coach,
                    String position, String performance) {
        this.trainingId = trainingId;
        this.userId = userId;
        this.trainingStart = trainingStart;
        this.trainingEnd = trainingEnd;
        this.trainingKind = trainingKind;
        this.coach = coach;
        this.position = position;
        this.performance = performance;
    }


    public long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(long trainingId) {
        this.trainingId = trainingId;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public java.sql.Timestamp getTrainingStart() {
        return trainingStart;
    }

    public void setTrainingStart(java.sql.Timestamp trainingStart) {
        this.trainingStart = trainingStart;
    }


    public java.sql.Timestamp getTrainingEnd() {
        return trainingEnd;
    }

    public void setTrainingEnd(java.sql.Timestamp trainingEnd) {
        this.trainingEnd = trainingEnd;
    }


    public String getTrainingKind() {
        return trainingKind;
    }

    public void setTrainingKind(String trainingKind) {
        this.trainingKind = trainingKind;
    }


    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

}
