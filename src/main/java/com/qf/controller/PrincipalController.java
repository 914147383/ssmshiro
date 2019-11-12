package com.qf.controller;

import com.qf.pojo.User;
import com.qf.pojo.Vacate;
import com.qf.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    //员工请假审批
    @RequestMapping("updVacate")
    public String updVacate(HttpSession session, Vacate vacate){
        //String username = (String) session.getAttribute("username");

        String username = "b";

        return "updVacate";
    }
}
