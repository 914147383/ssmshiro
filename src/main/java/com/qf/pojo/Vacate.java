package com.qf.pojo;

public class Vacate {
    private int vid;
    private String vname;
    private String startdate;
    private String enddate;
    private String reason;
    private int state;

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public Vacate(int vid, String vname, String startdate, String enddate, String reson, int state) {
        this.vid = vid;
        this.vname = vname;
        this.startdate = startdate;
        this.enddate = enddate;
        this.reason = reson;
        this.state = state;
    }

    public Vacate(String vname, String startdate, String enddate, String reason, int state) {
        this.vname = vname;
        this.startdate = startdate;
        this.enddate = enddate;
        this.reason = reason;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Vacate() {
    }

    @Override
    public String toString() {
        return "Vacate{" +
                "vid=" + vid +
                ", vname='" + vname + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", reason='" + reason + '\'' +
                ", state=" + state +
                '}';
    }
}
