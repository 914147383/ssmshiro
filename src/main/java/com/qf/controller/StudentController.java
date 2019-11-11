package com.qf.controller;

import com.qf.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qf.service.StudentService;

import javax.servlet.http.HttpSession;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("tablePage")
    public String tablePage(){
        return "tables";
    }

    //个人资料管理   界面
    @RequestMapping("updStuPage")
    public String updStuPage(HttpSession session, Model model){
       // String username = (String) session.getAttribute("username");
        String username = "a";
        User userByUname = studentService.getUserByUname(username);

        System.out.println("userByUname:"+userByUname);

        model.addAttribute("userByUname",userByUname);
        return "updStuPage";
    }

    @RequestMapping("loginP")
    public String loginP(){
        return "login";
    }

    //个人资料管理   保存
    @RequestMapping("savStuPage")
    public String savStuPage(HttpSession session,User user){
        // String username = (String) session.getAttribute("username");
        String username = "a";
        User userByUname = studentService.getUserByUname(username);

        if(user.getImg().equals("") || user.getImg()==null){
            user.setImg(userByUname.getImg());
        }
        int i = studentService.updUserAndStuByUname(user);

        if(i>0){
            return "updStuSuccessful";
        }
        return "redirect:updStuPage";
    }

}
