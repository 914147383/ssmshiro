package com.qf.pojo;

public class Holiday {
    private int hid;
    private String hname;
    private String startdate;
    private String enddate;
    private String reason;
    private int state;
    private int state2;
    private int state3;

    public Holiday(int hid, String hname, String startdate, String enddate, String reason, int state, int state2, int state3) {
        this.hid = hid;
        this.hname = hname;
        this.startdate = startdate;
        this.enddate = enddate;
        this.reason = reason;
        this.state = state;
        this.state2 = state2;
        this.state3 = state3;
    }

    public int getState2() {
        return state2;
    }

    public void setState2(int state2) {
        this.state2 = state2;
    }

    public int getState3() {
        return state3;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Holiday(int hid, String hname, String startdate, String enddate, String reason, int state) {
        this.hid = hid;
        this.hname = hname;
        this.startdate = startdate;
        this.enddate = enddate;
        this.reason = reason;
        this.state = state;
    }

    public Holiday(String hname, String startdate, String enddate, String reason, int state) {
        this.hname = hname;
        this.startdate = startdate;
        this.enddate = enddate;
        this.reason = reason;
        this.state = state;
    }

    public void setState3(int state3) {
        this.state3 = state3;
    }

    public Holiday() {
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "hid=" + hid +
                ", hname='" + hname + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", reason='" + reason + '\'' +
                ", state=" + state +
                ", state2=" + state2 +
                ", state3=" + state3 +
                '}';
    }
}
