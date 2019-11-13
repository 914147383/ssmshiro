package com.qf.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.mapper.StudentMapper;
import com.qf.pojo.*;
import com.qf.pojo.Class;
import com.qf.service.StudentService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    private RepositoryService repositoryService;

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
    public PageInfo<Weekly> getWeekly(String uname,String time,String score, String title,int pageNum,int pageSize ) {
        PageHelper.startPage(pageNum,pageSize);
        List<Weekly> weekly = studentMapper.getWeekly(uname, title,time,score);
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

        /*repositoryService.deleteDeployment("1");
        repositoryService.deleteDeployment("2501");*/

        studentMapper.addHoliday(holiday);

        Student stu = studentMapper.getStu(holiday.getHname());
        Class classBy = studentMapper.getClassBy(stu.getSclass());
        int days = getDays(holiday.getStartdate(), holiday.getEnddate());

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("stuName",holiday.getHname());
        map.put("teaName",classBy.getLecturer());
        map.put("claName",classBy.getClass_teacher());
        map.put("BossName","b");
        map.put("days",days);


        //发起流程实例teaQingJia  stuQingJia1
        runtimeService.startProcessInstanceByKey("stuQingJia1",holiday.getHid()+"",map);
        //完成任务
        Task task = taskService.createTaskQuery().taskAssignee(holiday.getHname()).singleResult();
        taskService.complete(task.getId());
        return holiday.getHid();
    }
    /**
     * 老师申请请假
     * @param vacate
     * @return
     */
    @Override
    public int addVacate(Vacate vacate) {

        studentMapper.addVacate(vacate);

        Map<String,Object> map = new HashMap<String,Object>();

        map.put("teaName",vacate.getVname());
        map.put("bossName","b");

        //发起流程实例teaQingJia  stuQingJia1
        runtimeService.startProcessInstanceByKey("teaQingJia",vacate.getVid()+"",map);
        //完成任务
        Task task = taskService.createTaskQuery().processDefinitionKeyLike("teaQingJia").taskAssignee(vacate.getVname()).singleResult();
        taskService.complete(task.getId());
        return vacate.getVid();
    }

    @Override
    public Class getClassBy(String gname) {
        return studentMapper.getClassBy(gname);
    }

    @Override
    public Student getStu(String sname) {
        return studentMapper.getStu(sname);
    }

    /**
     * 获取请假天数
     */
    private int getDays(String startDate,String endDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date start = null;
        Date end = null;
        try {
            start = simpleDateFormat.parse(startDate);
            end = simpleDateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long daysTime = end.getTime() - start.getTime();
        int days = (int) (daysTime/(24*60*60*1000));
        return days;
    }


}
