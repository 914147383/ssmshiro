package com.qf.service.Impl;

import com.qf.mapper.SuperAdminMapper;
import com.qf.pojo.Class;
import com.qf.pojo.Employee;
import com.qf.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SuperAdminServiceImpl implements SuperAdminService {
    @Autowired
    private SuperAdminMapper superAdminMapper;

    public SuperAdminMapper getSuperAdminMapper() {
        return superAdminMapper;
    }

    public void setSuperAdminMapper(SuperAdminMapper superAdminMapper) {
        this.superAdminMapper = superAdminMapper;
    }

    @Override
    public int addEmployee(Employee employee) {
        return superAdminMapper.addEmployee(employee);
    }

    @Override
    public int deleteEmployee(int eid) {
        return superAdminMapper.deleteEmployee(eid);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return superAdminMapper.updateEmployee(employee);
    }

    @Override
    public Employee getEmployeeById(int eid) {
        return superAdminMapper.getEmployeeById(eid);
    }

    @Override
    public List<Employee> getEmployeeByName(String ename) {
        return superAdminMapper.getEmployeeByName(ename);
    }

    @Override
    public List<Employee> getEmployeeList() {
        return superAdminMapper.getEmployeeList();
    }

    @Override
    public int addClass(Class classroom) {
        return superAdminMapper.addClass(classroom);
    }

    @Override
    public int deleteClass(int gid) {
        return superAdminMapper.deleteEmployee(gid);
    }

    @Override
    public int updateClass(Class classroom) {
        return superAdminMapper.updateClass(classroom);
    }

    @Override
    public Class getClassById(int gid) {
        return superAdminMapper.getClassById(gid);
    }

    @Override
    public List<Class> getClassList() {
        return superAdminMapper.getClassList();
    }

    @Override
    public List<Class> getClassListByName(String gname) {
        return superAdminMapper.getClassListByName(gname);
    }
}
