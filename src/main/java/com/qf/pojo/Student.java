package com.qf.pojo;

public class Student {
    private int sid;
    private String sname;
    private String sclass;
    private String img;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Student() {
    }

    public Student(int sid, String sname, String sclass, String img) {
        this.sid = sid;
        this.sname = sname;
        this.sclass = sclass;
        this.img = img;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sclass='" + sclass + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
