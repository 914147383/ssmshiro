package com.qf.mapper;

import com.qf.pojo.Employee;
import com.qf.pojo.Score;
import com.qf.pojo.Student;
import com.qf.pojo.Weekly;

import java.util.List;

public interface LeturerMapper {
    public Employee selectSelf(String ename);
    public String myClass(String ename);
    public List<Student> stuList(String sclass);
    public List<Weekly> selfWeekly(String username);
    public int updateScore(Weekly weekly);
    public Double selectScore(String stage, String sclass);
    public Double studentScore(String stage, String username);
    public List<Score> selectStudentScore(String sname);
    public int addScore(Score score);
}
