
package com.qf.service.impl;

import com.qf.mapper.LeturerMapper;
import com.qf.pojo.Employee;
import com.qf.pojo.Score;
import com.qf.pojo.Student;
import com.qf.pojo.Weekly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qf.service.LecturerService;

import java.util.List;

@Service
public class LecturerServiceImpl implements LecturerService  {
    @Autowired
    private LeturerMapper leturerMapper;

    public LeturerMapper getLeturerMapper() {
        return leturerMapper;
    }

    public void setLeturerMapper(LeturerMapper leturerMapper) {
        this.leturerMapper = leturerMapper;
    }

    @Override
    public Employee selectSelf(String ename) {
        return leturerMapper.selectSelf(ename);
    }

    @Override
    public String myClass(String ename) {
        return leturerMapper.myClass(ename);
    }

    @Override
    public List<Student> stuList(String sclass) {
        return leturerMapper.stuList(sclass);
    }

    @Override
    public List<Weekly> selfWeekly(String username) {
        return leturerMapper.selfWeekly(username);
    }

    @Override
    public int updateScore(Weekly weekly) {
        return leturerMapper.updateScore(weekly);
    }

    @Override
    public Double selectScore(String stage,String sclass) {
        return leturerMapper.selectScore(stage,sclass);
    }

    @Override
    public Double studentScore(String stage, String username) {
        return leturerMapper.studentScore(stage,username);
    }

    @Override
    public List<Score> selectStudentScore(String sname) {
        return leturerMapper.selectStudentScore(sname);
    }

    @Override
    public int addScore(Score score) {
        return leturerMapper.addScore(score);
    }


}
