package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.Class;
import com.qf.pojo.Employee;
import com.qf.pojo.Role;
import com.qf.pojo.User;
import com.qf.service.SuperAdminService;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import util.HanyuPinyinHelper;
import util.MD5Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class SuperAdminController {
    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    private SuperAdminService superAdminService;

    public SuperAdminService getSuperAdminService() {
        return superAdminService;
    }

    public void setSuperAdminService(SuperAdminService superAdminService) {
        this.superAdminService = superAdminService;
    }

    @RequestMapping("superadmin")
    public String superadmin(HttpSession session, HttpServletRequest request){
        String username = (String) session.getAttribute("username");
        User user = userService.getUserByName(username);
        request.setAttribute("user",user);
        return "superadmin";
    }
    @RequestMapping("employee")
    public String employee(@RequestParam(defaultValue = "1") int pageNum,HttpServletRequest request) {
        PageHelper.startPage(pageNum, 5);
        List<Employee> employeeList = superAdminService.getEmployeeList();
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(employeeList);
        request.setAttribute("pageInfo", pageInfo);
        return "employeetable";
    }
    @RequestMapping("addEmployeePage")
    public String addEmployeePage(){
        return "addEmployee";
    }

    @RequestMapping("addEmployee")
    public String addEmployee(Employee employee, MultipartFile file){
        String filename=System.currentTimeMillis()+file.getOriginalFilename();
        employee.setImg("img/"+filename);
        String path=System.getProperty("user.dir")+"/target/classes/static/img/"+filename;
        String path1=System.getProperty("user.dir")+"/src/main/resources/static/img/"+filename;
        File file1=new File(path);
        File file2=new File(path1);
        try {
            file.transferTo(file1);
            file.transferTo(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = superAdminService.addEmployee(employee);
        if (i>0){
            return "redirect:employee";
        }
        return "addEmployee";
    }

    @RequestMapping("updateEmpolyeePage")
    public String updateUserPage(HttpServletRequest request,int eid){
        Employee employee = superAdminService.getEmployeeById(eid);
        request.setAttribute("employee",employee);

        return "updateEmployee";
    }

    @RequestMapping("updateEmployee")
    public String updateUser(int eid,String ename,String position,MultipartFile file){
        String filename=file.getOriginalFilename();
        Employee employee = new Employee(eid,ename,position);
        employee.setImg("img/"+filename);
        System.out.println(employee);
        String path=System.getProperty("user.dir")+"/target/classes/static/img/"+filename;
        String path1=System.getProperty("user.dir")+"/src/main/resources/static/img/"+filename;
        File file1=new File(path);
        File file2=new File(path1);
        try {
            file.transferTo(file1);
            file.transferTo(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = superAdminService.updateEmployee(employee);
        if (i>0){
            return "redirect:employee";
        }
        return "updateEmployee";
    }

    @RequestMapping("deleteEmpolyee")
    public String deleteUser(int eid){
        int i =superAdminService.deleteEmployee(eid);
        if (i>0){
            return "redirect:employee";
        }
        return "fail";
    }


    @RequestMapping("selectEmployeeByName")
    public String selectUserByRoleName(String ename,HttpServletRequest request,@RequestParam(defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Employee> employeeList = superAdminService.getEmployeeByName(ename);
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(employeeList);
        request.setAttribute("pageInfo",pageInfo);
        return "employeetable";
    }

    //班级


    @RequestMapping("classroom")
    public String classroom(@RequestParam(defaultValue = "1") int pageNum,HttpServletRequest request) {
        PageHelper.startPage(pageNum, 5);
        List<Class> classList= superAdminService.getClassList();
        PageInfo<Class> pageInfo = new PageInfo<Class>(classList);
        request.setAttribute("pageInfo", pageInfo);
        return "classroomtable";
    }
    @RequestMapping("addClassroomPage")
    public String addClassroomPage(HttpServletRequest request){
        List<User> userList1 = userService.getUserListByRoleName("lecturer");
        List<User> userList2 = userService.getUserListByRoleName("class_teacher");
        request.setAttribute("userList1",userList1);
        request.setAttribute("userList2",userList2);
        return "addClassroom";
    }

    @RequestMapping("addClassroom")
    public String addClassroom(Class classroom){
        int i = superAdminService.addClass(classroom);
        if (i>0){
            return "redirect:classroom";
        }
        return "addClassroom";
    }

    @RequestMapping("updateClassroomPage")
    public String updateClassroomPage(HttpServletRequest request,int gid){
        List<User> userList1 = userService.getUserListByRoleName("lecturer");
        List<User> userList2 = userService.getUserListByRoleName("class_teacher");
        request.setAttribute("userList1",userList1);
        request.setAttribute("userList2",userList2);
        Class classroom = superAdminService.getClassById(gid);
        request.setAttribute("classroom",classroom);
        return "updateClassroom";
    }

    @RequestMapping("updateClassroom")
    public String updateClassroom(Class classroom){
        int i = superAdminService.updateClass(classroom);
        if (i>0){
            return "redirect:classroom";
        }
        return "updateClassroom";
    }

    @RequestMapping("deleteClassroom")
    public String deleteClassroom(int gid){
        int i =superAdminService.deleteClass(gid);
        if (i>0){
            return "redirect:classroom";
        }
        return "fail";
    }


    @RequestMapping("selectClassroomByName")
    public String selectClassroomByName(String gname,HttpServletRequest request,@RequestParam(defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Class> classList = superAdminService.getClassListByName(gname);
        PageInfo<Class> pageInfo = new PageInfo<Class>(classList);
        request.setAttribute("pageInfo",pageInfo);
        return "classroomtable";
    }
    }

