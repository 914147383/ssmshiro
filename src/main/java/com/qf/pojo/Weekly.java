package com.qf.pojo;

public class Weekly {
    private int wid;
    private String username;
    private String title;
    private String time;
    private String report;
    private int score;

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Weekly() {
    }

    @Override
    public String toString() {
        return "Weekly{" +
                "wid=" + wid +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", report='" + report + '\'' +
                ", score=" + score +
                '}';
    }
}
