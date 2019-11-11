package com.qf.pojo;

public class Employee {
    private int eid;
    private String ename;
    private String position;
    private String img;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Employee(int eid, String ename, String position, String img) {
        this.eid = eid;
        this.ename = ename;
        this.position = position;
        this.img = img;
    }

    public Employee(String ename, String position, String img) {
        this.ename = ename;
        this.position = position;
        this.img = img;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", ename='" + ename + '\'' +
                ", position='" + position + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
