package com.qf.service;

import com.github.pagehelper.PageInfo;
import com.qf.pojo.Holiday;
import com.qf.pojo.User;
import com.qf.pojo.Vacate;

import java.util.List;

public interface PrincipalService {

    //查询个人基本信息   修改，修改密码
    public User getUserByUname(String username);

    //个人基本信息修改，修改密码
    public int updUserByUname(User user);

    //查询员工待审批假条
    public PageInfo<Vacate> getVacate(String username,int pageNum,int pageSize);

    //查询学生超过3天的待审批假条
    public PageInfo<Holiday> getHolidy(String username,int pageNum,int pageSize,String processDef);

    //员工请假审批
    public int updCheckedByVid(String username,int vid);

    //学生超过3天请假审批
    public int updCheckedByHid(String username,int hid);

    //查询所有的校长
    public List<User> getUserByRoleName(String rolename);

}
