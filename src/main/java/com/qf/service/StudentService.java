package com.qf.service;

import com.github.pagehelper.PageInfo;
import com.qf.pojo.Holiday;
import com.qf.pojo.User;
import com.qf.pojo.Weekly;

import java.util.List;

public interface StudentService {

    //查询个人基本信息   修改，修改密码
    public User getUserByUname(String uname);

    /*
    *个人基本信息修改，修改密码，图片---user表
    * 修改了user表的信息，在student表，也得改一下----student表
     */
    public int updUserAndStuByUname(User user);


    // public int updStuByUname(User user);

    //新增周报
    public int addWeekly(Weekly weekly);

    //查询周报,可根据   标题  模糊查询
    public PageInfo<Weekly> getWeekly(String uname, String title,int pageNum,int pageSize);

    //删除周报，可删除未打分的周报
    public int delWeekly(String username,int wid);

    //申请请假
    public int addHoliday(Holiday holiday);

}
