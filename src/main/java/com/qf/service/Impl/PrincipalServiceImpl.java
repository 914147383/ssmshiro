package com.qf.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.mapper.PrincipalMapper;
import com.qf.mapper.StudentMapper;
import com.qf.pojo.Holiday;
import com.qf.pojo.User;
import com.qf.pojo.Vacate;
import com.qf.service.PrincipalService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrincipalServiceImpl implements PrincipalService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private PrincipalMapper principalMapper;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;


    @Override
    public User getUserByUname(String username) {
        return studentMapper.getUserByUname(username);
    }

    @Override
    public int updUserByUname(User user) {
        return principalMapper.updUserByUname(user);
    }

    @Override
    public PageInfo<Vacate> getVacate(String username,int pageNum,int pageSize) {
        return null;
    }

    /**
     * 查询学生带审批假条--------校长
     * @param username
     * @return
     */
    @Override
    public PageInfo<Holiday> getHolidy(String username,int pageNum,int pageSize) {
        //待办任务集合
        List<Task> list = taskService.createTaskQuery().taskAssignee(username).list();
        List<String> bussinessKeys = new ArrayList<String>();
        //循环遍历获取bussiness keys,即请假条的id
        for (Task task : list){
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId()).singleResult();
            bussinessKeys.add(processInstance.getBusinessKey());
        }

        System.out.println("bussinessKeys:"+bussinessKeys.size());

        if(bussinessKeys.size()>0){
            PageHelper.startPage(pageNum,pageSize);
            List<Holiday> holidy = principalMapper.getHolidy(bussinessKeys);
            PageInfo<Holiday> pageInfo = new PageInfo<Holiday>(holidy);
            return pageInfo;
        }
        return null;
    }

    @Override
    public int updCheckedByVid(int vid) {
        return principalMapper.updCheckedByVid(vid);
    }

    @Override
    public int updCheckedByHid(String username,int hid) {
        //完成任务
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(hid + "").taskAssignee(username).singleResult();
        taskService.complete(task.getId());
        //审核通过
        return principalMapper.updCheckedByHid(hid);

    }


}
