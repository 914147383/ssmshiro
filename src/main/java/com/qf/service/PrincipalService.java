package com.qf.service;

import com.qf.pojo.User;

public interface PrincipalService {

    //查询个人基本信息   修改，修改密码
    public User getUserByUname(String username);

    //个人基本信息修改，修改密码
    public int updUserByUname(User user);

    //员工请假审批
    public int updCheckedByVid(int vid);

    //学生超过3天请假审批
    public int updCheckedByHid(int hid);
}
