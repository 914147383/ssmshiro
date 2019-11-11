package com.qf.controller;

import com.qf.pojo.Holiday;
import com.qf.pojo.User;
import com.qf.pojo.Weekly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qf.service.StudentService;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

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

        //System.out.println("userByUname:"+userByUname);

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

    //查询周报
    @RequestMapping("getWeekly")
    public String selWeeklyPage(HttpSession session, Model model, @RequestParam(defaultValue = "") String title){
        // String username = (String) session.getAttribute("username");
        String username = "a";
        List<Weekly> weekly = studentService.getWeekly(username, title);

        System.out.println("weekly:"+weekly);
        model.addAttribute("weekly",weekly);

        return "selWeeklyPage";
    }

    //删除周报
    @RequestMapping("delWeekly")
    public String delWeekly(HttpSession session,int wid){
        // String username = (String) session.getAttribute("username");
        String username = "a";
        int i = studentService.delWeekly(username, wid);
        return "redirect:getWeekly";
    }

    //新增周报
    @RequestMapping("addWeeklyPage")
    public String addWeeklyPage(){
        return "addWeeklyPage";
    }

    //保存新增的周报
    @RequestMapping("savWeekly")
    public String savWeekly(Weekly weekly){
        int i = studentService.addWeekly(weekly);

        return "redirect:getWeekly";
    }

    //请假申请
    @RequestMapping("addHoliday")
    public String addHoliday(){
        return "addHolidayPage";
    }

    //保存请假申请
    @RequestMapping("savHoliday")
    public String savHoliday(Holiday holiday){
        int i = studentService.addHoliday(holiday);

        return "login";
    }
}
