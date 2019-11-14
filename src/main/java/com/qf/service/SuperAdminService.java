package com.qf.service;

import com.qf.pojo.Class;
import com.qf.pojo.Employee;

import java.util.List;

public interface SuperAdminService {
    //员工表
    public int addEmployee(Employee employee);

    public int deleteEmployee(int eid);

    public int updateEmployee(Employee employee);

    public Employee getEmployeeById(int eid);

    public List<Employee>  getEmployeeByName(String ename);

    public List<Employee> getEmployeeList();

    //班级表
    public int addClass(Class classroom);

    public int deleteClass(int gid);

    public int updateClass(Class classroom);

    public Class getClassById(int gid);

    public List<Class> getClassList();

    public List<Class> getClassListByName(String gname);


}
