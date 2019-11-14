package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.Course;
import com.qf.pojo.Role;
import com.qf.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping("role")
    public String course(@RequestParam(defaultValue = "1") int pageNum, HttpServletRequest request){
        PageHelper.startPage(pageNum, 5);
        List<Role> roleList = roleService.getRoleList();
        PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
        request.setAttribute("pageInfo",pageInfo);
        return "roletable";
    }


    @RequestMapping("addRolePage")
    public String addRolePage(){
        return "addRole";
    }

    @RequestMapping("addRole")
    public String addRole(String rolename){
        int i = roleService.addRole(rolename);
        if (i>0){
            return "redirect:role";
        }
        return "addRolePage";
    }

    @RequestMapping("updateRolePage")
    public String updateRolePage(HttpServletRequest request,int rid){
        Role role = roleService.getRoleById(rid);
        request.setAttribute("role",role);
        return "updateRole";
    }

    @RequestMapping("updateRole")
    public String updateCourse(int rid,String rolename){
        Role role = new Role(rid, rolename);
        int i = roleService.updateRole(role);
        if (i>0){
            return "redirect:role";
        }
        return "updateRole";
    }

    @RequestMapping("deleteRole")
    public String deleteRole(int rid){
        int i =roleService.deleteRole(rid);
        if (i>0){
            return "redirect:role";
        }
        return "fail";
    }

}
