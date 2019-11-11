package com.qf.pojo;

public class Class {
    private int gid;
    private  String gname;
    private  String lecturer;
    private  String class_teacher;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getClass_teacher() {
        return class_teacher;
    }

    public void setClass_teacher(String class_teacher) {
        this.class_teacher = class_teacher;
    }

    public Class(int gid, String gname, String lecturer, String class_teacher) {
        this.gid = gid;
        this.gname = gname;
        this.lecturer = lecturer;
        this.class_teacher = class_teacher;
    }

    public Class(String gname, String lecturer, String class_teacher) {
        this.gname = gname;
        this.lecturer = lecturer;
        this.class_teacher = class_teacher;
    }

    public Class() {
    }

    @Override
    public String toString() {
        return "Class{" +
                "gid=" + gid +
                ", gname='" + gname + '\'' +
                ", lecturer='" + lecturer + '\'' +
                ", class_teacher='" + class_teacher + '\'' +
                '}';
    }
}
