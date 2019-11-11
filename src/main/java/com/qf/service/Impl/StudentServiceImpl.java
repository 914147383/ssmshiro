package com.qf.service.Impl;

import com.qf.mapper.StudentMapper;
import com.qf.pojo.Holiday;
import com.qf.pojo.User;
import com.qf.pojo.Weekly;
import com.qf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public User getUserByUname(String uname) {
        return studentMapper.getUserByUname(uname);
    }

    @Override
    public int updUserAndStuByUname(User user) {

        int i = studentMapper.updUserByUname(user);
        if(i>0){
            return studentMapper.updStuByUname(user);
        }
        return 0;
    }


    @Override
    public int addWeekly(Weekly weekly) {
        return studentMapper.addWeekly(weekly);
    }

    @Override
    public int addHoliday(Holiday holiday) {
        return studentMapper.addHoliday(holiday);
    }
}
