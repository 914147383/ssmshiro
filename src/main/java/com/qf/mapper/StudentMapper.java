package com.qf.mapper;

import com.qf.pojo.Class;
import com.qf.pojo.Holiday;
import com.qf.pojo.Student;
import com.qf.pojo.User;
import com.qf.pojo.Weekly;

import java.util.List;

public interface StudentMapper {

    //查询个人基本信息   修改，修改密码
    public User getUserByUname(String username);

    //个人基本信息修改，修改密码，图片---user表
    public int updUserByUname(User user);

    //修改了user表的信息，在student表，也得改一下----student表
    public int updStuByUname(User user);

    //新增周报
    public int addWeekly(Weekly weekly);

    //查询周报,可根据   标题  模糊查询
    public List<Weekly> getWeekly(String uname,String title,String time,String score);

    //删除周报，可删除未打分的周报
    public int delWeekly(String username,int wid);

    //申请请假
    public int addHoliday(Holiday holiday);

    //根据班级名称，查询讲师，班主任
    public Class getClassBy(String gname);

    //根据学生姓名，查查student表
    public Student getStu(String sname);
}
