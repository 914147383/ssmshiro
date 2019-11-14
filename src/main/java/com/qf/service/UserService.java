package com.qf.service;

import com.qf.pojo.User;

import java.util.List;

public interface UserService {
    public String getPassword(String username);

    public String getRoleName(String username);

    public User getUserById(int uid);

    public User getUserByName(String username);

    public List<User> getUserList();

    public List<User> getUserListByRoleName(String rolename);

    public int deleteUser(int uid);

    public int updateUser(User user);

    public int addUser(User user);
}
