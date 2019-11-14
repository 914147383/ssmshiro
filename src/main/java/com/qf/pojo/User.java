package com.qf.pojo;

public class User {
    private int uid;
    private String name;
    private String username;
    private String password;
    private String rolename;
    private String img;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public User(int uid, String name, String username, String password, String rolename, String img) {
        this.uid = uid;
        this.name = name;
        this.username = username;
        this.password = password;
        this.rolename = rolename;
        this.img = img;
    }

    public User(String name, String username, String password, String rolename, String img) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.rolename = rolename;
        this.img = img;
    }

    public User(String username, String password, String img) {
        this.username = username;
        this.password = password;
        this.img = img;
    }

    public User(int uid, String name, String rolename, String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int uid, String name, String rolename, String password) {
        this.setUid(uid);
        this.setName(name);
        this.setRolename(rolename);
        this.setPassword(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rolename='" + rolename + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public User() {
    }
}
