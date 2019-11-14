package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.Role;
import com.qf.pojo.User;
import com.qf.service.RoleService;
import com.qf.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import util.HanyuPinyinHelper;
import util.MD5Utils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
public class UserController {
       @Autowired
    private SecurityManager securityManager;

    public SecurityManager getSecurityManager() {
        return securityManager;
    }

    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }
    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private RoleService roleService;

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    @RequestMapping("loginPage")
    public String loginPage(){
      return "login";
    }
    @RequestMapping("log")
    public String login(String username,String password,HttpServletRequest request){
        SecurityUtils.setSecurityManager(securityManager);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(usernamePasswordToken);
            if(subject.isAuthenticated()){
                Session session = subject.getSession();
                session.setAttribute("username",username);
                User user = userService.getUserByName(username);
               session.setAttribute("name",user.getName());
               request.setAttribute("user",user);
                return "tables";
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:loginPage";
    }
    @RequestMapping("user")
    public String course(@RequestParam(defaultValue = "1") int pageNum, HttpServletRequest request){
        PageHelper.startPage(pageNum, 5);
        List<User> userList = userService.getUserList();
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        request.setAttribute("pageInfo",pageInfo);
        return "usertable";
    }


    @RequestMapping("addUserPage")
    public String addRolePage(HttpServletRequest request){
        List<Role> roleList = roleService.getRoleList();
        request.setAttribute("roleList",roleList);
        return "addUser";
    }

    @RequestMapping("addUser")
    public String addUser(User user, MultipartFile file){
        String filename=System.currentTimeMillis()+file.getOriginalFilename();
        user.setImg("img/"+filename);
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
        String a = user.getName();
        String b = HanyuPinyinHelper.toHanyuPinyin(a);
        user.setUsername(b);

        int i = userService.addUser(user);
        if (i>0){
            return "redirect:user";
        }
        return "addUserPage";
    }

    @RequestMapping("updateUserPage")
    public String updateUserPage(HttpServletRequest request,int uid){
        User user = userService.getUserById(uid);
        request.setAttribute("user",user);
        List<Role> roleList = roleService.getRoleList();
        request.setAttribute("roleList",roleList);
        return "updateuser";
    }

    @RequestMapping("updateUser")
    public String updateUser(int uid,String name,String rolename,String password,MultipartFile file){
        String filename=file.getOriginalFilename();
        User user = new User(uid,name,rolename,password);
        user.setImg("img/"+filename);
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
        String b = HanyuPinyinHelper.toHanyuPinyin(user.getName());
        user.setUsername(b);

        String password2 = MD5Utils.md5(user.getPassword());
        user.setPassword(password2);
        int i = userService.updateUser(user);
        if (i>0){
            return "redirect:user";
        }
        return "updateUser";
    }

    @RequestMapping("deleteUser")
    public String deleteUser(int uid){
        int i =userService.deleteUser(uid);
        if (i>0){
            return "redirect:user";
        }
        return "fail";
    }


    @RequestMapping("selectUserByRoleName")
    public String selectUserByRoleName(String rolename,HttpServletRequest request,@RequestParam(defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<User> userList = userService.getUserListByRoleName(rolename);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        request.setAttribute("pageInfo",pageInfo);
        return "usertable";
    }
}

