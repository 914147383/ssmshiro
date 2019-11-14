package com.qf.mapper;

import com.github.pagehelper.PageInfo;
import com.qf.pojo.Holiday;
import com.qf.pojo.User;
import com.qf.pojo.Vacate;

import java.util.List;

//校长
public interface PrincipalMapper {
    //查询个人基本信息   修改，修改密码
    //public User getUserByUname(String username);

    //个人基本信息修改，修改密码
    public int updUserByUname(User user);

    //查询员工待审批假条
    public List<Vacate> getVacate(List<String> list);

    //查询学生超过3天的待审批假条
    public List<Holiday> getHolidy(List<String> list);

    //员工请假审批
    public int updCheckedByVid(int vid);

    //学生超过3天请假审批state----班主任
    public int updCheckedByHid(int hid);
    //修改holiday里面的state2----讲师
    public int updCheckedByHidAndTea(int hid);
    //修改holiday里面的state3----校长
    public int updCheckedByHidAndBoss(int hid);

    //查询所有的校长
    public List<User> getUserByRoleName(String rolename);

}
