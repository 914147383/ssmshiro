package com.qf.controller;

import com.github.pagehelper.PageInfo;
import com.qf.pojo.Holiday;
import com.qf.pojo.User;
import com.qf.pojo.Vacate;
import com.qf.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class PrincipalController {

    @Autowired
    private PrincipalService principalService;
    /**
     * 跳转到校长的个人管理页面，可修改密码
     * @return
     */
   /* @RequestMapping("updStuPage")
    public String updStuPage(HttpSession session,Model model){
        //String username = (String) session.getAttribute("username");

        String username = "b";
        User userByUname = principalService.getUserByUname(username);
        model.addAttribute("userByUname",userByUname);

        return "updStuPage";
    }*/
    /**
     * 校长修改密码后，保存
     *在学生管理那里
     */
    /*@RequestMapping("savBoosPass")
    public String savBoosPass(HttpSession session,User user){
        //String username = (String) session.getAttribute("username");

        String username = "b";

        int i = principalService.updUserByUname(user);

        return "redirect:updStuPage";
    }*/

    //员工请假审批  页面
    @RequestMapping("selVacatePage")
    public String updVacate(Model model,HttpSession session,
                            @RequestParam(defaultValue = "1") int pageNo,
                            @RequestParam(defaultValue = "6") int pageSize){
        String username = (String) session.getAttribute("username");

        PageInfo<Vacate> vacate = principalService.getVacate(username, pageNo, pageSize);

        System.out.println("vacate:"+vacate);

        model.addAttribute("vacate",vacate);
        return "selVacatePage";
    }

    // 校长  审核  老师，班主任  请假  通过
    @RequestMapping("passVacate")
    public String passVacate(HttpSession session,int vid){
        String username = (String) session.getAttribute("username");

        int i = principalService.updCheckedByVid(username,vid);

        return "redirect:selVacatePage";
    }
    /**
     * 跳转到学生待  老师，班主任，校长  审批的列表页面
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("selStuHolidayPage")
    public String selStuHolidayPage(Model model,HttpSession session,
                                    @RequestParam(defaultValue = "1") int pageNo,
                                    @RequestParam(defaultValue = "6") int pageSize){
        String username = (String) session.getAttribute("username");

        PageInfo<Holiday> holidy = principalService.getHolidy(username,pageNo,pageSize,"stuQingJia1");

        System.out.println("holidy:"+holidy);

        model.addAttribute("holidy",holidy);

        return "selStuHolidayPage";
    }

    // 老师，班主任， 校长审核学生请假通过
    @RequestMapping("passHoliday")
    public String passHoliday(HttpSession session,int hid){
        String username = (String) session.getAttribute("username");

        int i = principalService.updCheckedByHid(username, hid);

        return "redirect:selStuHolidayPage";
    }
}
