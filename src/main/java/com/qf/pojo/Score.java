package com.qf.pojo;

public class Score {
    private int sid;
    private String sname;
    private String sclass;
    private String stage;
    private int score;

    public Score(int sid, String sname, String sclass, String stage, int score) {
        this.sid = sid;
        this.sname = sname;
        this.sclass = sclass;
        this.stage = stage;
        this.score = score;
    }

    public Score(int score) {
        this.score = score;
    }

    public Score() {
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sclass='" + sclass + '\'' +
                ", stage='" + stage + '\'' +
                ", score=" + score +
                '}';
    }
}
