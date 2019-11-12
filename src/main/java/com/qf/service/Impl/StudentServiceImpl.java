package com.qf.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.mapper.StudentMapper;
import com.qf.pojo.Holiday;
import com.qf.pojo.User;
import com.qf.pojo.Weekly;
import com.qf.service.StudentService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
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
    public PageInfo<Weekly> getWeekly(String uname, String title,int pageNum,int pageSize ) {
        PageHelper.startPage(pageNum,pageSize);
        List<Weekly> weekly = studentMapper.getWeekly(uname, title);
        PageInfo<Weekly> pageInfo = new PageInfo<Weekly>(weekly);
        return pageInfo;
    }

    @Override
    public int delWeekly(String username, int wid) {
        return studentMapper.delWeekly(username, wid);
    }

    /**
     * 学生申请请假
     * @param holiday
     * @return
     */
    @Override
    public int addHoliday(Holiday holiday) {
        studentMapper.addHoliday(holiday);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("stuName",holiday.getHname());
        map.put("teaName","t");
        map.put("claName","c");
        map.put("bossName","b");
        map.put("days",3);

        //发起流程实例
        runtimeService.startProcessInstanceByKey("stuQingJia",holiday.getHid()+"",map);
        //完成任务
        Task task = taskService.createTaskQuery().taskAssignee(holiday.getHname()).singleResult();
        taskService.complete(task.getId());
        return holiday.getHid();
    }
}
